package game.strategy.explore.map.cell;

import game.Constants;
import game.entities.Entity;
import game.entities.utils.Position;

public class Cell {
	public static final int WIDTH = 2000;

	public static final int HEIGHT = 2000;

	public CellStatus status = CellStatus.NOT_VISITED;

	public boolean isTargeted = false;

	private final Position pos;

	/**
	 * 
	 * @param x position as cell (0, 1, 2)
	 * @param y
	 */
	public Cell(int x, int y) {
		pos = new Position(x, y);
	}

	public long distance(Entity entity) {
		return entity.distance(pos);
	}

	/**
	 * 
	 * @return the position in the map. This is the position of the center of the
	 *         cell. If the cell over pass the board, we take maximum the end of the
	 *         board
	 */
	public Position getRealPosition() {
		int x = Math.min(Constants.WIDTH_MAP - 1, pos.x * WIDTH + WIDTH / 2);
		int y = Math.min(Constants.HEIGHT_MAP - 1, pos.y * HEIGHT + HEIGHT / 2);

		return new Position(x, y);
	}

	@Override
	public String toString() {
		return "Cell [status=" + status + ", isTargeted=" + isTargeted + ", pos=" + pos + "]";
	}

	public Position getCellPosition() {
		return pos;
	}
}
