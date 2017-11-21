package stu.demo.client.views;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class ImagePanel extends JPanel {

	private static final long serialVersionUID = 7027097633173752283L;

	Image[] imgs;

	int currentIndex;

	public ImagePanel() {

		File images = new File(getClass().getResource("/img").getFile());

		File[] files = images.listFiles();

		imgs = new Image[files.length];

		for (int i = 0; i < imgs.length; i++) {
			ImageIcon imageIcon = new ImageIcon(files[i].getPath());
			imgs[i] = imageIcon.getImage();
		}

		currentIndex = 0;

	}

	public void changeImage() {

		if (++currentIndex >= imgs.length) {
			currentIndex = 0;
		}

		// 调用repaint时 会调用paintComponent方法
		repaint();
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		// 下面这行是为了背景图片可以跟随窗口自行调整大小，可以自己设置成固定大小
		g.drawImage(imgs[currentIndex], 0, 0, this.getWidth(), this.getHeight(), this);
	}
}
