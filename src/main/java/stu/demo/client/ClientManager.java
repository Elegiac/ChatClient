package stu.demo.client;

import stu.demo.client.message.Connection;
import stu.demo.client.views.LoginFrame;

public class ClientManager {
	public static void startup() {
		Connection.startup();
		LoginFrame.generate();
		// Dashboard.generate();
	}
}
