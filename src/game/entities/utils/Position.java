package game.entities.utils;

import game.Constants;

public class Position {
	public int x;
	public int y;

	public Position(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public Position(Position otherPosition) {
		this.x = otherPosition.x;
		this.y = otherPosition.y;
	}

	public long distance(Position otherPosition) {
		return Math.round(Math.sqrt(Math.pow(otherPosition.x - this.x, 2) + Math.pow(otherPosition.y - this.y, 2)));
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Position) {
			Position otherPosition = (Position) obj;
			return x == otherPosition.x && y == otherPosition.y;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return (((x + y) * (x + y + 1)) / 2) + y;
	}

	@Override
	public String toString() {
		return "Position [x=" + x + ", y=" + y + "]";
	}

	public boolean isOutOfBound() {
		return x > Constants.WIDTH_MAP - 1 || x < 0 || y > Constants.HEIGHT_MAP - 1 || y < 0;
	}
}
