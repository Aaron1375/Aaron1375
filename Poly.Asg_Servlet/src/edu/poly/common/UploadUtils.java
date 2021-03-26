package edu.poly.common;






import java.io.IOException;
import java.nio.file.*;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

import org.apache.commons.io.FilenameUtils;

public class UploadUtils {
	public static String processUpladField(String fieldName,HttpServletRequest request,
			String storedFolder, String storedFilename) throws IOException, ServletException {
		Part filePath = request.getPart(fieldName);
		if (filePath == null || filePath.getSize() == 0) {
			return"";
		}
		
		if (storedFolder == null) {
			storedFolder = "/uploads";
		}
		
		if(storedFilename == null) {
			storedFilename = Paths.get(filePath.getSubmittedFileName()).getFileName().toString();
		}else {
			storedFilename += "." + FilenameUtils.getExtension(Paths.get(filePath.getSubmittedFileName()).toString());
		}
		
		String uploadFolder = request.getServletContext().getRealPath(storedFolder);
		
		Path uploadPath = Paths.get(uploadFolder);
		
		if(!Files.exists(uploadPath)) {
			Files.createDirectories(uploadPath);
		}
		
		filePath.write(Paths.get(uploadPath.toString(), storedFilename).toString());
		
		return storedFilename;
	}
}
