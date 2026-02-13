package dalvik.system;

@SuppressWarnings("unused")
public final class VMStack {
	private VMStack() {}

	@Deprecated
	public static ClassLoader getCallingClassLoader() {
		throw new RuntimeException("Stub!");
	}

	@Deprecated
	public static Class<?> getStackClass1() {
		throw new RuntimeException("Stub!");
	}

	public static Class<?> getStackClass2() {
		throw new RuntimeException("Stub!");
	}

	public static ClassLoader getClosestUserClassLoader() {
		throw new RuntimeException("Stub!");
	}

	public static StackTraceElement[] getThreadStackTrace(Thread t) {
		throw new RuntimeException("Stub!");
	}

	public static AnnotatedStackTraceElement[] getAnnotatedThreadStackTrace(Thread t) {
		throw new RuntimeException("Stub!");
	}

	public static int fillStackTraceElements(Thread t, StackTraceElement[] stackTraceElements) {
		throw new RuntimeException("Stub!");
	}
}
