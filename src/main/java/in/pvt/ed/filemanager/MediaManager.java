package in.pvt.ed.filemanager;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Logger;

import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.Parser;
import org.apache.tika.parser.mp3.Mp3Parser;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class MediaManager {

	private static final Logger LOGGER = Logger.getLogger(MediaManager.class.getName());
	
	public boolean organiseFileNames(String folderName){
		File folder = new File(folderName);
		File[] listOfFiles = folder.listFiles();
		for (File file: listOfFiles) {
			if (file.isFile()) {
				String filename = file.getName();
				String newFilename = filename.substring(4);
				
				if(isConvertable(filename)){
					newFilename = folderName + "/" + newFilename;
					LOGGER.info("Renaming the file - " + file.getAbsolutePath());
					file.renameTo(new File(newFilename));
					LOGGER.info("To - " + file.getAbsolutePath());
				}else{
					LOGGER.info("-----------   Unable to process the file - " + file.getAbsolutePath());
				}
				
			} else if (file.isDirectory()) {
				LOGGER.info("Directory " + file.getName());
			}
		}
		return true;
	}
	
	public void copyFileNameToTitle(String fileLocation){

        try {

        InputStream input = new FileInputStream(new File(fileLocation));
        ContentHandler handler = new DefaultHandler();
        Metadata metadata = new Metadata();
        Parser parser = new Mp3Parser();
        ParseContext parseCtx = new ParseContext();
        parser.parse(input, handler, metadata, parseCtx);
        input.close();

        // List all metadata
        String[] metadataNames = metadata.names();

        for(String name : metadataNames){
        System.out.println(name + ": " + metadata.get(name));
        }

        // Retrieve the necessary info from metadata
        // Names - title, xmpDM:artist etc. - mentioned below may differ based
        System.out.println("----------------------------------------------");
        System.out.println("Title: " + metadata.get("title"));
        System.out.println("Artists: " + metadata.get("xmpDM:artist"));
        System.out.println("Composer : "+metadata.get("xmpDM:composer"));
        System.out.println("Genre : "+metadata.get("xmpDM:genre"));
        System.out.println("Album : "+metadata.get("xmpDM:album"));

        metadata.set(Metadata.TITLE, "Test");
        
        } catch (FileNotFoundException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        } catch (SAXException e) {
        e.printStackTrace();
        } catch (TikaException e) {
        e.printStackTrace();
        }
	}
	
	private boolean isConvertable(String fileName){
		String pureName = fileName.replace(".mp3", "");
		if(pureName.length() > 4){
			return true;
		}
		return false;
	}
}
