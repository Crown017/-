package concurrent;

// 以下代码来源于【参考 1】
class VolatileExample {
  int x = 0;
  volatile boolean v = false;
  public void writer() {
    x = 42;
    v = true;
  }
  public int reader() {
    if (v == true) {
      // 这里 x 会是多少呢？
      return x;
    }
    return 0;
  }

  public static void main(String[] args) {
     VolatileExample  v = new VolatileExample();
      Thread th1 = new Thread(()->{
        v.writer();
      });
    Thread th2 = new Thread(()->{
      System.out.println(v.reader());
    });
    // 启动两个线程
    th1.start();
    th2.start();
    // 等待两个线程执行结束
    try {
      th1.join();
      th2.join();
    }catch (Exception e){
      e.printStackTrace();
    }


  }
}
