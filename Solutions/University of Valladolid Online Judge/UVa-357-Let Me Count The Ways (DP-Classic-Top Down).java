/* **Disclaimer** This solution uses a custom I/O routine.
 * It can be replaced with normal standards such as : Scanner, Bufferedreader, .......etc.
*/
import java.util.*;
import java.io.*;
import java.lang.*;
import java.math.*;
import java.text.*;
/*
 * @IO : BufferedReader, InputStreamReader, IOException.
 * @Util : Scanner, Arrays, ArrayList, StringTokenizer, TreeSet.
 * @Util : Collections, HashMap, HashSet.
 * @Lang : StringBuilder, StringBuffer.
 * @Math : BigInteger, BigDecimal.
 * @Text : DecimalFormat.
 * @System : System.currentTimeMillis(); , System.nanoTime();
 * @Custom I/O : new PrintWriter(new BufferedOutputStream(System.out))
 */

public class Main{
	
	// Custom I/O Routine
	public static PrintWriter out = new PrintWriter(System.out);
	public static Reader read = new Reader();
	
	// Intializations of Values
	static byte UNCAL = -1;
	static int money;
	
	// Intializations of Data Structures
	static int[] values = new int[]{1,5,10,25,50};
	static long[][] memo;
	
	public static void main(String[] args) throws IOException{
		
		//---------------------Solution Start---------------------//
		memo = new long[30100][10];
		for (long[] row : memo)
			Arrays.fill(row, UNCAL);
		
		while (read.ready()){
			money = read.nextInt();
			
			long solution = solve(money, 0);
			
			if (solution == 1)
				out.printf("There is only 1 way to produce %d cents change.\n", money);
			else
				out.printf("There are %d ways to produce %d cents change.\n", solution, money);
		}
		//---------------------Solution  End---------------------//
		
		out.flush();
		out.close();
	}
	
	static long solve(int rem, int coin){
		if (rem == 0)
			return 1;
		if (coin == 5){
			if (rem == 0)
				return 1;
			else
				return 0;
		}
		if (memo[rem][coin] != UNCAL)
			return memo[rem][coin];
			
		return memo[rem][coin] = (values[coin] <= rem ? solve((rem - values[coin]), coin) : 0) + solve(rem, coin + 1);
	}
}

class Reader{
	
	BufferedReader bufferedReader;
	StringTokenizer stringTokenizer;
	
	public Reader(){
		bufferedReader = new BufferedReader(new InputStreamReader(System.in));
	}
	
	public String next(){
		while (stringTokenizer == null || !stringTokenizer.hasMoreElements()){
			try{
				stringTokenizer = new StringTokenizer(bufferedReader.readLine());
			}
			catch (IOException Ex){
				Ex.printStackTrace();
			}
		}
		return stringTokenizer.nextToken();
	}
	
	public int nextInt(){
		return Integer.parseInt(this.next());
	}
	
	public long nextLong(){
		return Long.parseLong(this.next());
	}
	
	public double nextDouble(){
		return Double.parseDouble(this.next());
	}
	
	public String nextLine(){
		String str = "";
		try{
			str = bufferedReader.readLine();
		}
		catch (IOException Ex){
			Ex.printStackTrace();
		}
		return str;
	}
	
	public boolean ready() throws IOException{
		return bufferedReader.ready();
	}
}

