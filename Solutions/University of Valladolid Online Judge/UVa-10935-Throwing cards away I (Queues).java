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
    public static Scanner in = new Scanner(System.in);

    // Intializations of Values
    static byte UNCAL = -1;
    static int INF = (int) 1e6;
    static double EPSILON = (double) 1e-9;

    // Intializations of Data Structures & Graphs


    public static void main(String[] args) throws IOException
    {
        //---------------------Solution Start---------------------//
        while (true)
        {
            int n = in.nextInt();
            if (n == 0)
                break;

            Queue<Integer> Deck = new LinkedList<>();
            for (int i = 1 ; i <= n ; ++i)
				Deck.add(i);
			
			StringBuilder sb = new StringBuilder("Discarded cards:");
			
			while (Deck.size() != 1)
			{
				if (Deck.size() != 2)
					sb.append(" " + Deck.remove() + ",");
				else
					sb.append(" " + Deck.remove());
				
				Deck.add(Deck.remove());
			}
			
			out.println(sb.toString());
			out.println("Remaining card: " + Deck.peek());
        }
        //---------------------Solution  End---------------------//
        out.flush();
        out.close();
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


