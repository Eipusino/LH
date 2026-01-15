package dalvik.system;

import java.io.File;
import java.net.URL;
import java.nio.ByteBuffer;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Map;

@SuppressWarnings({"unused", "deprecation"})
public class BaseDexClassLoader extends ClassLoader {
	protected final ClassLoader[] sharedLibraryLoaders = null;

	protected final ClassLoader[] sharedLibraryLoadersAfter = null;

	public BaseDexClassLoader(String dexPath, File optimizedDirectory, String librarySearchPath, ClassLoader parent) {
		this(dexPath, librarySearchPath, parent, null, null, false);
	}

	public BaseDexClassLoader(String dexPath, File optimizedDirectory,
	                          String librarySearchPath, ClassLoader parent, boolean isTrusted) {
		this(dexPath, librarySearchPath, parent, null, null, isTrusted);
	}

	public BaseDexClassLoader(String dexPath, String librarySearchPath, ClassLoader parent, ClassLoader[] libraries) {
		this(dexPath, librarySearchPath, parent, libraries, null, false);
	}

	public BaseDexClassLoader(String dexPath, String librarySearchPath, ClassLoader parent, ClassLoader[] libraries, ClassLoader[] librariesAfter) {
		this(dexPath, librarySearchPath, parent, libraries, librariesAfter, false);
	}

	public BaseDexClassLoader(String dexPath, String librarySearchPath, ClassLoader parent, ClassLoader[] sharedLibraryLoaders, ClassLoader[] sharedLibraryLoadersAfter, boolean isTrusted) {
		super(parent);
	}

	public void reportClassLoaderChain() {
		throw new RuntimeException("Stub!");
	}

	public BaseDexClassLoader(ByteBuffer[] dexFiles, String librarySearchPath, ClassLoader parent) {
		throw new RuntimeException("Stub!");
	}

	@Override
	protected Class<?> findClass(String name) throws ClassNotFoundException {
		throw new RuntimeException("Stub!");
	}

	public void addDexPath(String dexPath) {
		throw new RuntimeException("Stub!");
	}

	public void addDexPath(String dexPath, boolean isTrusted) {
		throw new RuntimeException("Stub!");
	}

	public void addNativePath(Collection<String> libPaths) {
		throw new RuntimeException("Stub!");
	}

	@Override
	protected URL findResource(String name) {
		throw new RuntimeException("Stub!");
	}

	@Override
	protected Enumeration<URL> findResources(String name) {
		throw new RuntimeException("Stub!");
	}

	@Override
	public String findLibrary(String name) {
		throw new RuntimeException("Stub!");
	}

	@Deprecated
	@Override
	protected synchronized Package getPackage(String name) {
		throw new RuntimeException("Stub!");
	}

	public String getLdLibraryPath() {
		throw new RuntimeException("Stub!");
	}

	@Override
	public String toString() {
		throw new RuntimeException("Stub!");
	}

	public static void setReporter(Reporter newReporter) {
		throw new RuntimeException("Stub!");
	}

	public static Reporter getReporter() {
		throw new RuntimeException("Stub!");
	}

	public interface Reporter {
		void report(Map<String, String> contextsMap);
	}
}
