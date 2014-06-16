package cellTests;

import static org.junit.Assert.*;

import information.CellStructure;
import information.GameGrid;

import java.awt.GridBagConstraints;
import java.util.ArrayList;
import java.util.concurrent.ArrayBlockingQueue;


import org.junit.Test;


public class TestCell {

	/**
	 * Tests to see that cells are dead by default
	 */
	@Test
	public void testCellDeadByDeafault() {
		CellStructure cell = new CellStructure();
		
		assertFalse(cell.isStateAlive());
	}
	
	/**
	 * Tests to see cell switches when stateSwitch is true
	 */
	@Test
	public void testCellSwitchWhenMeantTo()
	{
		CellStructure cell = new CellStructure();
		
		assertFalse(cell.isStateAlive());
		assertFalse(cell.isSwitchState());
		
		cell.setSwitchState(true);
		assertTrue(cell.isSwitchState());

		// gets cells current state and flips it
		if(cell.isSwitchState()){
			cell.setStateAlive(!cell.isStateAlive());
		}
		assertTrue(cell.isStateAlive());
		
		//after switching change switchstate to be back to false
		cell.setSwitchState(false);
		assertFalse(cell.isSwitchState());	
	}
	
	
	/**
	 * this tests that can add neighbours 
	 */
	@Test
	public void testAddNeighbours()
	{
		CellStructure cell = new CellStructure();
		CellStructure cell2 = new CellStructure();
		
		assertEquals(new ArrayList<CellStructure>(), cell.getCellNeighbours());
		
		cell.addNeighbour(cell2);
		
		assertTrue(cell.getCellNeighbours().contains(cell2));
		assertTrue(cell2.getCellNeighbours().contains(cell));
	}
	
	
	/**
	 * test all appropriate neighbours are added upon game creation
	 */
	@Test
	public void testCorrectNumberOfNeighbours()
	{
		GameGrid grid = new GameGrid(4, 5, false);
		
		assertEquals(3, grid.getGridCellContents().get(0).getCellNeighbours().size());
		assertEquals(5, grid.getGridCellContents().get(4).getCellNeighbours().size());
		assertEquals(8, grid.getGridCellContents().get(5).getCellNeighbours().size());
	}
}
