package Coaches.Exceptions;

/**
 * Исключение для ситуации, если тренер не найден
 */
public class NotFoundException extends RuntimeException {
	public NotFoundException(String message) {
		super(message);
	}
}
