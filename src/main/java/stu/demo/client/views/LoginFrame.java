package stu.demo.client.views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginFrame extends BaseFrame {

	private static final long serialVersionUID = 1L;

	private static LoginFrame loginFrame;

	private LoginFrame(int width, int height) {
		super(width, height, "login");

		setLayout(new BorderLayout());

		JPanel north = new JPanel();
		north.setBackground(Color.BLUE);

		JPanel west = new JPanel();
		west.setBackground(Color.GREEN);

		JPanel east = new JPanel();
		east.setBackground(Color.RED);

		JPanel center = new JPanel();
		center.setBackground(Color.CYAN);

		JPanel south = new JPanel();
		south.setBackground(Color.YELLOW);

		south.add(new JButton("login"));
		south.add(new JButton("reset"));

		JTextField userNameInput = new JTextField();
		JPasswordField passwordInput = new JPasswordField();

		center.add(userNameInput);
		center.add(passwordInput);

		add(north, BorderLayout.NORTH);
		add(south, BorderLayout.SOUTH);
		add(west, BorderLayout.WEST);
		add(east, BorderLayout.EAST);
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
