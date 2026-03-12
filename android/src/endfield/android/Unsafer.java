package endfield.android;

import arc.util.Log;
import endfield.android.util.field.AndroidField;
import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

@SuppressWarnings("removal")
public final class Unsafer {
	static final Unsafe unsafe;

	static Object internalUnsafe;

	static {
		try {
			Field field = Unsafe.class.getDeclaredField("theUnsafe");
			field.setAccessible(true);
			unsafe = (Unsafe) field.get(null);
		} catch (NoSuchFieldException | IllegalAccessException e) {
			throw new RuntimeException(e);
		}

		try {
			Field field = Unsafe.class.getDeclaredField("theInternalUnsafe");
			field.setAccessible(true);
			internalUnsafe = field.get(null);
		} catch (NoSuchFieldException | IllegalAccessException e) {
			Log.err(e);
		}
	}

	private Unsafer() {}

	public static void setByte(Field field, Object object, byte value) {
		long offset = AndroidField.fieldOffset(field);

		if (Modifier.isVolatile(field.getModifiers()))
			unsafe.putByteVolatile(object, offset, value);
		else
			unsafe.putByte(object, offset, value);
	}

	public static void setByteStatic(Field field, byte value) {
		long offset = AndroidField.fieldOffset(field);

		if (Modifier.isVolatile(field.getModifiers()))
			unsafe.putByteVolatile(field.getDeclaringClass(), offset, value);
		else
			unsafe.putByte(field.getDeclaringClass(), offset, value);
	}

	public static byte getByte(Field field, Object object) {
		long offset = AndroidField.fieldOffset(field);

		return Modifier.isVolatile(field.getModifiers()) ?
				unsafe.getByteVolatile(object, offset) :
				unsafe.getByte(object, offset);
	}

	public static byte getByteStatic(Field field) {
		long offset = AndroidField.fieldOffset(field);

		return Modifier.isVolatile(field.getModifiers()) ?
				unsafe.getByteVolatile(field.getDeclaringClass(), offset) :
				unsafe.getByte(field.getDeclaringClass(), offset);
	}

	public static void setShort(Field field, Object object, short value) {
		long offset = AndroidField.fieldOffset(field);

		if (Modifier.isVolatile(field.getModifiers()))
			unsafe.putShortVolatile(object, offset, value);
		else
			unsafe.putShort(object, offset, value);
	}

	public static void setShortStatic(Field field, short value) {
		long offset = AndroidField.fieldOffset(field);

		if (Modifier.isVolatile(field.getModifiers()))
			unsafe.putShortVolatile(field.getDeclaringClass(), offset, value);
		else
			unsafe.putShort(field.getDeclaringClass(), offset, value);
	}

	public static short getShort(Field field, Object object) {
		long offset = AndroidField.fieldOffset(field);

		return Modifier.isVolatile(field.getModifiers()) ?
				unsafe.getShortVolatile(object, offset) :
				unsafe.getShort(object, offset);
	}

	public static short getShortStatic(Field field) {
		long offset = AndroidField.fieldOffset(field);

		return Modifier.isVolatile(field.getModifiers()) ?
				unsafe.getShortVolatile(field.getDeclaringClass(), offset) :
				unsafe.getShort(field.getDeclaringClass(), offset);
	}

	public static void setInt(Field field, Object object, int value) {
		long offset = AndroidField.fieldOffset(field);

		if (Modifier.isVolatile(field.getModifiers()))
			unsafe.putIntVolatile(object, offset, value);
		else
			unsafe.putInt(object, offset, value);
	}

	public static void setIntStatic(Field field, int value) {
		long offset = AndroidField.fieldOffset(field);

		if (Modifier.isVolatile(field.getModifiers()))
			unsafe.putIntVolatile(field.getDeclaringClass(), offset, value);
		else
			unsafe.putInt(field.getDeclaringClass(), offset, value);
	}

	public static int getInt(Field field, Object object) {
		long offset = AndroidField.fieldOffset(field);

		return Modifier.isVolatile(field.getModifiers()) ?
				unsafe.getIntVolatile(object, offset) :
				unsafe.getInt(object, offset);
	}

	public static int getIntStatic(Field field) {
		long offset = AndroidField.fieldOffset(field);

		return Modifier.isVolatile(field.getModifiers()) ?
				unsafe.getIntVolatile(field.getDeclaringClass(), offset) :
				unsafe.getInt(field.getDeclaringClass(), offset);
	}

	public static void setLong(Field field, Object object, long value) {
		long offset = AndroidField.fieldOffset(field);

		if (Modifier.isVolatile(field.getModifiers()))
			unsafe.putLongVolatile(object, offset, value);
		else
			unsafe.putLong(object, offset, value);
	}

	public static void setLongStatic(Field field, long value) {
		long offset = AndroidField.fieldOffset(field);

		if (Modifier.isVolatile(field.getModifiers()))
			unsafe.putLongVolatile(field.getDeclaringClass(), offset, value);
		else
			unsafe.putLong(field.getDeclaringClass(), offset, value);
	}

	public static long getLong(Field field, Object object) {
		long offset = AndroidField.fieldOffset(field);

		return Modifier.isVolatile(field.getModifiers()) ?
				unsafe.getLongVolatile(object, offset) :
				unsafe.getLong(object, offset);
	}

	public static long getLongStatic(Field field) {
		long offset = AndroidField.fieldOffset(field);

		return Modifier.isVolatile(field.getModifiers()) ?
				unsafe.getLongVolatile(field.getDeclaringClass(), offset) :
				unsafe.getLong(field.getDeclaringClass(), offset);
	}

	public static void setFloat(Field field, Object object, float value) {
		long offset = AndroidField.fieldOffset(field);

		if (Modifier.isVolatile(field.getModifiers()))
			unsafe.putFloatVolatile(object, offset, value);
		else
			unsafe.putFloat(object, offset, value);
	}

	public static void setFloatStatic(Field field, float value) {
		long offset = AndroidField.fieldOffset(field);

		if (Modifier.isVolatile(field.getModifiers())) {
			unsafe.putFloatVolatile(field.getDeclaringClass(), offset, value);
		} else unsafe.putFloat(field.getDeclaringClass(), offset, value);
	}

	public static float getFloat(Field field, Object object) {
		long offset = AndroidField.fieldOffset(field);

		return Modifier.isVolatile(field.getModifiers()) ?
				unsafe.getFloatVolatile(object, offset) :
				unsafe.getFloat(object, offset);
	}

	public static float getFloatStatic(Field field) {
		long offset = AndroidField.fieldOffset(field);

		return Modifier.isVolatile(field.getModifiers()) ?
				unsafe.getFloatVolatile(field.getDeclaringClass(), offset) :
				unsafe.getFloat(field.getDeclaringClass(), offset);
	}

	public static void setDouble(Field field, Object object, double value) {
		long offset = AndroidField.fieldOffset(field);

		if (Modifier.isVolatile(field.getModifiers()))
			unsafe.putDoubleVolatile(object, offset, value);
		else
			unsafe.putDouble(object, offset, value);
	}

	public static void setDoubleStatic(Field field, double value) {
		long offset = AndroidField.fieldOffset(field);

		if (Modifier.isVolatile(field.getModifiers()))
			unsafe.putDoubleVolatile(field.getDeclaringClass(), offset, value);
		else
			unsafe.putDouble(field.getDeclaringClass(), offset, value);
	}

	public static double getDouble(Field field, Object object) {
		long offset = AndroidField.fieldOffset(field);

		return Modifier.isVolatile(field.getModifiers()) ?
				unsafe.getDoubleVolatile(object, offset) :
				unsafe.getDouble(object, offset);
	}

	public static double getDoubleStatic(Field field) {
		long offset = AndroidField.fieldOffset(field);

		return Modifier.isVolatile(field.getModifiers()) ?
				unsafe.getDoubleVolatile(field.getDeclaringClass(), offset) :
				unsafe.getDouble(field.getDeclaringClass(), offset);
	}

	public static void setChar(Field field, Object object, char value) {
		long offset = AndroidField.fieldOffset(field);

		if (Modifier.isVolatile(field.getModifiers()))
			unsafe.putCharVolatile(object, offset, value);
		else
			unsafe.putChar(object, offset, value);
	}

	public static void setCharStatic(Field field, char value) {
		long offset = AndroidField.fieldOffset(field);

		if (Modifier.isVolatile(field.getModifiers()))
			unsafe.putCharVolatile(field.getDeclaringClass(), offset, value);
		else
			unsafe.putChar(field.getDeclaringClass(), offset, value);
	}

	public static char getChar(Field field, Object object) {
		long offset = AndroidField.fieldOffset(field);

		return Modifier.isVolatile(field.getModifiers()) ?
				unsafe.getCharVolatile(object, offset) :
				unsafe.getChar(object, offset);
	}

	public static char getCharStatic(Field field) {
		long offset = AndroidField.fieldOffset(field);

		return Modifier.isVolatile(field.getModifiers()) ?
				unsafe.getCharVolatile(field.getDeclaringClass(), offset) :
				unsafe.getChar(field.getDeclaringClass(), offset);
	}

	public static void setBoolean(Field field, Object object, boolean value) {
		long offset = AndroidField.fieldOffset(field);

		if (Modifier.isVolatile(field.getModifiers()))
			unsafe.putBooleanVolatile(object, offset, value);
		else
			unsafe.putBoolean(object, offset, value);
	}

	public static void setBooleanStatic(Field field, boolean value) {
		long offset = AndroidField.fieldOffset(field);

		if (Modifier.isVolatile(field.getModifiers()))
			unsafe.putBooleanVolatile(field.getDeclaringClass(), offset, value);
		else
			unsafe.putBoolean(field.getDeclaringClass(), offset, value);
	}

	public static boolean getBoolean(Field field, Object object) {
		long offset = AndroidField.fieldOffset(field);

		return Modifier.isVolatile(field.getModifiers()) ?
				unsafe.getBooleanVolatile(object, offset) :
				unsafe.getBoolean(object, offset);
	}

	public static boolean getBooleanStatic(Field field) {
		long offset = AndroidField.fieldOffset(field);

		return Modifier.isVolatile(field.getModifiers()) ?
				unsafe.getBooleanVolatile(field.getDeclaringClass(), offset) :
				unsafe.getBoolean(field.getDeclaringClass(), offset);
	}

	public static void setObject(Field field, Object object, Object value) {
		long offset = AndroidField.fieldOffset(field);

		if (Modifier.isVolatile(field.getModifiers()))
			unsafe.putObjectVolatile(object, offset, value);
		else
			unsafe.putObject(object, offset, value);
	}

	public static void setObjectStatic(Field field, Object value) {
		long offset = AndroidField.fieldOffset(field);

		if (Modifier.isVolatile(field.getModifiers()))
			unsafe.putObjectVolatile(field.getDeclaringClass(), offset, value);
		else
			unsafe.putObject(field.getDeclaringClass(), offset, value);
	}

	public static Object getObject(Field field, Object object) {
		long offset = AndroidField.fieldOffset(field);

		return Modifier.isVolatile(field.getModifiers()) ?
				unsafe.getObjectVolatile(object, offset) :
				unsafe.getObject(object, offset);
	}

	public static Object getObjectStatic(Field field) {
		long offset = AndroidField.fieldOffset(field);

		return Modifier.isVolatile(field.getModifiers()) ?
				unsafe.getObjectVolatile(field.getDeclaringClass(), offset) :
				unsafe.getObject(field.getDeclaringClass(), offset);
	}

	public static void set(Field field, Object object, Object value) {
		long offset = AndroidField.fieldOffset(field);
		Class<?> clazz = field.getType();
		if (Modifier.isVolatile(field.getModifiers()))
			put1(value, object, offset, clazz);
		else
			put0(value, object, offset, clazz);
	}

	public static void setStatic(Field field, Object value) {
		Object base = field.getDeclaringClass();
		long offset = AndroidField.fieldOffset(field);
		Class<?> clazz = field.getType();

		if (Modifier.isVolatile(field.getModifiers()))
			put1(value, base, offset, clazz);
		else
			put0(value, base, offset, clazz);
	}

	public static Object get(Field field, Object object) {
		long offset = AndroidField.fieldOffset(field);
		Class<?> clazz = field.getType();

		return Modifier.isVolatile(field.getModifiers()) ?
				get1(object, offset, clazz) :
				get0(object, offset, clazz);
	}

	public static Object getStatic(Field field) {
		Object base = field.getDeclaringClass();
		long offset = AndroidField.fieldOffset(field);
		Class<?> clazz = field.getType();

		return Modifier.isVolatile(field.getModifiers()) ?
				get1(base, offset, clazz) :
				get0(base, offset, clazz);
	}

	static void put0(Object value, Object object, long offset, Class<?> clazz) {
		if (clazz.isPrimitive()) {
			if (clazz == int.class) unsafe.putInt(object, offset, (int) value);
			else if (clazz == float.class) unsafe.putFloat(object, offset, (float) value);
			else if (clazz == boolean.class) unsafe.putBoolean(object, offset, (boolean) value);
			else if (clazz == byte.class) unsafe.putByte(object, offset, (byte) value);
			else if (clazz == double.class) unsafe.putDouble(object, offset, (double) value);
			else if (clazz == long.class) unsafe.putLong(object, offset, (long) value);
			else if (clazz == char.class) unsafe.putChar(object, offset, (char) value);
			else if (clazz == short.class) unsafe.putShort(object, offset, (short) value);
			else throw new IllegalArgumentException("unknown type of field " + clazz);
		} else {
			unsafe.putObject(object, offset, value);
		}
	}

	static void put1(Object value, Object object, long offset, Class<?> clazz) {
		if (clazz.isPrimitive()) {
			if (clazz == int.class) unsafe.putIntVolatile(object, offset, (int) value);
			else if (clazz == float.class) unsafe.putFloatVolatile(object, offset, (float) value);
			else if (clazz == boolean.class) unsafe.putBooleanVolatile(object, offset, (boolean) value);
			else if (clazz == byte.class) unsafe.putByteVolatile(object, offset, (byte) value);
			else if (clazz == long.class) unsafe.putLongVolatile(object, offset, (long) value);
			else if (clazz == double.class) unsafe.putDoubleVolatile(object, offset, (double) value);
			else if (clazz == char.class) unsafe.putCharVolatile(object, offset, (char) value);
			else if (clazz == short.class) unsafe.putShortVolatile(object, offset, (short) value);
			else throw new IllegalArgumentException("unknown type of field " + clazz);
		} else {
			unsafe.putObjectVolatile(object, offset, value);
		}
	}

	static Object get0(Object object, long offset, Class<?> clazz) {
		if (clazz.isPrimitive()) {
			if (clazz == int.class) return unsafe.getInt(object, offset);
			else if (clazz == float.class) return unsafe.getFloat(object, offset);
			else if (clazz == boolean.class) return unsafe.getBoolean(object, offset);
			else if (clazz == byte.class) return unsafe.getByte(object, offset);
			else if (clazz == long.class) return unsafe.getDouble(object, offset);
			else if (clazz == double.class) return unsafe.getLong(object, offset);
			else if (clazz == char.class) return unsafe.getChar(object, offset);
			else if (clazz == short.class) return unsafe.getShort(object, offset);
			else throw new IllegalArgumentException("unknown type of field " + clazz);
		} else {
			return unsafe.getObject(object, offset);
		}
	}

	static Object get1(Object object, long offset, Class<?> clazz) {
		if (clazz.isPrimitive()) {
			if (clazz == int.class) return unsafe.getIntVolatile(object, offset);
			else if (clazz == float.class) return unsafe.getFloatVolatile(object, offset);
			else if (clazz == boolean.class) return unsafe.getBooleanVolatile(object, offset);
			else if (clazz == byte.class) return unsafe.getByteVolatile(object, offset);
			else if (clazz == long.class) return unsafe.getLongVolatile(object, offset);
			else if (clazz == double.class) return unsafe.getDoubleVolatile(object, offset);
			else if (clazz == char.class) return unsafe.getCharVolatile(object, offset);
			else if (clazz == short.class) return unsafe.getShortVolatile(object, offset);
			else throw new IllegalArgumentException("unknown type of field " + clazz);
		} else {
			return unsafe.getObjectVolatile(object, offset);
		}
	}
}
