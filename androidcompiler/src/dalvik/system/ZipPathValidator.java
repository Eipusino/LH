package dalvik.system;

import java.util.zip.ZipException;

@SuppressWarnings("unused")
public final class ZipPathValidator {
	public static final Callback DEFAULT = null;

	public static void clearCallback() {
		throw new RuntimeException("Stub!");
	}

	public static void setCallback(Callback callback) {
		throw new RuntimeException("Stub!");
	}

	public static Callback getInstance() {
		throw new RuntimeException("Stub!");
	}

	public static boolean isClear() {
		throw new RuntimeException("Stub!");
	}

	public interface Callback {
		default void onZipEntryAccess(String path) throws ZipException {
			throw new RuntimeException("Stub!");
		}
	}

	private ZipPathValidator() {}
}
