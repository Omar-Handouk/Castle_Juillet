import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.PrintStream;
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
        char[] s;

        public void solve(int testNumber, Scanner in, OutputWriter out) {

            int n = in.nextInt();
            int q = in.nextInt();

            s = in.nextCharArray();

            while (q-- > 0) {
                int l = in.nextInt();
                int r = in.nextInt();

                query(l - 1, r - l + 1);
            }
        }

        void query(int idx, int range) {
            Vector<Character> ss = new Vector<>(range);
            for (int i = 0; i < range; ++i)
                ss.add(s[idx++]);

            int[] freq = new int[10];

            int dp = 1;
            int i = 0;

            while (0 <= i && i < ss.size()) {
                boolean erase = false;

                if ('0' <= ss.get(i) && ss.get(i) <= '9') {
                    freq[ss.get(i) - '0']++;

                    char check = (char) (ss.get(i) - 1);
                    ss.remove(i);
                    ss.add(i, check);
                    if (check < '0')
                        erase = true;
                } else {
                    if (ss.get(i) == '>')
                        dp = 1;
                    else
                        dp = -1;

                    if (i + dp >= 0 && i + dp < ss.size() && (ss.get(i + dp) == '>' || ss.get(i + dp) == '<'))
                        erase = true;
                }

                if (erase) {
                    ss.remove(i);

                    if (dp == -1)
                        i--;
                } else
                    i += dp;
            }

            for (int e : freq)
                System.out.print(e + " ");

            System.out.println();
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
}