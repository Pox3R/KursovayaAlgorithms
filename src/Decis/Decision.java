package Decis;

import java.util.BitSet;
import java.util.Random;
import java.util.Date;
/**
 * Created by Roman on 21.05.2017.
 */
public class Decision {
    private Date time, time1;
    private BitSet input;
    private Random rnd, rnd1;
    private int count;
    private int genCount=0;
    public int[] array = new int[16];
    public Decision(int in)
    {
        time = new Date();
        int tme=(int)time.getTime();
        count =in;
        rnd=new Random(tme);
        input = new BitSet(in);
    }

    public int generate()
    {
        if (genCount>= count)
            return -1;

        int next;
        do
        {
            next = rnd.nextInt(count)+1;
        }
        while (input.get(next));
        input.set(next);
        genCount++;

        return next;
    }
    public void filling(){
        for (int i=0;i<16;i++){
                array[i] = generate();
        }
    }
    public void refill44(){
        time1=new Date();
        int tme1 = (int)time1.getTime();
        rnd1=new Random(tme1);
        int ref = array[15];
        int i = rnd1.nextInt(15);
        array[15] = array[i];
        array[i] = ref;
    }

    public boolean checkSol(){
        int inv = 0;
        for (int i=0; i<16; ++i)
                for (int j=0; j<i; ++j)
                    if (array[j] > array[i])
                        ++inv;
        for (int i=0; i<16; ++i) {
            if (array[i] == -1)
                inv += 1 + i / 4;
        }
        if (inv%2==0) return true;
        else return false;
    }
    public int[] getArr(){
        return array;
    }

}
