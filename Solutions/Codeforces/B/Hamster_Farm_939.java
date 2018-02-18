import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Scanner;

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
        PrintWriter out = new PrintWriter(outputStream);
        TaskB solver = new TaskB();
        solver.solve(1, in, out);
        out.close();
    }

    static class TaskB {
        public void solve(int testNumber, Scanner in, PrintWriter out) {

            long n = in.nextLong();
            int k = in.nextInt();

            int type = 1;
            long left = Long.MAX_VALUE;
            long needed = 0;

            for (int i = 0; i < k; ++i) {
                long box = in.nextLong();
                long check = n % box;

                if (check < left) {
                    type = i + 1;
                    left = check;
                    needed = n / box;
                }
            }

            out.println(type + " " + needed);
        }

    }
}

