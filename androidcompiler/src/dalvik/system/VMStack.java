package dalvik.system;

@SuppressWarnings("unused")
public final class VMStack {
	private VMStack() {}

	@Deprecated
	public native static ClassLoader getCallingClassLoader();

	@Deprecated
	public static Class<?> getStackClass1() {
		throw new RuntimeException("Stub!");
	}

	public native static Class<?> getStackClass2();

	public native static ClassLoader getClosestUserClassLoader();

	public native static StackTraceElement[] getThreadStackTrace(Thread t);

	public native static AnnotatedStackTraceElement[] getAnnotatedThreadStackTrace(Thread t);

	public native static int fillStackTraceElements(Thread t, StackTraceElement[] stackTraceElements);
}
