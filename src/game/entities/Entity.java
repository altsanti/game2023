package game.entities;

import game.entities.utils.Position;

public class Entity {
	public int id;
	public Position pos;

	public Entity(int id, int x, int y) {
		this.id = id;
		this.pos = new Position(x, y);
	}

	protected void updatePos(int x, int y) {
		pos.x = x;
		pos.y = y;
	}

	public long distance(Entity otherEntity) {
		return distance(otherEntity.pos);
	}

	public long distance(Position otherPosition) {
		return pos.distance(otherPosition);
	}
}