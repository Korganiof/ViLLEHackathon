package fi.utu.ville.exercises.hope;

import com.vaadin.ui.Component;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

import fi.utu.ville.exercises.model.ExerciseException;
import fi.utu.ville.exercises.model.SubmissionVisualizer;
import fi.utu.ville.standardutils.Localizer;
import fi.utu.ville.standardutils.TempFilesManager;

public class HopeSubmissionViewer extends VerticalLayout implements
		SubmissionVisualizer<HopeExerciseData, HopeSubmissionInfo> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6260031633710031462L;
	private HopeExerciseData exer;
	private HopeSubmissionInfo submInfo;

	private Localizer localizer;
	
	public HopeSubmissionViewer() {
	}

	@Override
	public void initialize(HopeExerciseData exercise,
			HopeSubmissionInfo dataObject, Localizer localizer,
			TempFilesManager tempManager) throws ExerciseException {
		this.localizer = localizer;
		this.exer = exercise;
		this.submInfo = dataObject;
		doLayout();
	}

	private void doLayout() {
		this.addComponent(new Label(localizer.getUIText(HopeUiConstants.QUESTION) + 
				": " + exer.getQuestion()));
		Label answ = new Label(localizer.getUIText(HopeUiConstants.ANSWER) + 
				": "  + submInfo.getAnswer());
		answ.addStyleName(HopeThemeConsts.ANSWER_STYLE);
		this.addComponent(answ);
	}

	@Override
	public Component getView() {
		return this;
	}

	@Override
	public String exportSubmissionDataAsText() {
		return localizer.getUIText(HopeUiConstants.QUESTION, "\n", 
				exer.getQuestion(), submInfo.getAnswer());
		
	}

}
