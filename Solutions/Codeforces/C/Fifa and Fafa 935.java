package Problems;

import IO.Scanner;
import IO.OutputWriter;

public class TaskC {
    public void solve(int testNumber, Scanner in, OutputWriter out) {

        double r = in.nextDouble();
        double x1 = in.nextDouble();
        double y1 = in.nextDouble();
        double x2 = in.nextDouble();
        double y2 = in.nextDouble();

        double dist = Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));

        if (x1 == x2 && y1 == y2)
        {
            out.println((x1 + (r / 2)) + " " + y1 + " " + (r / 2));
        }
        else if (r <= dist)
            out.println(x1 + " " + y1 + " " + r);
        else
        {
            double x = x2 - (dist + r) * (x2 - x1) / (2 * dist);
            double y = y2 - (dist + r) * (y2 - y1) / (2 * dist);
            double radius = (dist + r) / 2;
            out.println(x + " " + y + " " + radius);
        }
    }
}
