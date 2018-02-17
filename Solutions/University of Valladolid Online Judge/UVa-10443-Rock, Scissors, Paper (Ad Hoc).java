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

public class Main
{
	
    // Custom I/O Routine
    public static PrintWriter out = new PrintWriter(System.out);
    public static Reader in = new Reader();

    // Intializations of Values
    static byte UNCAL = -1;
    static int INF = (int) 1e6;
    static double EPSILON = (double) 1e-9;
	static int r,c;
	
    // Intializations of Data Structures & Graphs
    static char[][] grid;
   
    public static void main(String[] args) throws IOException
    {
        //---------------------Solution Start---------------------//
        int tc = in.nextInt();
        
        while (tc --> 0)
        {
			r = in.nextInt();
			c = in.nextInt();
			int n = in.nextInt();
			
			grid = new char[r][c];
			for (int i = 0 ; i < r ; ++i)
				grid[i] = in.nextLine().toCharArray();
				
			while (n --> 0)
				war();
				
			for (int i = 0 ; i < r ; ++i)
			{
				for (int j = 0 ; j < c ; ++j)
				{
					out.print(grid[i][j]);
				}
				out.println();
			}
			
			if (tc != 0)
				out.println();
			
		}
        //---------------------Solution  End---------------------//
        out.flush();
        out.close();
    }
    
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};
    
    static void war()
    {
		char[][] update = new char[r][c];
		
		for (int i = 0 ; i < r ; ++i)
		{
			for (int j = 0 ; j < c ; ++j)
			{
				char opp;
				
				switch (grid[i][j])
				{
					case 'R' : opp = 'P'; break;
					case 'P' : opp = 'S'; break;
					default : opp = 'R'; break;
				}
				
				if (checkadj(i, j, opp))
				{
					update[i][j] = opp;
				}
				else
				{
					update[i][j] = grid[i][j];
				}
				
			}
		}
		
		grid = update;
	}
	
	static boolean checkadj(int x, int y, char c)
	{
		for (int i = 0 ; i < 4 ; ++i)
		{
			int a = x + dx[i], b = y + dy[i];
			
			if (valid(a,b) && grid[a][b] == c)
				return true;
		}
		
		return false;
	}
	
	static boolean valid(int x, int y)
	{
		return 0 <= x && x < r && 0 <= y && y < c;
	}
}

class Reader
{
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
class Graph
{
    int vertices;

    ArrayList<Integer>[] adjList;
    boolean[] visited;

    public Graph(int vertices)
    {
        this.vertices = vertices;

        adjList = new ArrayList[vertices];
        for (int i = 0 ; i < vertices ; i++)
            adjList[i] = new ArrayList<>();

        visited = new boolean[vertices];
    }

    public void addEdge(int u, int v)
    {
        adjList[u].add(v);
    }
	
	 public void removeEdge(int u, int v)
    {
        adjList[u].remove(v);
    }
	
    public boolean visit(int v)
    {
        return visited[v] = true;
    }

    public void clearVisit()
    {
        Arrays.fill(visited, false);
    }

    public boolean isVisited(int v)
    {
        return visited[v];
    }
    
    public ArrayList<Integer> edgeList(int v)
    {
		return adjList[v];
	}
}


