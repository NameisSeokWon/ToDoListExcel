package todolist.readexcel;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import todolist.viewexcel.ExcelViewerFrame;

public class ReadExcelFrame extends JFrame{

	private DefaultListModel<String> fileListModel;

	public ReadExcelFrame() {
		setTitle("ToDoListProgram - ReadExcelFile");
		setSize(400,250);
		setLocationRelativeTo(null);

		setLayout(new BorderLayout());

		JPanel panel = new JPanel(new GridLayout(1,1));
		fileListModel = new DefaultListModel<>();
		JList<String> fileList = new JList<>(fileListModel);
		JScrollPane listScrollPane = new JScrollPane(fileList);

		panel.add(listScrollPane);
		add(panel, BorderLayout.CENTER);

		JPanel panel2 = new JPanel(new GridLayout(1,3));
		JButton b1 = new JButton("検索");
		b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 파일 선택 대화 상자 생성
                JFileChooser fileChooser = new JFileChooser();

                // 파일 필터 설정: 엑셀 파일 확장자만 표시
                FileNameExtensionFilter filter = new FileNameExtensionFilter("Excel Files", "xls", "xlsx");
                fileChooser.setFileFilter(filter);

                // 파일 선택 모드를 파일 선택으로 설정
                fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

                // 파일 선택 대화 상자 표시
                int result = fileChooser.showOpenDialog(null);

                // 사용자가 선택한 파일이 있다면
                if (result == JFileChooser.APPROVE_OPTION) {
                    // 선택한 파일의 경로를 가져와서 리스트 영역에 표시
                    File selectedFile = fileChooser.getSelectedFile();
                    fileListModel.addElement(selectedFile.getAbsolutePath());
                }
            }
        });
		JButton b2 = new JButton("開く");
		b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // ExcelViewerFrame을 생성하고 보여주기
            	String selectedFilePath = fileList.getSelectedValue();
            	if(selectedFilePath != null) {
            		File excelFile = new File(selectedFilePath);
            		new ExcelViewerFrame(excelFile).setVisible(true);
            	}
            }
        });
		JButton b3 = new JButton("前に");
		b3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		panel2.add(b1);
		panel2.add(b2);
		panel2.add(b3);
		add(panel2, BorderLayout.SOUTH);

        setVisible(true);
    }

	
}
