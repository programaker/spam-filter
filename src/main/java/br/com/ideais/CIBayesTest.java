package br.com.ideais;


import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import com.enigmastation.classifier.FisherClassifier;
import com.enigmastation.classifier.Trainer;
import com.enigmastation.classifier.impl.FisherClassifierImpl;

public class CIBayesTest {
	private static FisherClassifier classifier;
	
	@BeforeClass
	public static void createAndTrainClassifier() throws Exception {
		List<String> trainingsToDetectEvil = new TrainingLoader("src/main/resources/training/evil").loadTrains();
		List<String> trainingsToDetectGood = new TrainingLoader("src/main/resources/training/good").loadTrains();
		classifier = new FisherClassifierImpl();
		
		for (String training : trainingsToDetectEvil) {
			((Trainer) classifier).train(training, "evil");
		}
		
		for (String training : trainingsToDetectGood) {
			((Trainer) classifier).train(training, "good");
		}
	}
	
	@Test
	public void classifyAsEvil() {
		List<String> evilMails = new TrainingLoader("src/main/resources/evil-mail").loadTrains();
		Boolean allEvil = true;
		
		for (String evilMail : evilMails) {
			String classification = classifier.getClassification(evilMail);
			System.out.println(Arrays.toString(classifier.getProbabilities(evilMail)));
			Boolean isEvil = "evil".equals(classification);
			allEvil &= isEvil;
		}
		
		assertTrue("all mails should be evil", allEvil);
	}
	
	@Test
	public void classifyAsGood() {
		List<String> goodMails = new TrainingLoader("src/main/resources/good-mail").loadTrains();
		Boolean allGood = true;
		
		for (String goodMail : goodMails) {
			String classification = classifier.getClassification(goodMail);
			System.out.println(Arrays.toString(classifier.getProbabilities(goodMail)));
			Boolean isGood = !("evil".equals(classification));
			allGood &= isGood;
		}
		
		assertTrue("all mails should be good", allGood);
	}
}
