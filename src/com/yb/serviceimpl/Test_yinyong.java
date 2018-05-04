package com.yb.serviceimpl;

public class Test_yinyong {
	public static void call(Test t) {
			Test t2 = new Test();
			t2.setName("cba");
			t.setName("abc");
			t = t2 ;
			System.out.println("11:" + t.getName());
		}

	public static void main(String[] arg) {
		Test obj = new Test();
		call(obj);
		System.out.println("obj" + obj.getName());
	}
}

class Test {
	String name = "";
	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return this.name;
	}
}
