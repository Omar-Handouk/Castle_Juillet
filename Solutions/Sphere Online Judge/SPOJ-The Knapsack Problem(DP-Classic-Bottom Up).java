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
	static int capacity, items;
	
	// Intializations of Data Structures & Graphs
	static int[] weights, values;
	static int[][]memo;
	
	public static void main(String[] args) throws IOException{
		
		//---------------------Solution Start---------------------//
		while (read.ready()){
			capacity = read.nextInt();
			items = read.nextInt();
			
			weights = new int[items];
			values = new int[items];
			for (int i = 0 ; i < items ; i++){
				weights[i] = read.nextInt();
				values[i] = read.nextInt();
			}
			
			memo = new int[items][capacity + 1];
			for (int weight = 0 ; weight < capacity + 1 ; weight++){
				if (weights[0] <= weight){
					memo[0][weight] = values[0];
				}
			}
			
			for (int i = 1 ; i < items ; i++){
				for (int weight = 0 ; weight < capacity + 1 ; weight++){
					if (weights[i] > weight){
						memo[i][weight] = memo[i - 1][weight];
					}
					else{
						memo[i][weight] = Math.max(values[i] + memo[i - 1][weight - weights[i]], memo[i - 1][weight]);
					}
				}
			}
			
			int max = memo[0][capacity];
			for (int i = 1 ; i < items ; i++){
				max = Math.max(max, memo[i][capacity]);
			}
			out.println(max);
		}
		//---------------------Solution  End---------------------//
		out.flush();
		out.close();
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
