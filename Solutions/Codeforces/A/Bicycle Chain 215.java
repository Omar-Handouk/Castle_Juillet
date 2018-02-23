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
        Scanner in = new Scanner(inputStream);
        OutputWriter out = new OutputWriter(outputStream);
        TaskA solver = new TaskA();
        solver.solve(1, in, out);
        out.close();
    }

    static class TaskA {
        public void solve(int testNumber, Scanner in, OutputWriter out) {

            int n = in.nextInt();

            int[] a = new int[n];
            for (int i = 0; i < n; ++i)
                a[i] = in.nextInt();

            int m = in.nextInt();

            int[] b = new int[m];
            for (int i = 0; i < m; ++i)
                b[i] = in.nextInt();

            int max = 0;
            int num = 0;

            for (int i = 0; i < n; ++i) {
                for (int j = 0; j < m; j++) {
                    if (b[j] / a[i] < (double) b[j] / a[i])
                        continue;
                    else {
                        if (max == b[j] / a[i])
                            num++;
                        else if (max < b[j] / a[i]) {
                            max = b[j] / a[i];
                            num = 1;
                        }
                    }
                }
            }

            out.println(num);
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

        public void println(int i) {
            writer.println(i);
        }

    }
}

