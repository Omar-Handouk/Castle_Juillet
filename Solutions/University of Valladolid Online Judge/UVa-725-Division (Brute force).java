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
        FastReader in = new FastReader(inputStream);
        OutputWriter out = new OutputWriter(outputStream);
        Division solver = new Division();
        solver.solve(1, in, out);
        out.close();
    }

    static class Division {
        public void solve(int testNumber, FastReader in, OutputWriter out) {

            boolean first = true;

            while (true) {
                int n = in.nextInt();
                if (n == 0) break;

                if (first)
                    first = false;
                else
                    out.println();

                boolean found = false;

                for (int i = 1234; i <= 99000 / n; ++i) {
                    int j = n * i;

                    int mask = (i < 10000 ? 1 : 0);

                    int tmp = i;
                    while (tmp != 0) {
                        mask |= (1 << (tmp % 10));
                        tmp /= 10;
                    }
                    tmp = j;
                    while (tmp != 0) {
                        mask |= (1 << (tmp % 10));
                        tmp /= 10;
                    }

                    if (mask == ((1 << 10) - 1)) {
                        if (j < 10000)
                            out.print(0 + "" + j);
                        else
                            out.print(j);

                        out.print(" / ");

                        if (i < 10000)
                            out.print(0 + "" + i);
                        else
                            out.print(i);

                        out.println(" = " + n);

                        found = true;
                    }
                }

                if (!found)
                    out.println("There are no solutions for " + n + ".");
            }
        }

    }

    static class FastReader {
        BufferedReader bufferedReader;
        StringTokenizer stringTokenizer;

        public FastReader(InputStream stream) {
            bufferedReader = new BufferedReader(new InputStreamReader(stream));
        }

        public String next() {
            while (stringTokenizer == null || !stringTokenizer.hasMoreTokens()) {
