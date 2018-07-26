
public class RotationOfString {
public static boolean checkRotation(String word1, String word2){
	if(word1.length()!=word2.length())
		return false;
	else
	{
		String s3=word1.toLowerCase()+word1.toLowerCase();
		if(s3.contains(word2.toLowerCase()))
		return true;
	}
	return false;
	
}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(checkRotation("MAdhuis","ismadhu"));
	}

}
