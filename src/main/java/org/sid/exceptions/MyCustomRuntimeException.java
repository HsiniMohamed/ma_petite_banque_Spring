package org.sid.exceptions;

public class MyCustomRuntimeException extends RuntimeException {

	public MyCustomRuntimeException(String message) {
        super(message);
    }

    public String getCustomMessage() {
        return getMessage();
    }
}
