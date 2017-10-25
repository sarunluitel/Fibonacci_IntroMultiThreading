/**************************************************************************
 @author Sarun Luitel
 This program makes two threads compute the Fibonacci series independently.
 The main thread invokes method in each worker threads to print what they're
 currenty computing every 2 seconds. after 10 cycles (20 cycles) it kills the
 threads and terminates the program.
 **************************************************************************/
class main
{
  //instantiate itself to use the instance variables.
  public static void main(String[] args)
  {
    main mainObj = new main();
    mainObj.instantiate();
  }

  private void instantiate()
  {
    // create object from class that extend thread.
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
      int i=0;
      for (;;)//infinite loop to check the threads
      {
        i++;
        Fib.sleep(2000);

        if (!threadA.isAlive() && !threadB.isAlive())
        {//both threads dead
          System.out.println("Both threads Dead");
          System.exit(1);//terminate program
        }

        threadA.printInfo();// print current computation.
        threadB.printInfo();
        if (i==10)
        {
          threadA.alive=false;//kill threads
          threadB.alive=false;
          System.out.println();
        }
        System.out.println();


      }
    }
    catch(InterruptedException e){}
  }

  class Fib extends Thread
  {
    private final String NAME; //Name of thread
    private long step = 0; //Steps since start.
    private long z;      // fib(step)
    private long y = 1;  // fib(step-1)
    private long x = 1;  // fib(step-2)
    private long temp= 0;
    volatile boolean alive=true;

    Fib(String threadName)
    {
      NAME=threadName;
    }

    @Override
    public void run() { calculate(); }

    void calculate()
    {
      for (;;)//infinite loop to do calculation
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
        if(!alive) // check if alive every cycle
        {
          System.out.println(NAME + " Dies on step: "+step);
          return;
        }
      }
    }
    synchronized void printInfo() //synchronized method to print details
    {
      System.out.println("From Thread "+NAME+" steps: " +step + " | Number Sequences:   " +
          temp+"   "+x +"   "+y);
    }
  }
}