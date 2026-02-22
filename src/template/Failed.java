package template;

public class Failed extends Throwable {
	public Failed(String message) {
		super(message);
	}

	public Failed() throws Failed {
		super();
		throw this;
	}
}
