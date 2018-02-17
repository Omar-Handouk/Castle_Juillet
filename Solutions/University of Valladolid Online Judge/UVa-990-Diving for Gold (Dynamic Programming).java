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
        Diving_For_Gold_990 solver = new Diving_For_Gold_990();
        solver.solve(1, in, out);
        out.close();
    }

    static class Diving_For_Gold_990 {
        int t;
        int w;
        int n;
        int[] d;
        int[] v;
        int[][] memo;
        private int ans;
        private StringBuilder build;

        public void solve(int testNumber, FastReader in, OutputWriter out) {


            boolean first = true;
            while (in.ready()) {
                if (!first)
                    out.println();

                build = new StringBuilder();
                first = false;

                t = in.nextInt();
                w = in.nextInt();
                n = in.nextInt();

                d = new int[n];
                v = new int[n];
                for (int i = 0; i < n; ++i) {
                    d[i] = in.nextInt();
                    v[i] = in.nextInt();
                }

                memo = new int[n + 1][t + 1];
                for (int[] row : memo)
                    Arrays.fill(row, -1);

                ans = 0;
                int max = dp(0, t);
                contruct(0, t);
                out.println(max);
                out.println(ans);
                out.print(build);
            }
        }

        private int dp(int idx, int rem) {
            if (idx == n)
                return 0;
            else if (memo[idx][rem] != -1)
                return memo[idx][rem];

            int max = dp(idx + 1, rem);

            if (rem >= (w * d[idx] + 2 * w * d[idx]))
                max = Math.max(max, v[idx] + dp(idx + 1, rem - (w * d[idx] + 2 * w * d[idx])));

            return memo[idx][rem] = max;
        }

        private void contruct(int idx, int rem) {
            if (idx == n)
                return;

            if (dp(idx, rem) == dp(idx + 1, rem))
                contruct(idx + 1, rem);
            else {
                ans = ans + 1;
                build.append(d[idx] + " " + v[idx] + "\n");
                contruct(idx + 1, rem - (w * d[idx] + 2 * w * d[idx]));
            }
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

        public void println() {
            writer.println();
        }

        public void close() {
            writer.close();
        }

        public void println(int i) {
            writer.println(i);
        }

    }

    static class FastReader {
        BufferedReader bufferedReader;
        StringTokenizer stringTokenizer;

        public FastReader(InputStream stream) {
            bufferedReader = new BufferedReader(new InputStreamReader(stream));
        }

        public boolean ready() {
            boolean isReady = false;

            try {
                isReady = bufferedReader.ready();
            } catch (IOException error) {
                error.printStackTrace();
            }

            return isReady;
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

