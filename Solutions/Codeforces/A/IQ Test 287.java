import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.BufferedWriter;
import java.io.Writer;
import java.io.OutputStreamWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
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
            char[][] grid = new char[4][4];
            for (int i = 0; i < 4; ++i)
                grid[i] = in.nextCharArray();

            int[] x = {0, 1, 1, 0};
            int[] y = {0, 0, 1, 1};

            boolean can = false;

            outer:
            for (int i = 0; i < 3; ++i) {
                for (int j = 0; j < 3; ++j) {
                    int a = 0;
                    int b = 0;
                    for (int k = 0; k < 4; ++k) {
                        if (grid[i + x[k]][j + y[k]] == '.')
                            ++b;
                        else
                            ++a;
                    }

                    if (a == 3 && b == 1 || b == 3 && a == 1 || a == 4 || b == 4) {
                        can = true;
                        break outer;
                    }
                }
            }

            out.println(can ? "YES" : "NO");
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

    static class Scanner {
        private BufferedReader bufferedReader;

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

        public char[] nextCharArray() {
            char[] charArray = this.nextLine().toCharArray();

            return charArray;
        }

    }
}