package fi.utu.ville.exercises.hope;

import java.io.Serializable;

public class QuestionData implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 32976560619495368L;

	public String text;
	public String result;
	public String finnishResult;
		
	public QuestionData(String question, String answer, String finnishAnswer) {
		text = question;
		result = answer;
		finnishResult = finnishAnswer;
	}
	
	
	
	
	
	
	
		
	
	
}
