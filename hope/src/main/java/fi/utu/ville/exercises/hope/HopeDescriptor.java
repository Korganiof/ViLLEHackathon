package fi.utu.ville.exercises.hope;

import com.vaadin.server.Resource;

import fi.utu.ville.exercises.helpers.GsonPersistenceHandler;
import fi.utu.ville.exercises.model.ExerciseTypeDescriptor;
import fi.utu.ville.exercises.model.PersistenceHandler;
import fi.utu.ville.exercises.model.SubmissionStatisticsGiver;
import fi.utu.ville.exercises.model.SubmissionVisualizer;
import fi.utu.ville.standardutils.Localizer;
import fi.utu.ville.standardutils.StandardIcon;
import fi.utu.ville.standardutils.StandardIcon.IconVariant;

public class HopeDescriptor implements
		ExerciseTypeDescriptor<HopeExerciseData, HopeSubmissionInfo> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5743225101617556960L;

	public static final HopeDescriptor INSTANCE = new HopeDescriptor();

	private HopeDescriptor() {

	}

	@Override
	public PersistenceHandler<HopeExerciseData, HopeSubmissionInfo> newExerciseXML() {
		// You can also implement your own PersistenceHandler if you want (see JavaDoc for more info)
		return new GsonPersistenceHandler<HopeExerciseData, HopeSubmissionInfo>(
				getTypeDataClass(), getSubDataClass());
	}

	@Override
	public HopeExecutor newExerciseExecutor() {
		return new HopeExecutor();
	}

	@Override
	public HopeEditor newExerciseEditor() {
		return new HopeEditor();
	}

	@Override
	public Class<HopeExerciseData> getTypeDataClass() {
		return HopeExerciseData.class;
	}

	@Override
	public Class<HopeSubmissionInfo> getSubDataClass() {
		return HopeSubmissionInfo.class;
	}

	@Override
	public SubmissionStatisticsGiver<HopeExerciseData, HopeSubmissionInfo> newStatisticsGiver() {
		return new HopeStatisticsGiver();
	}

	@Override
	public SubmissionVisualizer<HopeExerciseData, HopeSubmissionInfo> newSubmissionVisualizer() {
		return new HopeSubmissionViewer();
	}

	@Override
	public String getTypeName(Localizer localizer) {
		return localizer.getUIText(HopeUiConstants.NAME);
	}

	@Override
	public String getTypeDescription(Localizer localizer) {
		return localizer.getUIText(HopeUiConstants.DESC);
	}

	@Override
	public Resource getSmallTypeIcon() {
		return HopeIcon.SMALL_TYPE_ICON.getIcon();
	}

	@Override
	public Resource getMediumTypeIcon() {
		return HopeIcon.SMALL_TYPE_ICON.getIcon();
	}

	@Override
	public Resource getLargeTypeIcon() {
		return HopeIcon.SMALL_TYPE_ICON.getIcon();
	}
	
	@Override
	public String getHTMLIcon(){
		return StandardIcon.RawIcon.dribbble.variant(IconVariant.ORANGE);
	}
	
	@Override
	public boolean isManuallyGraded() {
		return false;
	}

}