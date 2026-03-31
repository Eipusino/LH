package template;

import sun.misc.Unsafe;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles.Lookup;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;

@SuppressWarnings("removal")
public class Main {
	//static final Unsafe unsafe;
	//static final Lookup lookup;

	static {

	}

	static void main(String... args) {
		try {
			System.out.println("oh no");
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
}
