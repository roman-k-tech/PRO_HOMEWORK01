package TASK_03;

import java.io.Serializable;

public class Container implements Serializable
{
    @Save
    private String text1 = "123456790";
    @Save
    private int number1 = 100;

    private String text2 = "qwerty";
    private boolean b = true;

    @Override
    public String toString() {
        return "Container{" +
                "text1='" + text1 + '\'' +
                ", number1=" + number1 +
                ", text2='" + text2 + '\'' +
                ", b=" + b +
                '}';
    }
}
