package todolist.choice;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import todolist.login.ToDoListLoginFrame;
import todolist.mainframe.ToDoListMainFrame;
import todolist.readexcel.ReadExcelFrame;

public class ToDoListChoiceFrame extends JFrame {
	

	public ToDoListChoiceFrame() {
		setTitle("ToDoListProgram - Choice");
		setSize(400,200);
		setLocationRelativeTo(null);

		setLayout(new GridLayout(2,1));

		JPanel panel1 = new JPanel(new GridLayout(2,2));
		JPanel p1 = new JPanel();

		JButton b1 = new JButton("新規ファイルを作成");
		b1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new ToDoListMainFrame();
			}
		});
		JLabel l1 = new JLabel("←新規ファイル作成はこちらへ");
		p1.add(b1);
		p1.add(l1);

		JPanel p2 = new JPanel();

		JButton b2 = new JButton("既存ファイルを探す");
		b2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new ReadExcelFrame();
			}
		});
		JLabel l2 = new JLabel("←既存ファイルを呼び出すはこちらへ");
		p2.add(b2);
		p2.add(l2);
		JPanel panel2 = new JPanel();
		JButton b3 = new JButton("ログイン画面に戻る");
		b3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				new ToDoListLoginFrame();
			}
		});
		panel1.add(p1);
		panel1.add(p2);
		panel2.add(b3);

		add(panel1);
		add(panel2);
		setVisible(true);
	}

}
