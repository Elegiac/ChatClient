package stu.demo.client.views;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import stu.demo.client.message.MessageManager;
import edu.demo.common.entity.action.Login;
import edu.demo.common.utils.JsonUtil;

public class LoginFrame extends BaseFrame {

	private static final long serialVersionUID = 1L;

	private static LoginFrame loginFrame;

	private LoginFrame(int width, int height) {
		super(width, height, "login");

		setLayout(new BorderLayout());

		JPanel center = new JPanel();

		JPanel south = new JPanel();

		final JTextField userNameInput = new JTextField();
		final JPasswordField passwordInput = new JPasswordField();

		center.setLayout(new GridLayout(9, 1));

		Box userNameBox = Box.createHorizontalBox();
		userNameBox.add(Box.createHorizontalStrut(20));
		userNameBox.add(new JLabel("userName:"));
		userNameBox.add(userNameInput);
		userNameBox.add(Box.createHorizontalStrut(20));

		Box passwordBox = Box.createHorizontalBox();
		passwordBox.add(Box.createHorizontalStrut(20));
		passwordBox.add(new JLabel("password:"));
		passwordBox.add(passwordInput);
		passwordBox.add(Box.createHorizontalStrut(20));

		center.add(Box.createVerticalGlue());
		center.add(Box.createVerticalGlue());
		center.add(Box.createVerticalGlue());
		center.add(userNameBox);
		center.add(Box.createVerticalGlue());
		center.add(passwordBox);

		JButton login = new JButton("login");
		JButton reset = new JButton("reset");

		login.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String loginName = userNameInput.getText();
				String password = passwordInput.getText();

				Login login = new Login(loginName, password);

				String receiveMessage = MessageManager
						.sendMessgaeAndReply(JsonUtil.objectToJson(login));
				System.out.println(receiveMessage);
			}
		});

		south.add(login);
		south.add(reset);

		add(south, BorderLayout.SOUTH);
		add(center, BorderLayout.CENTER);

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
