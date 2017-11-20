package test_implementation;

import core.math.WeightedRandom;
import core.run.ConditionalEvent;
import core.run.EventRunner;
import core.run.ScheduledEvent;
import core.sfx.AudioPlayer;
import test_implementation.gui.MainInterface;
import test_implementation.sfx.AudioLoader;

import java.awt.*;
import java.io.File;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Collections;
import java.util.LinkedList;

public class Main {

    private static  MainInterface ui;

    public static void main(String[] args) {
        testUI();
    }

    private static void testWR() { //tests the functionality of the weighted random class

        WeightedRandom random = new WeightedRandom(5, 15);
        LinkedList<Integer> results = new LinkedList<>();

        random.setSigma(1.8); //for best results - sigma between 1.5 (low normalization) and 4.0 (high normalization)
        random.setWeightedArray(4);

        for (int i = 0; i < 1000; i++) {
            results.addFirst(random.getWeightedRandom());
        }
        int temp;
        double percent;

        DecimalFormat df = new DecimalFormat("#.#");
        df.setRoundingMode(RoundingMode.FLOOR);

        for (int i = random.getLow(); i <= random.getHigh(); i++) {
            temp = Collections.frequency(results, i);
            percent = (((double)temp / results.size()) * 100);
            System.out.println(i + " | Occurrences: " + temp + " | " + df.format(percent) + "%");
        }

    }

    private static void testUI() { //tests the functionality of the UI system
        AudioLoader test;
        EventRunner event;

        ui = new MainInterface();
        test = new AudioLoader();
        test.loadMultipleAudioTestOne();
        test.playAudio();

        ui.loadMonsterSprite();
        event = new EventRunner();

        event.addEvent(10, new ScheduledEvent() {
            @Override
            public void runEvent() {
                ui.getMonster().indexNextSprite();
                ui.getMonstersprite().setImage(ui.getMonster().get(ui.getMonster().getCurrentIndex()));
                ui.getMonstersprite().getParent().repaint();
                ui.getMonstersprite().getParent().revalidate();
            }
        });

        ScheduledEvent leeroy = new ScheduledEvent(true, true) {
            @Override
            public void runEvent() {
                AudioPlayer test3 = new AudioPlayer();
                test3.setAudioFolder(new File(System.getProperty("user.dir") + "/src/test_implementation/sfx/resources/music/"));
                test3.addAudioFromFolder("run.mp3");
                test3.setCloseOnComplete(true);
                test3.setVolume(8);
                test3.execute();
            }
        };
        leeroy.setRemovedOnTriggered(true);
        event.addEvent(10, leeroy);

        event.addEvent(200, new ConditionalEvent(true, false) {
            @Override
            public boolean triggerConditions() {
                if (event.getCurrentCycle().intValue() > 0) {
                    if (event.getCurrentTick().intValue() > 200) {
                        return true;
                    }
                }
                return false;
            }

            @Override
            public void runEvent() {
                ui.getHeader().setText("Conditional Event");
            }
        });

        event.addEvent(400, new ConditionalEvent(true, false) {
            @Override
            public boolean triggerConditions() {
                if (event.getCurrentCycle().intValue() > 1) {
                    return true;
                }
                return false;
            }

            @Override
            public void runEvent() {
                ui.getHeader().setForeground(Color.WHITE);
            }
        });


        Thread main = new Thread(event);
        main.setName("Event Handler Thread");
        main.start();
    }


}
