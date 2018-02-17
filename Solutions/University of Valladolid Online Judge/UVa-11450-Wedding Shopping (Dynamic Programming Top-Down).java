import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Arrays;
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
        int[][] memo;
        static final int UNCAL = -1;
        static final int INF = (int) 1e6;
        int budget;
        int models;

        public void solve(int testNumber, FastReader in, OutputWriter out) {
            topDown(testNumber, in, out);
        }

        public void topDown(int testNumber, FastReader in, OutputWriter out) {
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

                memo = new int[models + 5][budget + 5];
                for (int[] row : memo)
                    Arrays.fill(row, UNCAL);

                int ans = dp(0, budget);

                out.println(ans < 0 ? "no solution" : ans);

            }
        }

        public int dp(int idx, int rem) {
            if (rem < 0)
                return -INF;
            else if (idx == models)
                return budget - rem;
            else if (memo[idx][rem] != UNCAL)
                return memo[idx][rem];

            int max = -INF;
            for (int i = 0; i < prices[idx].length; ++i)
                max = Math.max(max, dp(idx + 1, rem - prices[idx][i]));

            return memo[idx][rem] = max;
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

    }
}

