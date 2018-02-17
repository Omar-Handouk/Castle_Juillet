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
    static int vertices;
	static int edges;
	
    // Intializations of Data Structures & Graphs
    static Graph graph;
    static int[] color;

    public static void main(String[] args) throws IOException
    {
        //---------------------Solution Start---------------------//
        while (true)
		{
			vertices = in.nextInt();
			if (vertices == 0) break;
			
			edges = in.nextInt();
			
			graph = new Graph(vertices);
			color = new int[vertices];
			Arrays.fill(color, UNCAL);
			
			while (edges --> 0)
			{
				int u = in.nextInt();
				int v = in.nextInt();
				
				graph.addEdge(u, v);
				graph.addEdge(v, u);
			}
			
			out.println(bicolorable(0) ? "BICOLORABLE." : "NOT BICOLORABLE.");
		}
        //---------------------Solution  End---------------------//
        out.flush();
        out.close();
    }
    
    static boolean bicolorable(int source)
    {
		Queue<Integer> queue = new LinkedList<>();
			
		queue.add(source);
		color[source] = 0;
			
		boolean isPartite = true;
			
		while (!queue.isEmpty() && isPartite)
		{
			int current_node = queue.poll();
				
			for (int next_node : graph.edgeList(current_node))
			{
				if (color[next_node] == UNCAL)
				{
					queue.add(next_node);
					color[next_node] = 1 - color[current_node];
				}
				else if (color[current_node] == color[next_node])
				{
					isPartite = false;
					break;
				}
			}
		}
		
		return isPartite;
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
