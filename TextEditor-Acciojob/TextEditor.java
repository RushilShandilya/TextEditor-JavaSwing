import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class TextEditor extends JFrame {
    private JTextArea textArea;

    public TextEditor() {
        // Set up the JFrame
        setTitle("Text Editor Acciojob");
        setSize(1920, 1080);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create a JTextArea for text input
        textArea = new JTextArea();
        textArea.setBackground(Color.BLACK);
        textArea.setForeground(Color.WHITE);
        textArea.setCaretColor(Color.WHITE);
        textArea.setFont(new Font("Segoe UI", Font.PLAIN, 20)); // Use a prettier font

        JScrollPane scrollPane = new JScrollPane(textArea);
        getContentPane().add(scrollPane, BorderLayout.CENTER);

        //Menu Bar
        JMenuBar menuBar = new JMenuBar();
        menuBar.setBackground(Color.DARK_GRAY);
        menuBar.setForeground(Color.WHITE);
        menuBar.setFont(new Font("Segoe UI", Font.BOLD, 22));

        setJMenuBar(menuBar);

        // File Menu
        JMenu fileMenu = new JMenu("File");
        fileMenu.setForeground(Color.WHITE);
        fileMenu.setFont(new Font("Segoe UI", Font.BOLD, 22));
        menuBar.add(fileMenu);

        // Menu Items for File Menu
        JMenuItem newWindowMenuItem = new JMenuItem("New");
        newWindowMenuItem.setFont(new Font("Segoe UI", Font.BOLD, 18));

        JMenuItem openMenuItem = new JMenuItem("Open");
        openMenuItem.setFont(new Font("Segoe UI", Font.BOLD, 18));

        JMenuItem saveMenuItem = new JMenuItem("Save");
        saveMenuItem.setFont(new Font("Segoe UI", Font.BOLD, 18));

        // Action Listeners for Menu Items
        newWindowMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new TextEditor().setVisible(true);
            }
        });

        openMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openFile();
            }
        });

        saveMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveFile();
            }
        });


        // Adding Menu items to File menu
        fileMenu.add(newWindowMenuItem);
        fileMenu.add(openMenuItem);
        fileMenu.add(saveMenuItem);

        //Edit Menu
        JMenu editMenu = new JMenu("Edit");
        editMenu.setForeground(Color.WHITE);
        editMenu.setFont(new Font("Segoe UI", Font.BOLD, 22));
        menuBar.add(editMenu);

        // Menu Items for Edit Menu
        JMenuItem cutMenuItem = new JMenuItem("Cut");
        cutMenuItem.setFont(new Font("Segoe UI", Font.BOLD, 18));

        JMenuItem copyMenuItem = new JMenuItem("Copy");
        copyMenuItem.setFont(new Font("Segoe UI", Font.BOLD, 18));

        JMenuItem pasteMenuItem = new JMenuItem("Paste");
        pasteMenuItem.setFont(new Font("Segoe UI", Font.BOLD, 18));

        JMenuItem selectAllMenuItem = new JMenuItem("Select All");
        selectAllMenuItem.setFont(new Font("Segoe UI", Font.BOLD, 18));

        JMenuItem closeWindowMenuItem = new JMenuItem("Close Window");
        closeWindowMenuItem.setFont(new Font("Segoe UI", Font.BOLD, 18));


        // Action Listeners for Edit Menu Items
        cutMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textArea.cut();
            }
        });

        Action copyAction = new AbstractAction("Copy") {
            @Override
            public void actionPerformed(ActionEvent e) {
                textArea.copy();
            }
        };
        copyMenuItem.addActionListener(copyAction);

        pasteMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textArea.paste();
            }
        });

        selectAllMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textArea.selectAll(); // Add this line
            }
        });

        closeWindowMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Closing the window
            }
        });

        // Adding menu items to Edit menu
        editMenu.add(cutMenuItem);
        editMenu.add(copyMenuItem);
        editMenu.add(pasteMenuItem);
        editMenu.add(selectAllMenuItem);
        editMenu.add(closeWindowMenuItem);
    }

    private void openFile() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new FileNameExtensionFilter("Text Files", "txt"));
        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            try {
                java.util.Scanner scanner = new java.util.Scanner(fileChooser.getSelectedFile());
                StringBuilder fileContent = new StringBuilder();
                while (scanner.hasNextLine()) {
                    fileContent.append(scanner.nextLine()).append("\n");
                }
                textArea.setText(fileContent.toString());
                scanner.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void saveFile() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new FileNameExtensionFilter("Text Files", "txt"));
        int result = fileChooser.showSaveDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileChooser.getSelectedFile()))) {
                writer.write(textArea.getText());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new TextEditor().setVisible(true);
            }
        });
    }
}
