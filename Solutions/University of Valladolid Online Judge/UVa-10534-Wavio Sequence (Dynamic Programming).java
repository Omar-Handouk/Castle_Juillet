import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.io.Writer;
import java.io.OutputStreamWriter;
import java.io.BufferedReader;
import java.util.Collections;
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
        Wavio_Sequence solver = new Wavio_Sequence();
        solver.solve(1, in, out);
        out.close();
    }

    static class Wavio_Sequence {
        public void solve(int testNumber, FastReader in, OutputWriter out) {

            while (in.ready()) {
                int n = in.nextInt();

                int[] arr = new int[n];
                for (int i = 0; i < n; i++) {
                    arr[i] = in.nextInt();
                }

                out.println(lis(arr));
            }

        }

        int lis(int[] arr) {
            ArrayList<Integer> x = new ArrayList<>();
            int[] l = new int[arr.length], r = new int[arr.length];

            for (int i = 0; i < arr.length; ++i) {
                int pos = Collections.binarySearch(x, arr[i]);

                if (pos < 0)
                    pos = -(pos + 1);

                if (x.size() <= pos)
                    x.add(arr[i]);
                else
                    x.set(pos, arr[i]);

                l[i] = pos;
            }

            x = new ArrayList<>();
            for (int i = arr.length - 1; 0 <= i; --i) {
                int pos = Collections.binarySearch(x, arr[i]);

                if (pos < 0)
                    pos = -(pos + 1);

                if (x.size() <= pos)
                    x.add(arr[i]);
                else
                    x.set(pos, arr[i]);

                r[i] = pos;
            }

            int ans = 1;
            for (int i = 0; i < arr.length; ++i)
                ans = Math.max(ans, 1 + 2 * Math.min(l[i], r[i]));

            return ans;
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

    static class FastReader {
        BufferedReader bufferedReader;
        StringTokenizer stringTokenizer;

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

        public String next() {
            while (stringTokenizer == null || !stringTokenizer.hasMoreTokens()) {
                try {
                    stringTokenizer = new StringTokenizer(bufferedReader.readLine());
                } catch (IOException error) {
                    error.printStackTrace();
                }
            }

            return stringTokenizer.nextToken();
        }

        public int nextInt() {
            return Integer.parseInt(this.next());
        }

    }
}

