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
        TaskA solver = new TaskA();
        solver.solve(1, in, out);
        out.close();
    }

    static class TaskA {
        public void solve(int testNumber, Scanner in, OutputWriter out) {
            int n = in.nextInt();
            int m = in.nextInt();

            char[][] grid = new char[n][m];

            for (int i = 0; i < n; ++i)
                grid[i] = in.nextCharArray();

            for (int i = 0; i < n; ++i) {
                boolean black = (i + 1) % 2 != 0;

                for (int j = 0; j < m; ++j) {
                    if (grid[i][j] == '-')
                        out.print('-');
                    else if (black)
                        out.print('B');
                    else
                        out.print('W');
                    black = !black;
                }

                out.println();
            }
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

        public void println() {
            writer.println();
        }

        public void print(char i) {
            writer.print(i);
        }

        public void close() {
            writer.close();
        }

    }
}

