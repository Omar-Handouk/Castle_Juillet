import java.util.*;
import java.io.*;
import java.lang.*;
import java.math.*;
import java.text.*;
/*
 * @IO : BufferedReader, InputStreamReader, IOException.
 * @Util : Scanner, Arrays, ArrayList, StringTokenizer, TreeSet.
 * @Util : Collections, HashMap, HashSet.
 * @Lang : StringBuilder, StringBuffer.
 * @Math : BigInteger, BigDecimal.
 * @Text : DecimalFormat.
 * @System : System.currentTimeMillis(); , System.nanoTime();
 * @Custom I/O : new PrintWriter(new BufferedOutputStream(System.out))
 */

public class Main
{
    // Custom I/O Routine
    	public static PrintWriter out = new PrintWriter(System.out);
    	public static Reader in = new Reader();


    // Intializations of Values
    	static byte UNCAL = -1;
    	static int INF = (int) 1e6;
	static int sum;

    // Intializations of Data Structures & Graphs
    	static int memo[][];
	static int[] values;
	
    public static void main(String[] args) throws IOException
    {
        //---------------------Solution Start---------------------//
        int tc = in.nextInt();
        
        while (tc --> 0)
        {
			String[] v = in.nextLine().split(" ");
			
			values = new int[v.length];
			
			sum = 0;
			for (int i = 0; i < v.length; i++)
			{
				values[i] = Integer.parseInt(v[i]);
				sum += values[i];
			}
			
			memo = new int[v.length][sum + 1];
			for (int[] row : memo)
				Arrays.fill(row, UNCAL);
			
			if (sum % 2 != 0)
				out.println("NO");
			else
			{
				int ans = dp(0,0);
				
				if (ans == sum / 2)
				{
					out.println("YES");
				}
				else
				{
					out.println("NO");
				}
			}
			
		}	
        //---------------------Solution  End---------------------//
        out.flush();
        out.close();
    }
    
    static int dp(int idx, int cal)
    {
		if (cal == sum / 2)
		{
			return cal;
		}
		else if (cal > sum / 2)
		{
			return 0;
		}
		else if (idx == values.length)
		{
			if (cal > sum / 2)
			{
				return 0;
			}
			else
			{
				return cal;
			}
			
		}
		
		if (memo[idx][cal] != UNCAL)
			return memo[idx][cal];
			
		int take = dp(idx + 1, cal + values[idx]);
		int leave = dp(idx + 1, cal);
		
		return memo[idx][cal] = Math.max(leave,take);
	}
}

class Reader
{
    BufferedReader bufferedReader;
    StringTokenizer stringTokenizer;

    public Reader(){
        bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    }

    public String next(){
        while (stringTokenizer == null || !stringTokenizer.hasMoreElements()){
            try{
                stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            }
            catch (IOException Ex){
                Ex.printStackTrace();
            }
        }
        return stringTokenizer.nextToken();
    }

    public int nextInt(){
        return Integer.parseInt(this.next());
    }

    public long nextLong(){
        return Long.parseLong(this.next());
    }

    public double nextDouble(){
        return Double.parseDouble(this.next());
    }

    public String nextLine(){
        String str = "";
        try{
            str = bufferedReader.readLine();
        }
        catch (IOException Ex){
            Ex.printStackTrace();
        }
        return str;
    }

    public boolean ready() throws IOException{
        return bufferedReader.ready();
    }
}
