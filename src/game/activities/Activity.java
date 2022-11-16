package game.activities;

import game.activities.actions.Action;
import game.entities.Entity;

public abstract class Activity {
	protected static final long MAX_SCORE = 10000;

	private static long activityIdCounter = 0;

	public final long activityId;

	private boolean isActive;

	public Activity(int entityId) {
		activityId = activityIdCounter++;
	}

	/**
	 * Build action to reach the activity goal
	 * 
	 * @param entityId The entityId
	 * @return
	 */
	public abstract Action buildAction(Entity entity);

	/**
	 * To define which activity are the same
	 * 
	 * @return
	 */
	public abstract String identity();

	public abstract boolean isActivityFinished();

	public abstract long computeConfidenceScore();

	public abstract boolean activityShouldBeChanged();

	protected abstract void customeUnlinkActivity();

	public final void activateActivity() {
		linkActivity();
		isActive = true;
	}

	public final void removeActivity() {
		deactivateActivity();
	}

	public final void deactivateActivity() {
		if (isActive) {
			unlinkActivity();
			isActive = false;
		}
	}

	public final boolean isActive() {
		return isActive;
	}

	private final void unlinkActivity() {
		customeUnlinkActivity();
	}

	protected abstract void customeLinkActivity();

	private final void linkActivity() {
		customeLinkActivity();
	}

	@Override
	public String toString() {
		return "Activity [activityId=" + activityId + ", isActive=" + isActive + "]";
	}
}
