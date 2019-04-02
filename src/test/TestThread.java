package test;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Supplier;

public class TestThread extends Thread {

    public static void main(String[] args) {
        testThreadLocal();
    }

    static void testThreadLocal() {
        int a = 99;
        Supplier supplier = new Supplier<Integer>() {
            @Override
            public Integer get() {
                return a;
            }
        };
        ThreadLocal threadLocal = ThreadLocal.withInitial(supplier);
        Runnable runnable = () -> {
            System.out.println(Thread.currentThread().getName() + "获取了" + threadLocal.get());
            for (int i = 0; i < new Random().nextInt(30); i++) {
                threadLocal.set((int)threadLocal.get() + 1);
            }
            System.out.println(Thread.currentThread().getName() + "最终是" + threadLocal.get());
        };
        for (int i = 0; i < 10; i++) {
            new Thread(runnable).start();
        }
    }

    static void testInterrupt()  {
        final Integer a = 1;
        Runnable syn = () ->{
            synchronized (a) {
                try {
                   // Thread.sleep(2000);
//                    a.wait();
                    while (true) {
                        System.out.println(111);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.out.println("完成了1");
            }
        };
        Runnable lockInterruptibly = () ->{
            ReentrantLock reentrantLock = new ReentrantLock();
            try {
                reentrantLock.lockInterruptibly();
                try {
//                    Thread.sleep(2000);
                    while (true){}
                } catch (Exception e) {
                    System.out.println("lockInterruptibly sleeping interrupted!");
                    e.printStackTrace();
                }
            } catch (Exception e) {
                System.out.println("被中断了哈哈");
                e.printStackTrace();
            } finally {
                    reentrantLock.unlock();
                    System.out.println("完成了");
                }
        };
        Runnable lock = () ->{
            ReentrantLock reentrantLock = new ReentrantLock();
            try {
                reentrantLock.lock();
                try {
//                    Thread.sleep(2000);
                    while (true){
                        System.out.println("lock");
                    }
                } catch (Exception e) {
                    System.out.println("lock sleeping interrupted!");
                    e.printStackTrace();
                }
//                while (true){
//
//                }
            } catch (Exception e) {
                System.out.println("被中断了哈哈");
                e.printStackTrace();
            } finally {
                reentrantLock.unlock();
                System.out.println("完成了");
            }
        };
        Runnable wait = () -> {
            synchronized (a) {
                try {
                    a.wait();
                } catch (InterruptedException e) {
                    System.out.println("waiting interrupted!");
                    e.printStackTrace();
                }
            }
        };
        Runnable io = () -> {
            try {
                Scanner scanner= new Scanner(System.in);
                int aa = scanner.nextInt();
            } catch (Exception e) {
                System.out.println("io interrupted!");
                e.printStackTrace();
            }
        };
        Thread thread = new Thread(lockInterruptibly);
        thread.start();
        thread.interrupt();
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(thread.isInterrupted());
        System.out.println(thread.interrupted());
        System.out.println(thread.isInterrupted());
        System.out.println(thread.interrupted());
    }

    static void testSemaphore() {
        Semaphore semaphore = new Semaphore(8);
        Runnable consumer = () -> {
            int require = new Random().nextInt(7) + 1;
            try {
                System.out.println(Thread.currentThread().getName() + "需要" + require);
                semaphore.acquire(require);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "成功获取" + require);
            try {
                Thread.sleep(870L * require);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "即将释放" + require);
            for (int i = 0; i < require; i++)
            semaphore.release();
            System.out.println("现在可以用的有  " + semaphore.availablePermits());
        };

        for (int i = 0; i < 10; i++) {
            new Thread(consumer).start();
        }
    }

    static void testCountDownLatch() {
        CountDownLatch countDownLatch = new CountDownLatch(3);
        Runnable consumer = () -> {
            try {
                System.out.println(Thread.currentThread().getName() + "开始sleep");
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            countDownLatch.countDown();
            System.out.println(Thread.currentThread().getName() + "count down 完毕！");
        };
        Runnable waiter = () -> {
            try {
                System.out.println(Thread.currentThread().getName() + "开始了漫长的等待");
                countDownLatch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "终于等待完了");
        };
        for (int i = 0; i < 5; i++) {
            new Thread(consumer).start();
        }
        new Thread(waiter).start();
    }

    static void testConsumerProducer1() {
        final ArrayList<Integer> arrayList = new ArrayList(2);

        Runnable producer = () -> {
            while (true) {

                synchronized (arrayList) {
                    while (arrayList.size() == 2) {
                        System.out.println("是满的 --" + Thread.currentThread().getName());
                        try {
                            arrayList.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    arrayList.add(1);
                    arrayList.notify();
                    System.out.println("生产了一个  " +  Thread.currentThread().getName());
                }
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        Runnable consumer = () -> {
            while (true) {
                synchronized (arrayList) {
                    while (arrayList.isEmpty()) {
                        System.out.println("没有东西 --" + Thread.currentThread().getName());
                        try {
                            arrayList.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    arrayList.remove(arrayList.size() - 1);
                    arrayList.notify();
                    System.out.println("消费了一个  " + Thread.currentThread().getName());
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };

        new Thread(producer).start();
        new Thread(consumer).start();
        new Thread(consumer).start();
    }

    static void testConsumerProducer2() {
        final ArrayBlockingQueue<Integer> arrayList = new ArrayBlockingQueue(2);

        Runnable producer = () -> {
            while (true) {
                try {
                    System.out.println("准备生产： " +  Thread.currentThread().getName());
                    arrayList.put(1);
                    System.out.println("生产了一个  " +  Thread.currentThread().getName());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        Runnable consumer = () -> {
            while (true) {
                synchronized (arrayList) {
                        try {
                            System.out.println("准备消费： " +  Thread.currentThread().getName());
                            arrayList.take();
                            System.out.println("消费了一个  " +  Thread.currentThread().getName());
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };

        new Thread(producer).start();
        new Thread(consumer).start();
        new Thread(consumer).start();
    }

    static void testWaitAndNotify() throws InterruptedException {
        Integer integer = 3;
        Runnable runnable = () -> {
            synchronized (integer) {
                System.out.println(Thread.currentThread().getName() + " before wait");
                try {
                    integer.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + " after wait");
            }
        };

        Runnable runnable2 = () -> {
            synchronized (integer) {
                System.out.println(Thread.currentThread().getName() + " before notify");
                integer.notify();
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + " after notify");
            }
        };
        new Thread(runnable).start();
        Thread.sleep(2000);
        new Thread(runnable2).start();
    }
}
