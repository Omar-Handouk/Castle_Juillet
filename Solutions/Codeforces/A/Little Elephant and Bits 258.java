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
        TaskA solver = new TaskA();
        solver.solve(1, in, out);
        out.close();
    }

    static class TaskA {
        public void solve(int testNumber, Scanner in, OutputWriter out) {

            char[] number = in.nextCharArray();

            boolean noZeros = true;

            for (int i = 0; i < number.length; ++i) {
                if (number[i] == '0') {
                    noZeros = false;
                    number[i] = 'x';
                    break;
                }
            }

            if (noZeros) {
                for (int i = 0; i < number.length - 1; ++i)
                    out.print(number[i]);
            } else {
                for (int i = 0; i < number.length; ++i) {
                    if (number[i] == 'x')
                        continue;
                    out.print(number[i]);
                }
            }
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

    static class OutputWriter {
        private final PrintWriter writer;

        public OutputWriter(OutputStream outputStream) {
            writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(outputStream)));
        }

        public OutputWriter(Writer writer) {
            this.writer = new PrintWriter(writer);
        }

        public void print(char i) {
            writer.print(i);
        }

        public void close() {
            writer.close();
        }

    }
}