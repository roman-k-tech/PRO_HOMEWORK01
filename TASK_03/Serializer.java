package TASK_03;

import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;

public class Serializer {
    private String path = null;

    public Serializer(String path) {
        this.path = path;
    }

    public void serialize(Object object) throws IOException, IllegalAccessException {
        try (
                FileOutputStream fileOutputStream = new FileOutputStream(path);
                ObjectOutputStream OOS = new ObjectOutputStream(fileOutputStream)
        ) {
            Class<?> classSample = object.getClass();
            System.out.println("Class of type '" + classSample.getSimpleName() + "' is about to be serialized.");
            OOS.writeObject(classSample.getName());

            Field[] fields = classSample.getDeclaredFields();
            for (Field field : fields) {
                if (field.isAnnotationPresent(Save.class)) {
                    if (Modifier.isPrivate(field.getModifiers())) {
                        field.setAccessible(true);
                    }
                    Object[] savedField = new Object[3];
                    savedField[0] = field.getName();
                    savedField[1] = field.getType();
                    savedField[2] = field.get(object);
                    OOS.writeObject(savedField);
                }
            }
        }
    }

    public <T> T deserialize(Class<T> classSample) throws IOException, ClassNotFoundException, IllegalAccessException, InstantiationException
    {
        try (
                FileInputStream fileInputStream = new FileInputStream(path);
                ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)
        )
        {
            Object object = objectInputStream.readObject();
            Class<?> className = object.getClass();
            if (! (className == String.class)) {
                throw new ClassNotFoundException("INCORRECT CLASS");
            }
            System.out.println("Reading serialized class of type '" + object + "'");
            String name = (String) object;
            if (! name.equals(classSample.getName())) {
                throw new ClassNotFoundException("INCORRECT CLASS");
            }

            T restored = classSample.newInstance();
            Field[] fields = classSample.getDeclaredFields();

            while (true)
            {
                try {
                    object = objectInputStream.readObject();
                }
                catch (EOFException e) {
                    return restored;
                }
                className = object.getClass();
                if (!(className == Object[].class)) {
                    throw new ClassNotFoundException("INCORRECT CLASS");
                }
                Object[] savedField = (Object[]) object;
                for (Field field : fields)
                {
                    if (field.getName().equals(savedField[0]) && (field.getType() == savedField[1]))
                    {
                        if (Modifier.isPrivate(field.getModifiers())) {
                            field.setAccessible(true);
                        }
                        field.set(restored, savedField[2]);
                    }
                }
            }
        }
    }
}

















