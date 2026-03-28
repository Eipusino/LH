package template;

import sun.misc.Unsafe;

import java.lang.invoke.MethodHandles.Lookup;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;

@SuppressWarnings("removal")
public class Main {
	static final Unsafe unsafe;
	static final Lookup lookup;

	final Object obj = null;

	static {
		try {
			Field field = Unsafe.class.getDeclaredField("theUnsafe");
			field.setAccessible(true);
			unsafe = (Unsafe) field.get(null);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

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
			Field field = Main.class.getDeclaredField("obj");
			field.setAccessible(true);
			field.set(new Main(), "new Object()\\{}");
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
}
