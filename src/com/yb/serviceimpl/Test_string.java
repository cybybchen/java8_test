package com.yb.serviceimpl;

public class Test_string {
	public static void main(String[] args) {
		String s = "11";
		change(s);
		System.out.println(s);
	}
	
	public static void change(String s) {
		s = "22";
		System.out.println(s);
	}
}
