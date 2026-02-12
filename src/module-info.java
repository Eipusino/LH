module template {
	exports template;

	opens template;

	requires java.base;
	requires jdk.unsupported;
	requires org.objectweb.asm;
	requires org.objectweb.asm.commons;
	requires org.objectweb.asm.tree;
}