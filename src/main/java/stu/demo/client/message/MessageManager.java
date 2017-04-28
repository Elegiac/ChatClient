package stu.demo.client.message;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

public class MessageManager {
	static EventLoopGroup group = new NioEventLoopGroup();
	static Channel channel = null;

	static Message message = new Message();

	public static void startup() {
		try {
			Bootstrap b = new Bootstrap();
			b.group(group).channel(NioSocketChannel.class)
					.handler(new StringClientInitializer());

			// 连接服务端
			channel = b.connect("127.0.0.1", 8878).sync().channel();
		} catch (InterruptedException e) {
			e.printStackTrace();
			shutdown();
		}
	}

	public static void shutdown() {
		group.shutdownGracefully();
	}

	public static String sendMessgaeAndReply(String msg) {
		channel.writeAndFlush(msg + "\r\n");

		try {
			synchronized (message) {
				message.wait();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		return message.getMessage();
	}

	static class Message {
		private String message;

		public String getMessage() {
			return message;
		}

		public void setMessage(String message) {
			this.message = message;
		}
	}
}