import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.io.BufferedWriter;
import java.io.Writer;
import java.io.OutputStreamWriter;
import java.util.InputMismatchException;
import java.io.IOException;
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
        Divisibilty solver = new Divisibilty();
        solver.solve(1, in, out);
        out.close();
    }

    static class Divisibilty {
        static final int UNCAL = -1;
        int n;
        int k;
        int[] values;
        int[][] memo;

        public void solve(int testNumber, InputReader in, OutputWriter out) {

            int t = in.nextInt();

            while (t-- > 0) {
                n = in.nextInt();
                k = in.nextInt();

                values = new int[n];
                for (int i = 0; i < n; i++) {
                    values[i] = in.nextInt();
                }

                memo = new int[n][k];
                for (int[] row : memo)
                    Arrays.fill(row, UNCAL);

                int ans = dp(1, (values[0] % k + k) % k);

                if (ans == 1)
                    out.println("Divisible");
                else
                    out.println("Not divisible");
            }
        }

        int dp(int idx, int sum) {
            if (idx == n) {
                if (sum % k == 0)
                    return 1;
                else
                    return 0;
            } else if (memo[idx][sum] != UNCAL)
                return memo[idx][sum];

            int positive = dp(idx + 1, ((sum + values[idx]) % k + k) % k);
            int negative = dp(idx + 1, ((sum - values[idx]) % k + k) % k);

            return memo[idx][sum] = Math.max(positive, negative);
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

