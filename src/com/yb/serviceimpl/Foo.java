package com.yb.serviceimpl;

import java.util.ArrayList;
import java.util.List;

public class Foo {
	String name;
    List<Bar> bars = new ArrayList<>();

    Foo(String name) {
        this.name = name;
    }
}

class Bar {
    String name;

    Bar(String name) {
        this.name = name;
    }
} 

class Outer {
    Nested nested;
}

class Nested {
    Inner inner;
}

class Inner {
    String foo = "";
} 