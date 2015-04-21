package packageproj;

import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;

public class MyCellRenderer extends DefaultTableCellRenderer implements TableCellRenderer{

  /** serialVersionUID. */
  private static final long serialVersionUID = 1L;
  
  private ProjectModel model;
    
  /**
   * This class allows for color to be added to table cells.
   * @param mod given ProjectModel
   */
  public MyCellRenderer(ProjectModel mod) {
    super();
    setOpaque(true);
    model = mod;
  }
  
  /**
   * returns the component for the color.
   * @param table given JTable
   * @param project given project
   * @param tr given boolean
   * @param ui given boolean
   * @param index given index
   * @param ju given boolean
   */
  public Component getTableCellRendererComponent(JTable table, Object project, boolean tr, 
      boolean ui, int index, int ju) {
    Component theComponent = super.getTableCellRendererComponent(table, project, tr, ui, index, ju);
    theComponent.setBackground(model.get(index).getColor());
    return theComponent;   
  }
}
