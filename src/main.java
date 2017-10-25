class main
{
  public static void main(String[] args)
  {
    main mainObj = new main();
    mainObj.instantiate();
  }

  private void instantiate()
  {
    Fib thread1 = new Fib("Thread 1");
    Fib thread2 = new Fib("Thread 2");

    runThreads(thread1,thread2);
  }

  private void runThreads(Fib threadA, Fib threadB)
  {
    threadA.start();
    threadB.start();
    try
    {
      for (int i = 0; i < 10; i++)
      {
        Fib.sleep(2000);
        threadA.printInfo();
        threadB.printInfo();

        System.out.println();

      }
    }
    catch(InterruptedException e){
      System.out.println(e.getMessage());
    }
    System.exit(11);
  }

  class Fib extends Thread
  {
    private final String NAME; //Name of thread
    private long step = 0; //Steps since start.
    private long z;      // fib(step)
    private long y = 1;  // fib(step-1)
    private long x = 1;  // fib(step-2)
    private long temp= 0;

    Fib(String threadName)
    {
      NAME=threadName;
    }

    @Override
    public void run()
    {
      calculate();
    }
    void calculate()
    {
      while (true)
      {
        synchronized (this)
        {
         // 7540113804746346429L maxVal in long for fib
          if (z == 7540113804746346429L)
          {
            x = 1;
            y = 1;
          }
          step++;
          z = y + x;
          temp = x;
          x = y;
          y = z;
        }
      }
    }
    synchronized void printInfo()
    {

      System.out.println("From Thread "+NAME+" steps: " +step + " Number Sequences   " +
          temp+"   "+x +"   "+y);
    }
  }
}