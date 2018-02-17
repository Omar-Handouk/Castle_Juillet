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


    // Intializations of Data Structures & Graphs
    static ArrayList<char[]> grid = new ArrayList<>();
    static ArrayList<int[]> queries = new ArrayList<>();
    static boolean[][] visited;

    public static void main(String[] args) throws IOException{

        //---------------------Solution Start---------------------//
        int tc = in.nextInt();
        in.nextLine();

        while (tc --> 0){

            while (in.ready()){

                char[] seq = in.nextLine().toCharArray();

                if (seq.length == 0)
                    break;
                else if ('0' <= seq[0] && seq[0] <= '9') {

                    String s = "";
                    for (char e : seq)
                        s += e;

                    String[] t = s.split(" ");
                    queries.add(new int[]{Integer.parseInt(t[0]), Integer.parseInt(t[1])});
                }
                else
                    grid.add(seq);
            }
            visited = new boolean[grid.size()][grid.get(0).length];

            for (int[] e : queries){
                System.out.println(flood(e[0] - 1, e[1] - 1));

                for (boolean[] row : visited)
                    Arrays.fill(row, false);
            }
            if (tc > 0) System.out.println();

            grid = new ArrayList<>();
            queries = new ArrayList<>();
        }
        //---------------------Solution  End---------------------//
        out.flush();
        out.close();
    }

    static int[] dx = {0,-1,1,1,0,1,-1,-1};
    static int[] dy = {1,1,0,1,-1,-1,0,-1};

    static int flood(int x, int y){

        if (!isValid(x,y)) return 0;

        visited[x][y] = true;

        int c = 1;

        for (int i = 0 ; i < 8 ; i++){
            int a = x + dx[i], b = y + dy[i];

            if (isValid(a, b) && !visited[a][b] && grid.get(a)[b] == 'W')
                c += flood(a, b);
        }

        return c;
    }

    static boolean isValid(int x, int y){

        return 0 <= x && x < grid.size() && 0 <= y && y < grid.get(x).length;
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
