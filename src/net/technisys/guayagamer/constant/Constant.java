package net.technisys.guayagamer.constant;

import java.time.LocalTime;

public class Constant {

	private Constant() {
	}

	// morning session
	public static final LocalTime START_TIME_MORNING_SESSION = LocalTime.of(9, 0);
	public static final LocalTime END_TIME_MORNING_SESSION = LocalTime.of(12, 0);

	// evening session
	public static final LocalTime START_TIME_EVENING_SESSION = LocalTime.of(13, 0);
	public static final LocalTime MIN_END_TIME_EVENING_SESSION = LocalTime.of(16, 0);
	public static final LocalTime MAX_END_TIME_EVENING_SESSION = LocalTime.of(17, 0);

	// lunch
	public static final String LUNCH = "Refrigerio";
	public static final LocalTime START_LUNCH = LocalTime.of(12, 0);
	public static final LocalTime END_LUNCH = LocalTime.of(13, 0);

	public static final String CONFERENCE_REGEX = "^([a-zA-Z- ]*)(([0-9]{2})min|cuarto)";
	public static final String CUARTO = "cuarto";
	public static final Long CUARTO_DURATION = 15L;

	public static final String CONFERENCE_ROOM = "SALA";
	public static final String MORNING_SESSION = "SESION DIA";
	public static final String EVENING_SESSION = "SESION TARDE";

	// review
	public static final String REVIEW = "Mesa redonda";
	public static final LocalTime START_REVIEW = LocalTime.of(16, 0);
	public static final LocalTime END_REVIEW = LocalTime.of(17, 0);

	public static final int MIN_HOUR_PER_ROOM = 6;
	public static final int MAX_HOUR_PER_ROOM = 7;

	public static final String NAME_REQUIRED = "Nombre es requerido";
	public static final String DURATION_NOT_ZERO = "La duracion no puede ser cero";
	public static final String START_END_REQUIRED = "El tiempo de inicio y fin son requeridos";
	public static final String START_END_INVALID = "El tiempo de inicio y fin son incorrectos";

	public static final String NOT_ENOUGH_TIME = "El tiempo de disponible es menor al requerido";

}
