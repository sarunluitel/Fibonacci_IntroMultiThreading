public class main extends Thread
{

  private final String NAME = null; //Name of thread
  private long step = 0; //Steps since start.
  private long z;      // fib(step)
  private long y = 1;  // fib(step-1)
  private long x = 1;  // fib(step-2)

  public static void main(String[] args)
  {
    System.out.println("hello world");
    main main = new main();

    main.start();
    main.start();

  }


  @Override
  public void run()
  {
    synchronized ((Long)z)
    {
      while (z != 7540113804746346429L)
      {
        z = y + x;
        System.out.println("Inside the Thread       "+z+"   steps   "+step);
        x = y;
        y = z;
        step++;
      }
    }

    System.out.println(step);

  }
}