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
        TaskB solver = new TaskB();
        solver.solve(1, in, out);
        out.close();
    }

    static class TaskB {
        public void solve(int testNumber, Scanner in, OutputWriter out) {

            int[] low = new int[26];
            int[] high = new int[26];

            char[] s1 = in.nextCharArray();

            for (int i = 0; i < s1.length; ++i) {
                char c = s1[i];
                if (c == ' ')
                    continue;

                if (c < 'a')
                    high[c - 'A']++;
                else
                    low[c - 'a']++;
            }

            char[] s2 = in.nextCharArray();

            boolean can = true;

            for (int i = 0; i < s2.length; ++i) {
                char c = s2[i];
                if (c == ' ')
                    continue;

                if (c < 'a') {
                    if (high[c - 'A'] == 0)
                        can = false;
                    else
                        high[c - 'A']--;
                } else {
                    if (low[c - 'a'] == 0)
                        can = false;
                    else
                        low[c - 'a']--;
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