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

            int[] freq = new int[8];
            boolean can = true;
            for (int i = 1; i <= n; ++i) {
                int num = in.nextInt();
                if (num == 5 || num == 7)
                    can = false;
                freq[num]++;
            }

            if (!can) {
                out.println(-1);
                out.flush();
                return;
            }


            StringBuilder ans = new StringBuilder();

            int groups = 0;

            //1 2 4
            int min = Math.min(freq[1], Math.min(freq[2], freq[4]));
            groups += min;

            for (int i = 1; i <= min; ++i) {
                ans.append("1 2 4\n");
                freq[1]--;
                freq[2]--;
                freq[4]--;
            }

            //1 2 6

            min = Math.min(freq[1], Math.min(freq[2], freq[6]));
            groups += min;
            for (int i = 1; i <= min; ++i) {
                ans.append("1 2 6\n");
                freq[1]--;
                freq[2]--;
                freq[6]--;
            }

            //1 3 6
            min = Math.min(freq[1], Math.min(freq[3], freq[6]));

            groups += min;

            for (int i = 1; i <= min; ++i) {
                ans.append("1 3 6\n");
                freq[1]--;
                freq[3]--;
                freq[6]--;
            }

            if (groups * 3 != n) {
                out.println(-1);
            } else {
                out.print(ans);
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

        public void flush() {
            writer.flush();
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