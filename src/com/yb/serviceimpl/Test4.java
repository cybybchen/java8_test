package com.yb.serviceimpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class Test4 {
	public static void main(String[] args) {
		int max = 1000000;
		List<String> values = new ArrayList<>(max);
		for (int i = 0; i < max; i++) {
			UUID uuid = UUID.randomUUID();
			values.add(uuid.toString());
		}

		long t0 = System.nanoTime();
		System.out.println(t0);
		long count = values.stream().sorted().count();
		System.out.println(count);

		long t1 = System.nanoTime();
		System.out.println(t1);
		long millis = TimeUnit.NANOSECONDS.toMillis(t1 - t0);
		System.out.println(String.format("sequential sort took: %d ms", millis));

		t0 = System.nanoTime();

		count = values.parallelStream().sorted().count();
		System.out.println(count);

		t1 = System.nanoTime();

		millis = TimeUnit.NANOSECONDS.toMillis(t1 - t0);
		System.out.println(String.format("parallel sort took: %d ms", millis));

		Map<Integer, String> map = new HashMap<>();
		for (int i = 0; i < 10; i++) {
			map.putIfAbsent(i, "val" + i);
		}
		map.forEach((id, val) -> System.out.println(val));

		map.computeIfPresent(3, (num, val) -> val + num);
		map.get(3); // val33

		map.computeIfPresent(9, (num, val) -> null);
		map.containsKey(9); // false

		map.computeIfAbsent(23, num -> "val" + num);
		map.containsKey(23); // true

		map.computeIfAbsent(3, num -> "bam");
		map.get(3); // val33

		map.getOrDefault(42, "not found");

		map.merge(9, "val9", (value, newValue) -> value.concat(newValue));
		map.get(9); // val9

		map.merge(9, "concat", (value, newValue) -> value.concat(newValue));
		map.get(9); // val9concat
	}
}
