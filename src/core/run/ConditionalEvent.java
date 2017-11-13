package core.run;

import java.util.concurrent.Callable;

/**
    Lolita
    Nov 13 2017
    A type of ScheduledEvent that passes through a condition check before running.
 */

public abstract class ConditionalEvent extends ScheduledEvent implements Callable<Boolean> {

    public abstract boolean triggerConditions();
    public final boolean triggerConditionsMet() { return triggerConditions(); }
    public ConditionalEvent() { super(false, false); }
    public ConditionalEvent(boolean remove, boolean thread) { super(remove, thread); }

    @Override
    public Boolean call() {
        if (triggerConditionsMet()) {
            return super.call();
        } else { //conditions not met
            return false;
        }
    }

}
