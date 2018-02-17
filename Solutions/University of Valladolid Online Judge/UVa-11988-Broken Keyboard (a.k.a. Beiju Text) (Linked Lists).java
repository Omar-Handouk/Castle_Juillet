/* This solution was conducted with the help of Ahmad El-Sagheer's code at :
https://github.com/AhmadElsagheer/UVa-Solutions/blob/master/v119/BrokenKeyboard_UVa11988.java */

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

public class Main {

    // Custom I/O Routine
    public static PrintWriter out = new PrintWriter(System.out);
    public static Reader in = new Reader();

    // Intializations of Values
    static byte UNCAL = -1;
    static int INF = (int) 1e6;
    static double EPSILON = (double) 1e-9;

    // Intializations of Data Structures & Graphs


    public static void main(String[] args) throws IOException {
        //---------------------Solution Start---------------------//
        while ( in .ready()) {
            LinkedList < String > words = new LinkedList < > ();

            String s = in .nextLine();

            int i = 0, j = 0;
            char last = ']';

            while (j < s.length()) {
                if (s.charAt(j) == '[') {
                    String word = s.substring(i, j);

                    if (last == '[')
                        words.addFirst(word);
                    else
                        words.addLast(word);

                    last = '[';
                    i = j + 1;
                } else if (s.charAt(j) == ']') {
                    String word = s.substring(i, j);


                    if (last == '[')
                        words.addFirst(word);
                    else
                        words.addLast(word);

                    last = ']';
                    i = j + 1;
                }

                ++j;
            }

            String word = s.substring(i, j);

            if (last == '[')
                words.addFirst(word);
            else
                words.addLast(word);

            StringBuilder build = new StringBuilder();

            while (!words.isEmpty())
                build.append(words.remove());

            out.println(build);

        }
        //---------------------Solution  End---------------------//
        out.flush();
        out.close();
    }
}

class Reader {
    BufferedReader bufferedReader;
    StringTokenizer stringTokenizer;

    public Reader() {
        bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    }

    public String next() {
        while (stringTokenizer == null || !stringTokenizer.hasMoreElements()) {
            try {
                stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            } catch (IOException Ex) {
                Ex.printStackTrace();
            }
        }
        return stringTokenizer.nextToken();
    }

    public int nextInt() {
        return Integer.parseInt(this.next());
    }

    public long nextLong() {
        return Long.parseLong(this.next());
    }

    public double nextDouble() {
        return Double.parseDouble(this.next());
    }

    public String nextLine() {
        String str = "";
        try {
            str = bufferedReader.readLine();
        } catch (IOException Ex) {
            Ex.printStackTrace();
        }
        return str;
    }

    public boolean ready() throws IOException {
        return bufferedReader.ready();
    }
}
class Graph {
    int vertices;

    ArrayList < Integer > [] adjList;
    boolean[] visited;

    public Graph(int vertices) {
        this.vertices = vertices;

        adjList = new ArrayList[vertices];
        for (int i = 0; i < vertices; i++)
            adjList[i] = new ArrayList < > ();

        visited = new boolean[vertices];
    }

    public void addEdge(int u, int v) {
        adjList[u].add(v);
    }

    public void removeEdge(int u, int v) {
        adjList[u].remove(v);
    }

    public boolean visit(int v) {
        return visited[v] = true;
    }

    public void clearVisit() {
        Arrays.fill(visited, false);
    }

    public boolean isVisited(int v) {
        return visited[v];
    }

    public ArrayList < Integer > edgeList(int v) {
        return adjList[v];
    }
}
