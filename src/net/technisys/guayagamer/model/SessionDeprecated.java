package net.technisys.guayagamer.model;

import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import net.technisys.guayagamer.constant.Constant;
import net.technisys.guayagamer.exceptions.InvalidArgumentsException;
import net.technisys.guayagamer.exceptions.SessionException;
import net.technisys.guayagamer.interfaces.ISession;

public class SessionDeprecated implements ISession {

	private String name;
	private LocalTime startSession;
	private LocalTime minimumEndTimeSession;
	private LocalTime maximumEndTimeSession;
	private List<Conference> conferences;
	private Duration maxDuration;
	private Duration remainingMinutes;

	public SessionDeprecated(String name, LocalTime startSession, LocalTime minimumEndTimeSession,
			LocalTime maximumEndTimeSession) throws InvalidArgumentsException {
		super();

		if (Objects.isNull(name) || "".equals(name.trim())) {
			throw new InvalidArgumentsException("Session name is required");
		}

		if (Objects.isNull(startSession) || Objects.isNull(minimumEndTimeSession)
				|| Objects.isNull(maximumEndTimeSession)) {
			throw new InvalidArgumentsException("Session, Start, minimun and maximun time are required");
		}

		if (startSession.compareTo(minimumEndTimeSession) == 0 || startSession.compareTo(maximumEndTimeSession) == 0) {
			throw new InvalidArgumentsException("Session duration can't be zero or less");
		}

		if (minimumEndTimeSession.isAfter(maximumEndTimeSession) || startSession.isAfter(minimumEndTimeSession)) {
			throw new InvalidArgumentsException("Session, Start time, minimun or maximun time are incorrect");
		}

		this.name = name;
		this.startSession = startSession;
		this.minimumEndTimeSession = minimumEndTimeSession;
		this.maximumEndTimeSession = maximumEndTimeSession;
		this.maxDuration = Duration.between(startSession, maximumEndTimeSession);
		conferences = new ArrayList<>();
		calculateRemainingTime();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalTime getStartSession() {
		return startSession;
	}

	public void setStartSession(LocalTime startSession) {
		this.startSession = startSession;
	}

	public LocalTime getMinimumEndTimeSession() {
		return minimumEndTimeSession;
	}

	public void setMinimumEndTimeSession(LocalTime minimumEndTimeSession) {
		this.minimumEndTimeSession = minimumEndTimeSession;
	}

	public LocalTime getMaximumEndTimeSession() {
		return maximumEndTimeSession;
	}

	public void setMaximumEndTimeSession(LocalTime maximumEndTimeSession) {
		this.maximumEndTimeSession = maximumEndTimeSession;
	}

	public List<Conference> getConferences() {
		return conferences;
	}

	public void setConferences(List<Conference> conferences) {
		this.conferences = conferences;
	}

	public long getRemainingMinutes() {
		return remainingMinutes.toMinutes();
	}

	@Override
	public boolean isFull() {
		if (remainingMinutes.toMinutes() == 0) {
			return true;
		}
		return false;
	}

	@Override
	public void addConference(Conference conference) {

		if (isFull() || remainingMinutes.toMinutes() < conference.getDurationInMinutes()) {
			throw new RuntimeException(Constant.NOT_ENOUGH_TIME);
		}

		this.conferences.add(conference);
		calculateRemainingTime();
	}

	@Override
	public void removeConference(Conference conference) {
		this.conferences.remove(conference);
		calculateRemainingTime();
	}

	private void calculateRemainingTime() {
		Long minutesInSession = 0l;

		for (Conference conference : conferences) {
			minutesInSession += conference.getDurationInMinutes();
		}

		remainingMinutes = Duration.ofMinutes(maxDuration.toMinutes() - minutesInSession);
	}

	@Override
	public void printSession() {
		long timeUsed = 0;
		Conference lastConference = null;

		for (Conference conference : conferences) {
			conference.setStartTime(this.getStartSession().plusMinutes(timeUsed));
			timeUsed += conference.getDurationInMinutes();
			conference.printConference();

			if (conference.getEndTime().equals(Constant.START_LUNCH)) {
				System.out.println("\t" + Constant.START_LUNCH + "-" + Constant.END_LUNCH + " " + Constant.LUNCH);
			}
			lastConference = conference;
		}

		if (this.getStartSession().equals(Constant.START_TIME_EVENING_SESSION)) {
			System.out.print("\t");
			if (lastConference.getEndTime().isAfter(Constant.MIN_END_TIME_EVENING_SESSION)) {
				System.out.print(lastConference.getEndTime() + "-     ");
			} else {
				System.out.print(Constant.MIN_END_TIME_EVENING_SESSION + "-     ");
			}
			System.out.println(" " + Constant.REVIEW);
		}
	}
}
