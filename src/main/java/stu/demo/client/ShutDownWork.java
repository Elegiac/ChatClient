package stu.demo.client;

import stu.demo.client.message.MessageManager;

public class ShutDownWork extends Thread {

	@Override
	public void run() {
		MessageManager.shutdown();
	}

}
