package com.robin.occ.util;

import org.junit.Assert;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;



import com.robin.occ.bean.Person;

public class FileUtilTest{
	
	private String filePath = "a.txt";
	private BufferedWriter writer = null;
	private List<Person> personList = null;
	
	@Before
	public void Setup() {
		personList = new ArrayList<>();
		personList.add(new Person("MARY"));
		try {
			writer = new BufferedWriter(new FileWriter(new File(filePath)));
		}catch(IOException e) {
			System.out.print("No such file exists");
		}
	}
	
	/*
	 * Test case for null value of the filePath
	 * @input null for filePath
	 * @expect empty list
	 * 
	 */
	@Test
	public void testfile2ArrayNullPointerException() throws IOException{
		String filePath = null;
		int expectedCount=0;
		List<Person> actulaList = FileUtil.file2Array(filePath);
		Assert.assertEquals(expectedCount, actulaList.size());
	}

	/*
	 * Test case for file without parenthesis around the name
	 * @input a file with 1 record 
	 * @expect a list of 1 record and the name without parenthesis
	 * 
	 */
	@Test
	public void testfile2ArrayValidWithoutParenthis() throws IOException{
		StringBuilder sb = new StringBuilder();
		int expectedCount=1;
		for(Person p:personList) {
			sb.append(p.getFirstName());
		}
		writer.append(sb);
		writer.flush();
		List<Person> actualList = FileUtil.file2Array(filePath);
		Assert.assertEquals(expectedCount,actualList.size());
		Assert.assertTrue(compareRecord(actualList,personList,expectedCount));
	}

	/*
	 * Test case for file with parenthesis around the name
	 * @input a file with 1 record 
	 * @expect a list of 1 record and the name without parenthesis
	 * 
	 */
	@Test
	public void testfile2ArrayValidWithParenthis() throws IOException{
		StringBuilder sb = new StringBuilder();
		int expectedCount=1;
		for(Person p:personList) {
			sb.append("\""+p.getFirstName()+"\"");
		}
		writer.append(sb);
		writer.flush();
		List<Person> actualList = FileUtil.file2Array(filePath);
		Assert.assertEquals(actualList.size(),expectedCount);
		Assert.assertTrue(compareRecord(actualList,personList,expectedCount));
	}
	
	/*
	 * Test case for file with parenthesis around the name and with line break
	 * @input a file with 1 record 
	 * @expect a list of 1 record and the name without parenthesis
	 * 
	 */
	@Test
	public void testfile2ArrayValidWithLineBreak() throws IOException{
		StringBuilder sb = new StringBuilder();
		int expectedCount=1;
		for(Person p:personList) {
			sb.append("\""+p.getFirstName()+"\"\n");
		}
		writer.append(sb);
		writer.flush();
		List<Person> actualList = FileUtil.file2Array(filePath);
		Assert.assertEquals(actualList.size(),expectedCount);
		Assert.assertTrue(compareRecord(actualList,personList,expectedCount));
	}
	
	/*
	 * Test case for file without parenthesis around the name and with line break
	 * @input a file with 1 record 
	 * @expect a list of 1 record and the name without parenthesis
	 * 
	 */
	@Test
	public void testfile2ArrayValidWithLineBreakWithOutParenthis() throws IOException{
		StringBuilder sb = new StringBuilder();
		int expectedCount=1;
		for(Person p:personList) {
			sb.append(p.getFirstName()+"\n");
		}
		writer.append(sb);
		writer.flush();
		List<Person> actualList = FileUtil.file2Array(filePath);
		Assert.assertEquals(actualList.size(),expectedCount);
		Assert.assertTrue(compareRecord(actualList,personList,expectedCount));
	}
	
	/*
	 * Test case for file with multiple records
	 * @input a file with 1 record 
	 * @expect a list of 1 record and the name without parenthesis
	 * 
	 */
	@Test
	public void testfile2ArrayValidMultiplerecords() throws IOException{
		StringBuilder sb = new StringBuilder();
		int expectedCount=2;
		personList.add(new Person("ROBIN"));
		for(Person p:personList) {
			sb.append(p.getFirstName()+"\n");
		}
		writer.append(sb);
		writer.flush();
		List<Person> actualList = FileUtil.file2Array(filePath);
		Assert.assertEquals(actualList.size(),expectedCount);
		Assert.assertTrue(compareRecord(actualList,personList,expectedCount));
	}
	
	/*
	 * Test case for file with lower case
	 * @input a file with 1 record 
	 * @expect a list of 1 record and the name should be uppercase
	 * 
	 */
	@Test
	public void testfile2ArrayValidLowerCaseName() throws IOException{
		StringBuilder sb = new StringBuilder();
		int expectedCount=1;
		String firstName = "Anni";
		List<Person> expectedList = new ArrayList<Person>();
		expectedList.add(new Person(firstName.toUpperCase()));
		personList.clear();
		personList.add(new Person(firstName));
		for(Person p:personList) {
			sb.append(p.getFirstName()+"\n");
		}
		writer.append(sb);
		writer.flush();
		List<Person> actualList = FileUtil.file2Array(filePath);
		Assert.assertEquals(actualList.size(),expectedCount);
		Assert.assertTrue(compareRecord(actualList,expectedList,expectedCount));
	}
	/*
	 * Test case for a file with both first name and last name
	 * @input a file with 1 record 
	 * @expect a list of 1 record
	 * 
	 */
	@Test
	public void testfile2ArrayValidBothFirstAndLast() throws IOException{
		StringBuilder sb = new StringBuilder();
		int expectedCount=1;
		String firstName = "ANNIE";
		String lastName = "ZERO";
		personList.clear();
		personList.add(new Person(firstName,lastName));
		for(Person p:personList) {
			sb.append(p.getFirstName()+"\n");
		}
		writer.append(sb);
		writer.flush();
		List<Person> actualList = FileUtil.file2Array(filePath);
		Assert.assertEquals(actualList.size(),expectedCount);
		Assert.assertTrue(compareRecord(actualList,personList,expectedCount));
	}
	public boolean compareRecord(List<Person> actualList, List<Person> expectedList, int expectedCount) {
		for(int i=0;i<expectedCount;i++) {
			if(!actualList.get(i).equals(actualList.get(i)))
				return false;
		}
		return true;
	}
	
	/*
	 * 
	 * tear down method
	 * delete the file a.txt and set personList to null
	 * 
	 */
	@After
	public void tearDown() {
		String filePath = "a.txt";
		try {
			Files.deleteIfExists(Paths.get(filePath));
		}catch(NoSuchFileException e) {
			System.out.print("No such file exists");
		}catch(DirectoryNotEmptyException e) {
			System.out.print("No such directory exists");
		}catch(IOException e) {
			System.out.print("permission error");
		}
		personList =null;
	}

}
