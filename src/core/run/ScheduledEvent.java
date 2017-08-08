package core.run;

/**
 * Lolita's Revenge
 * August 07 2017
 * Schedules events for the EventRunner.
 */

public abstract class ScheduledEvent implements Runnable {

    public ScheduledEvent() {
        removedOnTriggered = false;
        threaded = false;
    }

    public ScheduledEvent(boolean remove, boolean thread) {
        removedOnTriggered = remove;
        threaded = thread;
    }

    private boolean removedOnTriggered; //Whether or not the event will be removed after it has been triggered.
    private boolean threaded;
    public abstract boolean triggerConditions(); //The conditions that must be met for the event to trigger.
    public abstract void runEvent(); //The event's actions
    public final boolean triggerConditionsMet() { return triggerConditions(); }

    public final void setRemovedOnTriggered(boolean b) { removedOnTriggered = b;}
    public final boolean isRemovedOnTriggered() { return removedOnTriggered; }

    public final void setThreaded(boolean b) { threaded = b; }
    public final boolean isThreaded() { return threaded; }

    @Override
    public void run() {
        runEvent();
        Thread.currentThread().interrupt();
    }

}
