package core.run;

/**
 * Lolita's Revenge
 * August 07 2017
 * Schedules events for the EventRunner.
 */

public abstract class ScheduledEvent {

    private boolean removedOnTriggered = false; //Whether or not the event will be removed after it has been triggered.
    abstract boolean triggerConditions(); //The conditions that must be met for the event to trigger.
    abstract void runEvent(); //The event's actions
    public final boolean triggerConditionsMet() { return triggerConditions(); }

    public final void setRemovedOnTriggered(boolean b) { removedOnTriggered = b;}
    public final boolean isRemovedOnTriggered() { return removedOnTriggered; }

}
