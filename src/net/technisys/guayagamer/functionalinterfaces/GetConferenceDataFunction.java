package net.technisys.guayagamer.functionalinterfaces;


@FunctionalInterface
public interface GetConferenceDataFunction<T> {

	Long getData(T line);

}
