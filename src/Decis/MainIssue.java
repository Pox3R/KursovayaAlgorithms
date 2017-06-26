package decis;

import java.util.BitSet;
import java.util.Random;
import java.util.Date;
/**
 * Created by Roman on 21.05.2017.
 */
public class MainIssue {
    private Date time;
    private BitSet input;
    private Random rnd;
    private int count;
    private int genCount = 0;
    private int[] array = new int[16];
    public int[] array1 = {1, 2, 3, 4,
                        5, 6, 7, -1,
                        9, 10, 11, 8,
                        13, 14, 15, 12};

    public MainIssue(int in) {
        time = new Date();
        int tme = (int) time.getTime();
        count = in;
        rnd = new Random(tme);
        input = new BitSet(in);
        for (int i = 0; i < 16; i++) {
            array[i] = generate();
        }

        int ref = array[15];
        int i = rnd.nextInt(15);
        array[15] = array[i];
        array[i] = ref;
    }

    private int generate() {
        if (genCount >= count)
            return -1;

        int next;
        do {
            next = rnd.nextInt(count) + 1;
        }
        while (input.get(next));
        input.set(next);
        genCount++;

        return next;
    }

    public boolean isOdd() {
        int inv = 0;
        for (int i = 0; i < 16; ++i)
            for (int j = 0; j < i; ++j)
                if (array[j] > array[i])
                    ++inv;
        for (int i = 0; i < 16; ++i) {
            if (array[i] == -1)
                inv += 1 + i / 4;
        }
        if (inv % 2 == 0) return true;
        else return false;
    }

    public int[] getArr() {
        return array;
    }

}
