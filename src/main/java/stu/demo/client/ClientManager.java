package stu.demo.client;

import stu.demo.client.message.MessageManager;
import stu.demo.client.views.LoginFrame;

public class ClientManager {
	public static void startup() {
		MessageManager.startup();
		LoginFrame.generate();
		// Dashboard.generate();
	}
}
