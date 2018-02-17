import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Arrays;
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
        RMQSQ solver = new RMQSQ();
        solver.solve(1, in, out);
        out.close();
    }

    static class RMQSQ {
        public void solve(int testNumber, FastReader in, OutputWriter out) {

            int n = in.nextInt();
            int arr[] = new int[n];
            for (int i = 0; i < n; ++i)
                arr[i] = in.nextInt();

            Square_Root_Decomposition solve = new Square_Root_Decomposition(arr);

            int q = in.nextInt();
            while (q-- > 0) {
                int l = in.nextInt();
                int r = in.nextInt();

                out.println(arr[solve.query(l, r)]);
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

    static class Square_Root_Decomposition {
        private int[] arr;
        private int[] table;
        private int arrSize;
        private int blockSize;

        public Square_Root_Decomposition(int arr[]) {
            this.arr = arr;
            arrSize = arr.length;
            this.pre_process();
        }

        private void pre_process() {
            blockSize = (int) Math.sqrt(arrSize) + 1;

            table = new int[(arrSize + blockSize - 1) / blockSize];
            Arrays.fill(table, -1); //Uncalculated Value

            for (int i = 0; i < arrSize; ++i)
                func(i);
        }

        private void func(int i) {
            //Minimization Function
            if (table[i / blockSize] == -1)
                table[i / blockSize] = i;
            else if (arr[i] <= arr[table[i / blockSize]])
                table[i / blockSize] = i;
        }

        public int query(int left, int right) {
            int Min = Integer.MAX_VALUE;
            int minIdx = -1;

            int block_left = left / blockSize;
            int block_right = right / blockSize;

            if (block_left == block_right) {
                for (int i = left; i <= right; ++i) {
                    minIdx = queryFunc(Min, i, minIdx, false);
                    Min = arr[minIdx];
                }
            } else {
                for (int i = left; i <= (block_left + 1) * blockSize - 1; ++i) {
                    minIdx = queryFunc(Min, i, minIdx, false);
                    Min = arr[minIdx];
                }
                for (int i = block_left + 1; i <= block_right - 1; ++i) {
                    minIdx = queryFunc(Min, i, minIdx, true);
                    Min = arr[minIdx];
                }
                for (int i = block_right * blockSize; i <= right; ++i) {
                    minIdx = queryFunc(Min, i, minIdx, false);
                    Min = arr[minIdx];
                }
            }

            return minIdx;
        }

        private int queryFunc(int Min, int i, int MinIdx, boolean check) {
            if (!check) {
                if (arr[i] < Min)
                    return i;
                else
                    return MinIdx;
            }

            if (arr[table[i]] < Min)
                return table[i];
            else
                return MinIdx;
        }

    }
}

