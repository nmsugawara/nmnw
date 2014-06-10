package com.nmnw.admin.utility;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.http.Part;

public class FileUtility {
	private static final String fileNameOfDateTimeType = "yyyyMMddHHmmssSSSS";

	public FileUtility() {
	}

	public static String getNewFileName(Part part, String fileBaseName) {
		String newFileName = "";
		String contentDispotision = part.getHeader("Content-Disposition");
		String[] contentDispotisions = contentDispotision.split(";");
		for (String cd : contentDispotisions) {
			if (cd.trim().startsWith("filename")) {
				// generate file name
				String filePath = cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");
				int lastSeparatorIndex = filePath.lastIndexOf(File.separator);
				String oldFileName = filePath.substring(lastSeparatorIndex + 1);
				String[] oldFileString = oldFileName.split("\\.");
				String oldFileExtension = oldFileString[oldFileString.length - 1];
				Calendar cal = Calendar.getInstance();
				SimpleDateFormat sdf = new SimpleDateFormat(fileNameOfDateTimeType);
				newFileName = fileBaseName + sdf.format(cal.getTime()) + "." + oldFileExtension;
				return newFileName;
			}
		}
		throw new IllegalStateException();
	}
}
