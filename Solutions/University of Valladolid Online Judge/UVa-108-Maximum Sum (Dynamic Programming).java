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
        Maximum_Sum solver = new Maximum_Sum();
        solver.solve(1, in, out);
        out.close();
    }

    static class Maximum_Sum {
        public void solve(int testNumber, FastReader in, OutputWriter out) {

            int n = in.nextInt();

            int[][] grid = new int[n][n];
            for (int i = 0; i < n; ++i)
                for (int j = 0; j < n; ++j)
                    grid[i][j] = in.nextInt();

            int max = 0;

            int[] temp = new int[n];

            for (int i = 0; i < n; ++i) {
                Arrays.fill(temp, 0);

                for (int j = i; j < n; ++j) {
                    for (int k = 0; k < n; ++k)
                        temp[k] += grid[k][j];

                    int sum = kadane(temp);

                    max = Math.max(sum, max);
                }
            }

            out.println(max);
        }

        int kadane(int[] arr) {
            int currMax = 0, max = 0;
            for (int i = 0; i < arr.length; ++i) {
                currMax += arr[i];
                max = Math.max(max, currMax);
                if (currMax < 0) currMax = 0;
            }

            return max;
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

