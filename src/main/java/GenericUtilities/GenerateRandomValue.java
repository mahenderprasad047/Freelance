package GenericUtilities;

public class GenerateRandomValue 
{
	public static String GenerateRandomString(int numberOfCharacters)
	{
		
		String allCharaters = "abcdefghijklmnopqrstuvwxyz";
		String random = "";
		for(int i=0;i<numberOfCharacters;i++)
		{
			int temp = (int) (Math.random()*25);
			char temp1 = allCharaters.charAt(temp);
			random = random+temp1;
		}
		
		return random;	
	}
	
	public static String GenerateRandomNumber(int numberOfDigits)
	{
		
		String allCharaters = "0123456789";
		String random = "";
		for(int i=0;i<numberOfDigits;i++)
		{
			int temp = (int) (Math.random()*10);
			char temp1 = allCharaters.charAt(temp);
			random = random+temp1;
		}
		
		return random;	
	}
}
