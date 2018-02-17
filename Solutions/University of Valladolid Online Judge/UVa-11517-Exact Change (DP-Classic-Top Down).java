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
	static int tc, price, notes;
	
	// Intializations of Data Structures
	static int[]values;
	static int[][] memo;
	
	public static void main(String[] args) throws IOException{
		
		//---------------------Solution Start---------------------//
		tc = read.nextInt();
		while (tc --> 0){
			price = read.nextInt();
			notes = read.nextInt();
			
			values = new int[notes];
			for (int i = 0 ; i < notes ; i++)
				values[i] = read.nextInt();
			
			memo = new int[price + 10][notes + 1];
			for (int[] row : memo)
				Arrays.fill(row, UNCAL);
				
			int min = dp(0,0);
			
			memo = new int[min + 10][notes + 1];
			for (int[] row : memo)
				Arrays.fill(row, UNCAL);
			
			out.printf("%d %d\n", min, cash(min,0));
		}
		//---------------------Solution  End---------------------//
		out.flush();
		out.close();
	}
	static int dp(int sum, int idx){
		if (price <= sum){
			return sum;
		}
		if (idx == notes)
			return INF;
			
		if (memo[sum][idx] != UNCAL)
			return memo[sum][idx];
		
		
		int leave = dp(sum, idx + 1);

		int take = dp(sum + values[idx], idx + 1);
		
		
		return memo[sum][idx] = Math.min(leave, take);
	}
	static int cash(int rem, int idx){
		if (rem == 0)
			return 0;
		if (idx == notes || rem < 0)
			return INF;
		
		if (memo[rem][idx] != UNCAL)
			return memo[rem][idx];
			
		int leave = cash(rem, idx + 1);
		int take = 1 + cash(rem - values[idx], idx + 1);
		
		return memo[rem][idx] = Math.min(leave,take);
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
