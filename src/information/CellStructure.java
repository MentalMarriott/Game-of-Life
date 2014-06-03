package information;

import java.util.ArrayList;

/**
 * This will hold all the information needed about each cell in the grid.
 * This includes cell state, whether state should be changed, list of neighbour objects.
 * @author marriott
 *
 */
public class CellStructure {

	ArrayList<CellStructure> cellNeighbours;
	boolean stateAlive;
	boolean switchState;
	
	public CellStructure()
	{
		this.stateAlive = false; // false means dead, set to this by default
		this.switchState = false; //whether it should be swithced from its current state
		this.cellNeighbours = new ArrayList<CellStructure>(); // creates a empty list of neighbours
	}
	
	
	/**
	 * returns the list of neighbouring cell objects
	 * @return
	 */
	public ArrayList<CellStructure> getCellNeighbours() {
		return cellNeighbours;
	}
	
	/**
	 * sets an array of neighbouring cells
	 * @param cellNeighbours
	 */
	public void setCellNeighbours(ArrayList<CellStructure> cellNeighbours) {
		this.cellNeighbours = cellNeighbours;
	}
	
	/**
	 * Adds a neighbour to current list of neighbours.
	 * Also adds self to other cell list of neighbours
	 */
	public void addNeighbour(CellStructure cell)
	{
		//if this cell does not contain cell passed add it then add it to neighbour
		if(!this.getCellNeighbours().contains(cell))
		{
			this.cellNeighbours.add(cell);
			cell.addNeighbour(this);
		}
		
	}
	
	/**
	 * returns a boolean to see if cell is alive
	 * @return
	 */
	public boolean isStateAlive() {
		return stateAlive;
	}
	
	/**
	 * sets alive state of the cell
	 * @param state
	 */
	public void setStateAlive(boolean state) {
		this.stateAlive = state;
	}
	
	/**
	 * Should the cells current state switch
	 * @return
	 */
	public boolean isSwitchState() {
		return switchState;
	}
	
	/**
	 * Sets whether cells current state should switch
	 * @param switchState
	 */
	public void setSwitchState(boolean switchState) {
		this.switchState = switchState;
	}
	
	
	
	
}
