package game.strategy.explore.map.cell;

import java.util.Comparator;

import game.Constants;
import game.entities.Entity;
import game.entities.utils.Position;

public class CellComparator implements Comparator<Cell> {

	private static final Position MAP_CENTER = new Position(Constants.WIDTH_MAP / 2, Constants.HEIGHT_MAP / 2);

	private static final long MAX_SCORE = 100000;

	/**
	 * Entity for which we want to do the comparison
	 */
	private Entity entity;

	public CellComparator(Entity entity) {
		this.entity = entity;
	}

	/**
	 * TODO to be updated if visited is not relevant
	 */
	@Override
	public int compare(Cell o1, Cell o2) {
		if (o1.isTargeted && !o2.isTargeted) {
			return -1;
		} else if (!o1.isTargeted && o2.isTargeted) {
			return 1;
		} else if (CellStatus.VISITED.equals(o1.status) && CellStatus.NOT_VISITED.equals(o2.status)) {
			return -1;
		} else if (CellStatus.NOT_VISITED.equals(o1.status) && CellStatus.VISITED.equals(o2.status)) {
			return 1;
		}

		return Long.compare(computeScore(o1), computeScore(o2));
	}

	/**
	 * TODO To be updated
	 * @param cell
	 * @return
	 */
	private Long computeScore(Cell cell) {
		Position realCellPosition = cell.getRealPosition();
		return MAX_SCORE / (entity.distance(realCellPosition) + MAP_CENTER.distance(realCellPosition));
	}

}
