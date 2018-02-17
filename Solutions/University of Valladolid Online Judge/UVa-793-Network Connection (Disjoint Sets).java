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
        Network_Connections solver = new Network_Connections();
        solver.solve(1, in, out);
        out.close();
    }

    static class Network_Connections {
        public void solve(int testNumber, FastReader in, OutputWriter out) {

            int n = in.nextInt();

            while (n-- > 0) {
                int computers = in.nextInt();

                Disjoint_Set set = new Disjoint_Set(computers);

                int successful = 0, unsuccessful = 0;

                while (in.ready()) {

                    String query[] = in.nextLine().split(" ");

                    if (query.length == 1)
                        break;

                    int memA = Integer.parseInt(query[1]) - 1;
                    int memB = Integer.parseInt(query[2]) - 1;

                    if (query[0].equals("c"))
                        set.union(memA, memB);
                    else if (set.sameSet(memA, memB))
                        successful++;
                    else
                        unsuccessful++;
                }

                out.printf("%d,%d\n", successful, unsuccessful);

                if (n != 0) out.println();
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

    }

    static class OutputWriter {
        private final PrintWriter writer;

        public OutputWriter(OutputStream outputStream) {
            writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(outputStream)));
        }

        public OutputWriter(Writer writer) {
            this.writer = new PrintWriter(writer);
        }

        public void println() {
            writer.println();
        }

        public void printf(String format, Object... objects) {
            writer.printf(format, objects);
        }

        public void close() {
            writer.close();
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

        public String nextLine() {
            String string = "";

            try {
                string = bufferedReader.readLine();
            } catch (IOException error) {
                error.printStackTrace();
            }

            return string;
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

