package fi.utu.ville.exercises.hope;

import fi.utu.ville.exercises.model.SubmissionInfo;

public class HopeSubmissionInfo implements SubmissionInfo {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8702870727095225372L;

	private final String answer;

	public HopeSubmissionInfo(String answer) {
		this.answer = answer;
	}

	public String getAnswer() {
		return answer;
	}

}
