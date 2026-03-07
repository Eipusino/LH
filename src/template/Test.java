package template;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodType;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.List;

import static template.Reflect.lookup;

public class Test {
	static final MethodHandle invoke, newInstance;

	static int number = 0;

	static {
		try {
			Class<?> refc = Class.forName("jdk.internal.reflect.DirectMethodHandleAccessor$NativeAccessor"),
					refc2 = Class.forName("jdk.internal.reflect.DirectConstructorHandleAccessor$NativeAccessor");

			invoke = lookup.findStatic(refc, "invoke0", MethodType.methodType(Object.class, Method.class, Object.class, Object[].class));
			newInstance = lookup.findStatic(refc2, "newInstance0", MethodType.methodType(Object.class, Constructor.class, Object[].class));
		} catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException e) {
			throw new RuntimeException(e);
		}
	}

	public Test() {}

	public Test(int i) {}

	public Test(Class<?> c) {}

	public Test(List<?> list) {}

	//@CallerSensitive
	public static void test() throws Throwable {
		/*Method method = Test.class.getMethod("number");
		Object none = null;
		System.out.println(invoke.invokeExact(method, none, new Object[0]));*/
	}

	public static int number() {
		return number++;
	}
}
