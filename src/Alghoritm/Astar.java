package Alghoritm;
import Decis.*;
import java.util.*;
/**
 * Created by Roman on 21.05.2017.
 */
public class Astar {
    public static final int UP = -4;
    public static final int DOWN = 4;
    public static final int LEFT = -1;
    public static final int RIGHT = 1;
    public int H;
    public int G;
    public int F;
    public int[] begArr = new int[16];
    public int[] termState1 = new int[16];
    public int[] termState2 = new int[16];
    public Astar(Decision begState){
        begArr=begState.array;
        fillTerm();
        H = getHTerm1(begArr);
        F=H+G;
    }

    public void fillTerm() {
        for (int i = 0; i < 16; i++) {
            termState1[i] = i + 1;
        }
        termState1[15] = -1;
        for (int i = 0; i < 16; i++) {
            termState2[i] = i + 1;
        }
        termState2[15] = -1;
        termState2[14] = 14;
        termState2[13] = 15;
    }
    public int getHTerm1(int[] arr) {
        int htemp=0;
        arr = begArr;
        for (int i = 0; i < 16; i++) {
            if ((arr[i]) == (termState1[i])) htemp++;
        }
        return 16-htemp;
    }
    public int findEmp(int[] arr) {
        for (int i = 0; i < 16; i++) {
            if (arr[i] == -1) return i;
        }
        return -1;
    }

    public int minF(int a, int b, int c, int d) {
        int m2=Math.min(a, b);
        if (m2<=c & m2<=d) return m2;
        if (c<=m2 & c<=d) return c;
        if (d<=c & d<=m2) return d;
        return m2;
    }

    public void alghor(){
        int empCell = 0;
        int tempCell=0;
        G=0;
        //int tempGU=G,tempGD=G,tempGL=G,tempGR=G;
        //int tempHU=H,tempHD=H,tempHL=H,tempHR=H;
        //int tempFU=G+H,tempFD=G+H,tempFL=G+H,tempFR=G+H;
        int tempEmp = 0;
        int[] temp = new int[16];
        int[] tempU = new int[16];
        int[] tempD = new int[16];
        int[] tempL = new int[16];
        int[] tempR = new int[16];
        int L = 0, R = 0, U = 0, D = 0;
        //for (int i = 0; i < 16; i++) {
        //    temp[i]=begArr[i];
        //}
        while(begArr!=termState1) {
            int tempGU=G,tempGD=G,tempGL=G,tempGR=G;
            int tempHU=H,tempHD=H,tempHL=H,tempHR=H;
            int tempFU=G+H,tempFD=G+H,tempFL=G+H,tempFR=G+H;
            for (int i=0;i<4;i++) {
                if (i==0){//UP
                    tempGU++;
                    for (int j = 0; j < 16; j++) {
                        temp[j]=begArr[j];
                    }
                    empCell = findEmp(temp);
                    if ((empCell!=0)&&(empCell!=1)&&(empCell!=2)&&(empCell!=3)) {
                        //empCell = findEmp(temp);
                        tempCell = temp[empCell + UP];
                        temp[empCell + UP] = -1;
                        temp[empCell] = tempCell;
                        tempHU = getHTerm1(temp);
                        //tempGU++;
                        tempFU=tempHU+tempGU;
                        System.out.println("up " + tempFU);
                        tempU=temp;
                        temp = new int[16];
                    }else tempFU = 1000000000;
                    //tempFU=tempHU+tempGU;
                }
                if(i==1){//DOWN
                    tempGD++;
                    for (int j = 0; j < 16; j++) {
                        temp[j]=begArr[j];
                    }
                    empCell = findEmp(temp);
                    if ((empCell!=12)&&(empCell!=13)&&(empCell!=14)&&(empCell!=15)){
                        //empCell = findEmp(temp);
                        tempCell = temp[empCell + DOWN];
                        temp[empCell + DOWN] = -1;
                        temp[empCell] = tempCell;
                        tempHD = getHTerm1(temp);
                        //tempGD++;
                        tempFD = tempHD+tempGD;
                        System.out.println("down " + tempFD);
                        tempD=temp;
                        temp = new int[16];
                    }else tempFD = 1000000000;
                    //tempFD = tempHD+tempGD;
                }
                if(i==2){//LEFT
                    tempGL++;
                    for (int j = 0; j < 16; j++) {
                        temp[j]=begArr[j];
                    }
                    empCell = findEmp(temp);
                    if ((empCell!=0)&&(empCell!=4)&&(empCell!=8)&&(empCell!=12)){
                        //empCell = findEmp(temp);
                        tempCell = temp[empCell + LEFT];
                        temp[empCell + LEFT] = -1;
                        temp[empCell] = tempCell;
                        tempHL = getHTerm1(temp);
                        //tempGL++;
                        tempFL = tempHL + tempGL;
                        System.out.println("left " + tempFL);
                        tempL=temp;
                        temp = new int[16];
                    }else tempFL = 1000000000;
                    //tempFL = tempHL + tempGL;
                }
                if(i==3){//RIGHT
                    tempGR++;
                    for (int j = 0; j < 16; j++) {
                        temp[j]=begArr[j];
                    }
                    empCell = findEmp(temp);
                    if ((empCell!=3)&&(empCell!=7)&&(empCell!=11)&&(empCell!=15)){
                        //empCell = findEmp(temp);
                        tempCell = temp[empCell + RIGHT];
                        temp[empCell + RIGHT] = -1;
                        temp[empCell] = tempCell;
                        tempHR = getHTerm1(temp);
                        //tempGR++;
                        tempFR = tempHR + tempGR;
                        System.out.println("right " + tempFR);
                        tempR=temp;
                        temp = new int[16];
                    }else tempFR = 1000000000;
                    //tempFR = tempHR + tempGR;
                }
            }
            F = minF(tempFU,tempFD,tempFL,tempFR);
        if ((F==tempFU)&&(U<1)) {
            begArr = tempU;
            H = tempHU;
            G = tempGU;
            D += 1;
        }else D=0;
        if ((F==tempFD)&&(D<1)) { begArr=tempD; H=tempHD;G=tempGD;U+=1;}else U=0;
        if ((F==tempFR)&&(R<1)) { begArr=tempR; H=tempHR;G=tempGR;L+=1;}else L=0;
        if ((F==tempFL)&&(L<1)) { begArr=tempL; H=tempHL;G=tempGL;R+=1;}else R=0;

            for (int i=0;i<16;i++){
                System.out.print(begArr[i] + " ");
                if(i>0)
                    if((15-i)%4==0)
                        System.out.println();
            }
        }

    }
}
