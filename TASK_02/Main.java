package TASK_02;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Main
{
    public static void main (String[] args) throws InvocationTargetException, IllegalAccessException
    {
        TextContainer textContainer = new TextContainer();
        Class<?> textContainerClass = TextContainer.class;

        if (textContainerClass.isAnnotationPresent(SaveTo.class))
        {
            SaveTo saveTo = textContainerClass.getAnnotation(SaveTo.class);
            String path = saveTo.path();
            Method[] methods = textContainerClass.getDeclaredMethods();

            for (Method method : methods)
            {
                if (method.isAnnotationPresent(Saver.class))
                {
                    method.invoke(textContainer, path);
                    System.out.println("Saved to path: " + path);
                    break;
                }
            }
        }
    }
}