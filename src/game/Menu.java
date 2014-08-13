package game;

import java.util.ArrayList;
import java.util.Scanner;

import information.CellStructure;
import information.GameGrid;

public class Menu {

	public Menu() {
		welcomeScreen();
	}

	public void welcomeScreen() {
		System.out.println("\n\n\n\n\n\n\n");
		System.out.println("\t\tWelcome to Conways Game of Life\n");
		System.out.println("\t\t================================\n");

		System.out.println("\n\n");
		System.out.println("Enter number to choose option:\n");
		System.out.println("1) Set grid size\n");
		System.out.println("2) Set alive cells\n");
		System.out.println("3) Set random alive cells\n");
		System.out.println("4) Get current game setup details\n");
		System.out.println("5) Run game\n");
		System.out.println("6) Quit game\n");
	}

	public void choice(int selection, GameGrid grid) {
		switch (selection) {
		case 1:
			setGridSize(grid);
			break;

		case 2:
			setAlivecells(grid);
			break;

		case 3:
			setRandomCellsAlive(grid);
			break;

		case 4:
			getCurrentSetup(grid);
			break;

		case 5:
			startGame();
			break;

		case 6:
			endGame();
			break;

		default:
			System.out.println("Invalid choice try again\n");

		}
	}

	private void endGame() {
		System.out.println("\n\n Thank you for playing, goodbye :)\n\n\n");
		System.exit(0);

	}

	private void startGame() {
		// TODO Auto-generated method stub

	}

	private void getCurrentSetup(GameGrid grid) {
		System.out.println("\nCurrent Setup is as follows\n");
		System.out.println("\n Grid is " + grid.getxSize() + " by "
				+ grid.getySize() + "\n");

	}

	private void setRandomCellsAlive(GameGrid grid) {
		if(grid.isUserChooseAliveCells())
		{
			System.out.println("\nUser has already selected alive cells, continuing" +
					" will overwrite these, continue Y/N");
		}

	}

	private void setAlivecells(GameGrid grid) {
		char choice;
		boolean setting = true;
		Scanner sc = new Scanner(System.in);
		ArrayList<Integer> aliveCells = new ArrayList<Integer>();

		while (setting) {
			int x, y;
			boolean continueAdding = true;

			System.out
					.println("Enter L to list alive cells\t Enter X to cancel all\n");
			System.out
					.println("\nEnter C to continue adding\t Enter Y to save all and quit\n");

			choice = sc.next().charAt(0);

			switch (choice) {
			case 'L':
			case 'l':
				listAlive(aliveCells);
				continueAdding = false;
				break;

			case 'X':
			case 'x':
				cancelAll(aliveCells);
				continueAdding = false;
				break;

			case 'Y':
			case 'y':
				System.out.println(grid.getTotalGridSize());
				saveChoices(aliveCells, grid);
				continueAdding = false;
				setting = false;
				grid.setUserChooseAliveCells(true);
				break;

			case 'c':
			case 'C':
				continueAdding = true;
				break;

			default:
				System.out.println("Invalid choice");
			}

			if (continueAdding) {
				System.out
						.println("\n\nPlease enter the grid co ordinates of the cells you wish to live\n");
				System.out.println("First number = X, Second number = Y\n");

				y = sc.nextInt();
				x = sc.nextInt();
				setAlive(x, y, aliveCells, grid);
			}

		}
	}

	/**
	 * Takes the temp array of user set alive cells and sets them to the grid.
	 * 
	 * @param setting
	 * @param aliveCells
	 * @param grid
	 */
	private void saveChoices(ArrayList<Integer> aliveCells, GameGrid grid) {
		// loads in a blank grid of dead cells
		for (int i = 0; i < grid.getTotalGridSize(); i++) {
			grid.getGridCellContents().add(new CellStructure());
		}

		for (int i = 0; i < aliveCells.size(); i++) {
			// calculate grid location
			int gridAliveLocation = aliveCells.get(i);

			grid.getGridCellContents().get(gridAliveLocation)
					.setStateAlive(true);
		}
	}

	/**
	 * empties the list of alive cells set
	 * 
	 * @param aliveCells
	 */
	private void cancelAll(ArrayList<Integer> aliveCells) {
		aliveCells.clear();
	}

	/**
	 * Prints cells that have been set alive
	 * 
	 * @param aliveCells
	 */
	private void listAlive(ArrayList<Integer> aliveCells) {

		if (aliveCells.isEmpty()) {
			System.out.println("\n\nNo cells set alive yet\n\n");
		} else {
			for (int i = 0; i < aliveCells.size(); i++) {
				System.out.println("\nCell at x = " + (aliveCells.get(i) % 5)
						+ " y = " + (aliveCells.get(i) / 5));
			}
			System.out.println("\n\n");
		}
	}

	/**
	 * Adds user input to a temporary array to allow for ease of maintenance
	 * 
	 * @param x
	 * @param y
	 * @param aliveCells
	 * @param grid
	 */
	private void setAlive(int x, int y, ArrayList<Integer> aliveCells,
			GameGrid grid) {
		if (x < grid.getxSize() && y < grid.getySize()) {
			int existingAliveCellLoc = (x * grid.getySize()) + y;
			
			if (!aliveCells.contains(existingAliveCellLoc)) {
				aliveCells.add(x * grid.getySize() + y);
			}else{
				System.out.println("\nCell already alive at that position\n");
			}
		} else {
			System.out.println("Invalid grid location\n\n");
		}
	}

	/**
	 * This will allow the user to set the size of the grid
	 * 
	 * @param grid
	 */
	private void setGridSize(GameGrid grid) {
		int x, y;
		Scanner sc = new Scanner(System.in);

		System.out.println("\nPlease enter the number or rows\n");
		y = sc.nextInt();

		System.out.println("\nPlease enter the number of cells in each row\n");
		x = sc.nextInt();

		grid.setxSize(x);
		grid.setySize(y);
		grid.setTotalGridSize(y * x);

		System.out.println("Grid size set\n");
		System.out.println(grid.getTotalGridSize());
	}

}
