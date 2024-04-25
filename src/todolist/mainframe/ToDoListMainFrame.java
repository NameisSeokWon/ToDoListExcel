package todolist.mainframe;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import todolist.readexcel.ReadExcelFrame;

public class ToDoListMainFrame extends JFrame {
	JTextField t1 = new JTextField();
	JTextField t2 = new JTextField();
	JTextField t3 = new JTextField(5);
	JTextField t5 = new JTextField(30);
	JTextField t8 = new JTextField(30);
	String[] department = {"","コンテンツ部","IT部"};
	String[] priority = {"","★","★★","★★★","★★★★","★★★★★"};
	String[] status = {"","進行中","完了"};
	JComboBox<String> cb4= new JComboBox<>(department);
	JComboBox<String> cb6= new JComboBox<>(priority);
	JComboBox<String> cb7= new JComboBox<>(status);
	private XSSFSheet sheet;
	private Row row;
	private XSSFWorkbook workbook;

	public ToDoListMainFrame () {

		setTitle("ToDoListProgram");
		setSize(505,550);
		setLayout(new BorderLayout(20,20));
		setLocationRelativeTo(null);

		dateAndDay();
		addToDo();
		buttonCollection();

		setVisible(true);
	}
	void dateAndDay() {
		JPanel panel1 = new JPanel();		

		JLabel l1 = new JLabel("DATE");
		l1.setVerticalAlignment(JLabel.CENTER);
	    l1.setHorizontalAlignment(JLabel.CENTER);
		t1.setPreferredSize(new Dimension(200, 20));

		JLabel l2 = new JLabel("DAY");
		l2.setVerticalAlignment(JLabel.CENTER);
		l2.setHorizontalAlignment(JLabel.CENTER);
		t2.setPreferredSize(new Dimension(200, 20));


		panel1.add(l1); panel1.add(t1);
		panel1.add(l2); panel1.add(t2);

		add(panel1,BorderLayout.NORTH);
	}
	void addToDo() {
		JPanel panel2 = new JPanel(new GridLayout(0,1));

		JPanel row3 = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
		JLabel l3 = new JLabel("NO");
		l3.setVerticalAlignment(JLabel.CENTER);
		l3.setHorizontalAlignment(JLabel.LEFT);
		row3.add(l3); row3.add(t3); panel2.add(row3);

		JPanel row4 = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
		JLabel l4 = new JLabel("部署");
		l4.setVerticalAlignment(JLabel.CENTER);
		l4.setHorizontalAlignment(JLabel.LEFT);
		row4.add(l4); row4.add(cb4); panel2.add(row4);

		JPanel row5 = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
		JLabel l5 = new JLabel("TODO");
		l5.setVerticalAlignment(JLabel.CENTER);
		l5.setHorizontalAlignment(JLabel.LEFT);
		t5.setPreferredSize(new Dimension(t5.getPreferredSize().width, 40));
		row5.add(l5); row5.add(t5); panel2.add(row5);

		JPanel row6 = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
		JLabel l6 = new JLabel("重要度");
		l6.setVerticalAlignment(JLabel.CENTER);
		l6.setHorizontalAlignment(JLabel.LEFT);
		row6.add(l6); row6.add(cb6); panel2.add(row6);

		JPanel row7 = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
		JLabel l7 = new JLabel("完了可否");
		l7.setVerticalAlignment(JLabel.CENTER);
		l7.setHorizontalAlignment(JLabel.LEFT);
		row7.add(l7); row7.add(cb7); panel2.add(row7);

		JPanel row8 = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
		JLabel l8 = new JLabel("備考欄");
		l8.setVerticalAlignment(JLabel.CENTER);
		l8.setHorizontalAlignment(JLabel.LEFT);
		t8.setPreferredSize(new Dimension(t8.getPreferredSize().width, 40));
		row8.add(l8); row8.add(t8); panel2.add(row8);

		add(panel2,BorderLayout.CENTER);
	}
	void buttonCollection() {
		JPanel panel3 = new JPanel(new FlowLayout(FlowLayout.CENTER,10,10));
		JButton b1 = new JButton("SAVE");
		b1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				boolean check_success = checkInputData();
				if(check_success) {
					saveToExcel();
				}
			}
		});
		JButton b2 = new JButton("追加");
		b2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				addNewRow();
			}
		});
		JButton b3 = new JButton("内容初期化");
		b3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				clearAllFields();
			}
		});

		JButton b4 = new JButton("ファイルを呼び出す");
		b4.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new ReadExcelFrame();
			}
		});

		JButton b5 = new JButton("前に");
		b5.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		panel3.add(b1);
		panel3.add(b2);
		panel3.add(b3);
		panel3.add(b4);
		panel3.add(b5);

		add(panel3,BorderLayout.SOUTH);
	}
	void saveToExcel() {
		String fileName = "ToDoList_" + t1.getText() + ".xlsx";
		String filePath = "C:\\Users\\msi\\Desktop\\" + fileName;
		workbook = new XSSFWorkbook();
		sheet = workbook.createSheet("ToDoList");
		row = sheet.createRow(0);
		 for (int i = 0; i < 8; i++) {
			 Cell cell = row.createCell(i);
			 switch (i) {
			 case 0:
				 cell.setCellValue("(*)DATE");
				 break;
			 case 1:
				 cell.setCellValue("DAY");
	             break;
			 case 2:
				 cell.setCellValue("NO");
				 break;
			 case 3:
				 cell.setCellValue("部署");
				 break;
			 case 4:
				 cell.setCellValue("TODO");
				 break;
			 case 5:
				 cell.setCellValue("重要度");
				 break;
			 case 6:
				 cell.setCellValue("完了可否");
				 break;
			 case 7:
				 cell.setCellValue("備考欄");
				 break;
	            }

	            // 셀 스타일 생성
	            CellStyle style = workbook.createCellStyle();
	            // 테두리 스타일 설정

                style.setBorderTop(BorderStyle.THICK); // 위쪽 굵은 테두리
	            style.setBorderBottom(BorderStyle.THICK); // 아래쪽 굵은 테두리
	            style.setBorderLeft(BorderStyle.THICK); // 왼쪽 굵은 테두리
	            style.setBorderRight(BorderStyle.THICK); // 오른쪽 굵은 테두리

	            // 스타일을 셀에 적용
	            cell.setCellStyle(style);
	        }

        row = sheet.createRow(1);
        row.createCell(0).setCellValue(t1.getText());
        row.createCell(1).setCellValue(t2.getText());
        row.createCell(2).setCellValue(t3.getText());
        row.createCell(3).setCellValue(((JComboBox<String>) cb4).getSelectedItem().toString());
        row.createCell(4).setCellValue(t5.getText());
        row.createCell(5).setCellValue(((JComboBox<String>) cb6).getSelectedItem().toString());
        row.createCell(6).setCellValue(((JComboBox<String>) cb7).getSelectedItem().toString());
        row.createCell(7).setCellValue(t8.getText());

        // 엑셀 파일에 데이터 쓰기
        try (FileOutputStream outputStream = new FileOutputStream(filePath)) {
            workbook.write(outputStream);
            JOptionPane.showMessageDialog(null, "데이터가 엑셀 파일에 저장되었습니다.");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 엑셀 워크북 닫기
        try {
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        clearAllFields();
	}
	void addNewRow() {
		String fileName = "ToDoList_" + t1.getText() + ".xlsx";
	    String filePath = "C:\\Users\\msi\\Desktop\\" + fileName;

	    File file = new File(filePath);
	    XSSFWorkbook workbook;
	    if (file.exists()) {
	    	try (FileInputStream inputStream = new FileInputStream(file)) {
	            workbook = new XSSFWorkbook(inputStream);
	        } catch (IOException e) {
	            e.printStackTrace();
	            return;
	        }
	    } else {
	        // 파일이 존재하지 않으면 새로운 워크북 생성
	        workbook = new XSSFWorkbook();
	    }

	    XSSFSheet sheet = workbook.getSheet("ToDoList");
	    if (sheet == null) {
	        sheet = workbook.createSheet("ToDoList");
	    }

	    int lastRowNum = sheet.getLastRowNum();
	    if (lastRowNum == -1) {
	        // 행이 하나도 없는 경우, 첫 번째 행을 생성합니다.
	        row = sheet.createRow(0);
	    } else {
	        // 마지막 행 다음에 행을 생성합니다.
	        row = sheet.createRow(lastRowNum + 1);
	    }

	    row.createCell(0).setCellValue(t1.getText());
	    row.createCell(1).setCellValue(t2.getText());
	    row.createCell(2).setCellValue(t3.getText());
	    row.createCell(3).setCellValue(((JComboBox<String>) cb4).getSelectedItem().toString());
	    row.createCell(4).setCellValue(t5.getText());
	    row.createCell(5).setCellValue(((JComboBox<String>) cb6).getSelectedItem().toString());
	    row.createCell(6).setCellValue(((JComboBox<String>) cb7).getSelectedItem().toString());
	    row.createCell(7).setCellValue(t8.getText());

	    try (FileOutputStream outputStream = new FileOutputStream(filePath)) {
	        workbook.write(outputStream);
	        JOptionPane.showMessageDialog(null, "追加されました");
	    } catch (IOException e) {
	        e.printStackTrace();
	    } finally {
	        clearAllFields();
	        try {
	            workbook.close();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	}
	void clearAllFields() {
		t1.setText("");
		t2.setText("");
		t3.setText("");
		t5.setText("");
		t8.setText("");
	}
	public boolean checkInputData() {
		// yyyy-MM-dd
		String regex = "^\\d{4}-(0?[1-9]|1[0-2])-(0?[1-9]|[1-2][0-9]|3[0-1])$";
	    // 정규식 패턴을 컴파일
	    Pattern pattern = Pattern.compile(regex);
	    // 입력된 날짜를 체크한다.
	    Matcher matcher = pattern.matcher(t1.getText());
	    // 일치 여부 반환
	    if(!matcher.matches()) {
			JOptionPane.showMessageDialog(null, "日付はyyyy-mm-dd形式で入力してください");
			return false;
	    	}
		String regex1 = "^[가-힣ぁ-んァ-ン一-龥]+$";
		pattern = Pattern.compile(regex1);
		matcher = pattern.matcher(t2.getText());
	    // 일치 여부 반환
	    if(!matcher.matches()) {
			JOptionPane.showMessageDialog(null, "曜日は韓国語や日本語だけで入力してください");
			return false;
	    	}
		String regex2 = "^[1-9]\\d*$";
		pattern = Pattern.compile(regex2);
		matcher = pattern.matcher(t3.getText());
	    // 일치 여부 반환
	    if(!matcher.matches()) {
			JOptionPane.showMessageDialog(null, "番号は整数だけで入力してください");
			return false;
	    	}
	    String regex3 = "^[a-zA-Z가-힣ぁ-んァ-ン一-龥\\p{Punct} ]+$";
	    pattern = Pattern.compile(regex3);
	    matcher = pattern.matcher(t5.getText());
	    if(!matcher.matches()) {
	    	JOptionPane.showMessageDialog(null, "TODOは韓国語や日本語、英語だけで入力してください");
	    	return false;
	    }
	    String regex4 = "^(?:[가-힣ぁ-んァ-ン一-龥a-zA-Z\\s\\p{Punct}]+)?$";

	    matcher = pattern.matcher(t8.getText());
	    if(!matcher.matches()) {
	    	JOptionPane.showMessageDialog(null, "備考欄は韓国語や日本語だけで入力してください");
	    	return false;
	    }
	    return true;
	    }

}
