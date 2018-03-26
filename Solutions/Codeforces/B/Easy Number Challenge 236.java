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
        Easy_Number_Challange solver = new Easy_Number_Challange();
        solver.solve(1, in, out);
        out.close();
    }

    static class Easy_Number_Challange {
        long mod = 1073741824;

        public void solve(int testNumber, Scanner in, OutputWriter out) {
            int a = in.nextInt();
            int b = in.nextInt();
            int c = in.nextInt();

            int[] divs = new int[1000001];
            for (int i = 1; i <= 1000000; ++i) {
                for (int j = 1; i * j <= 1000000; ++j) {
                    divs[i * j]++;
                }
            }

            long ans = 0;

            for (int i = 1; i <= a; ++i) {
                for (int j = 1; j <= b; ++j) {
                    for (int k = 1; k <= c; ++k) {
                        ans = (ans + divs[i * j * k]) % mod;
                    }
                }
            }

            out.println(ans);
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

        public void println(long i) {
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