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
    static HashMap<String, Integer> id;
    static HashMap<Integer, String> name;
    static int[] inDeg;
    static Graph graph;

    public static void main(String[] args) throws IOException
    {
        //---------------------Solution Start---------------------//

        int Case = 1;

        while (in.ready())
        {
            vertices = in.nextInt();

            inDeg = new int[vertices];
            graph = new Graph(vertices);

            id = new HashMap<>();
            name = new HashMap<>();

            for (int i = 0; i < vertices; i++)
            {
                String n = in.next();

                id.put(n ,i);
                name.put(i, n);
            }

            edges = in.nextInt();

            for (int i = 0 ; i < edges ; i++)
            {
                int u = id.get(in.next());
                int v = id.get(in.next());

                ++inDeg[v];

                graph.addEdge(u, v);
            }

            in.nextLine(); // Empty Line

            ans = new Vector<>();
            out.printf("Case #%d: Dilbert should drink beverages in this order: ", Case++);
            KahnsAlgorithm();
        }
        //---------------------Solution  End---------------------//
        out.flush();
        out.close();
    }

    static Vector<Integer> ans;

    static void KahnsAlgorithm()
    {
        PriorityQueue<Integer> queue = new PriorityQueue<>();

        for (int i = 0; i < vertices; i++)
        {
            if (!graph.isVisited(i) && inDeg[i] == 0)
            {
                queue.add(i);
                graph.visit(i);
            }
        }

        while (!queue.isEmpty())
        {
            int current_node = queue.poll();
            ans.add(current_node);

            for (int next_node : graph.edgeList(current_node))
            {
                if (--inDeg[next_node] == 0 && !graph.isVisited(next_node))
                {
                    queue.add(next_node);
                    graph.visit(next_node);
                }
            }
        }

        for (int i = 0 ; i < ans.size() ; i++)
        {
            if (i != ans.size() - 1)
                out.print(name.get(ans.get(i)) + " ");
            else
                out.println(name.get(ans.get(i)) + ".\n");
        }
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
