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
        Jill_Rides_Again solver = new Jill_Rides_Again();
        solver.solve(1, in, out);
        out.close();
    }

    static class Jill_Rides_Again {
        public void solve(int testNumber, FastReader in, OutputWriter out) {

            int t = in.nextInt();

            int route = 1;

            while (t-- > 0) {
                int n = in.nextInt();

                int[] roads = new int[n - 1];
                for (int i = 0; i < n - 1; ++i)
                    roads[i] = in.nextInt();

                int currMax = 0, max = 0;
                int start = -1, end = -1, s = 0;
                for (int i = 0; i < n - 1; ++i) {
                    currMax += roads[i];

                    if (currMax > max || currMax == max && i - s > end - start) {
                        max = currMax;
                        start = s;
                        end = i;
                    }

                    if (currMax < 0) {
                        currMax = 0;
                        s = i + 1;
                    }
                }


                if (end == -1)
                    out.printf("Route %d has no nice parts\n", route++);
                else
                    out.printf("The nicest part of route %d is between stops %d and %d\n", route++, start + 1, end + 2);


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

        public void printf(String format, Object... objects) {
            writer.printf(format, objects);
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

