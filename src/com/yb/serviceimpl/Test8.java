package com.yb.serviceimpl;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class Test8 {

	public static void main(String[] args) {
		Callable<Integer> task = () -> {
			try {
				TimeUnit.MINUTES.sleep(1);
				return 123;
			} catch (InterruptedException e) {
				throw new IllegalStateException("task interrupted", e);
			}
		};

		ExecutorService executor = Executors.newFixedThreadPool(1);
		Future<Integer> future = executor.submit(task);
		System.out.println("future done? " + future.isDone());
		Integer result = null;
		try {
			// executor.shutdownNow();
			result = future.get();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("future done? " + future.isDone());
		System.out.print("result: " + result);

		ExecutorService executor1 = Executors.newFixedThreadPool(1);
		Future<Integer> future1 = executor1.submit(() -> {
			try {
				TimeUnit.MINUTES.sleep(2);
				return 123;
			} catch (InterruptedException e) {
				throw new IllegalStateException("task interrupted", e);
			}
		});
		try {
			future1.get(1, TimeUnit.MINUTES);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TimeoutException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
