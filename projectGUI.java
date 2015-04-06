package package1;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.GregorianCalendar;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.WindowConstants;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;

public class ProjectGUI extends JFrame implements ActionListener {
	
	private static final long serialVersionUID = 1L;

	private JFrame frame;
	private JScrollPane scrollPane;
	private JTable table;
	
	private JButton week1Button, week2Button, week4Button, allButton, 
	newItem, deleteItem, editItem, subItem, doneItem;
	
	private JPanel buttonPanel, ProjectPanel, southPanel;
	
	private JMenu fileMenu, helpMenu;
	
	private JMenuItem exitItem, aboutItem, saveItem, loadItem;
	
	private int WIDTH, HEIGHT;
	
	//private JList<Project> ProjectList;
	
	//private MyArrayList<Project> ProjectArray;
	
	private ListSelectionListener listener;
	
	private ProjectModel model;
	private CreateGUI newProject;
	//private SubCreateGUI subGroup;
	
	public ProjectGUI(){
		setupFrame();
		model = new ProjectModel();
		model.load(new File("src/package1/file.ser"));
		table.setModel(model);
	}
	
	/**
	 * Sets up the panel for the parent GUI
	 */
	public void setupFrame(){
		frame = new JFrame();
		frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
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
/*		saveItem = new JMenuItem("Save");
		saveItem.addActionListener(this);
		loadItem = new JMenuItem("Load");
		loadItem.addActionListener(this);
		fileMenu.add(saveItem);
		fileMenu.add(loadItem);
		fileMenu.addSeparator();
		fileMenu.addSeparator();*/
		fileMenu.add(exitItem);
		fileMenu.addActionListener(this);
		helpMenu.add(aboutItem);
		menuBar.add(fileMenu);
		menuBar.add(helpMenu);

		//ProjectPanel = new JPanel();
		
		//ProjectArray = new MyArrayList<Project>();
		//ProjectList = new JList<Project>(ProjectArray);
		//ProjectList.addListSelectionListener(listener);
		
		//ProjectPanel.add(ProjectList);
//		ProjectPanel.setSize(new Dimension(800, 400));
//		add(ProjectPanel);
		
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
		
		subItem = new JButton("Split Project");
		subItem.addActionListener(this);
		
		doneItem = new JButton("Completed");
		doneItem.addActionListener(this);
		
		southPanel.add(newItem);
		southPanel.add(deleteItem);
		southPanel.add(editItem);
		southPanel.add(subItem);
		southPanel.add(doneItem);
		
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
			model.save(new File("src/package1/file.ser"));
			System.exit(0);
		}
		if(e.getSource() == aboutItem){
			JOptionPane.showMessageDialog(null, "Hello!");
		}
		if(e.getSource() == newItem){
			newProject = new CreateGUI(this);
		
			if(newProject.isOkPressed()){
				String t = null;
				Project p = new Project(newProject.getName(), t, newProject.getDueDate(), 
						 newProject.getNotes(), newProject.getReminder(), false);
				//Project p = newProject.whatProject();
				model.add(p);	
				model.sortByDate();
			}
		}
		if(e.getSource() == deleteItem){
			int index = table.getSelectedRow();
			if(index != -1){
				if (JOptionPane.showConfirmDialog(null, "Are you sure you would like to delete this project?"
						, null, JOptionPane.OK_CANCEL_OPTION) != 0){
					setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
				} else {
					model.remove(model.get(index));
				}
			}
		}
		if(e.getSource() == editItem){
			if(model.getSize() == 0){
				JOptionPane.showMessageDialog(null, "Error: Nothing to"
						+ " edit.");
			}
			else {
				int index = table.getSelectedRow();
				if(index != -1){
					newProject = new CreateGUI(this, model.get(index).getName(), model.get(index).getSubName(),
						model.get(index).getDueDate(), model.get(index).getReminder(), model.get(index).getNotes());
					//model.remove(model.get(index));
					
					if(newProject.isOkPressed()){
						Project s = new Project(newProject.getName(), newProject.getSub(), newProject.getDueDate(), 
								newProject.getNotes(), newProject.getReminder(), false);
						if(model.get(index).getName() != newProject.getName()){
							model.upDate(model.get(index).getName(), newProject.getName());
						}
						
						model.remove(model.get(index));
						model.add(s);
						model.sortByDate();
					}
				}else{
					JOptionPane.showMessageDialog(this, "Please pick a project.");
				}
			}
		}
		if (e.getSource() == doneItem) {
			if (model.getSize() == 0) {
				JOptionPane.showMessageDialog(null, "No Projects.");
			} else {
				int index = table.getSelectedRow();
				if(index != -1){
					if (model.get(index).getDone() == false){
						if (JOptionPane.showConfirmDialog(null, "Have You Completed the Project?"
								, null, JOptionPane.OK_CANCEL_OPTION) != 0){
							setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
							model.get(index).setDone(true);
						}
					}else{
							model.get(index).setDone(false);
					}
					
				}else{
					JOptionPane.showMessageDialog(null, "Pick a Project.");
				}
			}
		}/*
		if (e.getSource() == saveItem){
			JFileChooser choose = new JFileChooser();
			FileNameExtensionFilter filter = new 
					FileNameExtensionFilter("Serialized Files", "ser");
			choose.setFileFilter(filter);
			choose.setSelectedFile(new File("file.ser"));
			int value = choose.showSaveDialog(this);
			if(value == JFileChooser.APPROVE_OPTION){
				model.save(choose.getSelectedFile());
			}	
		}
		if(e.getSource() == loadItem) {
			JFileChooser choose = new JFileChooser();
			FileNameExtensionFilter filter = new 
					FileNameExtensionFilter("Serialized Files", "ser");
			choose.setFileFilter(filter);
			int value = choose.showOpenDialog(this);
			if(value == JFileChooser.APPROVE_OPTION) {
				model.load(choose.getSelectedFile());
			}
		}*/
		if(e.getSource() == subItem) {
			int index = table.getSelectedRow();
			if (index != -1){
				String name = model.get(index).getName();
				newProject = new CreateGUI(this, name);
			//subGroup = new SubCreateGUI(this);
			
				if(newProject.isOkPressed()){
					Project p = new Project(newProject.getName(), newProject.getSub(), newProject.getDueDate(), 
							newProject.getNotes(), newProject.getReminder(), false);
					//Project p = newProject.whatProject();
					model.add(p);
					model.sortByName();
					
				}
			}else{
				JOptionPane.showMessageDialog(this, "Please pick a project.");
			}
			
//			String subString = "SubProjects:\n";
//
//			for (int i = 0; i < proj.getSubtasks().size(); i++) {
//				subString += proj.getSubtasks().get(i).toString() + "\n----------------------------\n";
//			}
//			JOptionPane.showMessageDialog(null, subString);
		}
		if(e.getSource() == week1Button){

		}


	}

	
}
