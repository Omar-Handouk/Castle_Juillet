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
            int n = in.nextInt();

            int x = 0;
            int y = 0;

            String steps = in.next();

            boolean right = steps.charAt(0) == 'R';
            if (right)
                ++x;
            else
                ++y;

            int coins = 0;

            for (int i = 1; i < n; ++i) {
                char c = steps.charAt(i);

                if (x == y) {
                    if (right) {
                        if (c == 'R')
                            ++x;
                        else {
                            ++coins;
                            right = false;
                            ++y;
                        }
                    } else {
                        if (c == 'U')
                            ++y;
                        else {
                            ++coins;
                            right = true;
                            ++x;
                        }
                    }
                } else {
                    if (c == 'R')
                        ++x;
                    else
                        ++y;
                }
            }
            out.println(coins);
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