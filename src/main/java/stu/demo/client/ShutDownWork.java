package stu.demo.client;

import stu.demo.client.message.Connection;

public class ShutDownWork extends Thread {

	@Override
	public void run() {
		Connection.shutdown();
	}

}
