package stb.com.nyari.models;

public class LeftMenuModel {
    String title;

    int icon;
    
    int navIcon;

    /**
     * @param title
     * @param icon
     */
    public LeftMenuModel(String title, int icon) {
        super();
        this.title = title;
        this.icon = icon;
    }
    
    public LeftMenuModel(String title, int icon, int navIcon) {
        super();
        this.title = title;
        this.icon = icon;
        this.navIcon = navIcon;
    }
    
    public LeftMenuModel(String title) {
        super();
        this.title = title;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title
     *            the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the icon
     */
    public int getIcon() {
        return icon;
    }

    /**
     * @param icon
     *            the icon to set
     */
    public void setIcon(int icon) {
        this.icon = icon;
    }

	/**
	 * @return the navIcon
	 */
	public int getNavIcon() {
		return navIcon;
	}

	/**
	 * @param navIcon the navIcon to set
	 */
	public void setNavIcon(int navIcon) {
		this.navIcon = navIcon;
	}
}
