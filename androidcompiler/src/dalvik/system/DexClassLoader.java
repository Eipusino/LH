package dalvik.system;

@SuppressWarnings("unused")
public class DexClassLoader extends BaseDexClassLoader {
	public DexClassLoader(String dexPath, String optimizedDirectory, String librarySearchPath, ClassLoader parent) {
		super(dexPath, null, librarySearchPath, parent);
	}
}
