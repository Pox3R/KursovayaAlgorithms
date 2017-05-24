package decis;

/**
 * Created by Roman on 21.05.2017.
 */
import algorithm.*;
public class Main {
    public static void main(String[] args) {
        MainIssue arr = new MainIssue(15);
        //arr.filling();
        //arr.finalFill();
        //gege.fillTerm();
        boolean check = arr.isOdd();
        while (check != true) {
            arr = new MainIssue(15);
            //arr.filling();
            //arr.finalFill();
            check = arr.isOdd();
        }
        int[] array = new int[16];
        array = arr.getArr();
        Astar gege = new Astar(arr);
        for (int i = 0; i < 16; i++) {
            System.out.print(array[i] + " ");
            if (i > 0)
                if ((15 - i) % 4 == 0)
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
