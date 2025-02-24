package pcd.lab01.ex01;
import org.fusesource.jansi.Ansi;
import pcd.lab01.ex01.AuxLib.*;

import java.util.Random;

public class WordThread extends Thread {
    private static final int MAX_LINES = 5;
    private static final int MIN_RANDOM_SLEEP = 100;
    private static final int MAX_RANDOM_SLEEP = 500;
    private final WordPos word;
    private final Screen screen;
    private int line;

    public WordThread(final WordPos word, final Screen screen) {
        super(word.word());
        this.word = word;
        this.screen = screen;
        this.line = 0;
    }

    @Override
    public void run() {
        while (line < MAX_LINES) {
            try {
                screen.writeStringAt(line, word.pos(), Ansi.Color.GREEN, word.word());
                Thread.sleep(new Random().nextInt(MIN_RANDOM_SLEEP, MAX_RANDOM_SLEEP));
                screen.writeStringAt(line++, word.pos(), Ansi.Color.BLACK, word.word());
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
        screen.writeStringAt(line, word.pos(), Ansi.Color.GREEN, word.word());
        interrupt();
    }
}
