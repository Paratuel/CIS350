package package1;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.*;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;

public class ProjectGUI extends JDialog implements ActionListener {
	
	private static final long serialVersionUID = 1L;

	private JFrame frame;
	private JScrollPane scrollPane;
	private JTable table;
	
	private JButton week1Button, week2Button, week4Button, allButton, 
	newItem, deleteItem, editItem;
	
	private JPanel buttonPanel, projectPanel, southPanel;
	
	private JMenu fileMenu, helpMenu;
	
	private JMenuItem exitItem, aboutItem, saveItem, loadItem, evaluate;
	
	private int WIDTH, HEIGHT;
	
	private JList<project> projectList;
	
	private MyArrayList<project> projectArray;
	
	private ListSelectionListener listener;
	//working on it
	
	public ProjectGUI(){
		setupFrame();
	}
	
	/**
	 * Sets up the panel for the parent GUI
	 */
	public void setupFrame(){
		frame = new JFrame();
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setTitle("Project Management");
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);

		JMenu fileMenu = new JMenu("File");
		menuBar.add(fileMenu);
		fileMenu.addActionListener(this);
		
		helpMenu = new JMenu("Help");
		exitItem = new JMenuItem("Exit!");
		exitItem.addActionListener(this);
		aboutItem = new JMenuItem("About");
		aboutItem.addActionListener(this);
		saveItem = new JMenuItem("Save");
		saveItem.addActionListener(this);
		loadItem = new JMenuItem("Load");
		loadItem.addActionListener(this);
		evaluate = new JMenuItem("Evaluate");
		evaluate.addActionListener(this);
		fileMenu.add(saveItem);
		fileMenu.add(loadItem);
		fileMenu.addSeparator();
		fileMenu.add(evaluate);
		fileMenu.addSeparator();
		fileMenu.add(exitItem);
		helpMenu.add(aboutItem);
		menuBar.add(fileMenu);
		menuBar.add(helpMenu);
		
		projectPanel = new JPanel();
		
		projectArray = new MyArrayList<project>();
		projectList = new JList<project>(projectArray);
		projectList.addListSelectionListener(listener);
		
		projectPanel.add(projectList);
		
		JPanel panel;
		scrollPane = new JScrollPane();
		
		frame.add(scrollPane, BorderLayout.CENTER);
		
		panel = setupNorthPanel();
		frame.add(panel, BorderLayout.NORTH);
		
		panel = setupSouthPanel();
		frame.add(panel, BorderLayout.SOUTH);
		
		//add(buttonPanel);
		add(projectPanel);
		//add(southPanel);
		setJMenuBar(menuBar);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setVisible(true);
		//setSize(WIDTH,HEIGHT);
		setLocationRelativeTo(null);
		setLayout(new FlowLayout());
		//setTitle("Project Manager");
		
		frame.pack();
		frame.setSize(1000, 500);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
	
	/**
	 * Setting up bottom panel
	 * @return southPanel
	 */
	public JPanel setupSouthPanel(){
		southPanel = new JPanel(new GridLayout(1, 4));
		add(southPanel, BorderLayout.SOUTH);
		
		newItem = new JButton("New Project");
		newItem.addActionListener(this);
		
		deleteItem = new JButton("Delete Project");
		deleteItem.addActionListener(this);
		
		editItem = new JButton("Edit Project");
		editItem.addActionListener(this);
		
		southPanel.add(newItem);
		southPanel.add(deleteItem);
		southPanel.add(editItem);
		return southPanel;
	}
	
	/**
	 * Setting up top panel
	 * @return buttonPanel
	 */
	public JPanel setupNorthPanel(){
		buttonPanel = new JPanel(new GridLayout(1, 4));
		add(buttonPanel, BorderLayout.NORTH);
		
		week1Button = new JButton("1 Week");
		week1Button.addActionListener(this);
		
		week2Button = new JButton("2 Week");
		week2Button.addActionListener(this);
		
		week4Button = new JButton("4 Week");
		week4Button.addActionListener(this);
		
		allButton = new JButton("All Projects");
		allButton.addActionListener(this);
		
		buttonPanel.add(week1Button);
		buttonPanel.add(week2Button);
		buttonPanel.add(week4Button);
		buttonPanel.add(allButton);
		
		return buttonPanel;
	}
	
	/**
	 * Assigns actions to buttons and JMenuItems
	 * 
	 */
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == exitItem){
			System.exit(0);
		}
		if(e.getSource() == aboutItem){
			JOptionPane.showMessageDialog(null, "Hello!");
		}
		if(e.getSource() == newItem){
			project p = new project();
			CreateGUI x = new CreateGUI(this, p);
			if(x.isOkPressed()){
				p = x.whatProject();
				projectArray.add(p);	
			}
		}
		if(e.getSource() == deleteItem){
			if(projectArray.getSize() == 0){
				JOptionPane.showMessageDialog(null, "Error: Nothing to"
						+ " delete.");
			}
			else{
			projectArray.delete(
					projectList.getSelectedIndex());;
			}
		}
		if(e.getSource() == editItem){
			if(projectArray.getSize() == 0){
				JOptionPane.showMessageDialog(null, "Error: Nothing to"
						+ " edit.");
			}
			else {
				project p = (project) projectArray.getElementAt(
				projectList.getSelectedIndex());
			CreateGUI x = new CreateGUI(this, p);
				if(x.isOkPressed()){
					p = x.whatProject();
					projectArray.delete(
							projectList.getSelectedIndex());
					projectArray.add(p);
				}
			}
		}
		if(e.getSource() == saveItem){
			JFileChooser choose = new JFileChooser();
			FileNameExtensionFilter filter = new 
					FileNameExtensionFilter("Serialized Files", "ser");
			choose.setFileFilter(filter);
			choose.setSelectedFile(new File("file.ser"));
			int value = choose.showSaveDialog(this);
			if(value == JFileChooser.APPROVE_OPTION){
				projectArray.save(choose.getSelectedFile());
			}	
		}
		if(e.getSource() == loadItem){
			JFileChooser choose = new JFileChooser();
			FileNameExtensionFilter filter = new 
					FileNameExtensionFilter("Serialized Files", "ser");
			choose.setFileFilter(filter);
			int value = choose.showOpenDialog(this);
			if(value == JFileChooser.APPROVE_OPTION){
				projectArray.load(choose.getSelectedFile());
			}
		}
		if(e.getSource() == evaluate){
			project proj = (project) projectArray.getElementAt
					(projectList.getSelectedIndex());
			String subString = "SubProjects:\n";
			
			for (int i = 0; i < proj.getSubtasks().size(); i++){
				subString += proj.getSubtasks().elementAt(i);
			}
			JOptionPane.showMessageDialog(null, subString);
		}
		if(e.getSource() == week1Button){
			
		}
		
		
	}

	
}
