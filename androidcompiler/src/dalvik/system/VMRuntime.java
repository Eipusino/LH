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

	public String[] properties() {
		throw new RuntimeException("Stub!");
	}

	public String bootClassPath() {
		throw new RuntimeException("Stub!");
	}

	public String classPath() {
		throw new RuntimeException("Stub!");
	}

	public String vmVersion() {
		throw new RuntimeException("Stub!");
	}

	public String vmLibrary() {
		throw new RuntimeException("Stub!");
	}

	public String vmInstructionSet() {
		throw new RuntimeException("Stub!");
	}

	public boolean is64Bit() {
		throw new RuntimeException("Stub!");
	}

	public boolean isCheckJniEnabled() {
		throw new RuntimeException("Stub!");
	}

	public float getTargetHeapUtilization() {
		throw new RuntimeException("Stub!");
	}

	public long getFinalizerTimeoutMs() {
		throw new RuntimeException("Stub!");
	}

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

	public void setHiddenApiExemptions(String[] signaturePrefixes) {
		throw new RuntimeException("Stub!");
	}

	public void setHiddenApiAccessLogSamplingRate(int rate) {
		throw new RuntimeException("Stub!");
	}

	public Object newNonMovableArray(Class<?> componentType, int length) {
		throw new RuntimeException("Stub!");
	}

	public Object newUnpaddedArray(Class<?> componentType, int minLength) {
		throw new RuntimeException("Stub!");
	}

	public long addressOf(Object array) {
		throw new RuntimeException("Stub!");
	}

	public void clearGrowthLimit() {
		throw new RuntimeException("Stub!");
	}

	public void clampGrowthLimit() {
		throw new RuntimeException("Stub!");
	}

	public boolean isNativeDebuggable() {
		throw new RuntimeException("Stub!");
	}

	public boolean isJavaDebuggable() {
		throw new RuntimeException("Stub!");
	}

	public void registerNativeAllocation(long bytes) {
		throw new RuntimeException("Stub!");
	}

	public void registerNativeAllocation(int bytes) {
		throw new RuntimeException("Stub!");
	}

	public void registerNativeFree(long bytes) {
		throw new RuntimeException("Stub!");
	}

	@Deprecated
	public void registerNativeFree(int bytes) {
		throw new RuntimeException("Stub!");
	}

	public void notifyNativeAllocation() {
		throw new RuntimeException("Stub!");
	}

	public void notifyNativeAllocationsInternal() {
		throw new RuntimeException("Stub!");
	}

	public static void runFinalization(long timeout) {
		throw new RuntimeException("Stub!");
	}

	public void requestConcurrentGC() {
		throw new RuntimeException("Stub!");
	}

	public void requestHeapTrim() {
		throw new RuntimeException("Stub!");
	}

	public void trimHeap() {
		throw new RuntimeException("Stub!");
	}

	public void startHeapTaskProcessor() {
		throw new RuntimeException("Stub!");
	}

	public void stopHeapTaskProcessor() {
		throw new RuntimeException("Stub!");
	}

	public void runHeapTasks() {
		throw new RuntimeException("Stub!");
	}

	public void updateProcessState(int state) {
		throw new RuntimeException("Stub!");
	}

	public void notifyStartupCompleted() {
		throw new RuntimeException("Stub!");
	}

	public void preloadDexCaches() {
		throw new RuntimeException("Stub!");
	}

	public static final int CODE_PATH_TYPE_PRIMARY_APK = 1 << 0;

	public static final int CODE_PATH_TYPE_SPLIT_APK = 1 << 1;

	public static final int CODE_PATH_TYPE_SECONDARY_DEX = 1 << 2;

	public static void registerAppInfo(String packageName, String currentProfileFile, String referenceProfileFile, String[] appCodePaths, int codePathsType) {
		throw new RuntimeException("Stub!");
	}

	public static String getInstructionSet(String abi) {
		throw new RuntimeException("Stub!");
	}

	public static boolean is64BitInstructionSet(String instructionSet) {
		throw new RuntimeException("Stub!");
	}

	public static boolean is64BitAbi(String abi) {
		throw new RuntimeException("Stub!");
	}

	public static boolean isBootClassPathOnDisk(String instructionSet) {
		throw new RuntimeException("Stub!");
	}

	public static void bootCompleted() {
		throw new RuntimeException("Stub!");
	}

	public static void resetJitCounters() {
		throw new RuntimeException("Stub!");
	}

	public static String getCurrentInstructionSet() {
		throw new RuntimeException("Stub!");
	}

	public static void registerSensitiveThread() {
		throw new RuntimeException("Stub!");
	}

	public static void setSystemDaemonThreadPriority() {
		throw new RuntimeException("Stub!");
	}

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

	public static void setDedupeHiddenApiWarnings(boolean dedupe) {
		throw new RuntimeException("Stub!");
	}

	public static void setProcessPackageName(String packageName) {
		throw new RuntimeException("Stub!");
	}

	public static void setProcessDataDirectory(String dataDir) {
		throw new RuntimeException("Stub!");
	}

	public static boolean isValidClassLoaderContext(String encodedClassLoaderContext) {
		throw new RuntimeException("Stub!");
	}

	public static DexFile.OptimizationInfo getBaseApkOptimizationInfo() {
		throw new RuntimeException("Stub!");
	}

	public static boolean isVTrunkStableFlagEnabled() {
		throw new RuntimeException("Stub!");
	}

	public static boolean isArtTestFlagEnabled() {
		throw new RuntimeException("Stub!");
	}

	public static long getFullGcCount() {
		throw new RuntimeException("Stub!");
	}
}
