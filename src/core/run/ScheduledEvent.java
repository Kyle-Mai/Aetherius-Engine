package core.run;

import java.io.Serializable;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Lolita's Revenge
 * August 07 2017
 * Schedules events for the EventRunner.
 */

public abstract class ScheduledEvent implements Callable<Boolean> {

    private static AtomicInteger eventCode = new AtomicInteger(1);

    private boolean removedOnTriggered; //Whether or not the event will be removed after it has been triggered.
    private boolean threaded; //Whether or not this event will use a private thread
    private boolean concurrent = true; //Whether or not the completion of this event is required to finish before the EventRunner moves to the next tick.
    private final int eventID; //the ID value assigned to this particular event

    public ScheduledEvent() {
        this(false, false);
    }

    public ScheduledEvent(boolean remove, boolean thread) {
        removedOnTriggered = remove;
        threaded = thread;
        eventID = eventCode.incrementAndGet();
    }

    public abstract boolean triggerConditions(); //The conditions that must be met for the event to trigger.
    public abstract void runEvent(); //The event's actions
    public final boolean triggerConditionsMet() { return triggerConditions(); }

    public final void setRemovedOnTriggered(boolean b) { removedOnTriggered = b;}
    public final boolean isRemovedOnTriggered() { return removedOnTriggered; }

    public final void setThreaded(boolean b) { threaded = b; }
    public final boolean isThreaded() { return threaded; }
    
    public final boolean isConcurrent() { return concurrent; }
    public final void setConcurrency(boolean b) { concurrent = b; }
    
    public static int getNumberOfEvents() { return eventCode.intValue(); }

    @Override
    public Boolean call() {
        try {
            Thread.currentThread().setName("Scheduled Event no." + eventID); //tracks the current event number
            runEvent(); //runs the event designated by the user
        } catch (Exception e) { //event failed to run for some reason
            System.err.println(Thread.currentThread().getName() + " failed to execute:");
            e.printStackTrace();
            return false; //allows the EventRunner the potential to deal with a failed event
        }
        return true; //arbitrary return to signal the event's completion
    }
    
    public boolean equals(Object event) {
        try {
            if (event instanceof ScheduledEvent && (ScheduledEvent)event.hashCode() == this.hashCode()) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) { //Something went screwy when comparing - return a false just to be on the safe side
            return false;
        }
    }
    
    public int hashCode() { return eventID; }
}
