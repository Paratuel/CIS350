package package1;

import java.awt.Color;
import java.awt.Component;
import java.io.File;
import java.util.ArrayList;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;

public class MyCellRenderer extends DefaultTableCellRenderer implements TableCellRenderer{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//private boolean Opaqueness = true;
	
	private ProjectModel model;
	
	//private Color color;
	
	public MyCellRenderer(ProjectModel m){
		super();
		setOpaque(true);
		model = m;
		//model.load(new File("src/package1/file.ser"));
	}
	//@Override
	public Component getTableCellRendererComponent(JTable table, Object project, boolean t, boolean u, int i, int j) {
		Component aComponent = super.getTableCellRendererComponent(table, project, t, u, i, j);
	    //aComponent.setBackground(null);
	    aComponent.setBackground(model.get(i).getColor());
		return aComponent;
		//for(int q = 0; q < theList.size(); q++ ){
		//	for(int r = 0; r < 5; r++){
		//		table.setEditingRow(q);
		//		table.setEditingColumn(r);
				//table.setSelectionBackground(project.getColor());;
		//aComponent.setBackground(project.getColor());
		//return aComponent;
			//}
		//}
		
	}
}
