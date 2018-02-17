import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.io.BufferedWriter;
import java.text.DecimalFormat;
import java.util.InputMismatchException;
import java.io.IOException;
import java.text.NumberFormat;
import java.io.Writer;
import java.io.OutputStreamWriter;
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
        InputReader in = new InputReader(inputStream);
        OutputWriter out = new OutputWriter(outputStream);
        Term_Strategy solver = new Term_Strategy();
        solver.solve(1, in, out);
        out.close();
    }

    static class Term_Strategy {
        int n;
        int m;
        static final int UNCAL = -1;
        int[][] scores;
        int[][] memo;

        public void solve(int testNumber, InputReader in, OutputWriter out) {

            int t = in.nextInt();

            while (t-- > 0) {
                n = in.nextInt();
                m = in.nextInt();

                scores = new int[n][m];
                for (int i = 0; i < n; ++i)
                    for (int j = 0; j < m; ++j)
                        scores[i][j] = in.nextInt();

                memo = new int[n][m + 5];
                for (int[] row : memo)
                    Arrays.fill(row, UNCAL);

                int ans = dp(0, 0);

                if (ans < 0)
                    out.println("Peter, you shouldn't have played billiard that much.");
                else {
                    DecimalFormat format = new DecimalFormat("0.00");
                    double score = Math.round((100 * ans / (double) n))  / 100.0;
                    out.println("Maximal possible average mark - " + format.format(score) + ".");
                }


            }
        }

        public int dp(int idx, int time) {
            if (time > m)
                return (int) -1e6;
            else if (idx == n)
                return 0;
            else if (memo[idx][time] != UNCAL)
                return memo[idx][time];

            int max = (int) -1e6;
            for (int i = 0; i < m; ++i) {
                if (scores[idx][i] < 5)
                    continue;

                max = Math.max(max, scores[idx][i] + dp(idx + 1, time + (i + 1)));
            }

            return memo[idx][time] = max;
        }

    }

    static class InputReader {
        private InputStream stream;
        private byte[] buf = new byte[1024];
        private int curChar;
        private int numChars;
        private InputReader.SpaceCharFilter filter;

        public InputReader(InputStream stream) {
            this.stream = stream;
        }

        public int read() {
            if (numChars == -1) {
                throw new InputMismatchException();
            }
            if (curChar >= numChars) {
                curChar = 0;
                try {
                    numChars = stream.read(buf);
                } catch (IOException e) {
                    throw new InputMismatchException();
                }
                if (numChars <= 0) {
                    return -1;
                }
            }
            return buf[curChar++];
        }

        public int nextInt() {
            int c = read();
            while (isSpaceChar(c)) {
                c = read();
            }
            int sgn = 1;
            if (c == '-') {
                sgn = -1;
                c = read();
            }
            int res = 0;
            do {
                if (c < '0' || c > '9') {
                    throw new InputMismatchException();
                }
                res *= 10;
                res += c - '0';
                c = read();
            } while (!isSpaceChar(c));
            return res * sgn;
        }

        public boolean isSpaceChar(int c) {
            if (filter != null) {
                return filter.isSpaceChar(c);
            }
            return isWhitespace(c);
        }

        public static boolean isWhitespace(int c) {
            return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
        }

        public interface SpaceCharFilter {
            public boolean isSpaceChar(int ch);

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

