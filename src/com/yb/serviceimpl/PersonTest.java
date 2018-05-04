package com.yb.serviceimpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.StringJoiner;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class PersonTest {

	public static void main(String[] args) {
		// PersonFactory<Person> personFactory = Person::new;
		// Person person = personFactory.create("c", "y");

		List<Person> persons = Arrays.asList(new Person("Max", 18), new Person("Peter", 23), new Person("Pamela", 23),
				new Person("David", 12));

		List<Person> filtered = persons.stream().filter(p -> p.name.startsWith("P")).collect(Collectors.toList());

		System.out.println(filtered);

		Map<Integer, List<Person>> personsByAge = persons.stream().collect(Collectors.groupingBy(p -> p.age));

		personsByAge.forEach((age, p) -> System.out.format("age %s: %s\n", age, p));

		Double averageAge = persons.stream().collect(Collectors.averagingInt(p -> p.age));

		System.out.println(averageAge);

		IntSummaryStatistics ageSummary = persons.stream().collect(Collectors.summarizingInt(p -> p.age));

		System.out.println(ageSummary);
		// IntSummaryStatistics{count=4, sum=76, min=12, average=19.000000, max=23}

		String phrase = persons.stream().filter(p -> p.age >= 10).map(p -> p.name)
				.collect(Collectors.joining(" and ", "In Germany ", " are of legal age."));

		System.out.println(phrase);
		// In Germany Max and Peter and Pamela are of legal age.

		Map<Integer, String> map = persons.stream()
				.collect(Collectors.toMap(p -> p.age, p -> p.name, (name1, name2) -> name1 + ";" + name2));

		System.out.println(map);
		// {18=Max, 23=Peter;Pamela, 12=David}

		Collector<Person, StringJoiner, String> personNameCollector = Collector.of(() -> new StringJoiner(" | "), // supplier
				(j, p) -> j.add(p.name.toUpperCase()), // accumulator
				(j1, j2) -> j1.merge(j2), // combiner
				StringJoiner::toString); // finisher

		String names = persons.stream().collect(personNameCollector);

		System.out.println(names); // MAX | PETER | PAMELA | DAVID

		List<Foo> foos = new ArrayList<>();

		// create foos
		IntStream.range(1, 4).forEach(i -> foos.add(new Foo("Foo" + i)));

		// create bars
		foos.forEach(f -> IntStream.range(1, 4).forEach(i -> f.bars.add(new Bar("Bar" + i + " <- " + f.name))));

		foos.stream().flatMap(f -> f.bars.stream()).forEach(b -> System.out.println(b.name));

		IntStream.range(1, 4).mapToObj(i -> new Foo("Foo" + i)).peek(
				f -> IntStream.range(1, 4).mapToObj(i -> new Bar("Bar" + i + " <- " + f.name)).forEach(f.bars::add))
				.flatMap(f -> f.bars.stream()).forEach(b -> System.out.println(b.name));

		Optional.of(new Outer()).flatMap(o -> Optional.ofNullable(o.nested)).flatMap(n -> Optional.ofNullable(n.inner))
				.flatMap(i -> Optional.ofNullable(i.foo)).ifPresent(System.out::println);

		persons.stream().reduce((p1, p2) -> p1.age > p2.age ? p1 : p2).ifPresent(System.out::println);

		Person result = persons.stream().reduce(new Person("", 0), (p1, p2) -> {
			p1.age += p2.age;
			p1.name += p2.name;
			return p1;
		});

		System.out.format("name=%s; age=%s", result.name, result.age);
		// name=MaxPeterPamelaDavid; age=76

		// Integer ageSum = persons.stream().reduce(0, (sum, p) -> sum += p.age, (sum1,
		// sum2) -> sum1 + sum2);
		// System.out.println(ageSum); // 76

		Integer ageSum = persons.stream().reduce(0, (sum, p) -> {
			System.out.format("accumulator: sum=%s; person=%s\n", sum, p);
			return sum += p.age;
		}, (sum1, sum2) -> {
			System.out.format("combiner: sum1=%s; sum2=%s\n", sum1, sum2);
			return sum1 + sum2;
		});

		// accumulator: sum=0; person=Max
		// accumulator: sum=18; person=Peter
		// accumulator: sum=41; person=Pamela
		// accumulator: sum=64; person=David

		Integer ageSum1 = persons.parallelStream().reduce(0, (sum, p) -> {
			System.out.format("accumulator: sum=%s; person=%s\n", sum, p);
			return sum += p.age;
		}, (sum1, sum2) -> {
			System.out.format("combiner: sum1=%s; sum2=%s\n", sum1, sum2);
			return sum1 + sum2;
		});
		
		ForkJoinPool commonPool = ForkJoinPool.commonPool();
		System.out.println(commonPool.getParallelism());    // 3
	}
}
