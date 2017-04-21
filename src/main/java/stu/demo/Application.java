package stu.demo;

import stu.demo.client.ClientManager;
import stu.demo.client.ShutDownWork;

public class Application {

	public static void main(String[] args) {

		// LinkedBlockingQueue quene = new LinkedBlockingQueue();
		//
		//
		// try {
		// quene.take();
		// } catch (InterruptedException e) {
		//
		// }

		Runtime.getRuntime().addShutdownHook(new ShutDownWork());
		ClientManager.startup();
	}

}
