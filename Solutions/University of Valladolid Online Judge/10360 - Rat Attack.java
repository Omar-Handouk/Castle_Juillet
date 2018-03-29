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
        Rat_Attack solver = new Rat_Attack();
        solver.solve(1, in, out);
        out.close();
    }

    static class Rat_Attack {
        public void solve(int testNumber, Scanner in, OutputWriter out) {

            int queries = in.nextInt();

            while (queries-- > 0) {
                int radius = in.nextInt();
                int colonies = in.nextInt();

                int[][] killed = new int[1025][1025];

                for (int i = 0; i < colonies; ++i) {
                    int x = in.nextInt();
                    int y = in.nextInt();
                    int size = in.nextInt();

                    for (int tx = Math.max(0, x - radius); tx <= Math.min(x + radius, 1024); ++tx) {
                        for (int ty = Math.max(0, y - radius); ty <= Math.min(y + radius, 1024); ++ty) {
                            killed[tx][ty] += size;
                        }
                    }
                }

                int x = Integer.MAX_VALUE;
                int y = Integer.MAX_VALUE;
                int total = Integer.MIN_VALUE;

                for (int i = 0; i < 1025; ++i) {
                    for (int j = 0; j < 1025; ++j) {

                        if (killed[i][j] > total) {
                            total = killed[i][j];
                            x = i;
                            y = j;
                        }
                    }
                }

                out.printf("%d %d %d\n", x, y, total);
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

