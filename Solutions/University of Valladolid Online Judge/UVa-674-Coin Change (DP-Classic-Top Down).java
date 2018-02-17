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
	public static Reader in = new Reader();
	
	
	// Intializations of Values
	static byte UNCAL = -1;
	
	
	// Intializations of Data Structures & Graphs
	static int[] values = {1,5,10,25,50};
	static long[][] memo;
	
	public static void main(String[] args) throws IOException{
		
		//---------------------Solution Start---------------------//
		memo = new long[5][7490];
		for (long[] row : memo)
			Arrays.fill(row, UNCAL);
			
		while (in.ready())
		{
			int n = in.nextInt();
			out.println(dp(0,n));
		}
		//---------------------Solution  End---------------------//
		out.flush();
		out.close();
	}
	
	static long dp(int idx, int rem){
		if (rem == 0)
			return 1;
		else if (rem < 0)
			return 0;
		else if (idx == 5){
			if (rem == 0) return 1;
			else return 0;
		}
		else if (memo[idx][rem] != UNCAL)
			return memo[idx][rem];
			
		long leave = dp(idx + 1, rem);
		long take = dp(idx, rem - values[idx]);
		
		return memo[idx][rem] = leave + take; 
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
