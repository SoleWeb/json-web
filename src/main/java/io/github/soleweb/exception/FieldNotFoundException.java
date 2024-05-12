package com.soleweb.exception;

/**
 * Exception class: FieldNotFoundException
 *
 * Description:
 * This exception is thrown when a field is not found.
 *
 * Example usage:
 *
 * ```
 * try {
 *     // Code that may throw FieldNotFoundException
 * } catch (FieldNotFoundException e) {
 *     // Handle the exception
 *     e.printStackTrace();
 * }
 * ```
 */
public class FieldNotFoundException extends RuntimeException {

    /**
     * Constructs a new FieldNotFoundException with no detail message.
     */
    public FieldNotFoundException() {
    }

    /**
     * Constructs a new FieldNotFoundException with the specified detail message.
     *
     * @param message the detail message.
     */
    public FieldNotFoundException(String message) {
        super(message);
    }

    /**
     * Constructs a new FieldNotFoundException with the specified detail message and cause.
     *
     * @param message the detail message.
     * @param cause the cause (which is saved for later retrieval by the Throwable.getCause() method).
     */
    public FieldNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructs a new FieldNotFoundException with the specified cause and a detail message of (cause==null ? null : cause.toString()) (which typically contains the class and detail message of cause).
     *
     * @param cause the cause (which is saved for later retrieval by the Throwable.getCause() method).
     */
    public FieldNotFoundException(Throwable cause) {
        super(cause);
    }

    /**
     * Constructs a new FieldNotFoundException with the specified detail message, cause, suppression enabled or disabled, and writable stack trace enabled or disabled.
     *
     * @param message the detail message.
     * @param cause the cause. (A null value is permitted, and indicates that the cause is nonexistent or unknown.)
     * @param enableSuppression whether or not suppression is enabled or disabled.
     * @param writableStackTrace whether or not the stack trace should be writable.
     */
    public FieldNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
