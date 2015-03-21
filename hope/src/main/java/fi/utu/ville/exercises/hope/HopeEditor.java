package fi.utu.ville.exercises.hope;

import java.io.File;

import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

import fi.utu.ville.exercises.model.Editor;
import fi.utu.ville.exercises.model.EditorHelper;
import fi.utu.ville.exercises.model.EditorHelper.EditedExerciseGiver;
import fi.utu.ville.standardutils.AFFile;
import fi.utu.ville.standardutils.AbstractFile;
import fi.utu.ville.standardutils.Localizer;
import fi.utu.ville.standardutils.SimpleFileUploader;
import fi.utu.ville.standardutils.StandardUIFactory;
import fi.utu.ville.standardutils.SimpleFileUploader.UploaderListener;
import fi.utu.ville.standardutils.ui.AbstractEditorLayout;
import fi.utu.ville.exercises.model.VilleContent;
import fi.utu.ville.exercises.model.VilleUI;

public class HopeEditor extends VilleContent implements
		Editor<HopeExerciseData> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4600841604409240872L;

	private static final int MAX_FILE_SIZE_KB = 1024;
	
	private static final String MIME_FILTER = "^image/.*$";
	
	private EditorHelper<HopeExerciseData> editorHelper;

	private TextField questionText;

	private AbstractFile currImgFile;

	private Localizer localizer;
	
	private AbstractEditorLayout layout;


	public HopeEditor() {
		super(null);
	}

	@Override
	public Layout getView() {
		return this;
	}

	@Override
	public void initialize(VilleUI ui, Localizer localizer, HopeExerciseData oldData,
			EditorHelper<HopeExerciseData> editorHelper) {
		this.init(ui);
		this.localizer = localizer;

		this.editorHelper = editorHelper;

		editorHelper.getTempManager().initialize();

		doLayout(oldData);
	}

	private HopeExerciseData getCurrentExercise() {
		return new HopeExerciseData(questionText.getValue(), currImgFile);
	}

	@Override
	public boolean isOkToExit() {
		// TODO Auto-generated method stub
		return true;
	}
	
	@Override
	public void doLayout() {
		
	}
	
	public void doLayout(HopeExerciseData oldData) {

		this.setMargin(false);
		this.setSpacing(false);
		this.setWidth("100%");

		layout = StandardUIFactory.getTwoColumnView();
		addComponent(layout);
		
		layout.setTitle("Editor");
		
		String oldQuestion;
		if (oldData != null) {
			oldQuestion = oldData.getQuestion();
			currImgFile = oldData.getImgFile();
		} else {
			oldQuestion = "";
			currImgFile = null;
		}


		layout.addToLeft(editorHelper.getInfoEditorView());

		layout.addToTop(editorHelper
				.getControlbar(new EditedExerciseGiver<HopeExerciseData>() {

					@Override
					public HopeExerciseData getCurrExerData(
							boolean forSaving) {
						return getCurrentExercise();
					}
				}));


		VerticalLayout editlayout = new VerticalLayout();

		Label questionTextCapt = new Label(
				localizer.getUIText(HopeUiConstants.QUESTION));
		questionTextCapt.addStyleName(HopeThemeConsts.TITLE_STYLE);
		questionText = new TextField(null, oldQuestion);

		SimpleFileUploader uploader = new SimpleFileUploader(localizer,
				editorHelper.getTempManager(), MAX_FILE_SIZE_KB,
				MIME_FILTER);

		uploader.registerUploaderListener(new UploaderListener() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 8266397773350713952L;

			@Override
			public void fileUploadSucceeded(File tempFile, String fileName,
					String mimeType) {
				currImgFile = new AFFile(tempFile);
			}

			@Override
			public void uploadedFileDeleted(File tempFile) {
				currImgFile = null;
			}

		});

		if (currImgFile != null) {
			uploader.setAbstractUploadedFile(currImgFile);
		}
		editlayout.addComponent(questionTextCapt);
		editlayout.addComponent(questionText);
		editlayout.addComponent(uploader);

		layout.addToRight(editlayout);

	}
	
	@Override
	public String getViewName(){
		return "StubExercise";
	}
}
