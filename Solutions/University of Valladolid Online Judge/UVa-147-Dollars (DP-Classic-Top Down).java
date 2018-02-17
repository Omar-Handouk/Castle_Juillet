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
	
	// Intializations of Data Structures & Graphs
	static int[] values = {5,10,20,50,100,200,500,1000,2000,5000,10000};
	static long[][] memo;
	
	public static void main(String[] args) throws IOException{
		
		//---------------------Solution Start---------------------//
		memo = new long[11][30001];
		for (long[] row : memo)
		{
			Arrays.fill(row, UNCAL);
		}
		
		
		while (true)
		{
			String[] in = read.next().split("\\.");
			
			money = Integer.parseInt(in[0] + in[1]);
			if (money == 0)
			{
				break;
			}
			
			long answer = combinations(0, money);
			
			String s = in[0] + "." + in[1];
			
			while (s.length() < 6)
			{
				s = " " + s;
			}
			
			String m = Long.toString(answer);
			while (m.length() < 17)
			{
				m = " " + m;
			}
			
			out.println(s + m);
		}
		//---------------------Solution  End---------------------//
		out.flush();
		out.close();
	}
	
	static long combinations(int idx, int rem){
		if (rem == 0)
		{
			return 1;
		}
		
		else if (rem < 0 || idx == 11)
		{
			return 0;
		}
		
		if (memo[idx][rem] != UNCAL)
		{
			return memo[idx][rem];
		}
		
		long take = combinations(idx, rem - values[idx]);
		long leave = combinations(idx + 1, rem);
		
		return memo[idx][rem] = take + leave;
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
