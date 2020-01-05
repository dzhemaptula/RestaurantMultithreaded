package domein;

public class Order
{
    private static int i = 1;
    private int count = i++;


    @Override
    public String toString()
    {
        return String.format("order %d", count);
    }

}
