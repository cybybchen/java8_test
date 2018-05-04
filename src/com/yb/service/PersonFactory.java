package com.yb.service;

import com.yb.serviceimpl.Person;

public interface PersonFactory<P extends Person> {
	P create(String f, String l);
}
