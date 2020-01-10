package util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Set;

public class ReflectUtils {
    public static void showAllPros(Object obj) {
        try {
            Class<?> objClass = obj.getClass();
            for (Field field : objClass.getFields()) {
                Object o = field.get(obj);
                System.out.println(field.getName() + " : " + o);
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public static void showAllGet(Object obj) {
        Class<?> objClass = obj.getClass();
        System.out.println("====" + objClass.getName() + "=====");
        for (Method method : objClass.getMethods()) {
            if (method.getName().startsWith("get") || method.getName().startsWith("is")) {
                try {
                    Object invoke = method.invoke(obj);
                    System.out.println(method.getName() + " : " + invoke);
                } catch (Exception e) {

                }
            }
        }

    }
}
