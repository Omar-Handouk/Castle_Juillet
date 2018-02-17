import java.io.*;
import java.util.*;

public class Main
{
	
	public static void main(String[] args) throws IOException
	{
		Reader in = new Reader();
		PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
		Task task = new Task();
		task.solve(in, out);
		out.flush();
	}
	
	private static class Task
	{
		public void solve(Reader in, PrintWriter out)
		{
			while (true)
			{
				int s = in.nextInt();
				
				if (s == 0)
					break;
				
				int a = 0;
				int b = 0;
				
				int i = 0;
				boolean A = true;
				
				while (s != 0)
				{
					if (s % 2 == 1)
					{
						if (A)
							a += (int) Math.pow(2, i);
						else
							b += (int) Math.pow(2, i);
						A = !A;
					}
					
					++i;
					s /= 2;
				}
				
				out.printf("%d %d\n",a,b);
				}
		}
	}
	
	private static class Reader
	{
		BufferedReader bufferedReader;
		StringTokenizer stringTokenizer;
		
		public Reader()
		{
			bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		}
		
		public boolean ready()
		{
			boolean isReady = false;
			try
			{
				isReady = bufferedReader.ready();
			}
			catch (IOException error)
			{
				error.printStackTrace();
			}
			return isReady;
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
		
		public String next()
		{
			while (stringTokenizer == null || !stringTokenizer.hasMoreTokens())
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
		
		public double nextDouble()
		{
			return Double.parseDouble(this.next());
		}
	}
}
