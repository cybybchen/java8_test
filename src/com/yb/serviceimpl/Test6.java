package com.yb.serviceimpl;

import java.lang.annotation.Repeatable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ForkJoinPool;

public class Test6 {
	@interface Hints {
		Hint[] value();
	}

	@Repeatable(Hints.class)
	@interface Hint {
		String value();
	}

	public static void main(String[] args) {
		ConcurrentMap<String, String> map = new ConcurrentHashMap<>();
		map.put("foo", "bar");
		map.put("han", "solo");
		map.put("r2", "d2");
		map.put("c3", "p0");

		map.merge("foo", "boo", (o, n) -> n + " was " + o);
		System.out.println(map.get("foo")); // boo was bar

		System.out.println(ForkJoinPool.getCommonPoolParallelism()); // 3

		ConcurrentHashMap<String, String> map1 = new ConcurrentHashMap<>();
		map1.put("foo", "bar");
		map1.put("han", "solo");
		map1.put("r2", "d2");
		map1.put("c3", "p0");
		map1.put("c4", "p0");
		map1.put("c5", "p0");

		map1.forEach(1, (key, value) -> System.out.printf("key: %s; value: %s; thread: %s\n", key, value,
				Thread.currentThread().getName()));

		String result = map1.search(1, (key, value) -> {
			System.out.println(Thread.currentThread().getName());
			if ("foo".equals(key)) {
				return value;
			}
			return null;
		});
		System.out.println("Result: " + result);
		// ForkJoinPool.commonPool-worker-2
		// main
		// ForkJoinPool.commonPool-worker-3
		// Result: bar

		String result1 = map1.searchValues(1, value -> {
			System.out.println(Thread.currentThread().getName());
			if (value.length() > 3) {
				return value;
			}
			return null;
		});
		System.out.println("Result1: " + result1);
		// ForkJoinPool.commonPool-worker-2
		// main
		// main
		// ForkJoinPool.commonPool-worker-1
		// Result: solo

		String result2 = map1.reduce(1, (key, value) -> {
			System.out.println("Transform: " + Thread.currentThread().getName());
			return key + "=" + value;
		}, (s1, s2) -> {
			System.out.println("Reduce: " + Thread.currentThread().getName());
			return s1 + ", " + s2;
		});
		System.out.println("Result2: " + result2);
	}
}
