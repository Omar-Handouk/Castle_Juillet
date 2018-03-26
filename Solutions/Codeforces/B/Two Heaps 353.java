import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Vector;
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

            int n = 2 * in.nextInt();
            int[] arr = new int[n];
            //Distribute first then see
            for (int i = 0; i < n; ++i)
                arr[i] = in.nextInt();

            int[] freq = new int[101];
            for (int i = 0; i < n; ++i)
                freq[arr[i]]++;

            Vector<Integer>[] dist = new Vector[2];
            for (int i = 0; i < 2; ++i)
                dist[i] = new Vector<>();

            for (int i = 10; i <= 99; ++i) {
                int f = freq[i];
                if (f > 1)
                    dist[0].add(i);
                else
                    dist[1].add(i);
            }

            int[] ans = new int[n];
            for (int e : dist[0]) {
                int found = 0;
                for (int i = 0; i < n; ++i) {
                    int c = arr[i];
                    if (c == e) {
                        if (found == 0)
                            ans[i] = 1;
                        else
                            ans[i] = 2;
                        ++found;
                    }

                    if (found == 2)
                        break;
                }
            }

            boolean first = true;
            for (int e : dist[1]) {
                for (int i = 0; i < n; ++i) {
                    if (arr[i] == e) {
                        if (first)
                            ans[i] = 1;
                        else
                            ans[i] = 2;
                        first = !first;
                    }
                }
            }

            int distA = 0;
            int distB = 0;
            for (int i = 0; i < n; ++i) {
                if (ans[i] == 1)
                    ++distA;
                else if (ans[i] == 2)
                    ++distB;
            }

            out.println(distA * distB);

            int leftA = n / 2 - distA;

            for (int i = 0; i < n; ++i) {
                if (ans[i] == 0) {
                    if (leftA != 0) {
                        ans[i] = 1;
                        leftA--;
                    } else
                        ans[i] = 2;
                }
            }

            for (int e : ans)
                out.print(e + " ");
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

        public void close() {
            writer.close();
        }

        public void println(int i) {
            writer.println(i);
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