import java.io.*;
import java.io.OutputStream;
import java.io.IOException;
import java.util.StringTokenizer;
import java.io.InputStream;

/**
 * Built using CHelper plug-in
 * Actual solution is at the top
 *
 * @author Omar-Handouk
 */
public class Main {
    public static void main(String[] args) throws IOException{
        /*InputStream inputStream = System.in;
        OutputStream outputStream = System.out;*/
        Scanner in = new Scanner(new FileInputStream(new File("input.txt")));
        OutputWriter out = new OutputWriter(new FileOutputStream("output.txt"));
        Cards_with_Numbers solver = new Cards_with_Numbers();
        solver.solve(1, in, out);
        out.close();
    }

    static class Cards_with_Numbers {
        public void solve(int testNumber, Scanner in, OutputWriter out) {

            int[] pos = new int[5001];

            int n = in.nextInt();
            int left = 2 * n;

            StringBuilder ans = new StringBuilder();
            for (int i = 1; i <= 2 * n; ++i) {
                int num = in.nextInt();
                if (pos[num] == 0)
                    pos[num] = i;
                else {
                    ans.append(pos[num] + " " + i + "\n");
                    left -= 2;
                    pos[num] = 0;
                }
            }

            if (left != 0)
                out.println(-1);
            else
                out.print(ans);
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