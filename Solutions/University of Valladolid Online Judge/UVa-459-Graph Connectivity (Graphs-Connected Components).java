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
    static int nodes;

    // Intializations of Data Structures & Graphs
    static ArrayList<Integer>[] adjList;
    static boolean[] visited;

    public static void main(String[] args) throws IOException{

        //---------------------Solution Start---------------------//
        int tc = in.nextInt();
        in.nextLine();
        while (tc --> 0)
        {
            nodes = in.next().charAt(0) - 'A' + 1;

            adjList = new ArrayList[nodes];
            for (int i = 0; i < nodes; i++)
            {
                adjList[i] = new ArrayList<>();
            }

            visited = new boolean[nodes];

            while (in.ready()){
                char[] desc = in.nextLine().toCharArray();
                if (desc.length == 0)
                    break;

                adjList[desc[0] - 'A'].add(desc[1] - 'A');
                adjList[desc[1] - 'A'].add(desc[0] - 'A');
            }

            int countC = 0;
            for (int i = 0 ; i < nodes ; i++){
                if (!visited[i]){
                    ++countC;
                    dfs(i);
                }
            }
			if (tc > 0)
				out.printf("%d\n\n", countC);
			else
				out.printf("%d\n", countC);
        }

        //---------------------Solution  End---------------------//
        out.flush();
        out.close();
    }

    static void dfs(int v){

        visited[v] = true;

        for (int e : adjList[v]){
            if (!visited[e])
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
