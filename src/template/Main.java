package template;

import sun.misc.Unsafe;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles.Lookup;
import java.lang.invoke.MethodType;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class Main {
	private static final Unsafe unsafe = getUnsafe();
	private static final Lookup lookup = getLookup();

	private static MethodHandle clone;
	private static MethodHandle implAddOpens;
	private static MethodHandle load;
	private static Method loadm;

	private Object unl;

	public static void main(String... arg) {
		try {
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	public Main() {

	}

	public static boolean isAssignable(Class<?>[] sourceTypes, Class<?>[] targetTypes) {
		if (sourceTypes.length != targetTypes.length) return false;

		for (int i = 0; i < sourceTypes.length; i++) {
			if (sourceTypes[i] != targetTypes[i] && !targetTypes[i].isAssignableFrom(sourceTypes[i])) return false;
		}

		return true;
	}

	static class CA implements Cloneable {
		static final CA INSTANCE = new CA();

		int number;

		private CA() {}

		public void load() {
			number++;
		}

		@Override
		public CA clone() {
			try {
				return null;
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
	}

	private static Unsafe getUnsafe() {
		try {
			Field field = Unsafe.class.getDeclaredField("theUnsafe");
			field.setAccessible(true);
			return (Unsafe) field.get(null);
		} catch (Throwable e) {
			throw new AssertionError(e);
		}
	}

	private static Lookup getLookup() {
		try {
			Field field = Lookup.class.getDeclaredField("IMPL_LOOKUP");
			return (Lookup) unsafe.getObject(unsafe.staticFieldBase(field), unsafe.staticFieldOffset(field));
		} catch (Throwable e) {
			throw new AssertionError(e);
		}
	}
}
