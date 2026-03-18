package endfield.desktop;

import endfield.util.FieldAccessor;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import static endfield.desktop.Unsafer.getGetMessage;
import static endfield.desktop.Unsafer.getSetMessage;
import static endfield.desktop.Unsafer.unsafe;

public abstract class UnsafeFieldAccessor implements FieldAccessor {
	protected final Field field;
	protected final long offset;

	protected UnsafeFieldAccessor(Field f) {
		field = f;
		if (Modifier.isStatic(f.getModifiers())) {
			offset = unsafe.staticFieldOffset(f);
		} else {
			offset = unsafe.objectFieldOffset(f);
		}
	}

	public static FieldAccessor obtainFieldAccessor(Field f) {
		Class<?> type = f.getType();
		int modifiers = f.getModifiers();
		boolean isStatic = Modifier.isStatic(modifiers), isVolatile = Modifier.isVolatile(modifiers);

		if (isStatic) {
			if (isVolatile) {
				if (type.isPrimitive()) {
					if (type == boolean.class) return new UnsafeQualifiedStaticBooleanFieldAccessor(f);
					else if (type == byte.class) return new UnsafeQualifiedStaticByteFieldAccessor(f);
					else if (type == char.class) return new UnsafeQualifiedStaticCharFieldAccessor(f);
					else if (type == short.class) return new UnsafeQualifiedStaticShortFieldAccessor(f);
					else if (type == int.class) return new UnsafeQualifiedStaticIntFieldAccessor(f);
					else if (type == long.class) return new UnsafeQualifiedStaticLongFieldAccessor(f);
					else if (type == float.class) return new UnsafeQualifiedStaticFloatFieldAccessor(f);
					else if (type == double.class) return new UnsafeQualifiedStaticDoubleFieldAccessor(f);
					else throw new IllegalArgumentException("unknown type of field " + f);
				} else return new UnsafeQualifiedStaticObjectFieldAccessor(f);
			} else {
				if (type.isPrimitive()) {
					if (type == boolean.class) return new UnsafeStaticBooleanFieldAccessor(f);
					else if (type == byte.class) return new UnsafeStaticByteFieldAccessor(f);
					else if (type == char.class) return new UnsafeStaticCharFieldAccessor(f);
					else if (type == short.class) return new UnsafeStaticShortFieldAccessor(f);
					else if (type == int.class) return new UnsafeStaticIntFieldAccessor(f);
					else if (type == long.class) return new UnsafeStaticLongFieldAccessor(f);
					else if (type == float.class) return new UnsafeStaticFloatFieldAccessor(f);
					else if (type == double.class) return new UnsafeStaticDoubleFieldAccessor(f);
					else throw new IllegalArgumentException("unknown type of field " + f);
				} else return new UnsafeStaticObjectFieldAccessor(f);
			}
		} else {
			if (isVolatile) {
				if (type.isPrimitive()) {
					if (type == boolean.class) return new UnsafeQualifiedBooleanFieldAccessor(f);
					else if (type == byte.class) return new UnsafeQualifiedByteFieldAccessor(f);
					else if (type == char.class) return new UnsafeQualifiedCharFieldAccessor(f);
					else if (type == short.class) return new UnsafeQualifiedShortFieldAccessor(f);
					else if (type == int.class) return new UnsafeQualifiedIntFieldAccessor(f);
					else if (type == long.class) return new UnsafeQualifiedLongFieldAccessor(f);
					else if (type == float.class) return new UnsafeQualifiedFloatFieldAccessor(f);
					else if (type == double.class) return new UnsafeQualifiedDoubleFieldAccessor(f);
					else throw new IllegalArgumentException("unknown type of field " + f);
				} else return new UnsafeQualifiedObjectFieldAccessor(f);
			} else {
				if (type.isPrimitive()) {
					if (type == boolean.class) return new UnsafeBooleanFieldAccessor(f);
					else if (type == byte.class) return new UnsafeByteFieldAccessor(f);
					else if (type == char.class) return new UnsafeCharFieldAccessor(f);
					else if (type == short.class) return new UnsafeShortFieldAccessor(f);
					else if (type == int.class) return new UnsafeIntFieldAccessor(f);
					else if (type == long.class) return new UnsafeLongFieldAccessor(f);
					else if (type == float.class) return new UnsafeFloatFieldAccessor(f);
					else if (type == double.class) return new UnsafeDoubleFieldAccessor(f);
					else throw new IllegalArgumentException("unknown type of field " + f);
				} else return new UnsafeObjectFieldAccessor(f);
			}
		}
	}

	public void ensureObject(Object object) {
		if (!field.getDeclaringClass().isInstance(object))
			throw new IllegalArgumentException(getSetMessage(field, object));
	}

	public void ensureValue(Object value) {
		if (value != null && !field.getType().isInstance(value))
			throw new IllegalArgumentException(getSetMessage(field, value));
	}

	@Override
	public <T> T get(Object object) {
		throw new IllegalArgumentException(getGetMessage(field, Object.class.getName()));
	}

	@Override
	public <T> T getStatic() {
		throw new IllegalArgumentException(getGetMessage(field, Object.class.getName()));
	}

	@Override
	public void set(Object object, Object value) {
		throw new IllegalArgumentException(getSetMessage(field, value));
	}

	@Override
	public void setStatic(Object value) {
		throw new IllegalArgumentException(getSetMessage(field, value));
	}

	@Override
	public <T> T getObject(Object object) {
		throw new IllegalArgumentException(getGetMessage(field, Object.class.getName()));
	}

	@Override
	public <T> T getObjectStatic() {
		throw new IllegalArgumentException(getGetMessage(field, Object.class.getName()));
	}

	@Override
	public void setObject(Object object, Object value) {
		throw new IllegalArgumentException(getSetMessage(field, value));
	}

	@Override
	public void setObjectStatic(Object value) {
		throw new IllegalArgumentException(getSetMessage(field, value));
	}

	@Override
	public boolean getBoolean(Object object) {
		throw new IllegalArgumentException(getGetMessage(field, "boolean"));
	}

	@Override
	public boolean getBooleanStatic() {
		throw new IllegalArgumentException(getGetMessage(field, "boolean"));
	}

	@Override
	public void setBoolean(Object object, boolean value) {
		throw new IllegalArgumentException(getSetMessage(field, "boolean", String.valueOf(value)));
	}

	@Override
	public void setBooleanStatic(boolean value) {
		throw new IllegalArgumentException(getSetMessage(field, "boolean", String.valueOf(value)));
	}

	@Override
	public byte getByte(Object object) {
		throw new IllegalArgumentException(getGetMessage(field, "boolean"));
	}

	@Override
	public byte getByteStatic() {
		throw new IllegalArgumentException(getGetMessage(field, "boolean"));
	}

	@Override
	public void setByte(Object object, byte value) {
		throw new IllegalArgumentException(getSetMessage(field, "byte", String.valueOf(value)));
	}

	@Override
	public void setByteStatic(byte value) {
		throw new IllegalArgumentException(getSetMessage(field, "byte", String.valueOf(value)));
	}

	@Override
	public char getChar(Object object) {
		throw new IllegalArgumentException(getGetMessage(field, "char"));
	}

	@Override
	public char getCharStatic() {
		throw new IllegalArgumentException(getGetMessage(field, "char"));
	}

	@Override
	public void setChar(Object object, char value) {
		throw new IllegalArgumentException(getSetMessage(field, "char", String.valueOf(value)));
	}

	@Override
	public void setCharStatic(char value) {
		throw new IllegalArgumentException(getSetMessage(field, "char", String.valueOf(value)));
	}

	@Override
	public short getShort(Object object) {
		throw new IllegalArgumentException(getGetMessage(field, "short"));
	}

	@Override
	public short getShortStatic() {
		throw new IllegalArgumentException(getGetMessage(field, "short"));
	}

	@Override
	public void setShort(Object object, short value) {
		throw new IllegalArgumentException(getSetMessage(field, "short", String.valueOf(value)));
	}

	@Override
	public void setShortStatic(short value) {
		throw new IllegalArgumentException(getSetMessage(field, "short", String.valueOf(value)));
	}

	@Override
	public int getInt(Object object) {
		throw new IllegalArgumentException(getGetMessage(field, "int"));
	}

	@Override
	public int getIntStatic() {
		throw new IllegalArgumentException(getGetMessage(field, "int"));
	}

	@Override
	public void setInt(Object object, int value) {
		throw new IllegalArgumentException(getSetMessage(field, "int", String.valueOf(value)));
	}

	@Override
	public void setIntStatic(int value) {
		throw new IllegalArgumentException(getSetMessage(field, "int", String.valueOf(value)));
	}

	@Override
	public long getLong(Object object) {
		throw new IllegalArgumentException(getGetMessage(field, "long"));
	}

	@Override
	public long getLongStatic() {
		throw new IllegalArgumentException(getGetMessage(field, "long"));
	}

	@Override
	public void setLong(Object object, long value) {
		throw new IllegalArgumentException(getSetMessage(field, "long", String.valueOf(value)));
	}

	@Override
	public void setLongStatic(long value) {
		throw new IllegalArgumentException(getSetMessage(field, "long", String.valueOf(value)));
	}

	@Override
	public float getFloat(Object object) {
		throw new IllegalArgumentException(getGetMessage(field, "float"));
	}

	@Override
	public float getFloatStatic() {
		throw new IllegalArgumentException(getGetMessage(field, "float"));
	}

	@Override
	public void setFloat(Object object, float value) {
		throw new IllegalArgumentException(getSetMessage(field, "float", String.valueOf(value)));
	}

	@Override
	public void setFloatStatic(float value) {
		throw new IllegalArgumentException(getSetMessage(field, "float", String.valueOf(value)));
	}

	@Override
	public double getDouble(Object object) {
		throw new IllegalArgumentException(getGetMessage(field, "double"));
	}

	@Override
	public double getDoubleStatic() {
		throw new IllegalArgumentException(getGetMessage(field, "double"));
	}

	@Override
	public void setDouble(Object object, double value) {
		throw new IllegalArgumentException(getSetMessage(field, "double", String.valueOf(value)));
	}

	@Override
	public void setDoubleStatic(double value) {
		throw new IllegalArgumentException(getSetMessage(field, "double", String.valueOf(value)));
	}

	@Override
	public Field getField() {
		return field;
	}

	@Override
	public boolean equals(Object obj) {
		return obj == this || obj instanceof FieldAccessor other && other.getField().equals(field);
	}

	@Override
	public int hashCode() {
		return field.hashCode();
	}

	@Override
	public String toString() {
		return getClass().getSimpleName() + '{' + field.toString() + '}';
	}
}

class UnsafeObjectFieldAccessor extends UnsafeFieldAccessor {
	public UnsafeObjectFieldAccessor(Field f) {
		super(f);
	}

	@Override
	public <T> T get(Object object) {
		return getObject(object);
	}

	@Override
	public void set(Object object, Object value) {
		setObject(object, value);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T getObject(Object object) {
		ensureObject(object);
		return (T) unsafe.getReference(object, offset);
	}

	@Override
	public void setObject(Object object, Object value) {
		ensureObject(object);
		ensureValue(value);
		unsafe.putReference(object, offset, value);
	}
}

class UnsafeBooleanFieldAccessor extends UnsafeFieldAccessor {
	public UnsafeBooleanFieldAccessor(Field f) {
		super(f);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T get(Object object) {
		return (T) Boolean.valueOf(getBoolean(object));
	}

	@Override
	public void set(Object object, Object value) {
		setBoolean(object, (boolean) value);
	}

	@Override
	public boolean getBoolean(Object object) {
		ensureObject(object);
		return unsafe.getBoolean(object, offset);
	}

	@Override
	public void setBoolean(Object object, boolean value) {
		ensureObject(object);
		unsafe.putBoolean(object, offset, value);
	}
}

class UnsafeByteFieldAccessor extends UnsafeFieldAccessor {
	public UnsafeByteFieldAccessor(Field f) {
		super(f);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T get(Object object) {
		return (T) Byte.valueOf(getByte(object));
	}

	@Override
	public void set(Object object, Object value) {
		setByte(object, ((Number) value).byteValue());
	}

	@Override
	public byte getByte(Object object) {
		ensureObject(object);
		return unsafe.getByte(object, offset);
	}

	@Override
	public void setByte(Object object, byte value) {
		ensureObject(object);
		unsafe.putByte(object, offset, value);
	}

	@Override
	public short getShort(Object object) {
		return getByte(object);
	}

	@Override
	public int getInt(Object object) {
		return getByte(object);
	}

	@Override
	public long getLong(Object object) {
		return getByte(object);
	}

	@Override
	public float getFloat(Object object) {
		return getByte(object);
	}

	@Override
	public double getDouble(Object object) {
		return getByte(object);
	}
}

class UnsafeCharFieldAccessor extends UnsafeFieldAccessor {
	public UnsafeCharFieldAccessor(Field f) {
		super(f);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T get(Object object) {
		return (T) Character.valueOf(getChar(object));
	}

	@Override
	public void set(Object object, Object value) {
		setChar(object, (char) value);
	}

	@Override
	public char getChar(Object object) {
		ensureObject(object);
		return unsafe.getChar(object, offset);
	}

	@Override
	public void setChar(Object object, char value) {
		ensureObject(object);
		unsafe.putChar(object, offset, value);
	}

	@Override
	public int getInt(Object object) {
		return getChar(object);
	}

	@Override
	public long getLong(Object object) {
		return getChar(object);
	}

	@Override
	public float getFloat(Object object) {
		return getChar(object);
	}

	@Override
	public double getDouble(Object object) {
		return getChar(object);
	}
}

class UnsafeShortFieldAccessor extends UnsafeFieldAccessor {
	public UnsafeShortFieldAccessor(Field f) {
		super(f);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T get(Object object) {
		return (T) Short.valueOf(getShort(object));
	}

	@Override
	public void set(Object object, Object value) {
		setShort(object, ((Number) value).shortValue());
	}

	@Override
	public void setByte(Object object, byte value) {
		setShort(object, value);
	}

	@Override
	public short getShort(Object object) {
		ensureObject(object);
		return unsafe.getShort(object, offset);
	}

	@Override
	public void setShort(Object object, short value) {
		ensureObject(object);
		unsafe.putShort(object, offset, value);
	}

	@Override
	public int getInt(Object object) {
		return getShort(object);
	}

	@Override
	public long getLong(Object object) {
		return getShort(object);
	}

	@Override
	public float getFloat(Object object) {
		return getShort(object);
	}

	@Override
	public double getDouble(Object object) {
		return getShort(object);
	}
}

class UnsafeIntFieldAccessor extends UnsafeFieldAccessor {
	public UnsafeIntFieldAccessor(Field f) {
		super(f);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T get(Object object) {
		return (T) Integer.valueOf(getInt(object));
	}

	@Override
	public void set(Object object, Object value) {
		setInt(object, ((Number) value).intValue());
	}

	@Override
	public void setByte(Object object, byte value) {
		setInt(object, value);
	}

	@Override
	public void setChar(Object object, char value) {
		setInt(object, value);
	}

	@Override
	public void setShort(Object object, short value) {
		setInt(object, value);
	}

	@Override
	public int getInt(Object object) {
		ensureObject(object);
		return unsafe.getInt(object, offset);
	}

	@Override
	public void setInt(Object object, int value) {
		ensureObject(object);
		unsafe.putInt(object, offset, value);
	}

	@Override
	public long getLong(Object object) {
		return getInt(object);
	}

	@Override
	public float getFloat(Object object) {
		return getInt(object);
	}

	@Override
	public double getDouble(Object object) {
		return getInt(object);
	}
}

class UnsafeLongFieldAccessor extends UnsafeFieldAccessor {
	public UnsafeLongFieldAccessor(Field f) {
		super(f);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T get(Object object) {
		return (T) Long.valueOf(getLong(object));
	}

	@Override
	public void set(Object object, Object value) {
		setLong(object, ((Number) value).longValue());
	}

	@Override
	public void setByte(Object object, byte value) {
		setLong(object, value);
	}

	@Override
	public void setChar(Object object, char value) {
		setLong(object, value);
	}

	@Override
	public void setShort(Object object, short value) {
		setLong(object, value);
	}

	@Override
	public void setInt(Object object, int value) {
		setLong(object, value);
	}

	@Override
	public long getLong(Object object) {
		ensureObject(object);
		return unsafe.getLong(object, offset);
	}

	@Override
	public void setLong(Object object, long value) {
		ensureObject(object);
		unsafe.putLong(object, offset, value);
	}

	@Override
	public float getFloat(Object object) {
		return getLong(object);
	}

	@Override
	public double getDouble(Object object) {
		return getLong(object);
	}
}

class UnsafeFloatFieldAccessor extends UnsafeFieldAccessor {
	public UnsafeFloatFieldAccessor(Field f) {
		super(f);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T get(Object object) {
		return (T) Float.valueOf(getFloat(object));
	}

	@Override
	public void set(Object object, Object value) {
		setFloat(object, ((Number) value).floatValue());
	}

	@Override
	public void setByte(Object object, byte value) {
		setFloat(object, value);
	}

	@Override
	public void setChar(Object object, char value) {
		setFloat(object, value);
	}

	@Override
	public void setShort(Object object, short value) {
		setFloat(object, value);
	}

	@Override
	public void setInt(Object object, int value) {
		setFloat(object, value);
	}

	@Override
	public void setLong(Object object, long value) {
		setFloat(object, value);
	}

	@Override
	public float getFloat(Object object) {
		ensureObject(object);
		return unsafe.getFloat(object, offset);
	}

	@Override
	public void setFloat(Object object, float value) {
		ensureObject(object);
		unsafe.putFloat(object, offset, value);
	}

	@Override
	public double getDouble(Object object) {
		return getFloat(object);
	}
}

class UnsafeDoubleFieldAccessor extends UnsafeFieldAccessor {
	public UnsafeDoubleFieldAccessor(Field f) {
		super(f);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T get(Object object) {
		return (T) Double.valueOf(getDouble(object));
	}

	@Override
	public void set(Object object, Object value) {
		setDouble(object, ((Number) value).doubleValue());
	}

	@Override
	public void setByte(Object object, byte value) {
		setDouble(object, value);
	}

	@Override
	public void setChar(Object object, char value) {
		setDouble(object, value);
	}

	@Override
	public void setShort(Object object, short value) {
		setDouble(object, value);
	}

	@Override
	public void setInt(Object object, int value) {
		setDouble(object, value);
	}

	@Override
	public void setLong(Object object, long value) {
		setDouble(object, value);
	}

	@Override
	public void setFloat(Object object, float value) {
		setDouble(object, value);
	}

	@Override
	public double getDouble(Object object) {
		ensureObject(object);
		return unsafe.getDouble(object, offset);
	}

	@Override
	public void setDouble(Object object, double value) {
		ensureObject(object);
		unsafe.putDouble(object, offset, value);
	}
}

class UnsafeQualifiedObjectFieldAccessor extends UnsafeObjectFieldAccessor {
	public UnsafeQualifiedObjectFieldAccessor(Field f) {
		super(f);
	}

	@Override
	public <T> T get(Object object) {
		return getObject(object);
	}

	@Override
	public void set(Object object, Object value) {
		setObject(object, value);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T getObject(Object object) {
		ensureObject(object);
		return (T) unsafe.getReferenceVolatile(object, offset);
	}

	@Override
	public void setObject(Object object, Object value) {
		ensureObject(object);
		ensureValue(value);
		unsafe.putReferenceVolatile(object, offset, value);
	}
}

class UnsafeQualifiedBooleanFieldAccessor extends UnsafeBooleanFieldAccessor {
	public UnsafeQualifiedBooleanFieldAccessor(Field f) {
		super(f);
	}

	@Override
	public boolean getBoolean(Object object) {
		ensureObject(object);
		return unsafe.getBooleanVolatile(object, offset);
	}

	@Override
	public void setBoolean(Object object, boolean value) {
		ensureObject(object);
		unsafe.putBooleanVolatile(object, offset, value);
	}
}

class UnsafeQualifiedByteFieldAccessor extends UnsafeByteFieldAccessor {
	public UnsafeQualifiedByteFieldAccessor(Field f) {
		super(f);
	}

	@Override
	public byte getByte(Object object) {
		ensureObject(object);
		return unsafe.getByteVolatile(object, offset);
	}

	@Override
	public void setByte(Object object, byte value) {
		ensureObject(object);
		unsafe.putByteVolatile(object, offset, value);
	}
}

class UnsafeQualifiedCharFieldAccessor extends UnsafeCharFieldAccessor {
	public UnsafeQualifiedCharFieldAccessor(Field f) {
		super(f);
	}

	@Override
	public char getChar(Object object) {
		ensureObject(object);
		return unsafe.getCharVolatile(object, offset);
	}

	@Override
	public void setChar(Object object, char value) {
		ensureObject(object);
		unsafe.putCharVolatile(object, offset, value);
	}
}

class UnsafeQualifiedShortFieldAccessor extends UnsafeShortFieldAccessor {
	public UnsafeQualifiedShortFieldAccessor(Field f) {
		super(f);
	}

	@Override
	public short getShort(Object object) {
		ensureObject(object);
		return unsafe.getShortVolatile(object, offset);
	}

	@Override
	public void setShort(Object object, short value) {
		ensureObject(object);
		unsafe.putShortVolatile(object, offset, value);
	}
}

class UnsafeQualifiedIntFieldAccessor extends UnsafeIntFieldAccessor {
	public UnsafeQualifiedIntFieldAccessor(Field f) {
		super(f);
	}

	@Override
	public int getInt(Object object) {
		ensureObject(object);
		return unsafe.getIntVolatile(object, offset);
	}

	@Override
	public void setInt(Object object, int value) {
		ensureObject(object);
		unsafe.putIntVolatile(object, offset, value);
	}
}

class UnsafeQualifiedLongFieldAccessor extends UnsafeLongFieldAccessor {
	public UnsafeQualifiedLongFieldAccessor(Field f) {
		super(f);
	}

	@Override
	public long getLong(Object object) {
		ensureObject(object);
		return unsafe.getLongVolatile(object, offset);
	}

	@Override
	public void setLong(Object object, long value) {
		ensureObject(object);
		unsafe.putLongVolatile(object, offset, value);
	}
}

class UnsafeQualifiedFloatFieldAccessor extends UnsafeFloatFieldAccessor {
	public UnsafeQualifiedFloatFieldAccessor(Field f) {
		super(f);
	}

	@Override
	public float getFloat(Object object) {
		ensureObject(object);
		return unsafe.getFloatVolatile(object, offset);
	}

	@Override
	public void setFloat(Object object, float value) {
		ensureObject(object);
		unsafe.putFloatVolatile(object, offset, value);
	}
}

class UnsafeQualifiedDoubleFieldAccessor extends UnsafeDoubleFieldAccessor {
	public UnsafeQualifiedDoubleFieldAccessor(Field f) {
		super(f);
	}

	@Override
	public double getDouble(Object object) {
		ensureObject(object);
		return unsafe.getDoubleVolatile(object, offset);
	}

	@Override
	public void setDouble(Object object, double value) {
		ensureObject(object);
		unsafe.putDoubleVolatile(object, offset, value);
	}
}

abstract class UnsafeStaticFieldAccessor extends UnsafeFieldAccessor {
	protected final Object base;

	protected UnsafeStaticFieldAccessor(Field f) {
		super(f);

		if (Modifier.isStatic(f.getModifiers())) base = unsafe.staticFieldBase(f);
		else throw new IllegalArgumentException(f.toString());
	}
}

class UnsafeStaticObjectFieldAccessor extends UnsafeStaticFieldAccessor {
	public UnsafeStaticObjectFieldAccessor(Field f) {
		super(f);
	}

	@Override
	public <T> T getStatic() {
		return getObjectStatic();
	}

	@Override
	public void setStatic(Object value) {
		setObjectStatic(value);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T getObjectStatic() {
		return (T) unsafe.getReference(base, offset);
	}

	@Override
	public void setObjectStatic(Object value) {
		ensureValue(value);
		unsafe.putReference(base, offset, value);
	}
}

class UnsafeStaticBooleanFieldAccessor extends UnsafeStaticFieldAccessor {
	public UnsafeStaticBooleanFieldAccessor(Field f) {
		super(f);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T getStatic() {
		return (T) Boolean.valueOf(getBooleanStatic());
	}

	@Override
	public void setStatic(Object value) {
		setBooleanStatic((boolean) value);
	}

	@Override
	public boolean getBooleanStatic() {
		return unsafe.getBoolean(base, offset);
	}

	@Override
	public void setBooleanStatic(boolean value) {
		unsafe.putBoolean(base, offset, value);
	}
}

class UnsafeStaticByteFieldAccessor extends UnsafeStaticFieldAccessor {
	public UnsafeStaticByteFieldAccessor(Field f) {
		super(f);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T getStatic() {
		return (T) Byte.valueOf(getByteStatic());
	}

	@Override
	public void setStatic(Object value) {
		setByteStatic(((Number) value).byteValue());
	}

	@Override
	public byte getByteStatic() {
		return unsafe.getByte(base, offset);
	}

	@Override
	public void setByteStatic(byte value) {
		unsafe.putByte(base, offset, value);
	}

	@Override
	public short getShortStatic() {
		return getByteStatic();
	}

	@Override
	public int getIntStatic() {
		return getByteStatic();
	}

	@Override
	public long getLongStatic() {
		return getByteStatic();
	}

	@Override
	public float getFloatStatic() {
		return getByteStatic();
	}

	@Override
	public double getDoubleStatic() {
		return getByteStatic();
	}
}

class UnsafeStaticCharFieldAccessor extends UnsafeStaticFieldAccessor {
	public UnsafeStaticCharFieldAccessor(Field f) {
		super(f);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T getStatic() {
		return (T) Character.valueOf(getCharStatic());
	}

	@Override
	public void setStatic(Object value) {
		setCharStatic((char) value);
	}

	@Override
	public char getCharStatic() {
		return unsafe.getChar(base, offset);
	}

	@Override
	public void setCharStatic(char value) {
		unsafe.putChar(base, offset, value);
	}

	@Override
	public int getIntStatic() {
		return getCharStatic();
	}

	@Override
	public long getLongStatic() {
		return getCharStatic();
	}

	@Override
	public float getFloatStatic() {
		return getCharStatic();
	}

	@Override
	public double getDoubleStatic() {
		return getCharStatic();
	}
}

class UnsafeStaticShortFieldAccessor extends UnsafeStaticFieldAccessor {
	public UnsafeStaticShortFieldAccessor(Field f) {
		super(f);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T getStatic() {
		return (T) Short.valueOf(getShortStatic());
	}

	@Override
	public void setStatic(Object value) {
		setShortStatic(((Number) value).shortValue());
	}

	@Override
	public void setByteStatic(byte value) {
		setShortStatic(value);
	}

	@Override
	public short getShortStatic() {
		return unsafe.getShort(base, offset);
	}

	@Override
	public void setShortStatic(short value) {
		unsafe.putShort(base, offset, value);
	}

	@Override
	public int getIntStatic() {
		return getShortStatic();
	}

	@Override
	public long getLongStatic() {
		return getShortStatic();
	}

	@Override
	public float getFloatStatic() {
		return getShortStatic();
	}

	@Override
	public double getDoubleStatic() {
		return getShortStatic();
	}
}

class UnsafeStaticIntFieldAccessor extends UnsafeStaticFieldAccessor {
	public UnsafeStaticIntFieldAccessor(Field f) {
		super(f);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T getStatic() {
		return (T) Integer.valueOf(getIntStatic());
	}

	@Override
	public void setStatic(Object value) {
		setIntStatic(((Number) value).intValue());
	}

	@Override
	public void setByteStatic(byte value) {
		setIntStatic(value);
	}

	@Override
	public void setCharStatic(char value) {
		setIntStatic(value);
	}

	@Override
	public void setShortStatic(short value) {
		setIntStatic(value);
	}

	@Override
	public int getIntStatic() {
		return unsafe.getInt(base, offset);
	}

	@Override
	public void setIntStatic(int value) {
		unsafe.putInt(base, offset, value);
	}

	@Override
	public long getLongStatic() {
		return getIntStatic();
	}

	@Override
	public float getFloatStatic() {
		return getIntStatic();
	}

	@Override
	public double getDoubleStatic() {
		return getIntStatic();
	}
}

class UnsafeStaticLongFieldAccessor extends UnsafeStaticFieldAccessor {
	public UnsafeStaticLongFieldAccessor(Field f) {
		super(f);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T getStatic() {
		return (T) Long.valueOf(getLongStatic());
	}

	@Override
	public void setStatic(Object value) {
		setLongStatic(((Number) value).longValue());
	}

	@Override
	public void setByteStatic(byte value) {
		setLongStatic(value);
	}

	@Override
	public void setCharStatic(char value) {
		setLongStatic(value);
	}

	@Override
	public void setShortStatic(short value) {
		setLongStatic(value);
	}

	@Override
	public void setIntStatic(int value) {
		setLongStatic(value);
	}

	@Override
	public long getLongStatic() {
		return unsafe.getLong(base, offset);
	}

	@Override
	public void setLongStatic(long value) {
		unsafe.putLong(base, offset, value);
	}

	@Override
	public float getFloatStatic() {
		return getLongStatic();
	}

	@Override
	public double getDoubleStatic() {
		return getLongStatic();
	}
}

class UnsafeStaticFloatFieldAccessor extends UnsafeStaticFieldAccessor {
	public UnsafeStaticFloatFieldAccessor(Field f) {
		super(f);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T getStatic() {
		return (T) Float.valueOf(getFloatStatic());
	}

	@Override
	public void setStatic(Object value) {
		setFloatStatic(((Number) value).floatValue());
	}

	@Override
	public void setByteStatic(byte value) {
		setFloatStatic(value);
	}

	@Override
	public void setCharStatic(char value) {
		setFloatStatic(value);
	}

	@Override
	public void setShortStatic(short value) {
		setFloatStatic(value);
	}

	@Override
	public void setIntStatic(int value) {
		setFloatStatic(value);
	}

	@Override
	public void setLongStatic(long value) {
		setFloatStatic(value);
	}

	@Override
	public float getFloatStatic() {
		return unsafe.getFloat(base, offset);
	}

	@Override
	public void setFloatStatic(float value) {
		unsafe.putFloat(base, offset, value);
	}

	@Override
	public double getDoubleStatic() {
		return getFloatStatic();
	}
}

class UnsafeStaticDoubleFieldAccessor extends UnsafeStaticFieldAccessor {
	public UnsafeStaticDoubleFieldAccessor(Field f) {
		super(f);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T getStatic() {
		return (T) Double.valueOf(getDoubleStatic());
	}

	@Override
	public void setStatic(Object value) {
		setDoubleStatic(((Number) value).doubleValue());
	}

	@Override
	public void setByteStatic(byte value) {
		setDoubleStatic(value);
	}

	@Override
	public void setCharStatic(char value) {
		setDoubleStatic(value);
	}

	@Override
	public void setShortStatic(short value) {
		setDoubleStatic(value);
	}

	@Override
	public void setIntStatic(int value) {
		setDoubleStatic(value);
	}

	@Override
	public void setLongStatic(long value) {
		setDoubleStatic(value);
	}

	@Override
	public void setFloatStatic(float value) {
		setDoubleStatic(value);
	}

	@Override
	public double getDoubleStatic() {
		return unsafe.getDouble(base, offset);
	}

	@Override
	public void setDoubleStatic(double value) {
		unsafe.putDouble(base, offset, value);
	}
}

class UnsafeQualifiedStaticObjectFieldAccessor extends UnsafeStaticObjectFieldAccessor {
	public UnsafeQualifiedStaticObjectFieldAccessor(Field f) {
		super(f);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T getObjectStatic() {
		return (T) unsafe.getReferenceVolatile(base, offset);
	}

	@Override
	public void setObjectStatic(Object value) {
		ensureValue(value);
		unsafe.putReferenceVolatile(base, offset, value);
	}
}

class UnsafeQualifiedStaticBooleanFieldAccessor extends UnsafeStaticBooleanFieldAccessor {
	public UnsafeQualifiedStaticBooleanFieldAccessor(Field f) {
		super(f);
	}

	@Override
	public boolean getBooleanStatic() {
		return unsafe.getBooleanVolatile(base, offset);
	}

	@Override
	public void setBooleanStatic(boolean value) {
		unsafe.putBooleanVolatile(base, offset, value);
	}
}

class UnsafeQualifiedStaticByteFieldAccessor extends UnsafeStaticByteFieldAccessor {
	public UnsafeQualifiedStaticByteFieldAccessor(Field f) {
		super(f);
	}

	@Override
	public byte getByteStatic() {
		return unsafe.getByteVolatile(base, offset);
	}

	@Override
	public void setByteStatic(byte value) {
		unsafe.putByteVolatile(base, offset, value);
	}
}

class UnsafeQualifiedStaticCharFieldAccessor extends UnsafeStaticCharFieldAccessor {
	public UnsafeQualifiedStaticCharFieldAccessor(Field f) {
		super(f);
	}

	@Override
	public short getShortStatic() {
		return unsafe.getShortVolatile(base, offset);
	}

	@Override
	public void setShortStatic(short value) {
		unsafe.putShortVolatile(base, offset, value);
	}
}

class UnsafeQualifiedStaticShortFieldAccessor extends UnsafeStaticShortFieldAccessor {
	public UnsafeQualifiedStaticShortFieldAccessor(Field f) {
		super(f);
	}

	@Override
	public short getShortStatic() {
		return unsafe.getShortVolatile(base, offset);
	}

	@Override
	public void setShortStatic(short value) {
		unsafe.putShortVolatile(base, offset, value);
	}
}

class UnsafeQualifiedStaticIntFieldAccessor extends UnsafeStaticShortFieldAccessor {
	public UnsafeQualifiedStaticIntFieldAccessor(Field f) {
		super(f);
	}

	@Override
	public int getIntStatic() {
		return unsafe.getIntVolatile(base, offset);
	}

	@Override
	public void setIntStatic(int value) {
		unsafe.putIntVolatile(base, offset, value);
	}
}

class UnsafeQualifiedStaticLongFieldAccessor extends UnsafeStaticLongFieldAccessor {
	public UnsafeQualifiedStaticLongFieldAccessor(Field f) {
		super(f);
	}

	@Override
	public long getLongStatic() {
		return unsafe.getLongVolatile(base, offset);
	}

	@Override
	public void setLongStatic(long value) {
		unsafe.putLongVolatile(base, offset, value);
	}
}

class UnsafeQualifiedStaticFloatFieldAccessor extends UnsafeStaticFloatFieldAccessor {
	public UnsafeQualifiedStaticFloatFieldAccessor(Field f) {
		super(f);
	}

	@Override
	public float getFloatStatic() {
		return unsafe.getFloatVolatile(base, offset);
	}

	@Override
	public void setFloatStatic(float value) {
		unsafe.putFloatVolatile(base, offset, value);
	}
}

class UnsafeQualifiedStaticDoubleFieldAccessor extends UnsafeStaticDoubleFieldAccessor {
	public UnsafeQualifiedStaticDoubleFieldAccessor(Field f) {
		super(f);
	}

	@Override
	public double getDoubleStatic() {
		return unsafe.getDoubleVolatile(base, offset);
	}

	@Override
	public void setDoubleStatic(double value) {
		unsafe.putDoubleVolatile(base, offset, value);
	}
}