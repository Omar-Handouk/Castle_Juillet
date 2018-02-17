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
    	static int INF = (int) 1e6;
	static String pattern;
	static int Case = 1;
	static int nodes;

    // Intializations of Data Structures & Graphs
	static ArrayList<Integer>[] adjList;
	static boolean[] baseV;
	static boolean[] visited;

    public static void main(String[] args) throws IOException{

        //---------------------Solution Start---------------------//
        int tc = in.nextInt();
        
        while (tc --> 0)
		{
			nodes = in.nextInt();
			
			baseV = new boolean[nodes];
			visited = new boolean[nodes];
			
			adjList = new ArrayList[nodes];
			for (int i = 0 ; i < nodes ; i++)
				adjList[i] = new ArrayList<>();
				
			for (int i = 0 ; i < nodes ; i++)
				for (int j = 0 ; j < nodes ; j++)
					if (in.nextInt() == 1)
						adjList[i].add(j);
						
			dfsA(0);
			
			// Debug : out.println(Arrays.toString(baseV));
			
			out.printf("Case %d:\n", Case++);
			
			for (int i = 0 ; i < nodes ; i++){
				dfsB(0, i);
				print(i);
				// Debug : out.println(Arrays.toString(visited));
				Arrays.fill(visited, false);
			}
			pattern = null;
		}
		
        //---------------------Solution  End---------------------//
        out.flush();
        out.close();
    }
    
    static void dfsA(int v){
		
		baseV[v] = true;
		
		for (int e : adjList[v])
			if (!baseV[e])
				dfsA(e);
	}
	
	static void dfsB(int v, int cut){
		
		if (v == cut)
			return;
			
		visited[v] = true;
		
		for (int e : adjList[v])
			if (!visited[e])
				dfsB(e, cut);
	}
	
	static void print(int current){
		if (pattern == null)
		{
			pattern = "+";
			int sep = nodes / 2 * 2 + (nodes % 2 == 0 ? 0 : 1);
			for (int i = 1 ; i < sep + nodes ; i++)
				pattern += "-";
			pattern += "+";
			out.println(pattern);
		}
		
		out.print("|");
		for (int i = 0 ; i < nodes ; i++){
			
			if (!baseV[i] || visited[i] && i != current)
				out.print("N|");
			else
				out.print("Y|");
		}
		out.println();
		out.println(pattern);
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
