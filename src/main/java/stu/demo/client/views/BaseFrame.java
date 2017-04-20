package stu.demo.client.views;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class BaseFrame extends JFrame {
	private static final long serialVersionUID = 1L;

	static final Dimension SCREENSIZE;

	static {
		// 获取屏幕大小
		SCREENSIZE = Toolkit.getDefaultToolkit().getScreenSize();
	}

	public BaseFrame(int width, int height) {
		// 关闭窗体时关闭程序
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(width, height);
		// 居中
		setLocationRelativeTo(null);
	}
}
