package fi.utu.ville.exercises.hope;

import com.vaadin.server.Resource;
import com.vaadin.server.ThemeResource;

public enum HopeIcon {

	SMALL_TYPE_ICON("small_type_icon.png"), MEDIUM_TYPE_ICON("medium_type_icon.png"), 
	LARGE_TYPE_ICON("large_type_icon.png");
	
	// get out of the 'current-theme' and to the correct theme (even if starting
	// in correct theme)
	private static final String iconsBasePath = "../Hope/icons";
	private final String iconPath;

	private HopeIcon(String iconPath) {
		this.iconPath = iconPath;
	}

	/**
	 * @return represented icon as a {@link Resource}
	 */
	public Resource getIcon() {
		return new ThemeResource(iconsBasePath + "/" + iconPath);
	}
}