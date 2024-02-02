package Coaches.Exceptions;

/**
 * Исключение для ситуации, если тренер не найден
 */
public class CoachNotFoundException extends Exception {
	public CoachNotFoundException(String message) {
		super(message);
	}
}
