package core.run;

import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Lolita's Revenge
 * August 07 2017
 * Designed to run events on a 'tick' basis, with the ability to schedule different events to run on different ticks.
 */

public class EventRunner implements Runnable, EventConstants {

    /*------------------------------------------------------------------------------------------------------------------
     Variables.
     Define the function of the EventRunner.
     */

    private final static AtomicInteger idNumber = new AtomicInteger(0);

    private ArrayList<ScheduledEvent> removed = new ArrayList<>();
    private List<ScheduledEvent> eventPool = new LinkedList<>();
    private Hashtable<Integer, ArrayList<ScheduledEvent>> eventList = new Hashtable<>();

    private ExecutorService threadPool = Executors.newCachedThreadPool();

    private boolean isPaused = false;
    private boolean isInterrupted = false;
    private boolean isRunning = false;

    private AtomicLong currentCycle = new AtomicLong(0); //keeps track of the current cycle
    private AtomicLong currentTick = new AtomicLong(0); //keeps track of the current tick
    private final int cycleDuration = 1000; //how many ticks will pass before the cycle increases
    private final int serialValue; //a unique value assigned to each instance of the EventRunner upon creation

    private double tickDuration = render_tick; //uses the render tick speed by default (60/s)

    /*------------------------------------------------------------------------------------------------------------------
     Constructors.
     Used to construct instances of the EventRunner.
     */

    public EventRunner() {
        serialValue = idNumber.getAndIncrement();
        System.out.println("New EventRunner successfully created with ID " + serialValue);
    }

    /*------------------------------------------------------------------------------------------------------------------
     Accessible methods.
     Can be accessed outside of the EventRunner to edit values.
     */

    public double getTickDuration() { return tickDuration; }
    public void setTickDuration(double d) { tickDuration = d; }

    public long getCurrentCycle() { return currentCycle.get(); }
    public long getCurrentTick() { return currentTick.get(); }

    public int getCycleDuration() { return cycleDuration; }

    public boolean isRunning() { return isRunning; }
    public boolean isInterrupted() { return isInterrupted; }
    public boolean isPaused() { return isPaused; }

    public int getSerialValue() { return serialValue; }

    public ArrayList<ScheduledEvent> getEvents(int key) { return eventList.get(key); }
    public ArrayList<ScheduledEvent> getAllEvents() { //returns all of the events
        ArrayList<ScheduledEvent> combined = new ArrayList<>();
        for (int key : eventList.keySet()) {
            combined.addAll(eventList.get(key));
        }
        return combined;
    }

    public void togglePause() {
        if (isRunning) {
            isPaused = !isPaused;
            this.notify();
        } else {
            System.err.println("Attempted to pause EventRunner " + serialValue + " while it was inactive.");
        }
    }

    public void reset() {
        interrupt();
        currentTick.set(0);
        currentCycle.set(0);
    }

    @Override
    public void run() {
        try {
            eventCycle();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //EventRunner finished all processes!
    }

    public void addEvent(int tick, ScheduledEvent event) {
        if (tick > cycleDuration) {
            System.err.println("Attempted to insert an event into a tick outside of the EventRunner's cycle duration in EventRunner " + serialValue + ".");
            return;
        }
        try {
            if (eventList.get(tick) != null) { //array already exists at this node, insert the event into it
                eventList.get(tick).add(event);
            } else { //no arraylist found, create one and insert the event into it
                eventList.put(tick, new ArrayList<ScheduledEvent>());
                eventList.get(tick).add(event);
            }
            System.out.println("Event added successfully to EventRunner " + serialValue + ".");
        } catch (Exception e) { //something done goofed
            System.err.println("Error adding new event to EventRunner " + serialValue + ".");
            e.printStackTrace();
        }
    }

    public void dump(boolean preserveCycle) {
        eventList.clear();
        if (!preserveCycle) {
            reset();
        }
    }

    public void dispose() {
        interrupt();
        dump(false);
        try {
            threadPool.shutdown(); //shuts down the threading used by the EventRunner
            threadPool.awaitTermination(10, TimeUnit.SECONDS); //gives the thread pool 10 seconds to shutdown before forcing it to shutdown
        } catch (InterruptedException e) {
            System.err.println("EventRunner " + serialValue + " thread pool shutdown sequence interrupted prematurely.");
            e.printStackTrace();
        } finally {
            threadPool.shutdownNow(); //forces the thread pool to shutdown, even if tasks are still running
            System.gc(); //prompt garbage collector to clean up any remnants
        }
    }

    public void runSingleTick() { //manual call to run a single tick of events
        try {
            if (!isRunning) {
                runEvents();
            } else {
                System.err.println("EventRunner " + serialValue + " runSingleTick method cannot be invoked while the EventRunner is running.");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void interrupt() {
        if (isRunning) {
            isInterrupted = true;
        }
    }

    /*------------------------------------------------------------------------------------------------------------------
     Core methods.
     Methods used by the EventRunner after run() has been called. These should NOT be touched or accessed individually.
     */

    //private long cyclestart, cycleend;

    private void eventCycle() throws InterruptedException {
        isRunning = true;

        while (!isInterrupted) {
            if (!isPaused) {
                runEvents();
            } else {
                //event runner is paused, wait until given an okay to proceed
                this.wait();
            }
            //all actions performed, repeat the process
        }

        //event runner was interrupted artificially, was this done by an error - or by the user?
        isRunning = false;
        isInterrupted = false;
        throw new InterruptedException("EventRunner " + serialValue + " cycle interrupted -- was this intentional?");
    }

    private synchronized boolean runEvents() throws InterruptedException {

        switch ((int) currentTick.get()) {
            case cycleDuration:
                currentCycle.incrementAndGet();
                currentTick.set(0);
                //System.out.println("Cycle " + currentCycle);
                System.gc(); //tells the garbage collector to consider cleaning, just to ensure there's no major memory overflow between cycles
            default:
                currentTick.incrementAndGet();
        }

        //cyclestart = System.currentTimeMillis();

        eventPool.clear();

        for (int key : eventList.keySet()) { //check all of the keys in the eventList
            if (currentTick.intValue() % key == 0) { //check to see if the current tick and the key are of the same multiple
                checkEventConditions(key, eventList.get(key)); //if the tick is a multiple of the key, run the events in that key
            }
        }

        threadPool.invokeAll(eventPool).stream().map(booleanFuture -> { //run the events
            try {
                return booleanFuture.get();
            } catch (Exception e) {
                throw new IllegalStateException(e);
            }
        }).forEach(System.out::println); //TODO: Not properly printing results..

        //cycleend = System.currentTimeMillis();
        //System.out.println("Tick " + currentTick + " completed in " + (cycleend - cyclestart) + "ms");

        // pauses the thread for the duration of the tick
        try {
            Thread.sleep((long) (tickDuration * 1000));
            //TimeUnit.SECONDS.sleep((long) tickDuration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return true; //returns an arbitrary true value to allow for possible usage of checking when the tick is 'complete'
    }

    private void checkEventConditions(int id, ArrayList<ScheduledEvent> events) { //checks and runs events

        if (events.size() > 0) {
            for (ScheduledEvent e : events) { //check the event roster
                try {
                    if (e.triggerConditionsMet()) {
                        //conditions met, run the event
                        //System.out.println("Conditions met for event " + e.toString());

                        if (e.isThreaded()) {
                            //event is to be given its own private thread (harder on CPU, faster)
                            eventPool.add(e);
                            //threadPool.submit(e);
                        } else {
                            //run event in main EventRunner thread (better on CPU, slower)
                            e.runEvent();
                        }

                        if (e.isRemovedOnTriggered()) {
                            //if the event is set to be removed once it has been triggered, remove it from the queue
                            removed.add(e);
                        }
                    }
                } catch (Exception p) {
                    p.printStackTrace();
                    removed.add(e); //remove the faulty event from the queue
                }
                //nothing loaded in the queue to be triggered
            }

            if (!removed.isEmpty()) {
                events.removeAll(removed);
                removed.clear();
            }
        }

        if (events.isEmpty()) {
            eventList.remove(id); //empty list, remove it from the queue
        }
    }

    //------------------------------------------------------------------------------------------------------------------

}
