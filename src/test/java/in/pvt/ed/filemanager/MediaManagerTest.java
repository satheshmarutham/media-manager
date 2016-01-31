package in.pvt.ed.filemanager;

import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class MediaManagerTest {
	
	private static final Logger LOGGER = Logger.getLogger(MediaManagerTest.class.getName());
	
	private static final String TEST_PATH_ROOT = "D:/workspace/eclipse/media-manager/src/test/resources";
	private static final String TEST_PATH = TEST_PATH_ROOT + "/MediaManagerTest";
	
	private MediaManager manager;
	
	@Before
	public void setup(){
		createFolder(TEST_PATH);
		manager = new MediaManager();
	}
	
	@After
	public void teardown(){
		deleteFolder(TEST_PATH);
	}
	
	@Test
	public void testOrganiseFileNamesForNumberedFiles() throws IOException{
		File testFile = new File(TEST_PATH + "/001 abc.mp3");
		testFile.createNewFile();
		
		manager.organiseFileNames(TEST_PATH);
		
		File folder = new File(TEST_PATH);
		String[] fileNames = folder.list();
		Assert.assertEquals(1,fileNames.length);
		Assert.assertEquals("abc.mp3",fileNames[0]);
	}
	
	@Test
	public void testOrganiseFileNamesForNumberOnly() throws IOException{
		String fileName = TEST_PATH_ROOT + "/aasaya.mp3";
		//testFile.createNewFile();
		
		manager.copyFileNameToTitle(fileName);
		
		/*File folder = new File(TEST_PATH);
		String[] fileNames = folder.list();
		Assert.assertEquals(1,fileNames.length);
		Assert.assertEquals("001.mp3",fileNames[0]);*/
	}
	
	@Test
	public void testEditTitle() throws IOException{
		File testFile = new File(TEST_PATH + "/001.mp3");
		testFile.createNewFile();
		
		manager.organiseFileNames(TEST_PATH);
		
		File folder = new File(TEST_PATH);
		String[] fileNames = folder.list();
		Assert.assertEquals(1,fileNames.length);
		Assert.assertEquals("001.mp3",fileNames[0]);
	}
	
	private void createFolder(String folderName){
		File testFile = new File(folderName);
		testFile.mkdirs();
	}
	
	private void deleteFolder(String folderName){
		File testFolder = new File(folderName);
		String[] fileNames = testFolder.list();
		for(String fileName : fileNames){
			File fileToBeDeleted = new File(TEST_PATH + '/' + fileName);
			LOGGER.info("Deleteting file - " + fileToBeDeleted.getAbsolutePath());
			fileToBeDeleted.delete();
		}
		testFolder.delete();
	}
}
