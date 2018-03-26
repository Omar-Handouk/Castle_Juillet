import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Source_Test {

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);

        int n = in.nextInt();

        ArrayList<Integer> primes = sieve(n);

        int ans = 0;

        for (int i = 1 ; i <= n ; ++i)
        {
            int count = 0;

            for (int j = 0 ; j < primes.size() ; ++j)
            {
                if (i % primes.get(j) == 0)
                    ++count;
            }

            if (count == 2)
                ++ans;
        }

        System.out.println(ans);

    }

    public static ArrayList<Integer> sieve(int range)
    {
        boolean[] isPrime = new boolean[range + 1];
        Arrays.fill(isPrime, true);

        isPrime[0] = false;
        isPrime[1] = false;

        for (int i = 4 ; i <= range ; i += 2)
            isPrime[i] = false;

        for (int i = 3 ; i * i <= range ; i += 2)
        {
            if (isPrime[i])
            {
                for (int j = i ; i * j <= range ; j += 2)
                    isPrime[i * j] = false;
            }
        }

        ArrayList<Integer> primes = new ArrayList<>();
        for (int i = 0 ; i <= range ; ++i)
        {
            if (isPrime[i])
                primes.add(i);
        }

        return primes;
    }
}