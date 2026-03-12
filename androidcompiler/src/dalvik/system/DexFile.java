package dalvik.system;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Enumeration;
import java.util.List;

@SuppressWarnings({"unused", "rawtypes", "deprecation"})
public class DexFile {
	public static final long ENFORCE_READ_ONLY_JAVA_DCL = 218865702;

	@Deprecated
	public DexFile(File file) throws IOException {
		throw new RuntimeException("Stub!");
	}

	@Deprecated
	public DexFile(String fileName) throws IOException {
		throw new RuntimeException("Stub!");
	}

	@Deprecated
	public String getName() {
		throw new RuntimeException("Stub!");
	}

	@Override
	public String toString() {
		throw new RuntimeException("Stub!");
	}

	@Deprecated
	public void close() throws IOException {
		throw new RuntimeException("Stub!");
	}

	@Deprecated
	public Class loadClass(String name, ClassLoader loader) {
		throw new RuntimeException("Stub!");
	}

	public Class loadClassBinaryName(String name, ClassLoader loader, List<Throwable> suppressed) {
		throw new RuntimeException("Stub!");
	}

	@Deprecated
	public Enumeration<String> entries() {
		throw new RuntimeException("Stub!");
	}

	@Deprecated
	public static boolean isDexOptNeeded(String fileName) throws FileNotFoundException, IOException {
		throw new RuntimeException("Stub!");
	}

	@Deprecated
	public static final int NO_DEXOPT_NEEDED = 0;

	@Deprecated
	public static final int DEX2OAT_FROM_SCRATCH = 1;

	@Deprecated
	public static final int DEX2OAT_FOR_BOOT_IMAGE = 2;

	@Deprecated
	public static final int DEX2OAT_FOR_FILTER = 3;

	public static int getDexOptNeeded(String fileName, String instructionSet, String compilerFilter, boolean newProfile, boolean downgrade) throws FileNotFoundException, IOException {
		throw new RuntimeException("Stub!");
	}

	@Deprecated
	public static int getDexOptNeeded(String fileName, String instructionSet, String compilerFilter, String classLoaderContext, boolean newProfile, boolean downgrade) throws FileNotFoundException, IOException {
		throw new RuntimeException("Stub!");
	}

	public static final class OptimizationInfo {
		private OptimizationInfo(String status, String reason) {
			throw new RuntimeException("Stub!");
		}

		public String getStatus() {
			throw new RuntimeException("Stub!");
		}

		public String getReason() {
			throw new RuntimeException("Stub!");
		}

		public boolean isVerified() {
			throw new RuntimeException("Stub!");
		}

		public boolean isOptimized() {
			throw new RuntimeException("Stub!");
		}

		public boolean isFullyCompiled() {
			throw new RuntimeException("Stub!");
		}
	}

	@Deprecated
	public static OptimizationInfo getDexFileOptimizationInfo(String fileName, String instructionSet) throws FileNotFoundException {
		throw new RuntimeException("Stub!");
	}

	public static String[] getDexFileOutputPaths(String fileName, String instructionSet) throws FileNotFoundException {
		throw new RuntimeException("Stub!");
	}

	public static boolean isValidCompilerFilter(String filter) {
		throw new RuntimeException("Stub!");
	}

	public static boolean isProfileGuidedCompilerFilter(String filter) {
		throw new RuntimeException("Stub!");
	}

	public static boolean isVerifiedCompilerFilter(String filter) {
		throw new RuntimeException("Stub!");
	}

	public static boolean isOptimizedCompilerFilter(String filter) {
		throw new RuntimeException("Stub!");
	}

	public static boolean isReadOnlyJavaDclEnforced() {
		throw new RuntimeException("Stub!");
	}

	public static String getNonProfileGuidedCompilerFilter(String filter) {
		throw new RuntimeException("Stub!");
	}

	public static String getSafeModeCompilerFilter(String filter) {
		throw new RuntimeException("Stub!");
	}

	public long getStaticSizeOfDexFile() {
		throw new RuntimeException("Stub!");
	}
}
