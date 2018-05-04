package com.yb.serviceimpl;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Test7 {

	public static void main(String[] args) {
		Runnable task = () -> {
			String threadName = Thread.currentThread().getName();
			System.out.println("Hello " + threadName);
		};
		task.run();
		Thread thread1 = new Thread(task);
		thread1.start();
		System.out.println("Done!");

		Runnable runnable = () -> {
			try {
				String name = Thread.currentThread().getName();
				System.out.println("Foo " + name);
				TimeUnit.MINUTES.sleep(1);
				System.out.println("Bar " + name);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		};
		Thread thread = new Thread(runnable);
		thread.start();
		
		ExecutorService executor = Executors.newSingleThreadExecutor();
		executor.submit(() -> {
		String threadName = Thread.currentThread().getName();
		System.out.println("Hello " + threadName);
		});
		
		try {
		    System.out.println("attempt to shutdown executor");
		    executor.shutdown();
		    executor.awaitTermination(5, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
		    System.err.println("tasks interrupted");
		} finally {
		    if (!executor.isTerminated()) {
		        System.err.println("cancel non-finished tasks");
		    }
		    executor.shutdownNow();
		    System.out.println("shutdown finished");
		}
	}
}
