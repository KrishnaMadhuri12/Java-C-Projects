import java.util.Arrays;


public class checkAnagram {
	public static boolean isAnAnagram(String word1, String word2)
	{
	if (word1 == null || word2 == null)
	return false;
	if (word1.length() != word2.length())
	return false;
	char[] test1 = (word1.toLowerCase()).toCharArray();
	char[] test2 = (word2.toLowerCase()).toCharArray();
	Arrays.sort(test1);
	Arrays.sort(test2);
	if(Arrays.equals(test1,test2))
	return true;

	else
		
	return false;

	}
	
	public static void main(String args[]){
		System.out.println(isAnAnagram("cat","ct"));
	}
}