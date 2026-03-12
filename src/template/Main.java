package template;

import jdk.internal.misc.Unsafe;
import jdk.internal.reflect.Reflection;
import sun.reflect.ReflectionFactory;

import java.lang.StackWalker.Option;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles.Lookup;
import java.lang.invoke.MethodType;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;

import static template.Reflect.lookup;

public class Main {
	static MethodHandle add;

	static int number;

	static {
		try {
			Demodulator.init();
			Demodulator.openModules();
			Reflect.init();
		} catch (Throwable e) {
			throw new ExceptionInInitializerError(e);
		}
	}

	static void main(String... arg) {
		try {

		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	public Main() {}

	public static int number() {
		return number++;
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
		static final Class<?> staticcaller = null;
		final Class<?> caller;

		private CA() {
			caller = null;
		}

		@Override
		public CA clone() {
			try {
				return (CA) super.clone();
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
	}
}
