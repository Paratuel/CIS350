package package1;

import java.awt.BorderLayout;
import java.awt.Dimension;
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
import javax.swing.table.TableModel;

import CIS350.ProjectModel;

public class ProjectGUI extends JFrame implements ActionListener {
	
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
	
	//private JList<Project> projectList;
	
	//private MyArrayList<Project> projectArray;
	
	private ListSelectionListener listener;
	
	private ProjectModel model;
	private CreateGUI newProject;
	private SubCreateGUI subGroup;
	
	public ProjectGUI(){
		setupFrame();
		model = new ProjectModel();
		table.setModel(model);
	}
	
	/**
	 * Sets up the panel for the parent GUI
	 */
	public void setupFrame(){
		frame = new JFrame();
		frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		frame.setTitle("Project Management");
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		fileMenu = new JMenu("File");
		helpMenu = new JMenu("Help");
		helpMenu.addActionListener(this);
		exitItem = new JMenuItem("Exit");
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
		fileMenu.addActionListener(this);
		helpMenu.add(aboutItem);
		menuBar.add(fileMenu);
		menuBar.add(helpMenu);

		//projectPanel = new JPanel();
		
		//projectArray = new MyArrayList<Project>();
		//projectList = new JList<Project>(projectArray);
		//projectList.addListSelectionListener(listener);
		
		//projectPanel.add(projectList);
//		projectPanel.setSize(new Dimension(800, 400));
//		add(projectPanel);
		
		JPanel panel;
		scrollPane = new JScrollPane();
		table = new JTable();
		
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		
		table.setToolTipText("");
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.getTableHeader().setReorderingAllowed(false);
		table.addMouseListener(new java.awt.event.MouseAdapter(){
			public void mouseClicked(java.awt.event.MouseEvent evt){
				//tableMouseClicked();
			}
		});
		
		scrollPane.setViewportView(table);
		
		frame.add(scrollPane, BorderLayout.CENTER);
		
		panel = setupNorthPanel();
		frame.add(panel, BorderLayout.NORTH);
		
		panel = setupSouthPanel();
		frame.add(panel, BorderLayout.SOUTH);
		
		frame.setJMenuBar(menuBar);
		frame.pack();
		frame.setVisible(true);
		frame.setSize(1000, 500);
		frame.setLocationRelativeTo(null);
	}
	
	/**
	 * Setting up bottom panel
	 * @return southPanel
	 */
	public JPanel setupSouthPanel(){
		southPanel = new JPanel(new FlowLayout());
		
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
		buttonPanel = new JPanel(new FlowLayout());
		//add(buttonPanel, BorderLayout.NORTH);
		
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
			newProject.clear();
			newProject.setVisible(true);
		
			if(newProject.isOkPressed()){
				Project p = new Project(newProject.getName(), newProject.getDueDate(), 
						newProject.getNotes());
				model.add(p);	
			}
			if(newProject.isCancel()){
				newProject.setVisible(false);
				return;
			}
		}
		if(e.getSource() == deleteItem){
			int index = table.getSelectedRow();
			if(index != -1){
				if (JOptionPane.showConfirmDialog(null, "You Are About To Delete This Project"
						, null, JOptionPane.OK_CANCEL_OPTION) != 0){
					setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
				}else{
					model.remove(model.get(index));
				}
			}
		}
		if(e.getSource() == editItem){
			int index = table.getSelectedRow();
//			if(projectArray.getSize() == 0){
//				JOptionPane.showMessageDialog(null, "Error: Nothing to"
//						+ " edit.");
//			}
//			else {
//				Project p = (Project) projectArray.getElementAt(
//				projectList.getSelectedIndex());
				//CreateGUI x = new CreateGUI(this, p);
				//if(x.isOkPressed()){
					//p = x.whatProject();
					//projectArray.delete(
					//		projectList.getSelectedIndex());
					//projectArray.add(p);
				//}
			//}
		}
		if(e.getSource() == saveItem){
			JFileChooser choose = new JFileChooser();
			FileNameExtensionFilter filter = new 
					FileNameExtensionFilter("Serialized Files", "ser");
			choose.setFileFilter(filter);
			choose.setSelectedFile(new File("file.ser"));
			int value = choose.showSaveDialog(this);
			if(value == JFileChooser.APPROVE_OPTION){
				//projectArray.save(choose.getSelectedFile());
			}	
		}
		if(e.getSource() == loadItem){
			JFileChooser choose = new JFileChooser();
			FileNameExtensionFilter filter = new 
					FileNameExtensionFilter("Serialized Files", "ser");
			choose.setFileFilter(filter);
			int value = choose.showOpenDialog(this);
			if(value == JFileChooser.APPROVE_OPTION){
				//projectArray.load(choose.getSelectedFile());
			}
		}
		if(e.getSource() == evaluate){
			//Project proj = (Project) projectArray.getElementAt
			//		(projectList.getSelectedIndex());
			String subString = "SubProjects:\n";
			
			//for (int i = 0; i < proj.getSubtasks().size(); i++){
			//	subString += proj.getSubtasks().elementAt(i);
			//}
			JOptionPane.showMessageDialog(null, subString);
		}
		if(e.getSource() == week1Button){
			
		}
		
		
	}

	
}
