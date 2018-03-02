package IO;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class Scanner {

    private BufferedReader bufferedReader;
    private StringTokenizer stringTokenizer;

    public Scanner(InputStream stream)
    {
        bufferedReader = new BufferedReader(new InputStreamReader(stream));
    }

    public String nextLine()
    {
        String string = "";

        try
        {
            string = bufferedReader.readLine();
        }
        catch (IOException Error)
        {
            Error.printStackTrace();
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
            catch (IOException Error)
            {
                Error.printStackTrace();;
            }
        }

        return stringTokenizer.nextToken();
    }

    public char[] nextCharArray()
    {
        char[] charArray = this.nextLine().toCharArray();

        return charArray;
    }

    public String[] nextStringArray()
    {
        String[] stringArray = this.nextLine().split(" ");

        return stringArray;
    }

    public byte nextByte()
    {
        byte number = Byte.parseByte(this.next());

        return number;
    }

    public short nextShort()
    {
        short number = Short.parseShort(this.next());

        return number;
    }

    public int nextInt()
    {
        int number = Integer.parseInt(this.next());

        return number;
    }

    public long nextLong()
    {
        long number = Long.parseLong(this.next());

        return number;
    }

    public BigInteger nextBigInteger()
    {
        BigInteger number = new BigInteger(this.next());

        return number;
    }

    public int[] nextIntArray()
    {
        String[] line = this.nextStringArray();

        int[] numbers = new int[line.length];

        for (int i = 0 ; i < numbers.length ; ++i)
            numbers[i] = Integer.parseInt(line[i]);

        return numbers;
    }

    public long[] nextLongArray()
    {
        String[] line = this.nextStringArray();

        long[] numbers = new long[line.length];

        for (int i = 0 ; i < numbers.length ; ++i)
            numbers[i] = Long.parseLong(line[i]);

        return numbers;
    }

    public float nextFloat()
    {
        float number = Float.parseFloat(this.next());

        return number;
    }

    public double nextDouble()
    {
        double number = Double.parseDouble(this.next());

        return number;
    }

    public BigDecimal nextBigDecimal()
    {
        BigDecimal number = new BigDecimal(this.next());

        return number;
    }

    public double[] nextDoubleArray()
    {
        String[] line = this.nextStringArray();

        double[] numbers = new double[line.length];

        for (int i = 0 ; i < numbers.length ; ++i)
            numbers[i] = Double.parseDouble(line[i]);

        return numbers;
    }

    public boolean ready()
    {
        boolean isReady = false;

        try
        {
            isReady = bufferedReader.ready();
        }
        catch (IOException Error)
        {
            Error.printStackTrace();
        }

        return isReady;
    }
}
