package template;

import arc.graphics.Color;
import arc.graphics.Pixmap;
import mindustry.Vars;
import sun.misc.Unsafe;
import sun.reflect.ReflectionFactory;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles.Lookup;
import java.lang.invoke.MethodType;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Map;

public class Main {
	static ReflectionFactory factory;
	static Unsafe unsafe;
	static Lookup lookup;

	static int test;

	public static void main(String... arg) {
		try {
			System.out.println(Integer.MAX_VALUE);
			System.out.println(1 << 30);
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	public static void test() {
		test++;
	}

	static void init() throws NoSuchFieldException, IllegalAccessException {
		factory = ReflectionFactory.getReflectionFactory();

		Field field = Unsafe.class.getDeclaredField("theUnsafe");
		field.setAccessible(true);
		unsafe = (Unsafe) field.get(null);

		lookup = (Lookup) unsafe.getObject(Lookup.class, unsafe.staticFieldOffset(Lookup.class.getDeclaredField("IMPL_LOOKUP")));
	}

	public static void ensureFieldOpen() throws Throwable {
		Class<?> c = Class.forName("jdk.internal.reflect.Reflection");

		Map<?, ?> fieldFilterMap = (Map<?, ?>) lookup.findStaticGetter(c, "fieldFilterMap", Map.class).invokeExact();
		if (fieldFilterMap != null) {
			fieldFilterMap.clear();
		}

		Map<?, ?> methodFilterMap = (Map<?, ?>) lookup.findStaticGetter(c, "methodFilterMap", Map.class).invokeExact();
		if (methodFilterMap != null) {
			methodFilterMap.clear();
		}
	}

	public static Unsafe getUnsafe() {
		try {
			Field field = Unsafe.class.getDeclaredField("theUnsafe");
			field.setAccessible(true);
			return (Unsafe) field.get(null);
		} catch (NoSuchFieldException | IllegalAccessException e) {
			throw new RuntimeException(e);
		}
	}

	public static Lookup getLookup() {
		try {
			return (Lookup) unsafe.getObject(Lookup.class, unsafe.staticFieldOffset(Lookup.class.getDeclaredField("IMPL_LOOKUP")));
		} catch (NoSuchFieldException e) {
			throw new RuntimeException(e);
		}
	}

	@Retention(RetentionPolicy.RUNTIME)
	public @interface Null {
		Class<?>[] classes() default {};
	}
}
