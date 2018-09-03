package stu.demo.client.views;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import edu.demo.common.entity.User;
import edu.demo.common.entity.action.Chat;
import edu.demo.common.utils.JsonUtil;
import stu.demo.client.message.MessageManager;

public class Dashboard extends BaseFrame {

	private static final long serialVersionUID = 1L;

	private static Dashboard dashboard;

	static JTabbedPane jTabbedpane = new JTabbedPane();

	static JList<User> jlist = new JList<>();

	public static class MainPanel extends JPanel {

		private static final long serialVersionUID = 8409752051305560889L;

		JTextArea messageViewArea = new JTextArea();

		public MainPanel() {

			setLayout(new BorderLayout());

			JPanel messageViewContainer = new JPanel();
			messageViewContainer.setLayout(new BorderLayout());

			// tab占4个字符
			messageViewArea.setTabSize(4);
			messageViewArea.setFont(new Font("标楷体", Font.BOLD, 16));
			messageViewArea.setLineWrap(true);// 激活自动换行功能
			messageViewArea.setWrapStyleWord(true);// 激活断行不断字功能
			messageViewArea.setEditable(false);
			messageViewContainer.add(new JScrollPane(messageViewArea), BorderLayout.CENTER);

			JPanel messageInputContainer = new JPanel();
			messageInputContainer.setLayout(new BorderLayout());
			final JTextArea messageInputArea = new JTextArea();
			// tab占4个字符
			messageInputArea.setTabSize(4);
			messageInputArea.setFont(new Font("标楷体", Font.BOLD, 16));
			messageInputArea.setLineWrap(true);// 激活自动换行功能
			messageInputArea.setWrapStyleWord(true);// 激活断行不断字功能
			messageInputContainer.add(new JScrollPane(messageInputArea), BorderLayout.CENTER);

			JButton sendButton = new JButton("发送");

			sendButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					String sendMessage = messageInputArea.getText();
					messageInputArea.setText("");
					messageViewArea.append("我:" + sendMessage);
					messageViewArea.append("\r\n");

					Chat chat = new Chat();
					chat.setChatMessage(sendMessage);
					chat.setFrom(LoginFrame.CURRENT_USER);

					chat.setTo(LoginFrame.CHAT_TO);

					MessageManager.sendMessgae(JsonUtil.objectToJson(chat));
				}
			});

			messageInputContainer.add(sendButton, BorderLayout.SOUTH);

			add(messageViewContainer, BorderLayout.CENTER);
			add(messageInputContainer, BorderLayout.SOUTH);
		}

		public void showIncomingMessage(User from, String message) {
			messageViewArea.append(from.getId() + ":" + message);
			messageViewArea.append("\r\n");
		}
	}

	private Dashboard(int width, int height) {
		super(width, height, "dashboard");

		jlist.setFixedCellWidth(50);
		jlist.setCellRenderer(new UserRenderer());

		jlist.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (e.getValueIsAdjusting()) {
					User user = jlist.getSelectedValue();

					LoginFrame.CHAT_TO = user;

					Component showComponent = getPanelByUser(user);

					if (showComponent == null) {
						JPanel panel = new MainPanel();
						panel.setName("User" + user.getId());

						jTabbedpane.addTab(user.getLoginName(), null, panel, "与\"" + user.getLoginName() + "\"的会话");

						showComponent = panel;
					}

					jTabbedpane.setSelectedComponent(showComponent);

				}

			}
		});

		add(new JScrollPane(jlist), BorderLayout.EAST);

		add(jTabbedpane, BorderLayout.CENTER);

		setVisible(true);
	}

	private class UserRenderer extends DefaultListCellRenderer {

		private static final long serialVersionUID = 7269079715233196107L;

		public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected,
				boolean cellHasFocus) {

			super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
			User user = (User) value;
			setText(user.getLoginName());
			return this;
		}
	}

	public static void refreshUserList(Set<User> users) {
		List<User> userList = new ArrayList<>(users);

		userList.remove(LoginFrame.CURRENT_USER);

		jlist.setModel(new DefaultComboBoxModel<User>(userList.toArray(new User[userList.size()])));
	}

	public static MainPanel getPanelByUser(User user) {
		for (Component component : jTabbedpane.getComponents()) {
			System.out.println(component.getName());
			if (("User" + user.getId()).equals(component.getName())) {
				return (MainPanel) component;
			}
		}
		return null;
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

	public static void showincomingMessage(User user, String message) {
		MainPanel panel = getPanelByUser(user);
		if (panel == null) {
			panel = new MainPanel();
			panel.setName("User" + user.getId());
			jTabbedpane.addTab(user.getLoginName(), null, panel, "与\"" + user.getLoginName() + "\"的会话");
		}
		panel.showIncomingMessage(user, message);
	}

	public static void main(String[] args) {
		generate();
	}
}
