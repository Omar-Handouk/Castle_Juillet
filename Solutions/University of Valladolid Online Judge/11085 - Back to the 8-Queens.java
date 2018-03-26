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
        Back_To_Eight_Queens solver = new Back_To_Eight_Queens();
        solver.solve(1, in, out);
        out.close();
    }

    static class Back_To_Eight_Queens {
        int[] row;
        int cs = 1;
        long ans;

        public void solve(int testNumber, Scanner in, OutputWriter out) {

            while (in.ready()) {
                row = new int[8];
                for (int i = 0; i < 8; ++i)
                    row[i] = in.nextInt() - 1;

                ans = Long.MAX_VALUE;

                sol(0, 0);

                out.printf("Case %d: %d\n", cs++, ans);
            }
        }

        boolean place(int r, int c) {
            for (int i = 0; i < c; ++i)
                if (row[i] == r || Math.abs(i - c) == Math.abs(row[i] - r))
                    return false;
            return true;
        }

        void sol(int c, int moves) {
            if (c == 8) {
                ans = Math.min(ans, moves);
                return;
            }

            //Leave if placement is correct
            if (place(row[c], c))
                sol(c + 1, moves);

            //Change
            for (int i = 0; i < 8; ++i) {
                if (place(i, c)) {
                    int temp = row[c];
                    row[c] = i;
                    sol(c + 1, moves + 1);
                    row[c] = temp;
                }
            }
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

        public boolean ready() {
            boolean isReady = false;

            try {
                isReady = bufferedReader.ready();
            } catch (IOException Error) {
                Error.printStackTrace();
            }

            return isReady;
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

