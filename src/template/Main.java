package template;

import jdk.internal.misc.Unsafe;

import java.lang.invoke.MethodHandles.Lookup;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;

public class Main {
	static final Unsafe unsafe;
	static final Lookup lookup;

	static {
		unsafe = Unsafe.getUnsafe();

		try {
			Field field = Lookup.class.getDeclaredField("IMPL_LOOKUP");
			field.setAccessible(true);
			lookup = (Lookup) field.get(null);
		} catch (NoSuchFieldException | IllegalAccessException e) {
			throw new RuntimeException(e);
		}
	}

	static void main(String... args) {
		try {
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
}
