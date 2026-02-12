package template;

import sun.reflect.ReflectionFactory;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles.Lookup;
import java.lang.invoke.MethodType;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class Main {
	private static final Lookup lookup = getLookup();

	private static MethodHandle clone;
	private static MethodHandle implAddOpens;
	private static MethodHandle load;
	private static Method loadm;

	private Object unl;

	static void main(String... arg) {
		try {
			System.out.println(Main.class.getModule());
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	public Main() {

	}


	public int getModifiers(Field field) {
		return field.getModifiers();
	}

	public static boolean isFinalOrStatic(Field field) {
		return (field.getModifiers() & (Modifier.FINAL | Modifier.STATIC)) != 0;
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

	private static Lookup getLookup() {
		try {
			return (Lookup) ReflectionFactory.getReflectionFactory()
					.newConstructorForSerialization(Lookup.class, Lookup.class.getDeclaredConstructor(Class.class, Class.class, int.class))
					.newInstance(Main.class, null, -1);
		} catch (Throwable e) {
			throw new AssertionError(e);
		}
	}
}
