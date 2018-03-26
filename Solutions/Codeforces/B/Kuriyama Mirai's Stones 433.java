import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Vector;
import java.util.StringTokenizer;
import java.io.Writer;
import java.io.OutputStreamWriter;
import java.io.BufferedReader;
import java.util.Collections;
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
        Scanner in = new Scanner(inputStream);
        OutputWriter out = new OutputWriter(outputStream);
        TaskB solver = new TaskB();
        solver.solve(1, in, out);
        out.close();
    }

    static class TaskB {
        public void solve(int testNumber, Scanner in, OutputWriter out) {
            int n = in.nextInt();
            Vector<Integer> arr = new Vector<>();
            for (int i = 0; i < n; ++i)
                arr.add(in.nextInt());

            long[] cum1 = new long[n];
            cum1[0] = arr.get(0);
            for (int i = 1; i < n; ++i)
                cum1[i] = arr.get(i) + cum1[i - 1];

            Collections.sort(arr);

            long[] cum2 = new long[n];
            cum2[0] = arr.get(0);
            for (int i = 1; i < n; ++i)
                cum2[i] = arr.get(i) + cum2[i - 1];

            int q = in.nextInt();

            while (q-- > 0) {
                int type = in.nextInt();
                int l = in.nextInt() - 1;
                int r = in.nextInt() - 1;

                if (type == 1) {
                    if (l == 0)
                        out.println(cum1[r]);
                    else
                        out.println(cum1[r] - cum1[l - 1]);
                } else {
                    if (l == 0)
                        out.println(cum2[r]);
                    else
                        out.println(cum2[r] - cum2[l - 1]);
                }
            }

            //Comment
        }

    }

    static class Scanner {
        private BufferedReader bufferedReader;
        private StringTokenizer stringTokenizer;

        public Scanner(InputStream stream) {
            bufferedReader = new BufferedReader(new InputStreamReader(stream));
        }

        public String next() {
            while (stringTokenizer == null || !stringTokenizer.hasMoreTokens()) {
                try {
                    stringTokenizer = new StringTokenizer(bufferedReader.readLine());
                } catch (IOException Error) {
                    Error.printStackTrace();
                    ;
                }
            }

            return stringTokenizer.nextToken();
        }

        public int nextInt() {
            int number = Integer.parseInt(this.next());

            return number;
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

        public void println(long i) {
            writer.println(i);
        }

    }
}