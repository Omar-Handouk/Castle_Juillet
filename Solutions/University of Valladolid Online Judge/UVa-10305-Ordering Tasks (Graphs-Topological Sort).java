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
	static ArrayList<Integer>[] adjList;
	static boolean[] visited;
	static int[] inDeg;
	static ArrayList<Integer> solution;
	
	public static void main(String[] args) throws IOException{
		
		//---------------------Solution Start---------------------//
		
		while (true)
		{
			int n = in.nextInt(), m = in.nextInt();
			if (n == 0 && m == 0) break;
			
			adjList = new ArrayList[n];
			for (int i = 0; i < n; i++)
			{
				adjList[i] = new ArrayList<>();
			}
			
			visited = new boolean[n];
			inDeg = new int[n];
			solution = new ArrayList<>();
			
			for (int i = 0; i < m; i++)
			{
				int v = in.nextInt() - 1;
				int c = in.nextInt() - 1;
				++inDeg[c];
				adjList[v].add(c);
			}
			
			for (int i = 0 ; i < n ; i++)
				if (!visited[i] && inDeg[i] == 0)
					dfs(i);
			//out.println(solution.size());	
			for (int i = 0 ; i < n ; i++){
				if (i != n - 1)
					out.print((solution.get(i) + 1) + " ");
				else
					out.println(solution.get(i) + 1);
			}
		}
		
		//---------------------Solution  End---------------------//
		out.flush();
		out.close();
	}
	
	static void dfs(int v){
		
		visited[v] = true;
		solution.add(v);
		
		for (int e : adjList[v]){
			--inDeg[e];
			if (inDeg[e] == 0)
				dfs(e);
		}
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
