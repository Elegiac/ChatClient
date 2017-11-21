package stu.demo.client.views;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import edu.demo.common.entity.action.Login;
import edu.demo.common.utils.JsonUtil;
import stu.demo.client.message.MessageManager;

public class LoginFrame extends BaseFrame {

	private static final long serialVersionUID = 1L;

	private static LoginFrame loginFrame;

	private LoginFrame(int width, int height) {
		super(width, height, "login");

		final ImagePanel panel = new ImagePanel();

		add(panel);

		panel.setLayout(new BorderLayout());

		JPanel center = new JPanel();

		center.setOpaque(false);

		final JTextField userNameInput = new JTextField();
		final JPasswordField passwordInput = new JPasswordField();

		Font font = new Font("黑体", Font.BOLD, 20);
		
		userNameInput.setFont(font);

		userNameInput.setOpaque(false);
		passwordInput.setOpaque(false);
		
		JButton login = new JButton("login");
		JButton reset = new JButton("reset");

		login.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String loginName = userNameInput.getText();
				String password = passwordInput.getText();

				Login login = new Login(loginName, password);

				String receiveMessage = MessageManager.sendMessgaeAndReply(JsonUtil.objectToJson(login));
				System.out.println(receiveMessage);
			}
		});
		
		reset.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				panel.changeImage();
			}
		});

		center.setLayout(new GridLayout(9, 1));

		Box userNameBox = Box.createHorizontalBox();
		userNameBox.add(Box.createHorizontalStrut(20));
		userNameBox.add(Box.createHorizontalStrut(20));
		userNameBox.add(userNameInput);
		userNameBox.add(Box.createHorizontalStrut(20));

		Box passwordBox = Box.createHorizontalBox();
		passwordBox.add(Box.createHorizontalStrut(20));
		passwordBox.add(Box.createHorizontalStrut(20));
		passwordBox.add(passwordInput);
		passwordBox.add(Box.createHorizontalStrut(20));

		JPanel loginBox = new JPanel();
		loginBox.setOpaque(false);
		loginBox.add(login);
		loginBox.add(Box.createHorizontalStrut(20));
		loginBox.add(reset);

		center.add(Box.createVerticalGlue());
		center.add(Box.createVerticalGlue());
		center.add(userNameBox);
		center.add(Box.createVerticalGlue());
		center.add(passwordBox);
		center.add(Box.createVerticalGlue());
		center.add(loginBox);
		center.add(Box.createVerticalGlue());
		center.add(Box.createVerticalGlue());

		panel.add(center, BorderLayout.CENTER);

		setVisible(true);
	}

	public static void generate() {
		if (loginFrame == null) {
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
			loginFrame = new LoginFrame(size, size);
		}
	}

	public static void main(String[] args) {
		generate();
	}
}
