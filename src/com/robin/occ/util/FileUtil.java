package com.robin.occ.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.robin.occ.bean.Person;


/*
 * 
 * Convenient Utility class for file conversation
 * 
 * 
 * 
 * 
 */
public class FileUtil {
	
	
	/*
	 * 
	 * @param filePath the path for the file
	 * @return result a list of Person
	 * 
	 */
	public static List<Person> file2Array(String filePath) throws IOException{ 
		
		List<Person> personList = new ArrayList<>();
		if(filePath==null)
			return personList;

		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(new File(filePath)));
			String line = reader.readLine();
			while(line != null) {
				String[] names = line.split(",");
				for(String name:names) {
					name = name.replaceAll("\\\"","").toUpperCase();
					String[] nameArr = name.split(" ");
					int nameLen = nameArr.length;
					Person p = null;
					if(nameLen>2) {
						System.out.println("Invalid name "+name);
						continue;
					}else if (nameLen==2){
						p = new Person(nameArr[0],nameArr[1]);
					}else {
						p = new Person(nameArr[0]);
					}
					personList.add(p);
				}
				line = reader.readLine();
			}
		}catch(IOException exception) {
			exception.printStackTrace();
			
		}finally {
			if(reader!=null)
				reader.close();
		}
		return personList;
	}
	
	
	

}
