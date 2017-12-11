package TASK_01;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Main
{

    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException
    {
        Main samplemain = new Main();
        Class<?> sampleclass = Main.class;

        Method[] methods = sampleclass.getDeclaredMethods();
        Test_method test_method = null;
        int result;

        for (Method method : methods)
        {
            if (method.isAnnotationPresent(Test_method.class))
            {
                test_method = method.getAnnotation(Test_method.class);
                result = (int) method.invoke(samplemain, test_method.a(), test_method.b());
                System.out.println(result);
            }
        }
    }

    @Test_method(a = 1, b = 5)
    public int test(int a, int b)
    {
        return a*b;
    }

}
