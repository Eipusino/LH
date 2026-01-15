package dalvik.system;

import java.io.File;
import java.net.URL;
import java.util.Collection;
import java.util.Enumeration;
import java.util.List;

@SuppressWarnings("unused")
public final class DexPathList {
	public DexPathList(ClassLoader definingContext, String librarySearchPath) {
		throw new RuntimeException("Stub!");
	}

	public DexPathList(ClassLoader definingContext, String dexPath, String librarySearchPath, File optimizedDirectory) {
		this(definingContext, dexPath, librarySearchPath, optimizedDirectory, false);
	}

	DexPathList(ClassLoader definingContext, String dexPath, String librarySearchPath, File optimizedDirectory, boolean isTrusted) {
		throw new RuntimeException("Stub!");
	}

	@Override
	public String toString() {
		throw new RuntimeException("Stub!");
	}

	public List<File> getNativeLibraryDirectories() {
		throw new RuntimeException("Stub!");
	}

	public void addDexPath(String dexPath, File optimizedDirectory) {
		throw new RuntimeException("Stub!");
	}

	public void addDexPath(String dexPath, File optimizedDirectory, boolean isTrusted) {
		throw new RuntimeException("Stub!");
	}

	public Class<?> findClass(String name, List<Throwable> suppressed) {
		throw new RuntimeException("Stub!");
	}

	public URL findResource(String name) {
		throw new RuntimeException("Stub!");
	}

	public Enumeration<URL> findResources(String name) {
		throw new RuntimeException("Stub!");
	}

	public String findLibrary(String libraryName) {
		throw new RuntimeException("Stub!");
	}

	public void addNativePath(Collection<String> libPaths) {
		throw new RuntimeException("Stub!");
	}
}
