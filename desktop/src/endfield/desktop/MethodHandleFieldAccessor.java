package endfield.desktop;

import endfield.util.FieldAccessor;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodType;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import static endfield.desktop.DesktopImpl.lookup;
import static endfield.desktop.Unsafer.getGetMessage;
import static endfield.desktop.Unsafer.getSetMessage;

public abstract class MethodHandleFieldAccessor implements FieldAccessor {
	protected final Field field;
	protected final MethodHandle getter, setter;

	protected MethodHandleFieldAccessor(Field f) {
		try {
			field = f;
			Class<?> decl = f.getDeclaringClass();
			String name = f.getName();
			Class<?> type = f.getType(), rtype = type.isPrimitive() ? type : Object.class;

			if (Modifier.isStatic(f.getModifiers())) {
				getter = lookup.findStaticGetter(decl, name, type).asType(MethodType.methodType(rtype));
				setter = lookup.findStaticSetter(decl, name, type).asType(MethodType.methodType(void.class, rtype));
			} else {
				getter = lookup.findGetter(decl, name, type).asType(MethodType.methodType(rtype));
				setter = lookup.findSetter(decl, name, type).asType(MethodType.methodType(void.class, Object.class, rtype));
			}
		} catch (NoSuchFieldException | IllegalAccessException e) {
			throw new RuntimeException(e);
		}
	}

	public static FieldAccessor getMethodHandleFieldAccessor(Field f) {
		Class<?> type = f.getType();

		if (Modifier.isStatic(f.getModifiers())) {
			if (type.isPrimitive()) {
				if (type == boolean.class) return new MethodHandleStaticBooleanFieldAccessor(f);
				else if (type == byte.class) return new MethodHandleStaticByteFieldAccessor(f);
				else if (type == char.class) return new MethodHandleStaticCharFieldAccessor(f);
				else if (type == short.class) return new MethodHandleStaticShortFieldAccessor(f);
				else if (type == int.class) return new MethodHandleStaticIntFieldAccessor(f);
				else if (type == long.class) return new MethodHandleStaticLongFieldAccessor(f);
				else if (type == float.class) return new MethodHandleStaticFloatFieldAccessor(f);
				else if (type == double.class) return new MethodHandleStaticDoubleFieldAccessor(f);
				else throw new IllegalArgumentException("unknown type of field " + f);
			} else return new MethodHandleStaticObjectFieldAccessor(f);
		} else {
			if (type.isPrimitive()) {
				if (type == boolean.class) return new MethodHandleBooleanFieldAccessor(f);
				else if (type == byte.class) return new MethodHandleByteFieldAccessor(f);
				else if (type == char.class) return new MethodHandleCharFieldAccessor(f);
				else if (type == short.class) return new MethodHandleShortFieldAccessor(f);
				else if (type == int.class) return new MethodHandleIntFieldAccessor(f);
				else if (type == long.class) return new MethodHandleLongFieldAccessor(f);
				else if (type == float.class) return new MethodHandleFloatFieldAccessor(f);
				else if (type == double.class) return new MethodHandleDoubleFieldAccessor(f);
				else throw new IllegalArgumentException("unknown type of field " + f);
			} else return new MethodHandleObjectFieldAccessor(f);
		}
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
		return obj == this || obj instanceof MethodHandleFieldAccessor other && other.getField().equals(field);
	}

	@Override
	public int hashCode() {
		return field.hashCode();
	}

	@Override
	public String toString() {
		return getClass().getSimpleName() + ": " + field.toString();
	}
}

class MethodHandleObjectFieldAccessor extends MethodHandleFieldAccessor {
	public MethodHandleObjectFieldAccessor(Field f) {
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
		try {
			return (T) getter.invokeExact(object);
		} catch (Throwable e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void setObject(Object object, Object value) {
		try {
			setter.invokeExact(object, value);
		} catch (Throwable e) {
			throw new RuntimeException(e);
		}
	}
}

class MethodHandleBooleanFieldAccessor extends MethodHandleFieldAccessor {
	public MethodHandleBooleanFieldAccessor(Field f) {
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
		try {
			return (boolean) getter.invokeExact();
		} catch (Throwable e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void setBoolean(Object object, boolean value) {
		try {
			setter.invokeExact(object, value);
		} catch (Throwable e) {
			throw new RuntimeException(e);
		}
	}
}

class MethodHandleByteFieldAccessor extends MethodHandleFieldAccessor {
	public MethodHandleByteFieldAccessor(Field f) {
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
		try {
			return (byte) getter.invokeExact();
		} catch (Throwable e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void setByte(Object object, byte value) {
		try {
			setter.invokeExact(object, value);
		} catch (Throwable e) {
			throw new RuntimeException(e);
		}
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

class MethodHandleCharFieldAccessor extends MethodHandleFieldAccessor {
	public MethodHandleCharFieldAccessor(Field f) {
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
		try {
			return (char) getter.invokeExact();
		} catch (Throwable e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void setChar(Object object, char value) {
		try {
			setter.invokeExact(object, value);
		} catch (Throwable e) {
			throw new RuntimeException(e);
		}
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

class MethodHandleShortFieldAccessor extends MethodHandleFieldAccessor {
	public MethodHandleShortFieldAccessor(Field f) {
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
		try {
			return (short) getter.invokeExact();
		} catch (Throwable e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void setShort(Object object, short value) {
		try {
			setter.invokeExact(object, value);
		} catch (Throwable e) {
			throw new RuntimeException(e);
		}
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

class MethodHandleIntFieldAccessor extends MethodHandleFieldAccessor {
	public MethodHandleIntFieldAccessor(Field f) {
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
		try {
			return (int) getter.invokeExact(object);
		} catch (Throwable e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void setInt(Object object, int value) {
		try {
			setter.invokeExact(object, value);
		} catch (Throwable e) {
			throw new RuntimeException(e);
		}
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

class MethodHandleLongFieldAccessor extends MethodHandleFieldAccessor {
	public MethodHandleLongFieldAccessor(Field f) {
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
	public void setShort(Object object, short value) {
		setLong(object, value);
	}

	@Override
	public void setChar(Object object, char value) {
		setLong(object, value);
	}

	@Override
	public void setInt(Object object, int value) {
		setLong(object, value);
	}

	@Override
	public long getLong(Object object) {
		try {
			return (long) getter.invokeExact(object);
		} catch (Throwable e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void setLong(Object object, long value) {
		try {
			setter.invokeExact(object, value);
		} catch (Throwable e) {
			throw new RuntimeException(e);
		}
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

class MethodHandleFloatFieldAccessor extends MethodHandleFieldAccessor {
	public MethodHandleFloatFieldAccessor(Field f) {
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
		try {
			return (float) getter.invokeExact(object);
		} catch (Throwable e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void setFloat(Object object, float value) {
		try {
			setter.invokeExact(object, value);
		} catch (Throwable e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public double getDouble(Object object) {
		return getFloat(object);
	}
}

class MethodHandleDoubleFieldAccessor extends MethodHandleFieldAccessor {
	public MethodHandleDoubleFieldAccessor(Field f) {
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
		try {
			return (double) getter.invokeExact(object);
		} catch (Throwable e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void setDouble(Object object, double value) {
		try {
			setter.invokeExact(object, value);
		} catch (Throwable e) {
			throw new RuntimeException(e);
		}
	}
}

class MethodHandleStaticObjectFieldAccessor extends MethodHandleFieldAccessor {
	public MethodHandleStaticObjectFieldAccessor(Field f) {
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
		try {
			return (T) getter.invokeExact();
		} catch (Throwable e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void setObjectStatic(Object value) {
		try {
			setter.invokeExact(value);
		} catch (Throwable e) {
			throw new RuntimeException(e);
		}
	}
}

class MethodHandleStaticBooleanFieldAccessor extends MethodHandleFieldAccessor {
	public MethodHandleStaticBooleanFieldAccessor(Field f) {
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
		try {
			return (boolean) getter.invokeExact();
		} catch (Throwable e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void setBooleanStatic(boolean value) {
		try {
			setter.invokeExact(value);
		} catch (Throwable e) {
			throw new RuntimeException(e);
		}
	}
}

class MethodHandleStaticByteFieldAccessor extends MethodHandleFieldAccessor {
	public MethodHandleStaticByteFieldAccessor(Field f) {
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
		try {
			return (byte) getter.invokeExact();
		} catch (Throwable e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void setByteStatic(byte value) {
		try {
			setter.invokeExact(value);
		} catch (Throwable e) {
			throw new RuntimeException(e);
		}
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

class MethodHandleStaticCharFieldAccessor extends MethodHandleFieldAccessor {
	public MethodHandleStaticCharFieldAccessor(Field f) {
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
		try {
			return (char) getter.invokeExact();
		} catch (Throwable e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void setCharStatic(char value) {
		try {
			setter.invokeExact(value);
		} catch (Throwable e) {
			throw new RuntimeException(e);
		}
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

class MethodHandleStaticShortFieldAccessor extends MethodHandleFieldAccessor {
	public MethodHandleStaticShortFieldAccessor(Field f) {
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
		try {
			return (short) getter.invokeExact();
		} catch (Throwable e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void setShortStatic(short value) {
		try {
			setter.invokeExact(value);
		} catch (Throwable e) {
			throw new RuntimeException(e);
		}
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

class MethodHandleStaticIntFieldAccessor extends MethodHandleFieldAccessor {
	public MethodHandleStaticIntFieldAccessor(Field f) {
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
		try {
			return (int) getter.invokeExact();
		} catch (Throwable e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void setIntStatic(int value) {
		try {
			setter.invokeExact(value);
		} catch (Throwable e) {
			throw new RuntimeException(e);
		}
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

class MethodHandleStaticLongFieldAccessor extends MethodHandleFieldAccessor {
	public MethodHandleStaticLongFieldAccessor(Field f) {
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
		try {
			return (long) getter.invokeExact();
		} catch (Throwable e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void setLongStatic(long value) {
		try {
			setter.invokeExact(value);
		} catch (Throwable e) {
			throw new RuntimeException(e);
		}
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

class MethodHandleStaticFloatFieldAccessor extends MethodHandleFieldAccessor {
	public MethodHandleStaticFloatFieldAccessor(Field f) {
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
		try {
			return (float) getter.invokeExact();
		} catch (Throwable e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void setFloatStatic(float value) {
		try {
			setter.invokeExact(value);
		} catch (Throwable e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public double getDoubleStatic() {
		return getFloatStatic();
	}
}

class MethodHandleStaticDoubleFieldAccessor extends MethodHandleFieldAccessor {
	public MethodHandleStaticDoubleFieldAccessor(Field f) {
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
		try {
			return (double) getter.invokeExact();
		} catch (Throwable e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void setDoubleStatic(double value) {
		try {
			setter.invokeExact(value);
		} catch (Throwable e) {
			throw new RuntimeException(e);
		}
	}
}