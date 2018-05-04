package com.yb.service;

@FunctionalInterface
public interface Converter<F, T> {
	T converter(F from);
}
