package test_implementation;

import core.run.EventRunner;
import core.run.ScheduledEvent;
import test_implementation.gui.MainInterface;
import test_implementation.sfx.AudioLoader;

import java.awt.*;
import java.util.Random;

public class Main {

    private static EventRunner event;
    private static  MainInterface ui;
    private static AudioLoader test;

    public static void main(String[] args) {

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

            @Override
            public boolean triggerConditions() {
                return true;
            }
        });

        event.addEvent(100, new ScheduledEvent() {
            boolean greyscale = false;

            @Override
            public void runEvent() {
                if (greyscale) {
                    ui.getBackgroundimage().scaleImage(ui.getGsc().getSource());
                } else {
                    ui.getBackgroundimage().scaleImage(ui.getGsc().getGreyscaledImage());
                }
                ui.getBackgroundimage().refresh();
                greyscale = !greyscale;
            }

            @Override
            public boolean triggerConditions() {
                return true;
            }
        });

        event.addEvent(100, new ScheduledEvent() {
            private int counter;

            @Override
            public void runEvent() {
                counter++;

                if (test.getAudioMain().isPlaying()) {
                    test.getAudioMain().pause();
                } else {
                    test.getAudioMain().resume();
                }


                if (counter > 5) {
                    test.getAudioMain().stop();
                    counter = 0;
                }
            }

            @Override
            public boolean triggerConditions() {
                return true;
            }
        });

        event.addEvent(1, new ScheduledEvent() {
            int counter2 = 0;
            int counter = 0;
            Random lol = new Random();
            @Override
            public void runEvent() {
                switch (counter) {
                    case 0:
                        ui.getHeader().setText("// TESTING //");
                        break;
                    case 1:
                        ui.getHeader().setText("// - TESTING - //");
                        break;
                    case 2:
                        ui.getHeader().setText("// -- TESTING -- //");
                        break;
                    case 3:
                        ui.getHeader().setText("// --- TESTING --- //");
                        break;
                    case 4:
                        ui.getHeader().setText("// SUS //");
                        break;
                    default:
                        ui.getHeader().setText("BROKE");
                        break;
                }
                counter2++;
                if (counter2 >= 30) {
                    counter++;
                    counter2 = 0;
                }

                int r = lol.nextInt(255);
                int g = lol.nextInt(255);
                int b = lol.nextInt(255);
                ui.getHeader().setForeground(new Color(r, g, b, 255));
                ui.getHeader().refresh();

                if (counter > 4) {
                    counter = 0;
                }
            }

            @Override
            public boolean triggerConditions() {
                return true;
            }
        });

        event.run();

        System.out.println("Test complete.");

    }


}
