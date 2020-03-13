package net.technisys.guayagamer.model;

import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Objects;

import net.technisys.guayagamer.abstracts.Session;
import net.technisys.guayagamer.constant.Constant;
import net.technisys.guayagamer.exceptions.InvalidArgumentsException;
import net.technisys.guayagamer.exceptions.SessionException;

public class RegularSession extends Session {

	public RegularSession(String name, LocalTime startSession, LocalTime minimumEndTimeSession,
			LocalTime maximumEndTimeSession) throws InvalidArgumentsException {
		super();

		if (Objects.isNull(name) || "".equals(name.trim())) {
			throw new InvalidArgumentsException(Constant.NAME_REQUIRED);
		}

		if (Objects.isNull(startSession) || Objects.isNull(minimumEndTimeSession)
				|| Objects.isNull(maximumEndTimeSession)) {
			throw new InvalidArgumentsException(Constant.START_END_REQUIRED);
		}

		if (startSession.compareTo(minimumEndTimeSession) == 0 || startSession.compareTo(maximumEndTimeSession) == 0) {
			throw new InvalidArgumentsException(Constant.DURATION_NOT_ZERO);
		}

		if (minimumEndTimeSession.isAfter(maximumEndTimeSession) || startSession.isAfter(minimumEndTimeSession)) {
			throw new InvalidArgumentsException(Constant.START_END_INVALID);
		}

		setName(name);
		setStartSession(startSession);
		setMinimumEndTimeSession(minimumEndTimeSession);
		setMaximumEndTimeSession(maximumEndTimeSession);
		setMaxDuration(Duration.between(startSession, maximumEndTimeSession));
		setConferences(new ArrayList<>());
		calculateRemainingTime();
	}

	@Override
	public void addConference(Conference conference) { //throws SessionException {

		if (isFull() || getRemainingMinutes() < conference.getDurationInMinutes()) {
			throw new RuntimeException(Constant.NOT_ENOUGH_TIME);
		}

		getConferences().add(conference);
		calculateRemainingTime();
	}

	@Override
	public void removeConference(Conference conference) {
		getConferences().remove(conference);
		calculateRemainingTime();
	}

	private void calculateRemainingTime() {
		Long minutesInSession = 0l;

		for (Conference conference : getConferences()) {
			minutesInSession += conference.getDurationInMinutes();
		}

		setRemainingMinutes(Duration.ofMinutes(getMaxDuration().toMinutes() - minutesInSession));
	}

	@Override
	public void printSession() {
		long timeUsed = 0;

		for (Conference conference : getConferences()) {
			conference.setStartTime(this.getStartSession().plusMinutes(timeUsed));
			timeUsed += conference.getDurationInMinutes();
			conference.printConference();
		}
	}
}
