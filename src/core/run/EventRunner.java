package core.run;

import java.util.ArrayList;

/**
 * Lolita's Revenge
 * August 07 2017
 * Designed to run events on a 'tick' basis, with the ability to schedule different events to run on different ticks.
 */

public final class EventRunner implements Runnable {

    /*------------------------------------------------------------------------------------------------------------------
     Variables.
     Define the function of the EventRunner.
     */

    private ArrayList<ScheduledEvent> events_one = new ArrayList<>();
    private ArrayList<ScheduledEvent> events_ten = new ArrayList<>();
    private ArrayList<ScheduledEvent> events_hundred = new ArrayList<>();
    private ArrayList<ScheduledEvent> events_thousand = new ArrayList<>();
    private ArrayList<ScheduledEvent> events_other = new ArrayList<>();

    private boolean isPaused = false;
    private boolean isInterrupted = false;

    private long currentCycle = 0; //keeps track of the current cycle
    private long currentTick = 0; //keeps track of the current tick
    private final int cycleDuration = 1000; //how many ticks will pass before the cycle increases

    private double tickDuration = 0.0167; //approximately 60 ticks per second by default

    /*------------------------------------------------------------------------------------------------------------------
     Constructors.
     Used to construct instances of the EventRunner.
     */

    public EventRunner() {}

    /*------------------------------------------------------------------------------------------------------------------
     Accessible methods.
     Can be accessed outside of the EventRunner to edit values.
     */

    public double getTickDuration() { return tickDuration; }
    public void setTickDuration(double d) { tickDuration = d; }

    public long getCurrentCycle() { return currentCycle; }
    public long getCurrentTick() { return currentTick; }

    public int getCycleDuration() { return cycleDuration; }

    public void reset() {
        currentTick = 0;
        currentCycle = 0;
    }

    @Override
    public void run() {
        try {
            eventCycle();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Thread.currentThread().interrupt();
        //EventRunner finished all processes!
    }

    public void addEvent(int frequency, ScheduledEvent event) {
        switch (frequency) {
            case 1:
                events_one.add(event);
                break;
            case 10:
                events_ten.add(event);
                break;
            case 100:
                events_hundred.add(event);
                break;
            case 1000:
                events_thousand.add(event);
                break;
            default:
                events_other.add(event);
        }
    }

    /*------------------------------------------------------------------------------------------------------------------
     Core methods.
     Methods used by the EventRunner after run() has been called. These should NOT be touched or accessed individually.
     */

    private void eventCycle() throws InterruptedException {
        while (!isInterrupted) {
            if (!isPaused) {
                switch ((int) currentTick) {
                    case cycleDuration:
                        currentCycle++;
                        currentTick = 0;
                    default:
                        currentTick++;
                }

                checkEventConditions(events_one);
                if (currentTick % 10 == 0 && currentTick != 0) {
                    checkEventConditions(events_ten);
                }
                if (currentTick % 100 == 0 && currentTick != 0) {
                    checkEventConditions(events_hundred);
                }
                if (currentTick % 1000 == 0 && currentTick != 0) {
                    checkEventConditions(events_thousand);
                }
                checkEventConditions(events_other);

                // pauses the thread for the duration of the tick
                try {
                    Thread.sleep((long) (tickDuration * 1000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                eventCycle(); //recursive call to cycle to the next tick

            } else {
                //event runner is paused, keep checking for re-activation every 20ms
                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            //all actions performed, repeat the process
        }

        //event runner was interrupted artificially, was this done by an error - or by the user?
        isInterrupted = false;
        throw new InterruptedException("EventRunner cycle interrupted prematurely -- was this intentional?");
    }

    private void checkEventConditions(ArrayList<ScheduledEvent> events) { //checks and runs events
        try {
            if (events.size() > 0) {
                for (ScheduledEvent e : events) { //check the event roster
                    if (e.triggerConditionsMet()) {
                        //conditions met, run the event
                        e.runEvent();
                        if (e.isRemovedOnTriggered()) {
                            //if the event is set to be removed once it has been triggered, remove it from the queue
                            events.remove(e);
                        }
                    }
                }
                //nothing loaded in the queue to be triggered
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //------------------------------------------------------------------------------------------------------------------

}
