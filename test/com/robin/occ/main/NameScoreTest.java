package com.robin.occ.main;

import org.junit.Assert;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;



import com.robin.occ.bean.Person;

public class NameScoreTest{
	
	private List<Person> personList = null;
	private NameScore score = null;
	
	@Before
	public void Setup() {
		personList = new ArrayList<>();
		personList.add(new Person("MARY"));
		personList.add(new Person("PATRICIA"));
		personList.add(new Person("LINDA"));
		personList.add(new Person("BARBARA"));
		personList.add(new Person("VINCENZO"));
		personList.add(new Person("SHON"));
		personList.add(new Person("LYNWOOD"));
		personList.add(new Person("JERE"));
		personList.add(new Person("HAI"));
		score = new NameScore();
	}
	
	/*
	 * Test case for unsorted list
	 * @input unsorted list
	 * @expect score based on sorted list
	 * 
	 */
	@Test
	public void testComputeScoreUnsortedList(){
		int actualScore=score.computeScore(personList);
		int expectedScore = 3194;
		Assert.assertEquals(expectedScore, actualScore);
	}

	/*
	 *Test case for sorted list
	 * @input sorted list
	 * @expect score based on sorted list
	 * 
	 */
	@Test
	public void testComputeScoreSortedList(){
		personList.clear();
		personList.add(new Person("BARBARA"));
		personList.add(new Person("HAI"));
		personList.add(new Person("JERE"));
		personList.add(new Person("LINDA"));
		personList.add(new Person("LYNWOOD"));
		personList.add(new Person("MARY"));
		personList.add(new Person("PATRICIA"));
		personList.add(new Person("SHON"));
		personList.add(new Person("VINCENZO"));

		int actualScore=score.computeScore(personList);
		int expectedScore = 3194;
		Assert.assertEquals(expectedScore, actualScore);
	}

	
	/*
	 *Test case for list with duplicated names
	 * @input sorted list
	 * @expect score based on sorted list
	 * 
	 */
	@Test
	public void testfile2ArrayValidWithDups(){
		personList.clear();
		personList.add(new Person("LINDA"));
		personList.add(new Person("BA"));
		personList.add(new Person("BA"));
		int actualScore=score.computeScore(personList);
		int expectedScore = 129;
		Assert.assertEquals(expectedScore, actualScore);
	}
	
	
	/*
	 * 
	 * tear down method
	 * delete the file a.txt and set personList to null
	 * 
	 */
	@After
	public void tearDown() {
		personList =null;
	}

}
