package package1;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
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
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.FileNotFoundException;


/**
 * Creates the main GUI of the program.
 * @author Patrick Dishaw, Laura Young, Viet Duong, Nicholas Bushen
 *
 */
public class ProjectGUI extends JFrame implements ActionListener {

	/**
	 * serialVersionUID. 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Main frame of the GUI.
	 */
	private JFrame frame;

	/**
	 * The scrollPane to contain the JTable.
	 */
	private JScrollPane scrollPane;

	/**
	 * JTable for displaying all information.
	 */
	private JTable table;

	/**
	 * Button for the one week sort.
	 * (To be implemented later)
	 */
	private JButton week1Button;

	/**
	 * Button for the two week sort.
	 * (To be implemented later)
	 */
	private JButton week2Button; 

	/**
	 * Button for the four week sort.
	 * (To be implemented later)
	 */
	private JButton week4Button;

	/**
	 * Button for displaying all projects.
	 * (To be implemented later)
	 */
	private JButton allButton;

	/**
	 * Button for sorting projects by date.
	 */
	private JButton dateButton;

	/**
	 * Button to add a new project.
	 */
	private JButton newItem;

	/**
	 * Button to delete a project.
	 */
	private JButton deleteItem;

	/**
	 * Button to edit a project.
	 */
	private JButton editItem;

	/**
	 * Button to display sub-projects of a project.
	 */
	private JButton subItem;


	/**
	 * Button to declare a project complete or not complete.
	 */
	private JButton doneItem;

	/**
	 * Panel for buttons at the top of the GUI.
	 */
	private JPanel buttonPanel;

	/**
	 * Panel for buttons at the bottom of the GUI.
	 */
	private JPanel southPanel;

	/**
	 * Menu for containing exit, save, and load.
	 */
	private JMenu fileMenu; 

	/**
	 * Menu for containing about.
	 */
	private JMenu helpMenu;

	/**
	 * Exit MenuItem. Closes program.
	 */
	private JMenuItem exitItem;

	/**
	 * Displays information about the program.
	 */
	private JMenuItem aboutItem;

	/**
	 * Allows user to save current data as a file.
	 */
	private JMenuItem saveItem;

	/**
	 * Allows user to load previously saved data.
	 */
	private JMenuItem loadItem;

	/**
	 * Creates the ProjectModel for the program.
	 */
	private ProjectModel model;

	/**
	 * Creates a newProject for the program.
	 */
	private CreateGUI newProject;

	/**
	 * Instantiates the GUI.
	 */
	public ProjectGUI() {
		setupFrame();
		model = new ProjectModel();
		model.load(new File("src/package1/file.ser"));
		table.setModel(model);
	}

	/**
	 * Sets up the panel for the parent GUI.
	 */
	public final void setupFrame() {
		frame = new JFrame();
		frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		frame.setTitle("Project Management");

		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);

		fileMenu = new JMenu("File");
		//helpMenu = new JMenu("Help");
		//helpMenu.addActionListener(this);
		exitItem = new JMenuItem("Exit");
		exitItem.addActionListener(this);
		/*aboutItem = new JMenuItem("About");
		aboutItem.addActionListener(this);
		saveItem = new JMenuItem("Save");
		saveItem.addActionListener(this);
		loadItem = new JMenuItem("Load");
		loadItem.addActionListener(this);
		fileMenu.add(saveItem);
		fileMenu.add(loadItem);
		fileMenu.addSeparator();
		fileMenu.addSeparator(); */
		fileMenu.add(exitItem);
		fileMenu.addActionListener(this);
		//helpMenu.add(aboutItem);
		menuBar.add(fileMenu);
		//menuBar.add(helpMenu);

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

		scrollPane.setVerticalScrollBarPolicy(
				ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

		table.setToolTipText("");
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.getTableHeader().setReorderingAllowed(false);
		table.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(final java.awt.event.MouseEvent evt) {
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
	 * Setting up bottom panel.
	 * @return southPanel
	 */
	public final JPanel setupSouthPanel() {
		southPanel = new JPanel(new FlowLayout());

		newItem = new JButton("New Project");
		newItem.addActionListener(this);

		deleteItem = new JButton("Delete Project");
		deleteItem.addActionListener(this);

		editItem = new JButton("Edit Project");
		editItem.addActionListener(this);

		subItem = new JButton("Sub-Projects");
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
	 * Setting up top panel.
	 * @return buttonPanel
	 */
	public final JPanel setupNorthPanel() {
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

		dateButton = new JButton("Sort by Date");
		dateButton.addActionListener(this);

		buttonPanel.add(allButton);
		buttonPanel.add(week1Button);
		buttonPanel.add(week2Button);
		buttonPanel.add(week4Button);
		buttonPanel.add(dateButton);

		return buttonPanel;
	}

	/**
	 * Assigns actions to buttons and JMenuItems.
	 * @param e determines what is clicked and what to do
	 */
	public final void actionPerformed(final ActionEvent e) {
		if (e.getSource() == exitItem) {
			model.save(new File("src/package1/file.ser"));
			System.exit(0);
		}
		//		if (e.getSource() == aboutItem) {
		//			JOptionPane.showMessageDialog(null, "Hello!");
		//		}
		if (e.getSource() == newItem) {
			newProject = new CreateGUI(this);

			if (newProject.isOkPressed()) {
				Project p = new Project(newProject.getName(), null, newProject.getDueDate(), 
						newProject.getReminder(), newProject.getNotes(),  false);
				//				Project p = newProject.whatProject();
				model.add(p);	
				model.sortByName();
			}
		}
		if (e.getSource() == deleteItem) {
			int index = table.getSelectedRow();
			if (index != -1) {
				if (JOptionPane.showConfirmDialog(null, 
						"Are you sure you would like to delete this project?"
						, null, JOptionPane.OK_CANCEL_OPTION) != 0) {
					setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
				} else {
					model.remove(model.get(index));
				}
			}
		}
		if (e.getSource() == editItem) {
			if (model.getSize() == 0) {
				JOptionPane.showMessageDialog(null, "Error: Nothing to"
						+ " edit.");
			} else {
				int index = table.getSelectedRow();
				if (index != -1) {
					newProject = new CreateGUI(this, 
							model.get(index).getName(), 
							model.get(index).getSubName(),
							model.get(index).getDueDate(), 
							model.get(index).getReminder(),
							model.get(index).getNotes());
					//model.remove(model.get(index));

					if (newProject.isOkPressed()) {
						Project s = new Project(newProject.getName(), 
								newProject.getSub(), newProject.getDueDate(), 
								newProject.getReminder(), newProject.getNotes(), 
								false);
						if (model.get(index).getName() != newProject.getName()) {
							model.upDate(model.get(index).getName(), 
									newProject.getName());
						}

						model.remove(model.get(index));
						model.add(s);
						model.sortByName();
					}
				} else {
					JOptionPane.showMessageDialog(this, "Please pick a project.");
				}
			}
		}

		if (e.getSource() == doneItem) {
			if (model.getSize() == 0) {
				JOptionPane.showMessageDialog(null, "No project selected.");
			} else {
				int index = table.getSelectedRow();
				if (index != -1) {
					if (model.get(index).getDone() == false) {
						if (JOptionPane.showConfirmDialog(null, 
								"Have you completed the project?"
								, null, JOptionPane.OK_CANCEL_OPTION) != 0) {
							setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);


						} else {
							model.get(index).setDone(true);
						}
					} else {
						model.get(index).setDone(false);
					}
				} else {
					JOptionPane.showMessageDialog(null, "No project selected.");
				}
				model.refresh();
			}
		}
		/*
		if (e.getSource() == saveItem) {
			JFileChooser choose = new JFileChooser();
			FileNameExtensionFilter filter = new 
					FileNameExtensionFilter("Serialized Files", "ser");
			choose.setFileFilter(filter);
			choose.setSelectedFile(new File("file.ser"));
			int value = choose.showSaveDialog(this);
			if (value == JFileChooser.APPROVE_OPTION) {
				model.save(choose.getSelectedFile());
			}	
		}

		if (e.getSource() == loadItem) {
			JFileChooser choose = new JFileChooser();
			FileNameExtensionFilter filter = new 
					FileNameExtensionFilter("Serialized Files", "ser");
			choose.setFileFilter(filter);
			int value = choose.showOpenDialog(this);
			if (value == JFileChooser.APPROVE_OPTION) {
				model.load(choose.getSelectedFile());
			}
		}
		 */
		if (e.getSource() == subItem) {
			int index = table.getSelectedRow();
			if (index != -1) {
				String name = model.get(index).getName();
				newProject = new CreateGUI(this, name);
				//subGroup = new SubCreateGUI(this);

				if (newProject.isOkPressed()) {
					Project p = new Project(newProject.getName(), newProject.getSub(), newProject.getDueDate(), 
							newProject.getReminder(), newProject.getNotes(), false);
					//Project p = newProject.whatProject();
					model.add(p);
					model.sortByName();

				}
			} else {
				JOptionPane.showMessageDialog(this, "Please pick a project.");
			}
		}
		if (e.getSource() == week1Button) {
			model.sortByWeek(1);
		}
		if (e.getSource() == week2Button) {
			model.sortByWeek(2);
		}
		if (e.getSource() == week4Button) {
			model.sortByWeek(4);
		}
		if (e.getSource() == allButton) {
			model.sortByName();
		}
		if (e.getSource() == dateButton) {
			model.sortByDate();
		}



	}

}
