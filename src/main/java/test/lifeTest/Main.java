package test.lifeTest;

import test.helloworld;

public class Main {

	public static void main(String[] args) {
		
		helloworld<Object, Object, Object> d = new helloworld<Object, Object, Object>();
		d.printHello();
		d.printHelloWith("MYARGS");
		}

}
