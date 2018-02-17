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
        Most_Potent_Corner solver = new Most_Potent_Corner();
        solver.solve(1, in, out);
        out.close();
    }

    static class Most_Potent_Corner {
        int dim;
        int[] cube;
        int[] pot;

        public void solve(int testNumber, FastReader in, OutputWriter out) {

            while (in.ready()) {
                dim = in.nextInt();
                cube = new int[(int) Math.pow(2, dim)];
                pot = new int[cube.length];

                for (int i = 0; i < cube.length; ++i)
                    cube[i] = in.nextInt();

                potency();
                out.println(maxPot());
            }
        }

        void potency() {
            for (int i = 0; i < cube.length; ++i) {
                for (int j = 0; j < dim; ++j) {
                    pot[i] += cube[i ^ (1 << j)];
                }
            }
        }

        int maxPot() {
            int max = 0;
            for (int i = 0; i < cube.length; ++i) {
                for (int j = 0; j < dim; ++j) {
                    max = Math.max(max, pot[i] + pot[i ^ (1 << j)]);
                }
            }

            return max;
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

