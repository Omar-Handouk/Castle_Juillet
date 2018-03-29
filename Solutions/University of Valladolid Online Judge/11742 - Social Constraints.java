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
        Social_Constraints solver = new Social_Constraints();
        solver.solve(1, in, out);
        out.close();
    }

    static class Social_Constraints {
        int n;
        int m;
        int mask = 0;
        int[] perm;
        int[][] cons;

        public void solve(int testNumber, Scanner in, OutputWriter out) {

            while (true) {
                n = in.nextInt();
                m = in.nextInt();

                if (n == m && n == 0)
                    break;

                perm = new int[n];
                cons = new int[m][];
                for (int i = 0; i < m; ++i) cons[i] = in.nextIntArray();

                out.println(generate(0));
            }
        }

        int generate(int a) {
            if (a == n && check())
                return 1;

            int valid = 0;

            for (int i = 0; i < n; ++i) {
                if (((1 << i) & mask) == 0) {
                    mask |= (1 << i);
                    perm[a] = i;
                    valid += generate(a + 1);
                    mask &= ~(1 << i);
                }
            }

            return valid;
        }

        boolean check() {
            for (int i = 0; i < m; ++i)
                if (cons[i][2] > 0 && Math.abs(perm[cons[i][0]] - perm[cons[i][1]]) > cons[i][2] || 
                    cons[i][2] < 0 && Math.abs(perm[cons[i][0]] - perm[cons[i][1]]) < -cons[i][2])
                    return false;
            return true;
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

        public String[] nextStringArray() {
            String[] stringArray = this.nextLine().split(" ");

            return stringArray;
        }

        public int nextInt() {
            int number = Integer.parseInt(this.next());

            return number;
        }
