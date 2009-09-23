package br.com.ideais;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TrainingLoader {
	private String trainingDirectory;
	
	public TrainingLoader(String trainingDirectory) {
		this.trainingDirectory = trainingDirectory;
	}
	
	private void readTrainingFile(File trainingFile, List<String> trainings) throws Exception {
		String endOfFile = "\\Z";
		Scanner scanner = new Scanner(trainingFile).useDelimiter(endOfFile);
		
		try {
			if (scanner.hasNext()) {
				String trainingFileContent = scanner.next();
				trainings.add(trainingFileContent);
			}
		} finally {
			scanner.close();
		}
	}
	
	public List<String> loadTrains() {
		try {
			List<String> trainings = new ArrayList<String>();
			File trainingDir = new File(trainingDirectory);
			File[] trainingFiles = trainingDir.listFiles();
			
			for (File trainingFile : trainingFiles) {
				readTrainingFile(trainingFile, trainings);
			}
			
			return trainings;
		} 
		catch (Exception e) {
			return new ArrayList<String>();
		}
	}
}
