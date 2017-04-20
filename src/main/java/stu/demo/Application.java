package stu.demo;

import stu.demo.client.ClientManager;
import stu.demo.client.ShutDownWork;

public class Application {

	public static void main(String[] args) {
		Runtime.getRuntime().addShutdownHook(new ShutDownWork());
		ClientManager.startup();
	}

}
