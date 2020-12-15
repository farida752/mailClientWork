package models;

public class Folder {
 
	private String currentFolder;
	private String  showingInboxDefault;
	

	public Folder(String currentFolder, String showingInboxDefault) {
		super();
		this.currentFolder = currentFolder;
		this.showingInboxDefault = showingInboxDefault;
	}


	public String getCurrentFolder() {
		return currentFolder;
	}


	public void setCurrentFolder(String currentFolder) {
		this.currentFolder = currentFolder;
	}


	public String getShowingInboxDefault() {
		return showingInboxDefault;
	}


	public void setShowingInboxDefault(String showingInboxDefault) {
		this.showingInboxDefault = showingInboxDefault;
	}
	
	
}
