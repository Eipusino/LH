package endfield.android;

import arc.func.Prov;
import endfield.util.CollectionObjectMap;
import endfield.util.FieldAccessHelper;

import java.lang.reflect.Field;

public class AndroidUnsafeFieldAccessHelper implements FieldAccessHelper {
	protected static final CollectionObjectMap<Class<?>, CollectionObjectMap<String, Field>> fieldMap = new CollectionObjectMap<>(Class.class, CollectionObjectMap.class);

	protected static final Prov<CollectionObjectMap<String, Field>> prov = () -> new CollectionObjectMap<>(String.class, Field.class);

	public Field getField(Class<?> clazz, String name, boolean isStatic) throws NoSuchFieldException {
		CollectionObjectMap<String, Field> map = fieldMap.get(clazz, prov);
		Field field = map.get(name);
		if (field != null) return field;

		if (isStatic) {
			Field f = getField(clazz, name);
			map.put(name, f);
			return f;
		} else {
			Class<?> curr = clazz;
			while (curr != Object.class) {
				try {
					Field f = getField(clazz, name);
					map.put(name, f);
					return f;
				} catch (NoSuchFieldException ignored) {}

				curr = curr.getSuperclass();
			}
		}

		throw new NoSuchFieldException("field " + name + " was not found in class: " + clazz);
	}

	protected Field getField(Class<?> clazz, String name) throws NoSuchFieldException {
		Field field = clazz.getDeclaredField(name);
		field.setAccessible(true);

		return field;
	}

	@Override
	public void setByte(Object object, String name, byte value) {
		try {
			Field field = getField(object.getClass(), name, false);

			if (field.getType() != byte.class) throw new IllegalArgumentException(field.toString());

			Unsafer.setByte(field, object, value);
		} catch (Throwable e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void setByteStatic(Class<?> clazz, String name, byte value) {
		try {
			Field field = getField(clazz, name, true);

			if (field.getType() != byte.class) throw new IllegalArgumentException(field.toString());

			Unsafer.setByteStatic(field, value);
		} catch (Throwable e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public byte getByte(Object object, String name) {
		try {
			Field field = getField(object.getClass(), name, false);

			if (field.getType() != byte.class) throw new IllegalArgumentException(field.toString());

			return Unsafer.getByte(field, object);
		} catch (Throwable e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public byte getByteStatic(Class<?> clazz, String name) {
		try {
			Field field = getField(clazz, name, true);

			if (field.getType() != byte.class) throw new IllegalArgumentException(field.toString());

			return Unsafer.getByteStatic(field);
		} catch (Throwable e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void setShort(Object object, String name, short value) {
		try {
			Field field = getField(object.getClass(), name, false);

			if (field.getType() != short.class) throw new IllegalArgumentException(field.toString());

			Unsafer.setShort(field, object, value);
		} catch (Throwable e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void setShortStatic(Class<?> clazz, String name, short value) {
		try {
			Field field = getField(clazz, name, true);

			if (field.getType() != short.class) throw new IllegalArgumentException(field.toString());

			Unsafer.setShortStatic(field, value);
		} catch (Throwable e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public short getShort(Object object, String name) {
		try {
			Field field = getField(object.getClass(), name, false);

			if (field.getType() != short.class) throw new IllegalArgumentException(field.toString());

			return Unsafer.getShort(field, object);
		} catch (Throwable e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public short getShortStatic(Class<?> clazz, String name) {
		try {
			Field field = getField(clazz, name, true);

			if (field.getType() != short.class) throw new IllegalArgumentException(field.toString());

			return Unsafer.getShortStatic(field);
		} catch (Throwable e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void setInt(Object object, String name, int value) {
		try {
			Field field = getField(object.getClass(), name, false);

			if (field.getType() != int.class) throw new IllegalArgumentException(field.toString());

			Unsafer.setInt(field, object, value);
		} catch (Throwable e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void setIntStatic(Class<?> clazz, String name, int value) {
		try {
			Field field = getField(clazz, name, true);

			if (field.getType() != int.class) throw new IllegalArgumentException(field.toString());

			Unsafer.setIntStatic(field, value);
		} catch (Throwable e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public int getInt(Object object, String name) {
		try {
			Field field = getField(object.getClass(), name, false);

			if (field.getType() != int.class) throw new IllegalArgumentException(field.toString());

			return Unsafer.getInt(field, object);
		} catch (Throwable e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public int getIntStatic(Class<?> clazz, String name) {
		try {
			Field field = getField(clazz, name, true);

			if (field.getType() != int.class) throw new IllegalArgumentException(field.toString());

			return Unsafer.getIntStatic(field);
		} catch (Throwable e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void setLong(Object object, String name, long value) {
		try {
			Field field = getField(object.getClass(), name, false);

			if (field.getType() != long.class) throw new IllegalArgumentException(field.toString());

			Unsafer.setLong(field, object, value);
		} catch (Throwable e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void setLongStatic(Class<?> clazz, String name, long value) {
		try {
			Field field = getField(clazz, name, true);

			if (field.getType() != long.class) throw new IllegalArgumentException(field.toString());

			Unsafer.setLongStatic(field, value);
		} catch (Throwable e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public long getLong(Object object, String name) {
		try {
			Field field = getField(object.getClass(), name, false);

			if (field.getType() != long.class) throw new IllegalArgumentException(field.toString());

			return Unsafer.getLong(field, object);
		} catch (Throwable e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public long getLongStatic(Class<?> clazz, String name) {
		try {
			Field field = getField(clazz, name, true);

			if (field.getType() != long.class) throw new IllegalArgumentException(field.toString());

			return Unsafer.getLongStatic(field);
		} catch (Throwable e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void setFloat(Object object, String name, float value) {
		try {
			Field field = getField(object.getClass(), name, false);

			if (field.getType() != float.class) throw new IllegalArgumentException(field.toString());

			Unsafer.setFloat(field, object, value);
		} catch (Throwable e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void setFloatStatic(Class<?> clazz, String name, float value) {
		try {
			Field field = getField(clazz, name, true);

			if (field.getType() != float.class) throw new IllegalArgumentException(field.toString());

			Unsafer.setFloatStatic(field, value);
		} catch (Throwable e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public float getFloat(Object object, String name) {
		try {
			Field field = getField(object.getClass(), name, false);

			if (field.getType() != float.class) throw new IllegalArgumentException(field.toString());

			return Unsafer.getFloat(field, object);
		} catch (Throwable e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public float getFloatStatic(Class<?> clazz, String name) {
		try {
			Field field = getField(clazz, name, true);

			if (field.getType() != float.class) throw new IllegalArgumentException(field.toString());

			return Unsafer.getFloatStatic(field);
		} catch (Throwable e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void setDouble(Object object, String name, double value) {
		try {
			Field field = getField(object.getClass(), name, false);

			if (field.getType() != double.class) throw new IllegalArgumentException(field.toString());

			Unsafer.setDouble(field, object, value);
		} catch (Throwable e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void setDoubleStatic(Class<?> clazz, String name, double value) {
		try {
			Field field = getField(clazz, name, true);

			if (field.getType() != double.class) throw new IllegalArgumentException(field.toString());

			Unsafer.setDoubleStatic(field, value);
		} catch (Throwable e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public double getDouble(Object object, String name) {
		try {
			Field field = getField(object.getClass(), name, false);

			if (field.getType() != double.class) throw new IllegalArgumentException(field.toString());

			return Unsafer.getDouble(field, object);
		} catch (Throwable e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public double getDoubleStatic(Class<?> clazz, String name) {
		try {
			Field field = getField(clazz, name, true);

			if (field.getType() != double.class) throw new IllegalArgumentException(field.toString());

			return Unsafer.getDoubleStatic(field);
		} catch (Throwable e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void setChar(Object object, String name, char value) {
		try {
			Field field = getField(object.getClass(), name, false);

			if (field.getType() != char.class) throw new IllegalArgumentException(field.toString());

			Unsafer.setChar(field, object, value);
		} catch (Throwable e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void setCharStatic(Class<?> clazz, String name, char value) {
		try {
			Field field = getField(clazz, name, true);

			if (field.getType() != char.class) throw new IllegalArgumentException(field.toString());

			Unsafer.setCharStatic(field, value);
		} catch (Throwable e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public char getChar(Object object, String name) {
		try {
			Field field = getField(object.getClass(), name, false);

			if (field.getType() != char.class) throw new IllegalArgumentException(field.toString());

			return Unsafer.getChar(field, object);
		} catch (Throwable e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public char getCharStatic(Class<?> clazz, String name) {
		try {
			Field field = getField(clazz, name, true);

			if (field.getType() != char.class) throw new IllegalArgumentException(field.toString());

			return Unsafer.getCharStatic(field);
		} catch (Throwable e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void setBoolean(Object object, String name, boolean value) {
		try {
			Field field = getField(object.getClass(), name, false);

			if (field.getType() != boolean.class) throw new IllegalArgumentException(field.toString());

			Unsafer.setBoolean(field, object, value);
		} catch (Throwable e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void setBooleanStatic(Class<?> clazz, String name, boolean value) {
		try {
			Field field = getField(clazz, name, true);

			if (field.getType() != boolean.class) throw new IllegalArgumentException(field.toString());

			Unsafer.setBooleanStatic(field, value);
		} catch (Throwable e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public boolean getBoolean(Object object, String name) {
		try {
			Field field = getField(object.getClass(), name, false);

			if (field.getType() != boolean.class) throw new IllegalArgumentException(field.toString());

			return Unsafer.getBoolean(field, object);
		} catch (Throwable e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public boolean getBooleanStatic(Class<?> clazz, String name) {
		try {
			Field field = getField(clazz, name, true);

			if (field.getType() != boolean.class) throw new IllegalArgumentException(field.toString());

			return Unsafer.getBooleanStatic(field);
		} catch (Throwable e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void setObject(Object object, String name, Object value) {
		try {
			Field field = getField(object.getClass(), name, false);

			if (field.getType().isPrimitive()) throw new IllegalArgumentException(field.toString());

			Unsafer.setObject(field, object, value);
		} catch (Throwable e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void setObjectStatic(Class<?> clazz, String name, Object value) {
		try {
			Field field = getField(clazz, name, true);

			if (field.getType().isPrimitive()) throw new IllegalArgumentException(field.toString());

			Unsafer.setObjectStatic(field, value);
		} catch (Throwable e) {
			throw new RuntimeException(e);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T getObject(Object object, String name) {
		try {
			Field field = getField(object.getClass(), name, false);

			if (field.getType().isPrimitive()) throw new IllegalArgumentException(field.toString());

			return (T) Unsafer.getObject(field, object);
		} catch (Throwable e) {
			throw new RuntimeException(e);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T getObjectStatic(Class<?> clazz, String name) {
		try {
			Field field = getField(clazz, name, true);

			if (field.getType().isPrimitive()) throw new IllegalArgumentException(field.toString());

			return (T) Unsafer.getObjectStatic(field);
		} catch (Throwable e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void set(Object object, String name, Object value) {
		try {
			Field field = getField(object.getClass(), name, false);

			Unsafer.set(field, object, value);
		} catch (Throwable e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void setStatic(Class<?> clazz, String name, Object value) {
		try {
			Field field = getField(clazz, name, true);

			Unsafer.setStatic(field, value);
		} catch (Throwable e) {
			throw new RuntimeException(e);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T get(Object object, String name) {
		try {
			Field field = getField(object.getClass(), name, false);

			return (T) Unsafer.get(field, object);
		} catch (Throwable e) {
			throw new RuntimeException(e);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T getStatic(Class<?> clazz, String name) {
		try {
			Field field = getField(clazz, name, true);

			return (T) Unsafer.getStatic(field);
		} catch (Throwable e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void setByte(Object object, Field field, byte value) {
		if (field.getType() != byte.class) throw new IllegalArgumentException(field.toString());

		Unsafer.setByte(field, object, value);
	}

	@Override
	public void setByteStatic(Field field, byte value) {
		if (field.getType() != byte.class) throw new IllegalArgumentException(field.toString());

		Unsafer.setByteStatic(field, value);
	}

	@Override
	public byte getByte(Object object, Field field) {
		if (field.getType() != byte.class) throw new IllegalArgumentException(field.toString());

		return Unsafer.getByte(field, object);
	}

	@Override
	public byte getByteStatic(Field field) {
		if (field.getType() != byte.class) throw new IllegalArgumentException(field.toString());

		return Unsafer.getByteStatic(field);
	}

	@Override
	public void setShort(Object object, Field field, short value) {
		if (field.getType() != short.class) throw new IllegalArgumentException(field.toString());

		Unsafer.setShort(field, object, value);
	}

	@Override
	public void setShortStatic(Field field, short value) {
		if (field.getType() != short.class) throw new IllegalArgumentException(field.toString());

		Unsafer.setShortStatic(field, value);
	}

	@Override
	public short getShort(Object object, Field field) {
		if (field.getType() != short.class) throw new IllegalArgumentException(field.toString());

		return Unsafer.getShort(field, object);
	}

	@Override
	public short getShortStatic(Field field) {
		if (field.getType() != short.class) throw new IllegalArgumentException(field.toString());

		return Unsafer.getShortStatic(field);
	}

	@Override
	public void setInt(Object object, Field field, int value) {
		if (field.getType() != int.class) throw new IllegalArgumentException(field.toString());

		Unsafer.setInt(field, object, value);
	}

	@Override
	public void setIntStatic(Field field, int value) {
		if (field.getType() != int.class) throw new IllegalArgumentException(field.toString());

		Unsafer.setIntStatic(field, value);
	}

	@Override
	public int getInt(Object object, Field field) {
		if (field.getType() != int.class) throw new IllegalArgumentException(field.toString());

		return Unsafer.getInt(field, object);
	}

	@Override
	public int getIntStatic(Field field) {
		if (field.getType() != int.class) throw new IllegalArgumentException(field.toString());

		return Unsafer.getIntStatic(field);
	}

	@Override
	public void setLong(Object object, Field field, long value) {
		if (field.getType() != long.class) throw new IllegalArgumentException(field.toString());

		Unsafer.setLong(field, object, value);
	}

	@Override
	public void setLongStatic(Field field, long value) {
		if (field.getType() != long.class) throw new IllegalArgumentException(field.toString());

		Unsafer.setLongStatic(field, value);
	}

	@Override
	public long getLong(Object object, Field field) {
		if (field.getType() != long.class) throw new IllegalArgumentException(field.toString());

		return Unsafer.getLong(field, object);
	}

	@Override
	public long getLongStatic(Field field) {
		if (field.getType() != long.class) throw new IllegalArgumentException(field.toString());

		return Unsafer.getLongStatic(field);
	}

	@Override
	public void setFloat(Object object, Field field, float value) {
		if (field.getType() != float.class) throw new IllegalArgumentException(field.toString());

		Unsafer.setFloat(field, object, value);
	}

	@Override
	public void setFloatStatic(Field field, float value) {
		if (field.getType() != float.class) throw new IllegalArgumentException(field.toString());

		Unsafer.setFloatStatic(field, value);
	}

	@Override
	public float getFloat(Object object, Field field) {
		if (field.getType() != float.class) throw new IllegalArgumentException(field.toString());

		return Unsafer.getFloat(field, object);
	}

	@Override
	public float getFloatStatic(Field field) {
		if (field.getType() != float.class) throw new IllegalArgumentException(field.toString());

		return Unsafer.getFloatStatic(field);
	}

	@Override
	public void setDouble(Object object, Field field, double value) {
		if (field.getType() != double.class) throw new IllegalArgumentException(field.toString());

		Unsafer.setDouble(field, object, value);
	}

	@Override
	public void setDoubleStatic(Field field, double value) {
		if (field.getType() != double.class) throw new IllegalArgumentException(field.toString());

		Unsafer.setDoubleStatic(field, value);
	}

	@Override
	public double getDouble(Object object, Field field) {
		if (field.getType() != double.class) throw new IllegalArgumentException(field.toString());

		return Unsafer.getDouble(field, object);
	}

	@Override
	public double getDoubleStatic(Field field) {
		if (field.getType() != double.class) throw new IllegalArgumentException(field.toString());

		return Unsafer.getDoubleStatic(field);
	}

	@Override
	public void setChar(Object object, Field field, char value) {
		if (field.getType() != char.class) throw new IllegalArgumentException(field.toString());

		Unsafer.setChar(field, object, value);
	}

	@Override
	public void setCharStatic(Field field, char value) {
		if (field.getType() != char.class) throw new IllegalArgumentException(field.toString());

		Unsafer.setCharStatic(field, value);
	}

	@Override
	public char getChar(Object object, Field field) {
		if (field.getType() != char.class) throw new IllegalArgumentException(field.toString());

		return Unsafer.getChar(field, object);
	}

	@Override
	public char getCharStatic(Field field) {
		if (field.getType() != char.class) throw new IllegalArgumentException(field.toString());

		return Unsafer.getCharStatic(field);
	}

	@Override
	public void setBoolean(Object object, Field field, boolean value) {
		if (field.getType() != boolean.class) throw new IllegalArgumentException(field.toString());

		Unsafer.setBoolean(field, object, value);
	}

	@Override
	public void setBooleanStatic(Field field, boolean value) {
		if (field.getType() != boolean.class) throw new IllegalArgumentException(field.toString());

		Unsafer.setBooleanStatic(field, value);
	}

	@Override
	public boolean getBoolean(Object object, Field field) {
		if (field.getType() != boolean.class) throw new IllegalArgumentException(field.toString());

		return Unsafer.getBoolean(field, object);
	}

	@Override
	public boolean getBooleanStatic(Field field) {
		if (field.getType() != boolean.class) throw new IllegalArgumentException(field.toString());

		return Unsafer.getBooleanStatic(field);
	}

	@Override
	public void setObject(Object object, Field field, Object value) {
		if (field.getType().isPrimitive()) throw new IllegalArgumentException(field.toString());

		Unsafer.setObject(field, object, value);
	}

	@Override
	public void setObjectStatic(Field field, Object value) {
		if (field.getType().isPrimitive()) throw new IllegalArgumentException(field.toString());

		Unsafer.setObjectStatic(field, value);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T getObject(Object object, Field field) {
		if (field.getType().isPrimitive()) throw new IllegalArgumentException(field.toString());

		return (T) Unsafer.getObject(field, object);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T getObjectStatic(Field field) {
		if (field.getType().isPrimitive()) throw new IllegalArgumentException(field.toString());

		return (T) Unsafer.getObjectStatic(field);
	}

	@Override
	public void set(Object object, Field field, Object value) {
		Unsafer.set(field, object, value);
	}

	@Override
	public void setStatic(Field field, Object value) {
		Unsafer.setStatic(field, value);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T get(Object object, Field field) {
		return (T) Unsafer.get(field, object);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T getStatic(Field field) {
		return (T) Unsafer.getStatic(field);
	}
}
