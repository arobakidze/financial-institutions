package reflection;

import annotations.BusinessMethod;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;

public class ReflectionUtil {

    public static void printClassInfo(Class<?> clazz) {
        System.out.println("=== Class Info: " + clazz.getSimpleName() + " ===");

        System.out.println("-- Fields --");
        for (Field field : clazz.getDeclaredFields()) {
            System.out.println("  " + Modifier.toString(field.getModifiers()) + " "
                    + field.getType().getSimpleName() + " " + field.getName());
        }

        System.out.println("-- Constructors --");
        for (Constructor<?> constructor : clazz.getDeclaredConstructors()) {
            System.out.print("  " + Modifier.toString(constructor.getModifiers())
                    + " " + clazz.getSimpleName() + "(");
            Parameter[] params = constructor.getParameters();
            for (int i = 0; i < params.length; i++) {
                System.out.print(params[i].getType().getSimpleName() + " " + params[i].getName());
                if (i < params.length - 1) System.out.print(", ");
            }
            System.out.println(")");
        }

        System.out.println("-- Methods --");
        for (Method method : clazz.getDeclaredMethods()) {
            System.out.print("  " + Modifier.toString(method.getModifiers()) + " "
                    + method.getReturnType().getSimpleName() + " " + method.getName() + "(");
            Parameter[] params = method.getParameters();
            for (int i = 0; i < params.length; i++) {
                System.out.print(params[i].getType().getSimpleName() + " " + params[i].getName());
                if (i < params.length - 1) System.out.print(", ");
            }
            System.out.println(")");
        }
    }

    public static Object createObject(Class<?> clazz, Class<?>[] paramTypes, Object[] args) throws Exception {
        Constructor<?> constructor = clazz.getDeclaredConstructor(paramTypes);
        return constructor.newInstance(args);
    }

    public static Object invokeMethod(Object obj, String methodName, Class<?>[] paramTypes, Object[] args) throws Exception {
        Method method = obj.getClass().getDeclaredMethod(methodName, paramTypes);
        return method.invoke(obj, args);
    }

    public static void handleBusinessMethods(Class<?> clazz) {
        System.out.println("-- @BusinessMethod annotations in " + clazz.getSimpleName() + " --");
        for (Method method : clazz.getDeclaredMethods()) {
            if (method.isAnnotationPresent(BusinessMethod.class)) {
                BusinessMethod annotation = method.getAnnotation(BusinessMethod.class);
                System.out.println("  Method: " + method.getName() + " | Description: " + annotation.description());
            }
        }
    }

}
