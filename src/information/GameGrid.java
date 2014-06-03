package information;

import java.util.ArrayList;

/**
 * This will hold all the information on size of grid, what grid locations
 * hold what cells.
 * @author marriott
 *
 */
public class GameGrid {

	private int xSize, ySize, totalGridSize; // x(how many in each row) and y(how many rows)
								//size of grid
	private ArrayList<CellStructure> gridCellContents;
	private boolean userChooseAliveCells;
	
	/**
	 * initialises the game grid with dead cells
	 * @param xSize
	 * @param ySize
	 */
	public GameGrid(int xSize, int ySize, boolean userChooseAlivecells)
	{
		this.xSize = xSize;
		this.ySize = ySize;
		this.totalGridSize = this.xSize * this.ySize;
		this.gridCellContents = new ArrayList<CellStructure>();
		
		this.userChooseAliveCells = userChooseAlivecells;
		
		//creates a grid of dead cells
		for(int i = 0; i < this.totalGridSize; i++)
		{
			this.gridCellContents.add(new CellStructure());
		}
		
		setupNeighbours();
	}
	
	/**
	 * goes through cells in the grid and calculates whos neighbour is whose
	 * Only need to do half as the add neighbour adds itself to its added neighbour
	 */
	private void setupNeighbours() {
		int edgeCount = 0;
		for(int i = 0; i < this.gridCellContents.size(); i ++)
		{
			//if reached end of row start again
			if(edgeCount == xSize){
				edgeCount = 0;
			}
			
			//adds below neighbour
			if(i+xSize < this.gridCellContents.size())
			{
				this.gridCellContents.get(i).addNeighbour(this.gridCellContents.get(i+xSize));
			}
			
			//add right neighbours
			if(edgeCount != xSize-1)
			{
				this.gridCellContents.get(i).addNeighbour(this.gridCellContents.get(i+1));
			}
			
			//adds bottom left
			if(i + xSize < this.gridCellContents.size() && edgeCount != 0)
			{
				this.gridCellContents.get(i).addNeighbour(this.gridCellContents.get(i+xSize-1));
			}

			//adds bottom right neighbours
			if(i + xSize < this.gridCellContents.size() && edgeCount != xSize-1)
			{
				this.gridCellContents.get(i).addNeighbour(this.gridCellContents.get(i+xSize+1));
			}
			
			edgeCount++;		
		}
	}

	/**
	 * gets size of row
	 * @return
	 */
	public int getxSize() {
		return xSize;
	}

	/**
	 * gets number of rows
	 * @return
	 */
	public int getySize() {
		return ySize;
	}

	/**
	 * returns the array of cells in the grid. starting top left
	 * @return
	 */
	public ArrayList<CellStructure> getGridCellContents() {
		return gridCellContents;
	}

	/**
	 * this allows a check to be performed to see if user wants
	 * to set the cells that are alive or have a number of random
	 * cells spawn alive.
	 * @return
	 */
	public boolean isUserChooseAliveCells() {
		return userChooseAliveCells;
	}

	/**
	 * retruns total number of cells in the grid
	 * @return
	 */
	public int getTotalGridSize() {
		return totalGridSize;
	}

	/**
	 * returns character for the display to know what to put
	 * @param cell
	 * @return
	 */
	public char getCharacterForCell(CellStructure cell)
	{
		if(cell.isStateAlive())
		{
			return '*';
		}else{
			return '.';
		}
	}
	
	
}
