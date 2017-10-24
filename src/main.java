class main
{

  public static void main(String[] args)
  {
    main mainObj = new main();
    mainObj.instantiate();

  }
  private void instantiate()
  {
   // Fib thread1 = new Fib("Thread 1");
    Fib thread2 = new Fib("Thread 2");

    runThreads(null,thread2);
  }

  private void runThreads(Fib threadA, Fib threadB)
  {
    //threadA.start();
    threadB.start();

    try
    {
      for (int i = 0; i < 10; i++)
      {
       // threadA.printInfo();
        threadB.printInfo();

        System.out.println();
        Fib.sleep(2000);
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
      while (true)
      {

        if (z == 7540113804746346429L){x=1;y=1;}

        z = y + x;
        temp = x;
        x = y;
        y = z;
        System.out.println("From Thread "+NAME+ " Number Sequences   " +
            temp+"   "+x +"   "+y);

      }
    }
    void printInfo()
    {
      System.out.println("From Thread "+NAME+ " Number Sequences   " +
          temp+"   "+x +"   "+y);

    }
  }
}
