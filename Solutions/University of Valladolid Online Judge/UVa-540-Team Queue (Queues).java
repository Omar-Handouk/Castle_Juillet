import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {

        int scene = 1;
        Reader in = new Reader();
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));

        while (true) {
            int n = in.nextInt();
            if (n == 0) break;

            out.printf("Scenario #%d\n", scene++);

            TeamQueue queue = new TeamQueue(n);
            while (n-- > 0) {
                int members = in.nextInt();
                int team[] = new int[members];
                for (int i = 0; i < members; ++i)
                    team[i] = in.nextInt();
                queue.addTeam(team, members);
            }

            while (true) {
                String query = in.next();
                if (query.equals("STOP")) break;

                if (query.equals("ENQUEUE")) {
                    int member = in.nextInt();
                    queue.enqueue(member);
                } else
                    out.println(queue.dequeue());
            }

            out.println();
        }

        out.flush();
        out.close();
    }
}

class TeamQueue {
    int teamID;
    int belongTo[];
    Queue<Integer> queue;
    Queue<Integer> teamQueue[];

    TeamQueue(int teams) {
        teamID = 0;
        queue = new LinkedList<>();
        teamQueue = new LinkedList[teams];
        for (int i = 0; i < teams; ++i)
            teamQueue[i] = new LinkedList<>();
        belongTo = new int[1000000];
    }

    void addTeam(int team[], int size) {
        for (int i = 0; i < size; ++i)
            belongTo[team[i]] = teamID;
        ++teamID;
    }

    void enqueue(int member) {
        int team = belongTo[member];
        if (teamQueue[team].size() == 0)
            queue.add(member);
        teamQueue[team].add(member);
    }

    int dequeue() {
        int member = teamQueue[belongTo[queue.peek()]].remove();
        if (teamQueue[belongTo[queue.peek()]].size() == 0)
            queue.remove();
        return member;
    }
}


class Reader {
    BufferedReader bufferedReader;
    StringTokenizer stringTokenizer;

    public Reader() {
        bufferedReader = new BufferedReader(new InputStreamReader(System.in));
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

    public long nextLong() {
        return Long.parseLong(this.next());
    }

    public float nextFloat() {
        return Float.parseFloat(this.next());
    }

    public double nextDouble() {
        return Double.parseDouble(this.next());
    }
}
