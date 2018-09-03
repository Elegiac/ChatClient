package stu.demo.client.message;

import java.util.Map;

import edu.demo.common.entity.User;
import edu.demo.common.entity.action.Chat;
import edu.demo.common.entity.action.Login;
import edu.demo.common.entity.action.UserList;
import edu.demo.common.utils.JsonUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import stu.demo.client.views.Dashboard;
import stu.demo.client.views.LoginFrame;

public class StringClientHandler extends SimpleChannelInboundHandler<String> {

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
		System.out.println("服务器消息 : " + msg);

		Map<String, Object> map = JsonUtil.jsonToMap(msg);

		String actionName = map.get("actionName").toString();

		switch (actionName) {
		case "LOGIN":
			Login login = JsonUtil.jsonToObject(msg, Login.class);

			synchronized (LoginFrame.loginFlag) {
				LoginFrame.loginFlag.notify();
			}

			if (login.getResult().isSuccess()) {

				LoginFrame.CURRENT_USER = login.getUser();

				LoginFrame.close();

				Dashboard.generate();
			}
			break;
		case "CHAT":
			Chat chat = JsonUtil.jsonToObject(msg, Chat.class);
			System.err.println(chat);
			User from = chat.getFrom();
			if (from == null) {
				return;
			}
			Dashboard.showincomingMessage(from, chat.getChatMessage());
			break;
		case "USERLIST":
			UserList userList = JsonUtil.jsonToObject(msg, UserList.class);
			Dashboard.refreshUserList(userList.getUsers());
		}

	}

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		super.channelActive(ctx);
	}

	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		super.channelInactive(ctx);
	}
}
