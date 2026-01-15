package dalvik.system;

import java.util.function.Consumer;

@SuppressWarnings("unused")
public final class VMRuntime {
	public interface HiddenApiUsageLogger {
		int ACCESS_METHOD_NONE = 0;

		int ACCESS_METHOD_REFLECTION = 1;

		int ACCESS_METHOD_JNI = 2;

		int ACCESS_METHOD_LINKING = 3;

		void hiddenApiUsed(int sampledValue, String appPackageName, String signature, int accessType, boolean accessDenied);
	}

	public static void setHiddenApiUsageLogger(HiddenApiUsageLogger hiddenApiUsageLogger) {
		throw new RuntimeException("Stub!");
	}

	public static final int SDK_VERSION_CUR_DEVELOPMENT = 10000;

	private VMRuntime() {}

	public static VMRuntime getRuntime() {
		throw new RuntimeException("Stub!");
	}

	public native String[] properties();

	public native String bootClassPath();

	public native String classPath();

	public native String vmVersion();

	public native String vmLibrary();

	public native String vmInstructionSet();

	public native boolean is64Bit();

	public native boolean isCheckJniEnabled();

	public native float getTargetHeapUtilization();

	public native long getFinalizerTimeoutMs();

	public float setTargetHeapUtilization(float newTarget) {
		throw new RuntimeException("Stub!");
	}

	public synchronized void setTargetSdkVersion(int targetSdkVersion) {
		throw new RuntimeException("Stub!");
	}

	public synchronized void setDisabledCompatChanges(long[] disabledCompatChanges) {
		throw new RuntimeException("Stub!");
	}

	public static int getSdkVersion() {
		throw new RuntimeException("Stub!");
	}

	public static int getSdkExtensionSLevel() {
		throw new RuntimeException("Stub!");
	}

	public synchronized int getTargetSdkVersion() {
		throw new RuntimeException("Stub!");
	}

	@Deprecated
	public long getMinimumHeapSize() {
		throw new RuntimeException("Stub!");
	}

	@Deprecated
	public long setMinimumHeapSize(long size) {
		throw new RuntimeException("Stub!");
	}

	@Deprecated
	public void gcSoftReferences() {
		throw new RuntimeException("Stub!");
	}

	@Deprecated
	public void runFinalizationSync() {
		throw new RuntimeException("Stub!");
	}

	@Deprecated
	public boolean trackExternalAllocation(long size) {
		throw new RuntimeException("Stub!");
	}

	@Deprecated
	public void trackExternalFree(long size) {
		throw new RuntimeException("Stub!");
	}

	@Deprecated
	public long getExternalBytesAllocated() {
		throw new RuntimeException("Stub!");
	}

	public native void setHiddenApiExemptions(String[] signaturePrefixes);

	public native void setHiddenApiAccessLogSamplingRate(int rate);

	public native Object newNonMovableArray(Class<?> componentType, int length);

	public native Object newUnpaddedArray(Class<?> componentType, int minLength);

	public native long addressOf(Object array);

	public native void clearGrowthLimit();

	public native void clampGrowthLimit();

	public native boolean isNativeDebuggable();

	public native boolean isJavaDebuggable();

	public native void registerNativeAllocation(long bytes);

	public void registerNativeAllocation(int bytes) {
		throw new RuntimeException("Stub!");
	}

	public native void registerNativeFree(long bytes);

	@Deprecated
	public void registerNativeFree(int bytes) {
		throw new RuntimeException("Stub!");
	}

	public void notifyNativeAllocation() {
		throw new RuntimeException("Stub!");
	}

	public native void notifyNativeAllocationsInternal();

	public static void runFinalization(long timeout) {
		throw new RuntimeException("Stub!");
	}

	public native void requestConcurrentGC();

	public native void requestHeapTrim();

	public native void trimHeap();

	public native void startHeapTaskProcessor();

	public native void stopHeapTaskProcessor();

	public native void runHeapTasks();

	public native void updateProcessState(int state);

	public native void notifyStartupCompleted();

	public native void preloadDexCaches();

	public static final int CODE_PATH_TYPE_PRIMARY_APK = 1 << 0;

	public static final int CODE_PATH_TYPE_SPLIT_APK = 1 << 1;

	public static final int CODE_PATH_TYPE_SECONDARY_DEX = 1 << 2;

	public static native void registerAppInfo(String packageName, String currentProfileFile, String referenceProfileFile, String[] appCodePaths, int codePathsType);

	public static String getInstructionSet(String abi) {
		throw new RuntimeException("Stub!");
	}

	public static boolean is64BitInstructionSet(String instructionSet) {
		throw new RuntimeException("Stub!");
	}

	public static boolean is64BitAbi(String abi) {
		throw new RuntimeException("Stub!");
	}

	public static native boolean isBootClassPathOnDisk(String instructionSet);

	public static native void bootCompleted();

	public static native void resetJitCounters();

	public static native String getCurrentInstructionSet();

	public static native void registerSensitiveThread();

	public static native void setSystemDaemonThreadPriority();

	public static void setNonSdkApiUsageConsumer(Consumer<String> consumer) {
		throw new RuntimeException("Stub!");
	}

	public static void addPostCleanupCallback(Runnable runnable) {
		throw new RuntimeException("Stub!");
	}

	public static void removePostCleanupCallback(Runnable runnable) {
		throw new RuntimeException("Stub!");
	}

	public static void onPostCleanup() {
		throw new RuntimeException("Stub!");
	}

	public static native void setDedupeHiddenApiWarnings(boolean dedupe);

	public static native void setProcessPackageName(String packageName);

	public static native void setProcessDataDirectory(String dataDir);

	public static native boolean isValidClassLoaderContext(String encodedClassLoaderContext);

	public static native DexFile.OptimizationInfo getBaseApkOptimizationInfo();

	public static boolean isVTrunkStableFlagEnabled() {
		throw new RuntimeException("Stub!");
	}

	public static boolean isArtTestFlagEnabled() {
		throw new RuntimeException("Stub!");
	}

	public static native long getFullGcCount();
}
