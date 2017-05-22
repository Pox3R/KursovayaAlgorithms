package Decis;

/**
 * Created by Roman on 21.05.2017.
 */
import Alghoritm.*;
public class main {
    public static void main(String[] args) {
        Decision arr = new Decision(15);
        arr.filling();
        arr.refill44();
        //gege.fillTerm();
        boolean check = arr.checkSol();
        while (check!=true){
            arr = new Decision(15);
            arr.filling();
            arr.refill44();
            check = arr.checkSol();
        }
        Astar gege = new Astar(arr);
        for (int i=0;i<16;i++){
                System.out.print(arr.array[i] + " ");
            if(i>0)
            if((15-i)%4==0)
            System.out.println();
        }
        //System.out.println(check);
        System.out.println(gege.H);
        gege.alghor();

        //for (int i=0;i<16;i++){
        //    System.out.print(gege.termState1[i] + " ");
        //    if(i>0)
        //        if((15-i)%4==0)
        //            System.out.println();
        //}

        System.out.println("minF = " + gege.F);

    }
}
