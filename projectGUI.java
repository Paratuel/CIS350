package package1;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class ProjectGUI extends JDialog implements ActionListener {
	
	private static final long serialVersionUID = 1L;

	private JButton week1Button, week2Button, week4Button, allButton;
	
	private JPanel buttonPanel, projectPanel;

	private JMenuBar theMenuBar;
	
	private JMenu fileMenu, helpMenu;
	
	private JMenuItem exitItem, aboutItem, newItem, deleteItem, 
	editItem;
	
	private int WIDTH, HEIGHT;
	
	
	public ProjectGUI(){
		WIDTH = 500;
		HEIGHT = 500;
		week1Button = new JButton("1 Week");
		week1Button.addActionListener(this);
		week2Button = new JButton("2 Week");
		week2Button.addActionListener(this);
		week4Button = new JButton("4 Week");
		week4Button.addActionListener(this);
		allButton = new JButton("All Projects");
		allButton.addActionListener(this);
		buttonPanel = new JPanel();
		projectPanel = new JPanel();
		theMenuBar = new JMenuBar();
		fileMenu = new JMenu("File");
		helpMenu = new JMenu("Help");
		exitItem = new JMenuItem("Exit!");
		exitItem.addActionListener(this);
		aboutItem = new JMenuItem("About");
		aboutItem.addActionListener(this);
		newItem = new JMenuItem("New Project");
		newItem.addActionListener(this);
		deleteItem = new JMenuItem("Delete Project");
		deleteItem.addActionListener(this);
		editItem = new JMenuItem("Edit Project");
		editItem.addActionListener(this);
		
		fileMenu.add(newItem);
		fileMenu.add(editItem);
		fileMenu.add(deleteItem);
		fileMenu.addSeparator();
		fileMenu.add(exitItem);
		helpMenu.add(aboutItem);
		
		theMenuBar.add(fileMenu);
		theMenuBar.add(helpMenu);
		
		setJMenuBar(theMenuBar);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setVisible(true);
		setSize(WIDTH,HEIGHT);
		setLocationRelativeTo(null);
	}
	
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == exitItem){
			System.exit(0);
		}
		
		if(e.getSource() == aboutItem){
			JOptionPane.showMessageDialog(null, "Hello!");
		}
	}
}
