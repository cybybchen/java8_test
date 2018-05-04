package com.yb.serviceimpl;

import com.yb.service.Converter;

public class Test1 {
	public static void main(String[] args) {
//		List<String> names = Arrays.asList("c", "b", "y");
//		
//		Collections.sort(names, new Comparator<String>() {
//
//			@Override
//			public int compare(String o1, String o2) {
//				return o2.compareTo(o1);
//			}
//			
//		});
//		
//		Collections.sort(names, (String a, String b) -> b.compareTo(a));
//		Collections.sort(names, (a, b) -> b.compareTo(a));
		
//		Converter<String, Integer> converter = (from) -> Integer.valueOf(from);
//		Converter<String, Integer> converter = Integer::valueOf;
//		Integer converted = converter.converter("123");
//		System.out.println(converted);
		
		Something st = new Something();
		Converter<String, String> converter = st::startsWith;
		String converted = converter.converter("java");
		System.out.println(converted);
	}
}
