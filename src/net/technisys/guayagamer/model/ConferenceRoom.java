package net.technisys.guayagamer.model;

import java.util.ArrayList;
import java.util.List;

import net.technisys.guayagamer.abstracts.Session;
import net.technisys.guayagamer.constant.Constant;

public class ConferenceRoom {

	private String name;
	private List<Session> sessions;

	public ConferenceRoom() {
		sessions = new ArrayList<>();
	}

	public ConferenceRoom(String name) {
		super();
		this.name = name;
		sessions = new ArrayList<>();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Session> getSessions() {
		return sessions;
	}

	public void setSessions(List<Session> sessions) {
		this.sessions = sessions;
	}

	public void printConferenceRoom() {
		System.out.println(name.toUpperCase());

		Conference lastRoomConference = new Conference();

		for (Session session : sessions) {
			for (Conference conference : session.getConferences()) {
				lastRoomConference = conference;
			}

			if (session instanceof ReviewSession) {

				if (lastRoomConference.getEndTime().isAfter(Constant.MIN_END_TIME_EVENING_SESSION)) {
					session.setStartSession(lastRoomConference.getEndTime());
				} else {
					session.setStartSession(Constant.MIN_END_TIME_EVENING_SESSION);
				}
			}

			session.printSession();
		}
		System.out.println("");

	}

	public boolean isFull() {

		for (Session sesion : sessions) {
			if (!sesion.isFull()) {
				return false;
			}
		}

		return true;
	}

}
