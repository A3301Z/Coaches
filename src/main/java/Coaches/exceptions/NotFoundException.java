package Coaches.exceptions;

/**
 * Исключение для ситуации, когда сущность не найдена
 */
public class NotFoundException extends RuntimeException {
	public NotFoundException(String message) {
		super(message);
	}
}
