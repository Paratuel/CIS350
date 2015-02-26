package package1;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.*;

public class ProjectGUI extends JDialog implements ActionListener {
	
	private static final long serialVersionUID = 1L;

	private JButton week1Button, week2Button, week4Button, allButton;
	
	private JPanel buttonPanel, projectPanel;

	private JMenuBar theMenuBar;
	
	private JMenu fileMenu, helpMenu;
	
	private JMenuItem exitItem, aboutItem, newItem, deleteItem, 
	editItem, saveItem, loadItem;
	
	private int WIDTH, HEIGHT;
	
	private JList<Project> projectList;
	
	private Vector<Project> projectArray;
	//working on it
	
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
		saveItem = new JMenuItem("Save");
		saveItem.addActionListener(this);
		loadItem = new JMenuItem("Load");
		loadItem.addActionListener(this);
		projectArray = new Vector<Project>();
		projectList = new JList<Project>(projectArray);
		
		fileMenu.add(newItem);
		fileMenu.add(editItem);
		fileMenu.add(deleteItem);
		fileMenu.addSeparator();
		fileMenu.add(saveItem);
		fileMenu.add(loadItem);
		fileMenu.addSeparator();
		fileMenu.add(exitItem);
		helpMenu.add(aboutItem);
		
		theMenuBar.add(fileMenu);
		theMenuBar.add(helpMenu);
		
		buttonPanel.add(week1Button);
		buttonPanel.add(week2Button);
		buttonPanel.add(week4Button);
		buttonPanel.add(allButton);
		
		projectPanel.add(projectList);
		
		add(buttonPanel);
		add(projectPanel);
		setJMenuBar(theMenuBar);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setVisible(true);
		setSize(WIDTH,HEIGHT);
		setLocationRelativeTo(null);
		setLayout(new FlowLayout());
		setTitle("Beginning with the End");
	}
	
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == exitItem){
			System.exit(0);
		}
		
		if(e.getSource() == aboutItem){
			JOptionPane.showMessageDialog(null, "Hello!");
		}
		if(e.getSource() == newItem){
			Project p = new Project();
			new CreateGUI(this, p);
		}
		if(e.getSource() == deleteItem){
			projectList.remove(projectList.getSelectedIndex());
			
			
		}
		if(e.getSource() == editItem){
			Project p = projectArray.elementAt(
					projectList.getSelectedIndex());
			new CreateGUI(this, p);
		}
	}
}
