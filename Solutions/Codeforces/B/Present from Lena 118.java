import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
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

            int n = in.nextInt();

            for (int i = 1; i <= 2 * n + 1; ++i) {
                if (i <= n + 1) {
                    int spaces = 2 * (n + 1 - i);

                    for (int j = 1; j <= spaces; ++j)
                        System.out.print(" ");

                    int cal = 2 * n + 1 - 2 * (n + 1 - i);
                    int mid = 0;

                    print(cal, mid);

                    System.out.println();
                } else {
                    int spaces = 2 * (i - n - 1);

                    for (int j = 1; j <= spaces; ++j)
                        System.out.print(" ");

                    int cal = 2 * n + 1 - 2 * (i - n - 1);
                    int mid = 0;

                    print(cal, mid);

                    System.out.println();
                }
            }
        }

        void print(int cal, int mid) {
            for (int j = 0; j < cal; ++j) {
                if (j < cal / 2)
                    System.out.print(j + " ");
                else if (j == cal / 2) {
                    System.out.print(j);
                    mid = j;
                } else {
                    System.out.print(" " + (--mid));
                }
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

        public void close() {
            writer.close();
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
}

