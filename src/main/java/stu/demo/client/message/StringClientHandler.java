package stu.demo.client.message;

import stu.demo.entity.Message;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class StringClientHandler extends SimpleChannelInboundHandler<String> {

	private Message message;

	public StringClientHandler(Message message) {
		this.message = message;
	}

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, String msg)
			throws Exception {
		message.setMessage(msg);
		synchronized(message){
			message.notify();
		}
		System.out.println("Server say : " + msg);
	}

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		System.out.println("Client active ");
		super.channelActive(ctx);
	}

	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		System.out.println("Client close ");
		super.channelInactive(ctx);
	}
}
