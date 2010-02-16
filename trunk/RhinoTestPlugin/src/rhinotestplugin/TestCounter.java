package rhinotestplugin;

public class TestCounter
{
	private static int counter = 0;
	
	public static void increment() {
		counter++;
	}
	
	public static int getCounter() {
		//System.out.println(counter);
		return counter;
	}
	
	public static void setCounter(int n) {
		counter = n;
	}
}
