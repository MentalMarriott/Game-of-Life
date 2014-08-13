package game;

import information.GameGrid;
import information.GameRules;

import java.util.Scanner;

public class Game 
{

	GameRules gameRules = new GameRules();
	GameGrid grid = new GameGrid(0, 0, false); //sets default grid size
	Menu menu = new Menu();;

	public Game() 
	{
		boolean begin = false;
		Scanner sc = new Scanner(System.in);

		while (!begin) 
		{
			menu.welcomeScreen();
			int choice;

			choice = sc.nextInt();
			menu.choice(choice, grid);
		}

		setRandomAliveCells(grid, 50);

		int stepNum = 0;

		while (true) 
		{
			displayGrid(grid);
			step(grid);

			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			stepNum++;
			System.out.println("\n\n" + stepNum + "\n\n");
		}
	}

	private void step(GameGrid grid) {
		for (int i = 0; i < grid.getTotalGridSize(); i++) {
			for (int j = 0; j < grid.getGridCellContents().get(i)
					.getCellNeighbours().size(); j++) {
				gameRules.deadWithThreeLivingNeighboursSpawn(grid
						.getGridCellContents().get(i));
				gameRules.tooManyOrTooLittleNeighbours(grid
						.getGridCellContents().get(i));
			}
		}

		for (int i = 0; i < grid.getTotalGridSize(); i++) {
			if (grid.getGridCellContents().get(i).isSwitchState()) {
				grid.getGridCellContents()
						.get(i)
						.setStateAlive(
								!grid.getGridCellContents().get(i)
										.isStateAlive());
				grid.getGridCellContents().get(i).setSwitchState(false);
			}
		}
	}

	/**
	 * sets random cells alive to the amount of the number given
	 * 
	 * @param grid
	 */
	private void setRandomAliveCells(GameGrid grid, int numberAliveCells) {
		int rand;

		for (int i = 0; i < numberAliveCells; i++) {
			rand = (int) (Math.random() * grid.getTotalGridSize());
			grid.getGridCellContents().get(rand).setStateAlive(true);
		}

	}

	/**
	 * gets cells state and displays them in a grid
	 * 
	 * @param grid
	 */
	private void displayGrid(GameGrid grid) {
		int edgeCount = 0;

		for (int i = 0; i < grid.getTotalGridSize(); i++) {
			if (edgeCount < grid.getxSize()) {
				System.out.print(grid.getCharacterForCell(grid
						.getGridCellContents().get(i)) + "    ");
				edgeCount++;
			}

			if (edgeCount == grid.getxSize()) {
				System.out.println("\n");
				edgeCount = 0;
			}
		}
	}

}
