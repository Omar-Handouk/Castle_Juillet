import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException{

        //Scanner in = new Scanner(System.in);
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int n;
        
        n = 7489;
        int[] val = {1, 5, 10, 25, 50};

        int[][] memo = new int[5][n + 1];
        for (int[] row : memo)
            Arrays.fill(row , -1);

        Arrays.fill(memo[0], 1);
        for (int i = 0; i < 5; i++)
            memo[i][0] = 1;

        for (int i = 1; i < 5; i++) {
            for (int money = 0; money <= n; money++) {
                if (val[i] > money) {
                    memo[i][money] = memo[i - 1][money];
                    continue;
                }

                memo[i][money] = memo[i][money - val[i]] + memo[i - 1][money];
            }
        }

        while (in.ready()){
            n = Integer.parseInt(in.readLine());
            System.out.println(memo[4][n]);
        }
    }
}
