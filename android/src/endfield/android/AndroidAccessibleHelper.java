package endfield.android;

import endfield.util.AccessibleHelper;

import java.lang.reflect.Field;

import static endfield.android.AndroidImpl.exceptionHandler;

public class AndroidAccessibleHelper implements AccessibleHelper {
	static Field accessFlags;

	@Override
	public void makeClassAccessible(Class<?> clazz) {
		try {
			if (accessFlags == null) {
				accessFlags = Class.class.getDeclaredField("accessFlags");
				accessFlags.setAccessible(true);
			}

			int flags = accessFlags.getInt(clazz);
			accessFlags.setInt(clazz, 65535 & ((flags & 65535 & (-17) & (-3)) | 1));
		} catch (IllegalAccessException | NoSuchFieldException e) {
			exceptionHandler.get(e);
		}
	}
}
