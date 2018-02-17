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
        Bars solver = new Bars();
        solver.solve(1, in, out);
        out.close();
    }

    static class Bars {
        int[] values;
        int n;
        int p;
        int[][] memo;

        public void solve(int testNumber, FastReader in, OutputWriter out) {

            int t = in.nextInt();

            while (t-- > 0) {
                n = in.nextInt();
                p = in.nextInt();

                values = new int[p];
                for (int i = 0; i < p; ++i)
                    values[i] = in.nextInt();

                memo = new int[p + 1][100000];
                for (int[] row : memo)
                    Arrays.fill(row, -1);

                if (dp(0, n) == 1)
                    out.println("YES");
                else
                    out.println("NO");
            }
        }

        int dp(int idx, int rem) {
            if (rem < 0)
                return 0;
            else if (idx == p) {
                if (rem == 0)
                    return 1;
                else
                    return 0;
            } else if (memo[idx][rem] != -1)
                return memo[idx][rem];

            int take = dp(idx + 1, rem - values[idx]);
            int leave = dp(idx + 1, rem);

            return memo[idx][rem] = Math.max(take, leave);
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
}

