package stb.com.nyari.models;

public class DisabledChildMenuItem {

	private int groupPosition;
	private int childPosition;
	private String title;

	public DisabledChildMenuItem(int groupPosition, int childPosition, String title) {
		super();
		this.groupPosition = groupPosition;
		this.childPosition = childPosition;
		this.title	=	title;
	}

	public DisabledChildMenuItem() {
		super();
	}

	public void setGroupPosition(int groupPosition) {
		this.groupPosition = groupPosition;
	}

	public void setChildPosition(int childPosition) {
		this.childPosition = childPosition;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getGroupPosition() {
		return groupPosition;
	}

	public int getChildPosition() {
		return childPosition;
	}
	
	public String getTitle() {
		return title;
	}
	
}
