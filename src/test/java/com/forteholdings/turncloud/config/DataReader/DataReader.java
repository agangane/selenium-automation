package com.forteholdings.turncloud.config.DataReader;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.fasterxml.jackson.core.type.TypeReference;  
import com.fasterxml.jackson.databind.ObjectMapper;

public class DataReader {

	
	public static List<HashMap<String, Object>> getjsondatamap(String jsonName) throws IOException {
		

		 
		 String jsoncontent = FileUtils.readFileToString(new File (System.getProperty("user.dir")+"\\src\\test\\java\\com\\forteholdings\\turncloud\\resources\\json\\" + jsonName), 
				 StandardCharsets.UTF_8);
		 
		 ObjectMapper mapper = new ObjectMapper();
		 
		 List<HashMap<String, Object>> data = mapper.readValue(jsoncontent, new TypeReference<List<HashMap<String, Object>>>() {
		
		 
		 });
		 
	 return data;
		 
		 
		 
	 } 	
	}

