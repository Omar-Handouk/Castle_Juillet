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
        Eight_Queens solver = new Eight_Queens();
        solver.solve(1, in, out);
        out.close();
    }

    static class Eight_Queens {
        int lines;
        int[] rows;
        int row;
        int column;

        public void solve(int testNumber, Scanner in, OutputWriter out) {

            int q = in.nextInt();

            while (q-- > 0) {

                row = in.nextInt() - 1;
                column = in.nextInt() - 1;

                System.out.println("SOLN       COLUMN");
                System.out.println(" #      1 2 3 4 5 6 7 8\n");

                rows = new int[8];
                lines = 0;

                backtrack(0);

                if (q != 0) System.out.println();
            }
        }

        void backtrack(int c) {

            if (c == 8 && rows[column] == row)
                print();

            for (int r = 0; r < 8; ++r) {
                if (place(r, c)) {
                    rows[c] = r;
                    backtrack(c + 1);
                }
            }
        }

        boolean place(int r, int c) {
            for (int i = 0; i < c; ++i)
                if (rows[i] == r || Math.abs(rows[i] - r) == Math.abs(i - c))
                    return false;
            return true;
        }

        void print() {

            lines += 1;

            if (lines < 10)
                System.out.print(" " + lines + "      ");
            else
                System.out.print(lines + "      ");

            for (int i = 0; i < 8; ++i) {
                if (i == 7)
                    System.out.println(rows[i] + 1);
                else
                    System.out.print(rows[i] + 1 + " ");
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

