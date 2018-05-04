package com.yb.serviceimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;

import com.yb.service.Formula;

public class Test2 {

	public static void main(String[] args) {
		Formula f = new Formula() {

			@Override
			public double calculate(int a) {
				return sqrt(a * 100);
			}
		};

		System.out.println(f.calculate(100));
		System.out.println(f.sqrt(16));

		Predicate<String> p = (s) -> s.length() > 0;

		System.out.println(p.test("cyb"));

		System.out.println(p.negate().test("haha"));

		Function<String, Integer> toInteger = Integer::valueOf;
		Function<String, String> toString = toInteger.andThen(String::valueOf);

		toString.apply("123");

		Optional<String> optional = Optional.of("bam");
		optional.isPresent();
		optional.get();
		optional.orElse("fallback");
		optional.ifPresent((s) -> System.out.println(s.charAt(0)));

		List<String> stringCollection = new ArrayList<>();
		stringCollection.add("ddd2");
		stringCollection.add("aaa2");
		stringCollection.add("bbb1");
		stringCollection.add("aaa1");
		stringCollection.add("bbb3");
		stringCollection.add("ccc");
		stringCollection.add("bbb2");
		stringCollection.add("ddd1");
		stringCollection.stream().filter((s) -> s.startsWith("a")).forEach(System.out::println);

		stringCollection.stream().sorted().filter((s) -> s.startsWith("a")).forEach(System.out::println);

		stringCollection.stream().map(String::toUpperCase).sorted((a, b) -> b.compareTo(a))
				.forEach(System.out::println);

		boolean anyStartsWithA = stringCollection.stream().anyMatch((s) -> s.startsWith("a"));

		System.out.println(anyStartsWithA); // true

		boolean allStartsWithA = stringCollection.stream().allMatch((s) -> s.startsWith("a"));

		System.out.println(allStartsWithA); // false

		boolean noneStartsWithZ = stringCollection.stream().noneMatch((s) -> s.startsWith("z"));

		System.out.println(noneStartsWithZ); // true

		Optional<String> reduced = stringCollection.stream().sorted().reduce((s1, s2) -> s1 + "#" + s2);

		reduced.ifPresent(System.out::println);
		// "aaa1#aaa2#bbb1#bbb2#bbb3#ccc#ddd1#ddd2"
	}
}
