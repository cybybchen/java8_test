package com.yb.serviceimpl;

import java.util.function.Supplier;
import java.util.stream.Stream;

public class Test10 {

	public static void main(String[] args) {
		// Arrays.asList("a1", "a2",
		// "a3").stream().findFirst().ifPresent(System.out::println); // a1
		//
		// IntStream.range(1, 4).forEach(System.out::println);
		//
		// Arrays.stream(new int[] { 1, 2, 3 }).map(n -> 2 * n +
		// 1).average().ifPresent(System.out::println); // 5.0
		//
		// Stream.of("a1", "a2", "a3").findFirst().ifPresent(System.out::println); // a1
		//
		// Stream.of("a1", "a2", "a3").map(s ->
		// s.substring(1)).mapToInt(Integer::parseInt).max()
		// .ifPresent(System.out::println);
		//
		// IntStream.range(1, 4).mapToObj(i -> "a" + i).forEach(System.out::println);
		//
		// Stream.of(1.0, 2.0, 3.1).mapToInt(Double::intValue).mapToObj(i -> "a" +
		// i).forEach(System.out::println);

		// Stream.of("d2", "a2", "b1", "b3", "c").filter(s -> {
		// System.out.println("filter: " + s);
		// return true;
		// }).forEach(s -> System.out.println("forEach: " + s));

//		Stream.of("d2", "a2", "b1", "b3", "c").map(s -> {
//			System.out.println("map: " + s);
//			return s.toUpperCase();
//		}).anyMatch(s -> {
//			System.out.println("anyMatch: " + s);
//			return s.startsWith("A");
//		});
		//
		// Stream.of("d2", "a2", "b1", "b3", "c")
		// .map(s -> {
		// System.out.println("map: " + s);
		// return s.toUpperCase();
		// })
		// .filter(s -> {
		// System.out.println("filter: " + s);
		// return s.startsWith("A");
		// })
		// .forEach(s -> System.out.println("forEach: " + s));
		
		Supplier<Stream<String>> streamSupplier =
			    () -> Stream.of("d2", "a2", "b1", "b3", "c")
			            .filter(s -> s.startsWith("a"));

			streamSupplier.get().anyMatch(s -> true);   // ok
			streamSupplier.get().noneMatch(s -> true);  // ok
	}
}
