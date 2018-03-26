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
        Another_N_Queens_Problem solver = new Another_N_Queens_Problem();
        solver.solve(1, in, out);
        out.close();
    }

    static class Another_N_Queens_Problem {
        int row;
        int rightDiag;
        int leftDiag;
        int dim;
        int cs = 1;
        char[][] grid;

        public void solve(int testNumber, Scanner in, OutputWriter out) {

            while (true) {
                dim = in.nextInt();

                if (dim == 0)
                    break;

                grid = new char[dim][];
                for (int i = 0; i < dim; ++i)
                    grid[i] = in.nextCharArray();

                row = 0;
                rightDiag = 0;
                leftDiag = 0;

                long ans = backtrack(0);

                out.printf("Case %d: %d\n", cs++, ans);
            }
        }

        long backtrack(int col) {
            if (col == dim)
                return 1;

            long ans = 0;
            for (int rw = 0; rw < dim; ++rw) {
                if (grid[rw][col] == '.' && ((1 << rw) & row) == 0 && ((1 << (rw + col)) & rightDiag) == 0 && ((1 << (rw - col + dim - 1)) & leftDiag) == 0) {
                    row |= (1 << rw);
                    rightDiag |= (1 << (rw + col));
                    leftDiag |= (1 << (rw - col + dim - 1));

                    ans += backtrack(col + 1);

                    row &= (~(1 << rw));
                    rightDiag &= (~(1 << (rw + col)));
                    leftDiag &= (~(1 << (rw - col + dim - 1)));
                }
            }

            return ans;
        }

    }

    static class Scanner {
        private BufferedReader bufferedReader;
        private StringTokenizer stringTokenizer;

        public Scanner(InputStream stream) {
            bufferedReader = new BufferedReader(new InputStreamReader(stream));
        }

        public String nextLine() {
            String string = "";

            try {
                string = bufferedReader.readLine();
            } catch (IOException Error) {
                Error.printStackTrace();
            }

            return string;
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

        public char[] nextCharArray() {
            char[] charArray = this.nextLine().toCharArray();

            return charArray;
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

        public void printf(String format, Object... objects) {
            writer.printf(format, objects);
        }

        public void close() {
            writer.close();
        }

    }
}

