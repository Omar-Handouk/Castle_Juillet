
import java.util.*;
import java.io.*;

public class Main
{

  static Reader in = new Reader();
  static PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));

  public static void main(String[] args) throws IOException{
    int testCases = in.nextInt();

    while (testCases --> 0)
    {
      in.nextLine();

      for (int i = 0 ; i < 3 ; ++i){
        String seq = in.nextLine();

        for (int j = 0 ; j < 3 ; ++j)
          grid[0][i][j] = Character.getNumericValue(seq.charAt(j));
      }

      out.println(transform());
    }

    out.flush();
    out.close();
  }

  static int[][][] grid = new int[2][3][3];
  static int[] dx = {0, 0, 1, -1};
  static int[] dy = {-1, 1, 0, 0};

  static boolean valid(int x, int y)
  {
    return 0 <= x && x < 3 && 0 <= y && y < 3;
  }

  static boolean check()
  {
    for (int i = 0 ; i < 3 ; ++i)
      for (int j = 0 ; j < 3 ; ++j)
        if (grid[0][i][j] == 1)
          return false;
    return true;
  }

  static int transform()
  {
    if (check())
      return -1;

    for (int i = 0 ; i < 3 ; ++i)
    {
      for (int j = 0 ; j < 3 ; ++j)
      {
        int sum = 0;
        for (int k = 0 ; k < 4 ; ++k)
        {
          int a = i + dx[k], b = j + dy[k];
          if (valid(a, b) && grid[0][a][b] == 1)
            sum += 1;
        }
        grid[1][i][j] = sum % 2;
      }
    }

    for (int i = 0 ; i < 3 ; ++i)
      for (int j = 0 ; j < 3 ; ++j)
        grid[0][i][j] = grid[1][i][j];

    return 1 + transform();
  }

  public static class Reader
  {
    protected BufferedReader bufferedReader;
    protected StringTokenizer stringTokenizer;

    public Reader()
    {
      bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    }

    public String next()
    {
      while (stringTokenizer == null || !stringTokenizer.hasMoreElements())
      {
        try
        {
          stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        }
        catch (IOException error)
        {
          error.printStackTrace();
        }
      }

      return stringTokenizer.nextToken();
    }

    public String nextLine()
    {
      String string = "";

      try
      {
        string = bufferedReader.readLine();
      }
      catch (IOException error)
      {
        error.printStackTrace();
      }

      return string;
    }

    public int nextInt()
    {
      return Integer.parseInt(this.next());
    }

    public long nextLong()
    {
      return Long.parseLong(this.next());
    }

    public float nextFloat()
    {
      return Float.parseFloat(this.next());
    }

    public Double nextDouble()
    {
      return Double.parseDouble(this.next());
    }

    public boolean ready()
    {
      boolean isReady = true;

      try{
        isReady = bufferedReader.ready();
      }
      catch (IOException error)
      {
        error.printStackTrace();
      }

      return isReady;
    }

    public void close()
    {

      try{
        bufferedReader.close();
      }
      catch (IOException error)
      {
        error.printStackTrace();
      }

    }
  }
}
