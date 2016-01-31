package in.pvt.ed;

import java.io.File;
import java.util.logging.Logger;

import in.pvt.ed.filemanager.MediaManager;

public class ApplicationStarter {

	private static final Logger LOGGER = Logger.getLogger(ApplicationStarter.class.getName());
	public static void main(String[] args) {
		String folderName = "F:/Tamil/Collections/Ilayaraja/01ILAYARAJA ISAI MALAI";
		MediaManager mediaManager = new MediaManager();
		//mediaManager.organiseFileNames(folderName);
		mediaManager.copyFileNameToTitle(folderName + "/aalakadalil.mp3" );
		
		
		/*
		File testFolder = new File(folderName);
		String[] fileNames = testFolder.list();
		for(String fileName : fileNames){
			File fileToBeDeleted = new File(folderName + '/' + fileName);
			LOGGER.info("Deleteting file - " + fileToBeDeleted.getAbsolutePath());
			fileToBeDeleted.delete();
		}*/
	}

}
