package com.robin.occ.main;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.robin.occ.bean.Person;

/*
 * 
 * Class to compute score based on names
 * 
 * 
 * 
 */

public class NameScore implements Score{
	
	private Map<Person, Integer> scoreMap = new HashMap<Person,Integer>();
	
	
	
	/*
	 * 
	 * Compute score based on first name and last name
	 * The list will be sorted based on the first name and last name and 
	 * the score for each record is calculated by using the following formula
	 * sum(the value of each character in the name)*(the index of the record in the list starts from 1)
	 * @param A list of person
	 * @return the total score of the list
	 */
	@Override
	public int computeScore(List<Person> l) {
		
		Collections.sort(l, new Comparator<Person>() {
			public int compare(Person p1, Person p2) {
				
				return p1.toString().compareTo(p2.toString());
				
			}
		});
		int score = 0;
		for(int i=0;i<l.size();i++) {
			Person p = l.get(i);
			int personalScore = scoreMap.getOrDefault(p,0);
			if(personalScore == 0) {
				String firstName = p.getFirstName();
				if(firstName!=null) {
					char[] charArr = firstName.trim().toCharArray();
					for(char ch:charArr) {
						personalScore += (ch - '@');
					}
				}
				String lastName = p.getLastName();
				if(lastName!=null) {
					char[] charArr = lastName.trim().toCharArray();
					for(char ch:charArr) {
						personalScore += (ch - '@');
					}
				}
				scoreMap.put(p,personalScore);
			}
			
			score += personalScore*(i+1);
			
		}
		
		
		return score;
		
		
	}

}
