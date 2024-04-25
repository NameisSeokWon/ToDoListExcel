package todolist.login;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import todolist.choice.ToDoListChoiceFrame;
import todolist.login.dao.ToDoListLoginDao;
import todolist.signup.ToDoListSignUpFrame;

public class ToDoListLoginFrame extends JFrame{
	static public String userId;
	JTextField t1 = new JTextField(15);
	JPasswordField t2 = new JPasswordField(15);
	boolean loginSuccessFlag = false;
	private ToDoListLoginDao loginDao;

	public ToDoListLoginFrame() {
		setTitle("ToDoListProgram - Login");
		setSize(300,200);
		setLocationRelativeTo(null);

		setLayout(new GridLayout(2,1));
		

		// 1번 판넬(ID,PW라벨/ID,PW입력 텍스트필드
		JPanel panel1 = new JPanel(new GridLayout(2,1));
		JPanel p1 = new JPanel();
		JLabel l1 = new JLabel("ID");
		p1.add(l1);
		p1.add(t1);
		JPanel p2 = new JPanel();
		JLabel l2 = new JLabel("PW");
		p2.add(l2);
		p2.add(t2);
		panel1.add(p1);
		panel1.add(p2);

		//2번 판넬(로그인버튼, 회원가입버튼)

		JPanel panel2 = new JPanel();
		JButton b1 = new JButton("ログイン");
		JButton b2 = new JButton("会員登録");

		panel2.add(b1);
		panel2.add(b2);
		b1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				loginCheck();
			}
		});
		b2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new ToDoListSignUpFrame();
			}
		});


		add(panel1);
		add(panel2);

		setVisible(true);

	}
	public void loginCheck() {
		String id = t1.getText();
		String pw = String.valueOf(t2.getPassword());

		ToDoListLoginDao tdldao = new ToDoListLoginDao();
		loginSuccessFlag = tdldao.login(id, pw);

		if(loginSuccessFlag) {
			JOptionPane.showMessageDialog(null, "ログイン完了");
			setVisible(false);
			new ToDoListChoiceFrame();
		} else {
			JOptionPane.showMessageDialog(null, "IDまたはPWを確認してください");
		}
	}
	public JTextField getT1() {
        return t1;
    }

    public JPasswordField getT2() {
        return t2;
    }
    public void setLoginDao(ToDoListLoginDao loginDao) {
        this.loginDao = loginDao;
    }
	public static void main(String[] args) {
		new ToDoListLoginFrame();
	}
}
