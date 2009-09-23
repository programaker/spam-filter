package br.com.ideais;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

public class TrainingLoaderTest {
	@Test
	public void loadTrains() {
		TrainingLoader trainingLoader = new TrainingLoader("src/main/resources/training");
		List<String> trainings = trainingLoader.loadTrains();
		assertTrue("it should load some training files", trainings.size() > 0);
	}
}
