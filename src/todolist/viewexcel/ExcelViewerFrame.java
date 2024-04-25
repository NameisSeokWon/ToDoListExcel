package todolist.viewexcel;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelViewerFrame extends JFrame {

    public ExcelViewerFrame(File excelFile) {
        setTitle("Excel Viewer");
        setSize(800, 600); // Increased size for better view
        setLocationRelativeTo(null);

        setLayout(new BorderLayout());
       

        JPanel panel = new JPanel(new GridLayout(1, 1));
        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        panel.add(scrollPane);
        add(panel, BorderLayout.CENTER);

        JButton closeButton = new JButton("Close");
        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(closeButton);
        add(buttonPanel, BorderLayout.SOUTH);

        readExcel(excelFile, textArea);
    }

    private void readExcel(File excelFile, JTextArea textArea) {
        try (FileInputStream fis = new FileInputStream(excelFile);
                XSSFWorkbook workbook = new XSSFWorkbook(fis)) {
            StringBuilder sb = new StringBuilder();
            workbook.forEach(sheet -> {
                sheet.forEach(row -> {
                    row.forEach(cell -> {
                        sb.append(cell.toString()).append("\t");
                    });
                    sb.append("\n");
                });
            });

            textArea.setText(sb.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}