package net.technisys.guayagamer.model;

import java.util.ArrayList;
import java.util.Objects;

import net.technisys.guayagamer.abstracts.Session;
import net.technisys.guayagamer.constant.Constant;

public class ReviewSession extends Session {

	public ReviewSession() {
		setName(Constant.REVIEW);
		setConferences(new ArrayList<Conference>());
	}

	public void printSession() {
		if (!Objects.isNull(getStartSession())) {
			System.out.println("\t" + getStartSession() + "-     " + " " + getName());
		}
	}

}
