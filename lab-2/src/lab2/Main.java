package lab2;

import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;

public class Main {
    public static void main(String[] args) {
        var exampleClass = new ExampleClass();

        for (var method : ExampleClass.class.getDeclaredMethods()) {
            if (!method.isAnnotationPresent(Repeat.class))
                continue;

            var modifier = method.getModifiers();
            if (!(Modifier.isPrivate(modifier) || Modifier.isProtected(modifier)))
                continue;

            int repeatCount = method.getAnnotation(Repeat.class).value();

            method.setAccessible(true);
            Object[] argsForMethod = buildArgs(method.getParameterTypes());
            for (int i = 0; i < repeatCount; i++) {
                try {
                    method.invoke(exampleClass, argsForMethod);
                } catch (IllegalAccessException | InvocationTargetException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    private static Object[] buildArgs(Class<?>[] types) {
        Object[] args = new Object[types.length];
        for (int i = 0; i < types.length; i++) {
            Class<?> t = types[i];
            args[i] = Array.get(Array.newInstance(t, 1), 0);
        }
        return args;
    }
}