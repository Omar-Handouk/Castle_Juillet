import java.io.FileReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class Main
{
    public static void main(String[] args) throws Throwable
    {
        Scanner in = new Scanner(System.in);
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));

        int n = in.nextInt();
        in.next();
        while (n --> 0)
        {
            String[] s = in.nextLine().split(" ");
            int m = 0, f = 0;
            for (int i = 0 ; i < s.length ; ++i)
            {

                for (int j = 0 ; j < s[i].length() ; ++j)
                {
                    if (s[i].charAt(j) == 'M')
                        ++m;
                    else
                        ++f;
                }
            }

            if (m == f && s.length != 1)
                out.println("LOOP");
            else
                out.println("NO LOOP");
        }

        out.flush();
        out.close();
    }
}
