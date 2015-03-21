package fi.utu.ville.exercises.hope;

import java.util.ArrayList;
import java.util.List;

import com.vaadin.server.Page;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Window;

import fi.utu.ville.exercises.helpers.ExerciseExecutionHelper;
import fi.utu.ville.exercises.hope.jsmap.JsMapStack;
import fi.utu.ville.exercises.model.ExecutionSettings;
import fi.utu.ville.exercises.model.ExecutionState;
import fi.utu.ville.exercises.model.ExecutionStateChangeListener;
import fi.utu.ville.exercises.model.Executor;
import fi.utu.ville.exercises.model.ExerciseException;
import fi.utu.ville.exercises.model.SubmissionListener;
import fi.utu.ville.exercises.model.SubmissionType;
import fi.utu.ville.standardutils.Localizer;
import fi.utu.ville.standardutils.TempFilesManager;

import java.util.HashMap;

public class HopeExecutor extends VerticalLayout implements
		Executor<HopeExerciseData, HopeSubmissionInfo> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2682119786422750060L;
		
	private final ExerciseExecutionHelper<HopeSubmissionInfo> execHelper =

	new ExerciseExecutionHelper<HopeSubmissionInfo>();

	private final TextField answerField = new TextField();
	private Button btn;
	private final TextField correct = new TextField();
	private final Label questionLabel = new Label();
	
	

	private HashMap<Integer, QuestionData> questions;
	private int currentQuestion = 0; 
	private List<Boolean> totalAnswers = new ArrayList<Boolean>();

	
	public HopeExecutor() {
			questions = new HashMap<Integer, QuestionData>();
	}

	@Override
	public void initialize(Localizer localizer,
			HopeExerciseData exerciseData, HopeSubmissionInfo oldSubm,
			TempFilesManager materials, ExecutionSettings fbSettings)
			throws ExerciseException {
		answerField.setCaption(localizer.getUIText(HopeUiConstants.ANSWER));
		

			QuestionData question1 = new QuestionData("Missä maassa sijaitsee Thames-joki?", "United Kingdom", "Iso-Britannia");
			QuestionData question2 = new QuestionData("Minkä EU:n jäsenvaltion pääkaupunki on Berliini?", "Germany", "Saksa");
			QuestionData question3 = new QuestionData("Missä lähti liikkelle teollinen vallankumous?", "United Kingdom", "Iso-Britannia");
			QuestionData question4 = new QuestionData("Mistä nykyisestä valtiosta laajeni Rooman valtakunta? ", "Italy", "Italia");
			QuestionData question5 = new QuestionData("Mihin maahaan tehtiin Normandian maihinnousu?", "France", "Ranska");
			QuestionData question6 = new QuestionData("Minkä maalaisten muinainen Jumala on nimeltään Horus?", "Egypt", "Egypti");
			QuestionData question7 = new QuestionData("Mikä maa yhdistyi Euroopassa vuonna 1989?", "Germany", "Saksa");
			QuestionData question8 = new QuestionData("Missä maassa on maailman suurin väkiluku?", "China", "Kiina");
			QuestionData question9 = new QuestionData("Mikä on pinta-alaltaan maailman toiseksi suurin valtio?", "Canada", "Kanada");
			QuestionData question10 = new QuestionData("Missä maassa järjestettiin maailman ensimmäinen Ville Hackathon?", "Finland", "Suomi");
			questions.put(1, question1);
			questions.put(2, question2);
			questions.put(3, question3);
			questions.put(4, question4);
			questions.put(5, question5);
			questions.put(6, question6);
			questions.put(7, question7);
			questions.put(8, question8);
			questions.put(9, question9);
			questions.put(10, question10);
		doLayout(exerciseData, oldSubm != null ? oldSubm.getAnswer() : "");
	}

	private void doLayout(HopeExerciseData exerciseData, String oldAnswer) {
		JsMapStack map = new JsMapStack(this);
		this.addComponent(map);
		btn = new Button("Lisää");		
		btn.addClickListener(new Button.ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				
				Window subWindow = new Window("Sub-window");
				subWindow.setModal(true);

		        VerticalLayout subContent = new VerticalLayout();
		        subContent.setMargin(true);
		        
		        subWindow.setContent(subContent);
		        getUI().addWindow(subWindow);
	
			}
		});
		
		this.addComponent(correct);
		this.addComponent(questionLabel);

		this.addComponent(new Label(exerciseData.getQuestion()));
		answerField.setValue(oldAnswer);
		this.addComponent(btn);
		
		//label creation

		moveToNextQuestion();

	
	}

	@Override
	public void registerSubmitListener(
			SubmissionListener<HopeSubmissionInfo> submitListener) {
		execHelper.registerSubmitListener(submitListener);
	}
	
	public void setCorrect(String s) {
		correct.setValue(s);
	}
	
	public void checkAnswer(String answer) {
		if (currentQuestion < questions.size()+1) {
			QuestionData value = questions.get(currentQuestion);
			if (value.result.equals(answer)) {
				Notification notif = new Notification("TIDIDIDIIII OIKEIN");
				notif.setDelayMsec(1000);
				notif.show(Page.getCurrent());
				totalAnswers.add(true);
			}
			else {
				Notification notif = new Notification("ET SIE TIENNY, MUT KYL MIE! OIKEAHAN OLI: " + value.finnishResult);
				notif.setDelayMsec(1000);
				notif.show(Page.getCurrent());
				totalAnswers.add(false);
			}
			if (currentQuestion == questions.size()) {
				int score = 0;
				for(Boolean finalAnswer : totalAnswers) {
					if (finalAnswer) {
						score++;
					}
				}
				Notification notif = new Notification("Testi on päättynyt, sait oikein " + score + "/" + totalAnswers.size() + ". Hienoa!");
				notif.setDelayMsec(4000);
				notif.show(Page.getCurrent());
				//näyttää koko testin tulokset ja tyhjentää kyssärit yms
			}
		}
	}
	
	public void moveToNextQuestion() {
		currentQuestion++;
		if (currentQuestion < questions.size()+1) {
			QuestionData value = questions.get(currentQuestion);
			questionLabel.setValue(value.text);
		}
		
	}

	@Override
	public Layout getView() {
		return this;
	}

	@Override
	public void shutdown() {
		// nothing to do here
	}

	@Override
	public void askReset() {
		// nothing to do here
	}

	@Override
	public ExecutionState getCurrentExecutionState() {
		return execHelper.getState();
	}

	@Override
	public void askSubmit(SubmissionType submType) {
		double corr = 1.0;

		String answer = answerField.getValue();
		execHelper.informOnlySubmit(corr, new HopeSubmissionInfo(answer),
				submType, null);

	}

	@Override
	public void registerExecutionStateChangeListener(
			ExecutionStateChangeListener execStateListener) {
		execHelper.registerExerciseExecutionStateListener(execStateListener);

	}

}
