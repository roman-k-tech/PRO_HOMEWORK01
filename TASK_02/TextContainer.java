package TASK_02;

import java.io.FileWriter;
import java.io.IOException;


@SaveTo(path = "D:\\PROGRAMMING\\COURSES\\JAVA_PRO\\HOMEWORK_01\\task01.txt")
public class TextContainer
{
    String text = "qwerty123456";

    @Saver
    public void saver(String path) throws IOException
    {
        try (FileWriter fileWriter = new FileWriter(path))
        {
            fileWriter.write(text);
        }
    }
}
