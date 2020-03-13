package net.technisys.guayagamer.abstracts;

import java.time.Duration;
import java.time.LocalTime;
import java.util.List;

import net.technisys.guayagamer.exceptions.SessionException;
import net.technisys.guayagamer.interfaces.ISession;
import net.technisys.guayagamer.model.Conference;

public abstract class Session implements ISession {

	private String name;
	private LocalTime startSession;
	private LocalTime minimumEndTimeSession;
	private LocalTime maximumEndTimeSession;
	private List<Conference> conferences;
	private Duration maxDuration;
	private Duration remainingMinutes;

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

	public LocalTime getMaximumEndTimeSession() {
		return maximumEndTimeSession;
	}

	public void setMaximumEndTimeSession(LocalTime maximumEndTimeSession) {
		this.maximumEndTimeSession = maximumEndTimeSession;
	}

	public LocalTime getMinimumEndTimeSession() {
		return minimumEndTimeSession;
	}

	public void setMinimumEndTimeSession(LocalTime minimumEndTimeSession) {
		this.minimumEndTimeSession = minimumEndTimeSession;
	}

	public List<Conference> getConferences() {
		return conferences;
	}

	public void setConferences(List<Conference> conferences) {
		this.conferences = conferences;
	}

	public Duration getMaxDuration() {
		return maxDuration;
	}

	public void setMaxDuration(Duration maxDuration) {
		this.maxDuration = maxDuration;
	}

	public long getRemainingMinutes() {
		return remainingMinutes.toMinutes();
	}

	public void setRemainingMinutes(Duration remainingMinutes) {
		this.remainingMinutes = remainingMinutes;
	}

	public boolean isFull() {
		return getRemainingMinutes() == 0;
	}

	public void removeConference(Conference conference) {
	}

	public void addConference(Conference conference) { //throws SessionException {
	}

}
