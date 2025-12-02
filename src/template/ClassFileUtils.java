package template;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ClassFileUtils {
	public static byte[] readClassFromFile(String absolutePath) throws IOException {
		Path path = Paths.get(absolutePath);

		// 验证文件存在且是普通文件
		if (!Files.exists(path)) {
			throw new IOException("文件不存在: " + absolutePath);
		}
		if (!Files.isRegularFile(path)) {
			throw new IOException("路径不是文件: " + absolutePath);
		}

		byte[] classBytes = Files.readAllBytes(path);
		System.out.println("类文件已成功读取，大小: " + classBytes.length + " 字节");
		return classBytes;
	}

	public static void writeClassToFile(byte[] classBytes, String absolutePath) throws IOException {
		Path path = Paths.get(absolutePath);
		Path parentDir = path.getParent();

		if (parentDir != null && !Files.exists(parentDir)) {
			Files.createDirectories(parentDir);
		}

		try (FileOutputStream fos = new FileOutputStream(absolutePath)) {
			fos.write(classBytes);
		}

		System.out.println("类文件已成功写入: " + absolutePath);
	}

	public static String generateClassFilePath(String className, String baseDir) {
		String relativePath = className.replace('.', '/') + ".class";
		return Paths.get(baseDir, relativePath).toString();
	}
}
