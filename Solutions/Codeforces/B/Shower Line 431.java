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
        TaskB solver = new TaskB();
        solver.solve(1, in, out);
        out.close();
    }

    static class TaskB {
        int[][] g;
        int max = 0;

        public void solve(int testNumber, Scanner in, OutputWriter out) {

            g = new int[5][5];
            for (int i = 0; i < 5; ++i)
                for (int j = 0; j < 5; ++j)
                    g[i][j] = in.nextInt();

            int[] arr = {0, 1, 2, 3, 4};
            permute(arr, 5);

            out.println(max);
        }

        public void permute(int[] arr, int n) {
            if (n == 1)
                solve(arr);
            else {
                for (int i = 0; i < (n - 1); ++i) {
                    permute(arr, n - 1);

                    if (n % 2 == 0)
                        swap(arr, i, n - 1);
                    else
                        swap(arr, 0, n - 1);
                }

                permute(arr, n - 1);
            }
        }

        private void swap(int arr[], int i, int j) {
            int tmp = arr[i];
            arr[i] = arr[j];
            arr[j] = tmp;
        }

        public void solve(int[] arr) {
            int sum = 0;

            for (int i = 0; i < 5; ++i) {
                for (int j = i; j < 4; j += 2) {
                    sum += g[arr[j]][arr[j + 1]] + g[arr[j + 1]][arr[j]];
                }
            }

            max = Math.max(max, sum);
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

        public void close() {
            writer.close();
        }

        public void println(int i) {
            writer.println(i);
        }

    }
}