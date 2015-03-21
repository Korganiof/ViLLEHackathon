package fi.utu.ville.exercises.hope;

import fi.utu.ville.exercises.model.ExerciseData;
import fi.utu.ville.standardutils.AbstractFile;

public class HopeExerciseData implements ExerciseData {

	/**
	 * 
	 */
	private static final long serialVersionUID = -716445297446246493L;

	private final String question;
	private final AbstractFile imgFile;

	public HopeExerciseData(String question, AbstractFile imgFile) {
		this.question = question;
		this.imgFile = imgFile;
	}

	public String getQuestion() {
		return question;
	}

	public AbstractFile getImgFile() {
		return imgFile;
	}

}
