package information;

/**
 * this will controll all the 4 rules for conways game of life
 * 
 * @author marriott
 * 
 */
public class GameRules {

	/**
	 * if the cell given is dead and has 3 neighbours then its state should be
	 * switched.
	 * 
	 * @param cell
	 */
	public void deadWithThreeLivingNeighboursSpawn(CellStructure cell) {
		if (!cell.isStateAlive()) {
			int aliveNeighbours = 0;
			
			for (int i = 0; i < cell.getCellNeighbours().size(); i++) {
				if (cell.getCellNeighbours().get(i).isStateAlive()) {
					aliveNeighbours++;
				}
			}

			if (aliveNeighbours == 3) {
				cell.setSwitchState(true);
			}
		}
	}

	/**
	 * if cell given is alive and has 2/3 neighbours it stays alive so no need
	 * for state change
	 */

	/**
	 * if cell given is alive and has less than 2 neighbours or more that 3
	 * neighbours then it dies
	 */
	public void tooManyOrTooLittleNeighbours(CellStructure cell) {
		if (cell.isStateAlive()) {
			int aliveNeighbours = 0;
			
			for (int i = 0; i < cell.getCellNeighbours().size(); i++) {
				if (cell.getCellNeighbours().get(i).isStateAlive()) {
					aliveNeighbours++;
				}
			}

			if (aliveNeighbours > 3 || aliveNeighbours < 2) {
				cell.setSwitchState(true);
			}
		}
	}

}
