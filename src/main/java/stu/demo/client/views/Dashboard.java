package stu.demo.client.views;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import stu.demo.client.message.Connection;

public class Dashboard extends BaseFrame {

	private static final long serialVersionUID = 1L;

	private static Dashboard dashboard;

	private Dashboard(int width, int height) {
		super(width, height);

		JPanel messageViewContainer = new JPanel();
		messageViewContainer.setLayout(new BorderLayout());
		final JTextArea messageViewArea = new JTextArea();
		// tab占4个字符
		messageViewArea.setTabSize(4);
		messageViewArea.setFont(new Font("标楷体", Font.BOLD, 16));
		messageViewArea.setLineWrap(true);// 激活自动换行功能
		messageViewArea.setWrapStyleWord(true);// 激活断行不断字功能
		messageViewArea.setEditable(false);
		messageViewContainer.add(new JScrollPane(messageViewArea),
				BorderLayout.CENTER);

		JPanel messageInputContainer = new JPanel();
		messageInputContainer.setLayout(new BorderLayout());
		final JTextArea messageInputArea = new JTextArea();
		// tab占4个字符
		messageInputArea.setTabSize(4);
		messageInputArea.setFont(new Font("标楷体", Font.BOLD, 16));
		messageInputArea.setLineWrap(true);// 激活自动换行功能
		messageInputArea.setWrapStyleWord(true);// 激活断行不断字功能
		messageInputContainer.add(new JScrollPane(messageInputArea),
				BorderLayout.CENTER);

		JButton sendButton = new JButton("SEND");

		sendButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String sendMessage = messageInputArea.getText();
				String receiveMessage = Connection
						.sendMessgaeAndReply(sendMessage);
				messageViewArea.append("Me:" + sendMessage);
				messageViewArea.append("\r\n");
				messageViewArea.append("Target:" + receiveMessage);
				messageViewArea.append("\r\n");
			}
		});

		messageInputContainer.add(sendButton, BorderLayout.SOUTH);

		add(messageViewContainer, BorderLayout.CENTER);
		add(messageInputContainer, BorderLayout.SOUTH);

		setVisible(true);
	}

	public static void generate() {
		if (dashboard == null) {
			// 获取屏幕大小
			double width = SCREENSIZE.getWidth();
			double height = SCREENSIZE.getHeight();
			// 取小值
			int size = 0;
			if (width > height) {
				size = (int) height / 2;
			} else {
				size = (int) width / 2;
			}
			dashboard = new Dashboard(size, size);
		}
	}
}
