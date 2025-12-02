package template;

import arc.files.Fi;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Arrays;

public class ClassFileExample {
	private static final byte[] bytes = {-54, -2, -70, -66, 0, 0, 0, 52, 0, 13, 1, 0, 45, 106, 100, 107, 47, 105, 110, 116, 101, 114, 110, 97, 108, 47, 114, 101, 102, 108, 101, 99, 116, 47, 77, 97, 103, 105, 99, 65, 99, 99, 101, 115, 115, 111, 114, 73, 109, 112, 108, 95, 80, 85, 66, 76, 73, 67, 7, 0, 1, 1, 0, 38, 106, 100, 107, 47, 105, 110, 116, 101, 114, 110, 97, 108, 47, 114, 101, 102, 108, 101, 99, 116, 47, 77, 97, 103, 105, 99, 65, 99, 99, 101, 115, 115, 111, 114, 73, 109, 112, 108, 7, 0, 3, 1, 0, 13, 95, 95, 66, 89, 84, 69, 95, 67, 108, 97, 115, 115, 48, 1, 0, 6, 60, 105, 110, 105, 116, 62, 1, 0, 3, 40, 41, 86, 12, 0, 6, 0, 7, 10, 0, 4, 0, 8, 1, 0, 4, 67, 111, 100, 101, 1, 0, 13, 83, 116, 97, 99, 107, 77, 97, 112, 84, 97, 98, 108, 101, 1, 0, 10, 83, 111, 117, 114, 99, 101, 70, 105, 108, 101, 0, 1, 0, 2, 0, 4, 0, 0, 0, 0, 0, 1, 0, 1, 0, 6, 0, 7, 0, 1, 0, 10, 0, 0, 0, 25, 0, 1, 0, 1, 0, 0, 0, 5, 42, -73, 0, 9, -79, 0, 0, 0, 1, 0, 11, 0, 0, 0, 2, 0, 0, 0, 1, 0, 12, 0, 0, 0, 2, 0, 5};

	public static void load() {
		try {
			// 示例1: 生成类文件路径并写入
			String classFilePath = "F:/jc/pngs/to/amiya.png";

			byte[] readClassBytes = ClassFileUtils.readClassFromFile(classFilePath);
			//System.out.println(Arrays.toString(readClassBytes));

			try (BufferedWriter writer = new BufferedWriter(Fi.get("F:/jc/pngs/to/v.txt").writer(false))) {
				for (byte b : readClassBytes) {
					writer.write(b + ", ");
				}
			}

			//byte[] directReadBytes = ClassFileUtils.readClassFromFile(classFilePath);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 模拟获取类字节数组的方法 - 在实际使用中替换为你的真实数据源
	private static byte[] getSampleClassBytes() {
		// 这里应该是你从defineClass、网络、数据库等获取的字节数组
		// 为了示例，我们创建一个简单的模拟数据
		return bytes;
	}
}
