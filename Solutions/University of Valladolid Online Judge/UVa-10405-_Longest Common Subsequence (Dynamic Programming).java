import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
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
        LCS_10405 solver = new LCS_10405();
        solver.solve(1, in, out);
        out.close();
    }

    static class LCS_10405 {

    char[] a;
    char[] b;

    public void solve(int testNumber, FastReader in, OutputWriter out) {

        //in.nextLine();

        while (in.ready())
        {
            a = in.nextLine().toCharArray();
            b = in.nextLine().toCharArray();

            memo = new int[a.length + 5][b.length + 5];
            for (int[] row : memo)
                Arrays.fill(row, UNCAL);

            out.println(TopDown(a.length, b.length));
        }
    }

    int memo[][];
    static final int UNCAL = -1;

    int TopDown(int m, int n)
    {
        if (m == 0 || n == 0)
            return 0;
        else if (memo[m][n] != UNCAL)
            return memo[m][n];

        if (a[m - 1] == b[n - 1])
            memo[m][n] = 1 + TopDown(m - 1, n - 1);
        else
            memo[m][n] = Math.max(TopDown(m, n - 1), TopDown(m - 1, n));

        return memo[m][n];
    }

    int bottomUp(int m, int n)
    {
        for (int i = 0 ; i <= m ; ++i)
        {
            for (int j = 0; j <= n; ++j) {
                if (i == 0 || j == 0)
                {
                    memo[i][j] = 0;
                }
                else if (a[i - 1] == b[j - 1])
                {
                    memo[i][j] = 1 + memo[i - 1][j - 1];
                }
                else
                {
                    memo[i][j] = Math.max(memo[i][j - 1], memo[i - 1][j]);
                }
            }
        }

        return memo[m][n];
    }
}


    static class FastReader {
        BufferedReader bufferedReader;

        public FastReader(InputStream stream) {
            bufferedReader = new BufferedReader(new InputStreamReader(stream));
        }

        public boolean ready() {
            boolean isReady = false;

            try {
                isReady = bufferedReader.ready();
            } catch (IOException error) {
                error.printStackTrace();
            }

            return isReady;
        }

        public String nextLine() {
            String string = "";

            try {
                string = bufferedReader.readLine();
            } catch (IOException error) {
                error.printStackTrace();
            }

            return string;
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

