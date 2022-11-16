package game.strategy;

import game.activities.Activity;
import game.entities.Entity;

public abstract class Strategy {

	public abstract boolean shouldChangeActivity(Entity entity);

	public long getNewActivity(Entity entity) {
		return -1;
	}

	protected abstract Activity buildNewActivity(Entity entity);
}
