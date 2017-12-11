package TASK_03;

import java.io.*;

public class Main
{
    static String path = "D:\\PROGRAMMING\\COURSES\\JAVA_PRO\\HOMEWORK_01\\serialized";

    public static void main (String[] args) throws IOException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        Serializer serializer = new Serializer(path);
        serializer.serialize(new Container());

        Container container = serializer.deserialize(Container.class);

        System.out.println(container);

    }

}
