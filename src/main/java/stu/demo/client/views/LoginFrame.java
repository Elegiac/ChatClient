package stu.demo.client.views;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginFrame extends BaseFrame {

	private static final long serialVersionUID = 1L;

	private static LoginFrame loginFrame;

	private LoginFrame(int width, int height) {
		super(width, height);

		JPanel panel = new JPanel();
		JTextField userNameInput = new JTextField();
		JPasswordField passwordInput = new JPasswordField();
		
		panel.add(userNameInput);
		panel.add(passwordInput);
		
		add(panel,BorderLayout.CENTER);
		
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
