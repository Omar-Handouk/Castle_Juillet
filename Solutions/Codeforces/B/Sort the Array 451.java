import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Arrays;
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
        TaskB solver = new TaskB();
        solver.solve(1, in, out);
        out.close();
    }

    static class TaskB {
        public void solve(int testNumber, Scanner in, OutputWriter out) {
            int n = in.nextInt();
            int[] arr = new int[n + 1];
            for (int i = 1; i <= n; ++i)
                arr[i] = in.nextInt();

            int[] copy = Arrays.copyOf(arr, n + 1);
            Arrays.sort(copy);

            int start = -1;
            int end = -1;

            for (int i = 1; i <= n; ++i) {
                if (arr[i] != copy[i]) {
                    if (start == -1) {
                        start = i;
                        end = i;
                    } else
                        end = i;
                }
            }

            if (start == -1) {
                out.println("yes\n1 1");
                out.flush();
                return;
            }

            int range = end + 1 - start;

            for (int i = 0; i < range / 2; ++i) {
                int temp = arr[start + i];
                arr[start + i] = arr[end - i];
                arr[end - i] = temp;
            }

            boolean can = true;
            for (int i = 1; i <= n; ++i) {
                if (arr[i] < arr[i - 1])
                    can = false;
            }

            out.println(can ? "yes\n" + start + " " + end : "no");
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

        public void flush() {
            writer.flush();
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