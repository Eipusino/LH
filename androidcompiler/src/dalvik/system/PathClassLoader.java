package dalvik.system;

@SuppressWarnings("unused")
public class PathClassLoader extends BaseDexClassLoader {
	public PathClassLoader(String dexPath, ClassLoader parent) {
		super(dexPath, null, null, parent);
	}

	public PathClassLoader(String dexPath, String librarySearchPath, ClassLoader parent) {
		super(dexPath, null, librarySearchPath, parent);
	}

	public PathClassLoader(String dexPath, String librarySearchPath, ClassLoader parent, ClassLoader[] sharedLibraryLoaders) {
		this(dexPath, librarySearchPath, parent, sharedLibraryLoaders, null);
	}

	public PathClassLoader(String dexPath, String librarySearchPath, ClassLoader parent, ClassLoader[] sharedLibraryLoaders, ClassLoader[] sharedLibraryLoadersAfter) {
		super(dexPath, librarySearchPath, parent, sharedLibraryLoaders, sharedLibraryLoadersAfter);
	}
}
