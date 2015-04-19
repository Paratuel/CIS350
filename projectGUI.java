package package1;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.TableModel;
import javax.swing.text.Document;

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
	private final String newLine = "";

	/**
	 * Allows user to load previously saved data.
	 */
	private MyCellRenderer pleaseWork;
	
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
		model.checkingReminders();
		setDefaultLookAndFeelDecorated(true);
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
		exitItem = new JMenuItem("Exit");
		exitItem.addActionListener(this);
		fileMenu.add(exitItem);
		menuBar.add(fileMenu);
		JPanel panel;
		
		
		model = new ProjectModel();
		model.load(new File("src/package1/file.ser"));
		pleaseWork = new MyCellRenderer(model);
		table = new JTable(model);
		for(int i = 0; i < 6; i++){
		table.getColumnModel().getColumn(i).setCellRenderer(pleaseWork);
		//hello.getTableCellRendererComponent(table, model.get(j), false, false, j, i);
		}
		
		scrollPane = new JScrollPane(table);
		
		scrollPane.setVerticalScrollBarPolicy(
				ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

		table.setToolTipText("");
		ListSelectionModel listMod = table.getSelectionModel();
		listMod.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.addMouseListener(new MouseAdapter(){
		     public void mouseClicked(MouseEvent e){
		      if (e.getClickCount() == 2){
		    	  JTable target = (JTable)e.getSource();
		    	  int row = target.getSelectedRow();
		    	  edit(row);
		         }
		      }
		     } );

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
		southPanel = new JPanel(new GridLayout(1,1));

	    String name = "Serif"; //"Serif", "SansSerif", "Monospaced", or a font name
	    
	    int style = Font.BOLD; //Font.ITALIC, Font.BOLD, or Font.BOLD | Font.ITALIC
	    
	    int size = 18; //any number size
	    
	    newItem = new JButton("+");
	    newItem.setFont(new Font(name, style, size));
	    newItem.setHorizontalAlignment(SwingConstants.CENTER);
	    newItem.addActionListener(this);
	    southPanel.add(newItem);
		return southPanel;
	}

	/**
	 * Setting up top panel.
	 * @return buttonPanel
	 */
	public final JPanel setupNorthPanel() {
		buttonPanel = new JPanel(new FlowLayout());

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
		if (e.getSource() == newItem) {
			newProject = new CreateGUI(this);

			if (newProject.isOkPressed()) {
				
				Project p = new Project(newProject.getName(), null, 
						newProject.getDueDate(), 
						newProject.getNotes(), 
						newProject.getReminder(), false);
				//				Project p = newProject.whatProject();
				model.add(p);	
				model.sortByName();
				
			}
			refresh();
		}
//
//		if (e.getSource() == doneItem) {
//			if (model.getSize() == 0) {
//				JOptionPane.showMessageDialog(null, "No project selected.");
//			} else {
//				int index = table.getSelectedRow();
//				if (index != -1) {
//					if (model.get(index).getDone() == false) {
//						if (JOptionPane.showConfirmDialog(null, 
//								"Have you completed the project?"
//								, null, JOptionPane.OK_CANCEL_OPTION) != 0) {
//							setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
//
//
//						} else {
//							model.get(index).setDone(true);
//						}
//					} else {
//						model.get(index).setDone(false);
//					}
//				} else {
//					JOptionPane.showMessageDialog(null, "No project selected.");
//				}
//				model.refresh();
//			}
//		}
		if (e.getSource() == week1Button) {
			//model.sortByWeek(1);
		}
		if (e.getSource() == week2Button) {
			//model.sortByWeek(2);
		}
		if (e.getSource() == week4Button) {
			//model.sortByWeek(4);
		}
		if (e.getSource() == allButton) {
			model.sortByName();
		}
		if (e.getSource() == dateButton) {
			model.sortByDate();
		}
	}
	public void edit(int i) {
		if (model.get(i).getSubName() == null) {
			newProject = new CreateGUI(this, 
					model.get(i).getName(),
					model.get(i).getDueDate(),
					model.get(i).getReminder(),
					model.get(i).getNotes());
		} else {
			newProject = new CreateGUI(this, 
					model.get(i).getName(),
					model.get(i).getSubName(),
					model.get(i).getDueDate(),
					model.get(i).getReminder(),
					model.get(i).getNotes());
		}

		if (newProject.isOkPressed()) {
			Project s = new Project(newProject.getName(), 
					newProject.getSub(), newProject.getDueDate(), 
					newProject.getNotes(),
					newProject.getReminder(), false);
			if (model.get(i).getName() 
					!= newProject.getName()) {
				model.upDate(model.get(i).getName(), 
						newProject.getName());
			}
			model.remove(model.get(i));
			model.add(s);
			model.sortByName();
		}
		if(newProject.isDeletePressed()){
			if(model.get(i).getSubName() != null){
				if(JOptionPane.showConfirmDialog(null, "YOU ARE ABOUT TO DELETE THIS SUB PROJECT!",
						null, JOptionPane.OK_CANCEL_OPTION) != 0) {
					setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
				}else{
					model.remove(model.get(i));
				}
			}else{
				int count = 0;
				for(int j = 0; j < model.getSize(); j++){
					if(model.get(i).getName().equals(model.get(j).getName())){
						count++;
					}
				}
				if(count == 1){
					if(JOptionPane.showConfirmDialog(null, "YOU ARE ABOUT TO DELETE THIS PROJECT!",
							null, JOptionPane.OK_CANCEL_OPTION) != 0) {
						setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
					}else{
						model.remove(model.get(i));
					}
				}else{
					if(JOptionPane.showConfirmDialog(null, "YOU CAN'T DELETE THIS PROJECT.\n" + 
							"IT HAS SUB PROJECTS ATTACHED TO IT!",
							null, JOptionPane.OK_CANCEL_OPTION) != 0) {
						setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
					}
				}
			}
		}
		if(newProject.isCompletePressed()){
			if(model.get(i).getDone() == false){
			model.get(i).setDone(true);
			}
			else{
			model.get(i).setDone(false);
			}
			model.refreshCell(i, 6);

		}
		if(newProject.isSubPressed()){
			newProject = new CreateGUI(this, 
					model.get(i).getName(),
					model.get(i).getSubName(),
					model.get(i).getDueDate(),
					model.get(i).getReminder(),
					model.get(i).getNotes());

			if (newProject.isOkPressed()) {
				Project p = new Project(newProject.getName(), 
						newProject.getSub(), newProject.getDueDate(), 
						newProject.getNotes(), 
						newProject.getReminder(), false);
				
				model.add(p);

			}
		}
		model.refresh(i);
}
	public void refresh(){
		for(int i = 0; i < model.getSize(); i++){
		//	table.
		}
	}
}
