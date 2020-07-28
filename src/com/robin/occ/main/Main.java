package com.robin.occ.main;

import java.io.File;
import java.io.IOException;
import java.util.List;

import com.robin.occ.bean.Person;
import com.robin.occ.util.FileUtil;

public class Main {
	
	/*
	 * 
	 * The entry method for this project
	 * You need to pass the file path when run the code
	 * 
	 */
	
	public static void main(String[] args) {
		
		if(args.length!=1) {
			System.out.println("Usage: java -jar ${your file path}");
			System.exit(-1);
		}
		String filePath = args[0];
		
		File file = new File(filePath);
		boolean exists = file.exists();
		if(!exists) {
			System.out.printf("File %s doesn't exist\n",filePath);
			System.exit(-1);
		}
		try {
			List<Person> personList = FileUtil.file2Array(filePath);
			NameScore calculator =  new NameScore();
			int score = calculator.computeScore(personList);
			System.out.printf("The final score is %d\n",score);
			
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		
		
		
	}

}
