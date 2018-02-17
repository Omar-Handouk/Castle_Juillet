//SPOJ KNAPSACK

import java.util.*;
import java.io.*;

public class Main{
	
	static BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
	static PrintWriter out = new PrintWriter(System.out);
	static StringTokenizer tokenizer;
	
	static int capacity, items;
	static int STVAL = -1;
	
	static int[] weight, price;
	static int[][] memo;
	
	public static void main(String[] args) throws IOException{
		
		memo = new int[2500][2500];
		
		while (read.ready()){
			tokenizer = new StringTokenizer(read.readLine());
			capacity = Integer.parseInt(tokenizer.nextToken());
			items = Integer.parseInt(tokenizer.nextToken());
			
			weight = new int[items];
			price = new int[items];
			
			for (int[] row : memo)
				Arrays.fill(row, STVAL);
				
			for (int i = 0 ; i < items ; i++){
				tokenizer = new StringTokenizer(read.readLine());
				
				weight[i] = Integer.parseInt(tokenizer.nextToken());
				price[i] = Integer.parseInt(tokenizer.nextToken());
			}
			out.println(solve(capacity, 0));
		}
		
		//---------
		out.flush();
		out.close();
	}
	
	static int solve(int rem, int id){
		if (rem < 0)
			return Integer.MIN_VALUE;
		if (rem == 0 || id == items)
			return 0;
		if (memo[rem][id] != STVAL)
			return memo[rem][id];
		if (weight[id] > rem)
			return solve(rem, id + 1);
			
		int max = Math.max( price[id] + solve(rem - weight[id], id + 1), solve(rem, id + 1));
		
		return memo[rem][id] = max;
	}
}
