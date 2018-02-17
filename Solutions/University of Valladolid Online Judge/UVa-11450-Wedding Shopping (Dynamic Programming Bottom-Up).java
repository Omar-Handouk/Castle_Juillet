import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.io.Writer;
import java.io.OutputStreamWriter;
import java.io.BufferedReader;
import java.io.InputStream;

/**
 * Built using CHelper plug-in
 * Actual solution is at the top
 *
 * @author Omar-Handouk
 */
public class Main {
    public static void main(String[] args) {
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        FastReader in = new FastReader(inputStream);
        OutputWriter out = new OutputWriter(outputStream);
        Wedding_Shopping_11450 solver = new Wedding_Shopping_11450();
        solver.solve(1, in, out);
        out.close();
    }

    static class Wedding_Shopping_11450 {
        int[][] prices;
        int budget;
        int models;
        boolean[][] reachable;

        public void solve(int testNumber, FastReader in, OutputWriter out) {
            bottomUp(testNumber, in, out);
        }

        public void bottomUp(int testNumber, FastReader in, OutputWriter out) {
            int n = in.nextInt();

            while (n-- > 0) {
                budget = in.nextInt();
                models = in.nextInt();

                prices = new int[models][];
                for (int i = 0; i < models; ++i) {
                    prices[i] = new int[in.nextInt()];
                    for (int j = 0; j < prices[i].length; ++j)
                        prices[i][j] = in.nextInt();
                }

                reachable = new boolean[models + 5][budget + 5];

                //Fill-in Base Case
                for (int i = 0; i < prices[models - 1].length; ++i)
                    if (budget - prices[models - 1][i] >= 0)
                        reachable[models][budget - prices[models - 1][i]] = true;

                for (int i = models - 1; 1 <= i; --i) {
                    for (int j = 0; j <= budget; ++j) {
                        if (reachable[i + 1][j]) {
                            for (int k = 0; k < prices[i - 1].length; ++k)
                                if (j - prices[i - 1][k] >= 0)
                                    reachable[i][j - prices[i - 1][k]] = true;
                        }
                    }
                }

                int idx;
                for (idx = 0; idx <= budget && !reachable[1][idx]; ++idx) ;

                if (idx == budget + 1)
                    out.println("no solution");
                else
                    out.println(budget - idx);
            }
        }

    }

    static class FastReader {
        BufferedReader bufferedReader;
        StringTokenizer stringTokenizer;

        public FastReader(InputStream stream) {
            bufferedReader = new BufferedReader(new InputStreamReader(stream));
        }

        public String next() {
            while (stringTokenizer == null || !stringTokenizer.hasMoreTokens()) {
                try {
                    stringTokenizer = new StringTokenizer(bufferedReader.readLine());
                } catch (IOException error) {
                    error.printStackTrace();
                }
            }

            return stringTokenizer.nextToken();
        }

        public int nextInt() {
            return Integer.parseInt(this.next());
        }

    }

    static class OutputWriter {
        private final PrintWriter writer;

        public OutputWriter(OutputStream outputStream) {
            writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(outputStream)));
        }

        public OutputWriter(Writer writer) {
            this.writer = new PrintWriter(writer);
        }

        public void print(Object... objects) {
            for (int i = 0; i < objects.length; i++) {
                if (i != 0) {
                    writer.print(' ');
                }
                writer.print(objects[i]);
            }
        }

        public void println(Object... objects) {
            print(objects);
            writer.println();
        }

        public void close() {
            writer.close();
        }

        public void println(int i) {
            writer.println(i);
        }

    }
}

