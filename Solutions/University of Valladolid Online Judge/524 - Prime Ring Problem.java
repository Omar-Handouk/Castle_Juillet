import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
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
        PRP solver = new PRP();
        solver.solve(1, in, out);
        out.close();
    }

    static class PRP {
        int[] build;
        int mask = 1;

        public void solve(int testNumber, Scanner in, OutputWriter out) {

            //in.next();

            int cs = 1;
            boolean first = true;

            while (in.ready()) {
                if (!first)
                    System.out.println();
                else
                    first = false;

                int n = in.nextInt();
                build = new int[n];
                build[0] = 1;

                System.out.printf("Case %d:\n", cs++);
                backtrack(1);
            }
        }

        void backtrack(int c) {
            if (c == build.length && isPrime(1 + build[c - 1])) {
                print();
            }

            for (int n = 2; n <= build.length; ++n) {
                if (((1 << n) & mask) == 0 && isPrime(n + build[c - 1])) {
                    mask |= (1 << n);
                    build[c] = n;
                    backtrack(c + 1);
                    mask &= ~(1 << n);
                }
            }
        }

        boolean isPrime(int n) {
            for (int i = 2; i * i <= n; ++i)
                if (n % i == 0)
                    return false;
            return true;
        }

        void print() {
            for (int i = 0; i < build.length; ++i) {
                if (i < build.length - 1)
                    System.out.print(build[i] + " ");
                else
                    System.out.println(build[i]);
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

        public boolean ready() {
            boolean isReady = false;

            try {
                isReady = bufferedReader.ready();
            } catch (IOException Error) {
                Error.printStackTrace();
            }
