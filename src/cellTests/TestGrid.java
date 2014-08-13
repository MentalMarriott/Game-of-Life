package cellTests;

import static org.junit.Assert.assertEquals;
import information.GameGrid;
import org.junit.Test;

public class TestGrid {

	/**
	 * tests grid size is set as well as total grid size is correct
	 */
	@Test
	public void testGridSize() {
		GameGrid grid = new GameGrid(3, 4, false);
		
		assertEquals(12, grid.getTotalGridSize());
		assertEquals(12, grid.getGridCellContents().size());
		assertEquals(3, grid.getxSize());
		assertEquals(4, grid.getySize());
	}
	
	/**
	 * tests retruns correct character for each cell
	 */
	@Test
	public void testCorrectCellCharacter()
	{
		GameGrid grid = new GameGrid(3, 4, false);
		
		//sets first cell to be alive
		grid.getGridCellContents().get(0).setStateAlive(true);
		
		assertEquals('*', grid.getCharacterForCell(grid.getGridCellContents().get(0)));
		assertEquals('.', grid.getCharacterForCell(grid.getGridCellContents().get(1)));
	}

	
	
}
