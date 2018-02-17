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
	static int INF = 100000;
	static int tc, coins;
	
	// Intializations of Data Structures
	static int[]values;
	static int[][]memo;
	
	public static void main(String[] args) throws IOException{
		
		//---------------------Solution Start---------------------//
		tc = read.nextInt();
		while(tc --> 0){
			coins = read.nextInt();
			
			int total = 0;
			values = new int[coins];
			for (int i = 0 ; i < coins ; i++){
				values[i] = read.nextInt();
				total += values[i];
			}
			
			memo = new int[(total / 2) + 10][coins + 10];
			for (int[] row : memo)
				Arrays.fill(row, UNCAL);
			
			int max = dp(total / 2, 0);
			
			out.println(total - max - max);
		}
		//---------------------Solution  End---------------------//
		out.flush();
		out.close();
	}
	
	static int dp(int rem, int idx){
		if (rem < 0)
			return -INF;
		if (idx == coins)
			return 0;
		if (memo[rem][idx] != UNCAL)
			return memo[rem][idx];
			
		int leave = dp(rem, idx + 1);
		int take = values[idx] + dp(rem - values[idx], idx + 1);
		
		return memo[rem][idx] = Math.max(leave,take);
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
