package ch.pne.projects.xmltools.exceptions;

public class XMLToolException extends Exception{

    public XMLToolException(String message) {
        super(message);
    }

    public XMLToolException(String message, Throwable cause) {
        super(message, cause);
    }

    public XMLToolException(Throwable cause) {
        super(cause);
    }

    public XMLToolException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public XMLToolException() {
    }
}