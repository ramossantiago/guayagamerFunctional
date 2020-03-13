package net.technisys.guayagamer.model;

import java.util.ArrayList;

import net.technisys.guayagamer.abstracts.Session;
import net.technisys.guayagamer.constant.Constant;

public class LunchSession extends Session {

	public LunchSession() {
		setName(Constant.LUNCH);
		setStartSession(Constant.START_LUNCH);
		setMaximumEndTimeSession(Constant.END_LUNCH);
		setConferences(new ArrayList<Conference>());
	}

	public void printSession() {
		System.out.println("\t" + getStartSession() + "-" + getMaximumEndTimeSession() + " " + getName());
	}
}
