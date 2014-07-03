package com.nmnw.admin.utility;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

import com.nmnw.admin.constant.ConfigConstants;

public class PropertyUtility {

	public static String getPropertyValue (String key) {
		final Properties properties = new Properties();
		try {
			String basepath = System.getProperty("user.dir");
			InputStream inputStream = new BufferedInputStream(new FileInputStream(basepath + ConfigConstants.PROPERTIY_FILE_PATH));
			properties.load(inputStream);
			String value = properties.getProperty(key);
			inputStream.close();
			return value;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
