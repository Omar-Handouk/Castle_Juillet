import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.TreeMap;
import java.util.StringTokenizer;
import java.util.Map;
import java.util.Map.Entry;
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

            int[] arr = new int[n];
            for (int i = 0; i < n; ++i)
                arr[i] = in.nextInt();

            TreeMap<Integer, Integer> diff = new TreeMap<>();
            TreeMap<Integer, Integer> pos = new TreeMap<>();

            for (int i = 0; i < n; ++i) {
                if (!pos.containsKey(arr[i])) {
                    diff.put(arr[i], 0);
                    pos.put(arr[i], i);
                } else {
                    if (diff.get(arr[i]) == 0) {
                        diff.put(arr[i], i - pos.get(arr[i]));
                        pos.put(arr[i], i);
                    } else {
                        if (i - pos.get(arr[i]) != diff.get(arr[i]))
                            diff.put(arr[i], -1);
                        pos.put(arr[i], i);
                    }
                }
            }

            StringBuilder ans = new StringBuilder();
            int valid = 0;

            for (Map.Entry<Integer, Integer> e : diff.entrySet()) {
                if (e.getValue() == -1)
                    continue;
                ++valid;
                ans.append(e.getKey() + " " + e.getValue() + "\n");
            }

            out.println(valid);
            out.print(ans);
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
}