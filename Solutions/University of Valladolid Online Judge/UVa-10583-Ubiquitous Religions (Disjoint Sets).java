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
        Ubtiquitous_Religions solver = new Ubtiquitous_Religions();
        solver.solve(1, in, out);
        out.close();
    }

    static class Ubtiquitous_Religions {
        public void solve(int testNumber, FastReader in, OutputWriter out) {

            int t = 1;

            while (true) {
                int n = in.nextInt();
                int m = in.nextInt();
                if (n == m && n == 0) break;

                Disjoint_Set set = new Disjoint_Set(n);
                while (m-- > 0) {
                    set.union(in.nextInt() - 1, in.nextInt() - 1);
                }

                out.printf("Case %d: %d\n", t++, set.getSetCount());
            }
        }

    }

    static class Disjoint_Set {
        private int Parent[];
        private int setSize[];
        private int Rank[];
        private int setCount;

        public Disjoint_Set(int Members) {
            Parent = new int[Members];
            setSize = new int[Members];
            Rank = new int[Members];
            setCount = Members;
            initialize(Members);
        }

        private void initialize(int Members) {
            for (int i = 0; i < Members; ++i) {
                Parent[i] = i;
                setSize[i] = 1;
            }
        }

        public int findSet(int Member) {
            if (Member == Parent[Member])
                return Member;

            return Parent[Member] = findSet(Parent[Member]);
        }

        public boolean sameSet(int Member_1, int Member_2) {
            return findSet(Member_1) == findSet(Member_2);
        }

        public void union(int Member_1, int Member_2) {
            if (sameSet(Member_1, Member_2))
                return;

            setCount = setCount - 1;

            int Parent_1 = findSet(Member_1);
            int Parent_2 = findSet(Member_2);

            if (Rank[Parent_1] > Rank[Parent_2]) {
                Parent[Parent_2] = Parent_1;
                setSize[Parent_1] = setSize[Parent_1] + setSize[Parent_2];
            } else if (Rank[Parent_1] < Rank[Parent_2]) {
                Parent[Parent_1] = Parent_2;
                setSize[Parent_2] = setSize[Parent_2] + setSize[Parent_1];
            } else {
                Parent[Parent_2] = Parent_1;
                setSize[Parent_1] = setSize[Parent_1] + setSize[Parent_2];
                Rank[Parent_1] = Rank[Parent_1] + 1;
            }
        }

        public int getSetCount() {
            return setCount;
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

    static class OutputWriter {
        private final PrintWriter writer;

        public OutputWriter(OutputStream outputStream) {
            writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(outputStream)));
        }

        public OutputWriter(Writer writer) {
            this.writer = new PrintWriter(writer);
        }

        public void printf(String format, Object... objects) {
            writer.printf(format, objects);
        }

        public void close() {
            writer.close();
        }

    }
}

