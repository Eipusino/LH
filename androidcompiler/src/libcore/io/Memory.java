package libcore.io;

import java.nio.ByteOrder;

@SuppressWarnings("unused")
public final class Memory {
	private Memory() {}

	public static native void unsafeBulkGet(Object dst, int dstOffset, int byteCount, byte[] src, int srcOffset, int sizeofElements, boolean swap);

	public static native void unsafeBulkPut(byte[] dst, int dstOffset, int byteCount, Object src, int srcOffset, int sizeofElements, boolean swap);

	public static int peekInt(byte[] src, int offset, ByteOrder order) {
		throw new RuntimeException("Stub!");
	}

	public static long peekLong(byte[] src, int offset, ByteOrder order) {
		throw new RuntimeException("Stub!");
	}

	public static short peekShort(byte[] src, int offset, ByteOrder order) {
		throw new RuntimeException("Stub!");
	}

	public static void pokeInt(byte[] dst, int offset, int value, ByteOrder order) {
		throw new RuntimeException("Stub!");
	}

	public static void pokeLong(byte[] dst, int offset, long value, ByteOrder order) {
		throw new RuntimeException("Stub!");
	}

	public static void pokeShort(byte[] dst, int offset, short value, ByteOrder order) {
		throw new RuntimeException("Stub!");
	}

	public static native void memmove(Object dstObject, int dstOffset, Object srcObject, int srcOffset, long byteCount);

	public static native byte peekByte(long address);

	public static int peekInt(long address, boolean swap) {
		throw new RuntimeException("Stub!");
	}

	public static long peekLong(long address, boolean swap) {
		throw new RuntimeException("Stub!");
	}

	public static short peekShort(long address, boolean swap) {
		throw new RuntimeException("Stub!");
	}

	public static native void peekByteArray(long address, byte[] dst, int dstOffset, int byteCount);

	public static native void peekCharArray(long address, char[] dst, int dstOffset, int charCount, boolean swap);

	public static native void peekDoubleArray(long address, double[] dst, int dstOffset, int doubleCount, boolean swap);

	public static native void peekFloatArray(long address, float[] dst, int dstOffset, int floatCount, boolean swap);

	public static native void peekIntArray(long address, int[] dst, int dstOffset, int intCount, boolean swap);

	public static native void peekLongArray(long address, long[] dst, int dstOffset, int longCount, boolean swap);

	public static native void peekShortArray(long address, short[] dst, int dstOffset, int shortCount, boolean swap);

	public static native void pokeByte(long address, byte value);

	public static void pokeInt(long address, int value, boolean swap) {
		throw new RuntimeException("Stub!");
	}

	public static void pokeLong(long address, long value, boolean swap) {
		throw new RuntimeException("Stub!");
	}

	public static void pokeShort(long address, short value, boolean swap) {
		throw new RuntimeException("Stub!");
	}

	public static native void pokeByteArray(long address, byte[] src, int offset, int count);

	public static native void pokeCharArray(long address, char[] src, int offset, int count, boolean swap);

	public static native void pokeDoubleArray(long address, double[] src, int offset, int count, boolean swap);

	public static native void pokeFloatArray(long address, float[] src, int offset, int count, boolean swap);

	public static native void pokeIntArray(long address, int[] src, int offset, int count, boolean swap);

	public static native void pokeLongArray(long address, long[] src, int offset, int count, boolean swap);

	public static native void pokeShortArray(long address, short[] src, int offset, int count, boolean swap);
}