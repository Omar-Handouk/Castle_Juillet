import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
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
        Rotated_Square solver = new Rotated_Square();
        solver.solve(1, in, out);
        out.close();
    }

    static class Rotated_Square {
        char[][] gridA;
        char[][] gridB;

        public void solve(int testNumber, InputReader in, OutputWriter out) {

            while (true) {
                int n = in.nextInt();
                int m = in.nextInt();

                if (n == 0 && m == 0)
                    break;

                gridA = new char[n][n];
                gridB = new char[m][m];

                for (int i = 0; i < n; ++i)
                    gridA[i] = in.readLine().toCharArray();
                for (int i = 0; i < m; ++i)
                    gridB[i] = in.readLine().toCharArray();

                int[] ans = new int[4];

                for (int i = 0; i <= 3; ++i) {
                    for (int j = 0; j < n; j++) {
                        for (int k = 0; k < n; k++) {

                            if (validRange(j, k)) {
                                if (check(j, k))
                                    ++ans[i];
                            }
                        }
                    }
                    rotate();
                }

                for (int i = 0; i < 4; ++i)
                    out.print(i != 3 ? ans[i] + " " : ans[i]);

                out.println();
            }
        }

        boolean validRange(int i, int j) {
            return gridB.length <= gridA.length - i && gridB.length <= gridA.length - j;
        }

        void rotate() {
            char[][] temp = new char[gridB.length][gridB.length];

            for (int i = 0; i < gridB.length; i++) {
                for (int j = 0; j < gridB.length; j++) {
                    temp[i][j] = gridB[gridB.length - 1 - j][i];
                }
            }

            gridB = temp;
        }

        boolean check(int x, int y) {
            for (int i = 0; i < gridB.length; ++i) {
                for (int j = 0; j < gridB.length; j++) {
                    if (gridA[x + i][y + j] != gridB[i][j])
                        return false;
                }
            }

            return true;
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

        private String readLine0() {
            StringBuilder buf = new StringBuilder();
            int c = read();
            while (c != '\n' && c != -1) {
                if (c != '\r') {
                    buf.appendCodePoint(c);
                }
                c = read();
            }
            return buf.toString();
        }

        public String readLine() {
            String s = readLine0();
            while (s.trim().length() == 0) {
                s = readLine0();
            }
            return s;
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

        public void println() {
            writer.println();
        }

        public void close() {
            writer.close();
        }

    }
}

