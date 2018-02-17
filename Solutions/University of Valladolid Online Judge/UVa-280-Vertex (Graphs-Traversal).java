import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.InputStream;

/**
 * Built using CHelper plug-in
 * Actual solution is at the top
 *
 * @author Omar Handouk
 */
public class Main {
    public static void main(String[] args) {
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        Reader in = new Reader(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        UVa_280_Vertex solver = new UVa_280_Vertex();
        solver.solve(1, in, out);
        out.close();
    }

    static class UVa_280_Vertex {
        static boolean[][] adjMat;
        static boolean[] visited;
        static int start;

        public void solve(int testNumber, Reader in, PrintWriter out) {

            while (true) {

                int vertices = in.nextInt();
                if (vertices == 0) break;

                adjMat = new boolean[vertices][vertices];
                visited = new boolean[vertices];

                boolean early_termination = false;

                for (int i = 1; i <= vertices; i++) {

                    int node = in.nextInt();
                    if (node == 0) {
                        early_termination = true;
                        break;
                    }

                    while (true) {

                        int edge = in.nextInt();
                        if (edge == 0) break;

                        adjMat[node - 1][edge - 1] = true;
                    }
                }

                if (!early_termination)
                    in.nextInt();

                int queries = in.nextInt();

                while (queries-- > 0) {
                    start = in.nextInt() - 1;
                    dfs(start);

                    int unreached = 0;
                    for (boolean element : visited) {
                        if (!element) ++unreached;
                    }

                    if (unreached != 0)
                        out.print(unreached + " ");
                    else
                        out.println(0);

                    for (int i = 0; unreached != 0; i++) {
                        if (!visited[i] && unreached != 1) {
                            out.print((i + 1) + " ");
                            --unreached;
                        } else if (!visited[i] && unreached == 1) {
                            out.println(i + 1);
                            --unreached;
                        }
                    }

                    Arrays.fill(visited, false);
                }
            }
        }

        static void dfs(int vertex) {

            if (vertex != start) visited[vertex] = true;

            for (int i = 0; i < adjMat[vertex].length; i++) {

                if (adjMat[vertex][i] && !visited[i] && i == start)
                    visited[i] = true;

                else if (adjMat[vertex][i] && !visited[i])
                    dfs(i);

            }
        }

    }

    static class Reader {
        BufferedReader bufferedReader;
        StringTokenizer stringTokenizer;

        public Reader(InputStream inputStream) {

            bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
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

    }
}

