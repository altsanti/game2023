package game.strategy.explore.map;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

import game.Constants;
import game.entities.Entity;
import game.entities.utils.Position;
import game.strategy.explore.map.cell.Cell;
import game.strategy.explore.map.cell.CellComparator;

public class ExplorationMap {

	private static final int NB_CELLS_X = Constants.WIDTH_MAP / Cell.WIDTH + 1;
	private static final int NB_CELLS_Y = Constants.HEIGHT_MAP / Cell.HEIGHT + 1;

	private static final String VISITED_SYMBOL = "X";
	private static final String NOT_VISITED_SYMBOL = "O";
	private static final String DEFAULT_SYMBOL = "?";

	public static Map<Position, Cell> cellsByPosition = new HashMap<>();

	private ExplorationMap() {
	}

	public static void init() {
		for (int x = 0; x < NB_CELLS_X; x++) {
			for (int y = 0; y < NB_CELLS_Y; y++) {
				cellsByPosition.put(new Position(x, y), new Cell(x, y));
			}
		}
	}

	public static Cell getBestCell(Entity entity) {
		Comparator<Cell> cellComparator = new CellComparator(entity);
		return cellsByPosition.values().stream().max(cellComparator).get();
	}

	public static String getMapString() {
		StringBuilder mapStringBuilder = new StringBuilder();

		for (int y = 0; y < NB_CELLS_Y; y++) {
			for (int x = 0; x < NB_CELLS_X; x++) {
				switch (cellsByPosition.get(new Position(x, y)).status) {
				case VISITED:
					mapStringBuilder.append(VISITED_SYMBOL);
					break;

				case NOT_VISITED:
					mapStringBuilder.append(NOT_VISITED_SYMBOL);
					break;

				default:
					mapStringBuilder.append(DEFAULT_SYMBOL);
					break;
				}
			}
			mapStringBuilder.append("\n");
		}
		return mapStringBuilder.toString();
	}

}
