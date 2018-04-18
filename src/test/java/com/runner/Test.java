package com.runner;

public class Test {
	
	public Test(int a) {
		age=a;
	}
	
	public static void main(String args[]) {
		Test t = new Test(11);
		Test t1 = new Test(11);
		t=t1;
	}
	
	private int age;

}
