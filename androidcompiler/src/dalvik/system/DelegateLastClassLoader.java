package dalvik.system;

import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;

@SuppressWarnings("unused")
public final class DelegateLastClassLoader extends PathClassLoader {
	public DelegateLastClassLoader(String dexPath, ClassLoader parent) {
		this(dexPath, null, parent, true);
	}

	public DelegateLastClassLoader(String dexPath, String librarySearchPath, ClassLoader parent) {
		this(dexPath, librarySearchPath, parent, true);
	}

	public DelegateLastClassLoader(String dexPath, String librarySearchPath, ClassLoader parent, boolean delegateResourceLoading) {
		super(dexPath, librarySearchPath, parent);
	}

	public DelegateLastClassLoader(String dexPath, String librarySearchPath, ClassLoader parent, ClassLoader[] sharedLibraryLoaders) {
		this(dexPath, librarySearchPath, parent, sharedLibraryLoaders, null);
	}

	public DelegateLastClassLoader(String dexPath, String librarySearchPath, ClassLoader parent, ClassLoader[] sharedLibraryLoaders, ClassLoader[] sharedLibraryLoadersAfter) {
		super(dexPath, librarySearchPath, parent, sharedLibraryLoaders, sharedLibraryLoadersAfter);
	}

	@Override
	protected Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
		throw new RuntimeException("Stub!");
	}

	@Override
	public URL getResource(String name) {
		throw new RuntimeException("Stub!");
	}

	@Override
	public Enumeration<URL> getResources(String name) throws IOException {
		throw new RuntimeException("Stub!");
	}
}
