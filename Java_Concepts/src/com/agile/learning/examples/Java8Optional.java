package com.agile.learning.examples;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/****
 * Optional represents a value which may or may not exist
 * Optional helps to avoid null pointer exceptions and teh proper use of get
 *  and if present methods will helps us in avoiding no such element exception
 * Topics to remember:
 * What's optional
 * empty
 * with value
 * what if null?
 * checking for presence
 * get
 * action if present
 * alternative value
 * alternative exception
 * @author kkompell
 *
 */
public class Java8Optional {

	public static int compute(List<Integer> numbers)
	{
		int result=0;
		for(int number: numbers)
		{
			if(number%2==0)
				return number*2;
		}
		return result;
		
	}
	//if present
	//map can be used to transformation
	public static Optional<Integer> computeOptional(List<Integer> numbers)
	{
		for ( int number: numbers)
		{
			if(number%2==0)
			{
				return Optional.of(number *2); //of methods blows off if the value is null so we need to use ofNullable
			}
		}
		return Optional.empty();
	}
	
public static void receiveOptional(Optional<Integer> number)
{
	/** the below two lines of code is learn about map of Optional.ofNullable() type;
	 * 
	Optional<String> result= number.map(value -> value.toString()); //-> lambda experssion
	System.out.println("done");
	**/
	/**.orElse if it is not present alternate value
	 * Coming up with isPresent method of optional
	 * also get method
	 */
	if(number.isPresent())
	{
		System.out.println("got it:"+ number.get()); //if the optional is emplty then it will throw No such element found
	}
	else
		System.out.println("nope");
	/***
	 * Using number.ifPresent
	 */
	number.ifPresent(value -> System.out.println());
	number.ifPresent(System.out::println);
	
	/***
	 * Custom exception that is to morph exception
	 */
	System.out.println(number.orElseThrow(() -> new RuntimeException("Run back")));//Supplier is going to create exception
}
	
	
public static void main(String[] args) {
		// TODO Auto-generated method stub
List<Integer> number1=Arrays.asList(1,2,3,4,5,6);
List<Integer> number2=Arrays.asList(1,0,3);
List<Integer> number3=Arrays.asList(1,3);
System.out.println(compute(number1));
System.out.println(compute(number2));
System.out.println(compute(number3));


System.out.println(computeOptional(number1));

receiveOptional(Optional.empty());
Integer value1=6;
receiveOptional(Optional.ofNullable(value1));
Integer value2=null;
receiveOptional(Optional.ofNullable(value2));
	}
	

}
