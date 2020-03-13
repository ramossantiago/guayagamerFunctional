package net.technisys.guayagamer.model;

import java.time.Duration;
import java.time.LocalTime;
import java.util.Objects;

import net.technisys.guayagamer.constant.Constant;
import net.technisys.guayagamer.exceptions.InvalidArgumentsException;
import net.technisys.guayagamer.interfaces.IConference;

public class Conference implements IConference {

	private String name;
	private LocalTime startTime;
	private LocalTime endTime;
	private Duration duration;

	public Conference() {
	}

	public Conference(String name, Duration duration) throws InvalidArgumentsException {
		super();

		if (Objects.isNull(name) || "".equals(name.trim())) {
			throw new InvalidArgumentsException(Constant.NAME_REQUIRED);
		}

		if (Objects.isNull(duration) || duration.isZero()) {
			throw new InvalidArgumentsException(Constant.DURATION_NOT_ZERO);
		}

		this.name = name;
		this.duration = duration;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalTime getStartTime() {
		return startTime;
	}

	public void setStartTime(LocalTime startTime) {
		this.startTime = startTime;
		setEndTime();
	}

	public LocalTime getEndTime() {
		return endTime;
	}

	private void setEndTime() {
		if (!Objects.isNull(startTime)) {
			this.endTime = startTime.plusMinutes(duration.toMinutes());
		}
	}

	@Override
	public Long getDurationInMinutes() {
		return duration.toMinutes();
	}

	public void setDuration(Duration duration) {
		this.duration = duration;
	}

	@Override
	public void printConference() {
		System.out.println("\t" + this.getStartTime() + "-" + this.getEndTime() + " " + this.name + " "
				+ this.getDurationInMinutes() + "min");
	}
}
