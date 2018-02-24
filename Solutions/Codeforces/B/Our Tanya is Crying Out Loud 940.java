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
        TaskB solver = new TaskB();
        solver.solve(1, in, out);
        out.close();
    }

    static class TaskB {
        public void solve(int testNumber, Scanner in, OutputWriter out) {

            long n = in.nextLong();
            long k = in.nextLong();
            long a = in.nextLong();
            long b = in.nextLong();

            if (k == 1 || n < k) {
                out.print(a * (n - 1));
                out.flush();
                return;
            }

            long total = 0;

            while (k <= n) {
                if (n % k != 0) {
                    long rem = n % k;
                    n -= rem;
                    total += a * rem;
                } else {
                    if ((n - (n / k)) * a <= b)
                        total += (n - (n / k)) * a;
                    else
                        total += b;
                    n = n / k;
                }
            }

            out.println(total + (n - 1) * a);


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

        public void flush() {
            writer.flush();
        }

        public void print(long i) {
            writer.print(i);
        }

        public void println(long i) {
            writer.println(i);
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

        public long nextLong() {
            long number = Long.parseLong(this.next());

            return number;
        }

    }
}