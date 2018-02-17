import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.io.OutputStream;
import java.io.PrintWriter;
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
        _8Queens solver = new _8Queens();
        solver.solve(1, in, out);
        out.close();
    }

    static class _8Queens {
        int[] queens = new int[8];
        int r;
        int cc;
        int line;
        int t;
        boolean p = false;

        public void solve(int testNumber, InputReader in, OutputWriter out) {

            t = in.nextInt();

            while (t-- > 0) {
                System.out.println("SOLN       COLUMN");
                System.out.println(" #      1 2 3 4 5 6 7 8");

                line = 1;
                p = false;

                r = in.nextInt() - 1;
                cc = in.nextInt() - 1;
                rec(0);

                System.out.println();

                if (p && t != 0) // Handle the last line, i.e put a new line if there is another test case to create a space
                    System.out.println();
            }
        }

        void rec(int c) {
            if (c == 8 && queens[cc] == r) {
                print();
            }

            for (int i = 0; i < 8; ++i) {
                if (check(c, i)) {
                    queens[c] = i;
                    rec(c + 1);
                }
            }
        }

        boolean check(int c, int r) {
            for (int i = 0; i < c; ++i) {
                if (queens[i] == r || Math.abs(i - c) == Math.abs(queens[i] - r))
                    return false;
            }

            return true;
        }

        void print() {
            System.out.println();
            p = true;

            if (line < 10)
                System.out.printf(" %d      ", line++);
            else
                System.out.printf("%d      ", line++);

            for (int i = 0; i < 8; ++i) {
                if (i != 7)
                    System.out.print(queens[i] + 1 + " ");
                else
                    System.out.print(queens[i] + 1);
            }
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

        public void close() {
            writer.close();
        }

    }
}

