package dalvik.system;

@SuppressWarnings("unused")
public final class ApplicationRuntime {
	private ApplicationRuntime() {}

	public static DexFile.OptimizationInfo getBaseApkOptimizationInfo() {
		return VMRuntime.getBaseApkOptimizationInfo();
	}
}
