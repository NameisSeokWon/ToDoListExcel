package todolist.signup;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import todolist.login.ToDoListLoginFrame;
import todolist.signup.dao.ToDoListSignUpDao;

public class ToDoListSignUpFrame extends JFrame{

	JTextField t1 = new JTextField(10);
	JPasswordField t2 = new JPasswordField(10);
	JPasswordField t3 = new JPasswordField(10);
	JTextField t4 = new JTextField(10);
	JTextField t5 = new JTextField(10);
	JTextField t6 = new JTextField(10);
	JTextField t7 = new JTextField(10);
	JTextField t8 = new JTextField(10);
	JTextField t10 = new JTextField(10);
	JTextField t11 = new JTextField(10);
	JRadioButton r1 = new JRadioButton("男");
	JRadioButton r2 = new JRadioButton("女");
	String[] contract = {"フリー","契約社員","正社員"};
	JComboBox<String> cb12 = new JComboBox<>(contract);

	boolean inputErrorFlag = false;

	public ToDoListSignUpFrame () {

		setTitle("ToDoListProgram - 회원가입");
		setSize(600,600);
		setLocationRelativeTo(null);


		JPanel panel1 = new JPanel(new GridLayout(12,2));
		JLabel l1 = new JLabel("ID");
		panel1.add(l1);
		panel1.add(t1);
		JLabel l2 = new JLabel("PW");
		panel1.add(l2);
		panel1.add(t2);
		JLabel l3 = new JLabel("PW確認");
		panel1.add(l3);
		panel1.add(t3);
		JLabel l4 = new JLabel("名前(漢字)");
		panel1.add(l4);
		panel1.add(t4);
		JLabel l5 = new JLabel("名前(フリガナ)");
		panel1.add(l5);
		panel1.add(t5);
		JLabel l6 = new JLabel("名前(英語)");
		panel1.add(l6);
		panel1.add(t6);
		JLabel l7 = new JLabel("phonenumber");
		panel1.add(l7);
		panel1.add(t7);
		JLabel l8 = new JLabel("address");
		panel1.add(l8);
		panel1.add(t8);
		JLabel l9 = new JLabel("gender");
		JPanel genderPanel = new JPanel();
		ButtonGroup buttonGroup = new ButtonGroup();
		panel1.add(l9);
		buttonGroup.add(r1);
		buttonGroup.add(r2);

		genderPanel.add(r1);
		genderPanel.add(r2);
		panel1.add(genderPanel);

		JLabel l10 = new JLabel("email");
		panel1.add(l10);
		panel1.add(t10);
		JLabel l11 = new JLabel("birth");
		panel1.add(l11);
		panel1.add(t11);
		JLabel l12 = new JLabel("employment");
		panel1.add(l12);
		panel1.add(cb12);

		JPanel panel2 = new JPanel();
		JButton b1 = new JButton("登録");
		b1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				inputCheck();
				if(!inputErrorFlag && checkInputData()) {
					saveWeavusInfo();
				}
			}
		});
		JButton b2 = new JButton("前に");
		b2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				new ToDoListLoginFrame();
			}
		});
		panel2.add(b1);
		panel2.add(b2);

		add(panel1,BorderLayout.CENTER);
		add(panel2,BorderLayout.SOUTH);
		setVisible(true);

	}
	void inputCheck() {
		String pw1 = String.valueOf(t2.getPassword());
		String pw2 = String.valueOf(t3.getPassword());

		if(t1.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "IDを入力してください");
			inputErrorFlag = true;
		}
		if(pw1.isEmpty()) {
			JOptionPane.showMessageDialog(null, "PWを入力してください");
			inputErrorFlag = true;
		}
		if(pw2.isEmpty()) {
			JOptionPane.showMessageDialog(null, "確認PWを入力してください");
			inputErrorFlag = true;
		}
		if(t4.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "名前(漢字)を入力してください");
			inputErrorFlag = true;
		}
		if(t5.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "名前(フリガナ)を入力してください");
			inputErrorFlag = true;
		}
		if(t6.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "名前(英語)を入力してください");
			inputErrorFlag = true;
		}
		if(t7.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "phonenumberを入力してください");
			inputErrorFlag = true;
		}
		if(t8.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "addressを入力してください");
			inputErrorFlag = true;
		}
		if(t10.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "emailを入力してください");
			inputErrorFlag = true;
		}
		if(t11.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "birthを入力してください");
			inputErrorFlag = true;
		}
	}
	public boolean checkInputData() {
		boolean isValid = true;
		// 이름 한자 검사
		String regex = "^[一-龥]+$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(t4.getText());

		if(!matcher.matches()) {
			JOptionPane.showMessageDialog(null, "名前(漢字)は漢字で入力してください");
			return false;
		}
		// 이름 후리가나 검사
		String regex2 = "^[ァ-ン]+$";
		pattern = Pattern.compile(regex2);
		matcher = pattern.matcher(t5.getText());
		if(!matcher.matches()) {
			JOptionPane.showMessageDialog(null, "名前(フリガナ)はフリガナで入力してください");
			return false;
		}
		// 이름 영어 검사
		String regex3 = "^[a-zA-Z ]+$";
		pattern = Pattern.compile(regex3);
		matcher = pattern.matcher(t6.getText());
		if(!matcher.matches()) {
			JOptionPane.showMessageDialog(null, "名前(英語)は英語で入力してください");
			return false;
		}
		// 전화번호 검사
		String regex4 = "^[0-9]+$";
		pattern = Pattern.compile(regex4);
		matcher = pattern.matcher(t7.getText());
		if(!matcher.matches()) {
			JOptionPane.showMessageDialog(null, "phonenumberは数字だけで入力してください");
			return false;
		}
		// 메일 검사
		String regex5 = "^[a-zA-Z@\\p{Punct}]+$";
		pattern = Pattern.compile(regex5);
		matcher = pattern.matcher(t10.getText());
		if(!matcher.matches()) {
			JOptionPane.showMessageDialog(null, "emailはメールアドレス形式で入力してください");
			return false;
		}
		// 생년월일 검사
		String regex6 = "^\\d{4}-(0?[1-9]|1[0-2])-(0?[1-9]|[1-2][0-9]|3[0-1])$";
		pattern = Pattern.compile(regex6);
		matcher = pattern.matcher(t11.getText());
		if(!matcher.matches()) {
			JOptionPane.showMessageDialog(null, "birthは yyyy-mm-dd形式で入力してください");
			return false;
		}
		return isValid;
	}
	void saveWeavusInfo() {
		String id = t1.getText();
		String password = String.valueOf(t2.getPassword());
		String namekanji = t4.getText();
		String namekatakana = t5.getText();
		String nameenglish = t6.getText();
		String phone = t7.getText();
		String address = t8.getText();
		String gender = r1.isSelected() ? "0" : "1";
		String email = t10.getText();
		String birth = t11.getText();
		String employment = (String) cb12.getSelectedItem();

		ToDoListSignUpDao toDoListSignUpDao = new ToDoListSignUpDao();

		boolean signUpSuccess = toDoListSignUpDao.toDoListSignUp(id, password, namekanji, namekatakana, nameenglish, phone, address, gender, email, birth, employment);

		if (signUpSuccess) {
			JOptionPane.showMessageDialog(null, "会員登録完了");
			setVisible(false);
			new ToDoListLoginFrame();
		} else {
			JOptionPane.showMessageDialog(null, "会員登録失敗");
		}
	}

}
