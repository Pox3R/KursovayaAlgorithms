package algorithm;
import decis.*;


/**
 * Created by Roman on 21.05.2017.
 */
public class Astar {
    public int H;
    public int G;
    public int F;
    public int[] begArr = new int[16];
    public int[] termState1 = {1, 2, 3, 4,
            5, 6, 7, 8,
            9, 10, 11, 12,
            13, 14, 15, -1};
    public int[] termState2 = {1, 2, 3, 4,
            5, 6, 7, 8,
            9, 10, 11, 12,
            13, 15, 14, -1};
    public int[] temp = new int[16];
    public int[] tempU = new int[16];
    public int[] tempD = new int[16];
    public int[] tempL = new int[16];
    public int[] tempR = new int[16];
    public int flag = 0;
    public int[] tempG = new int[4];//up=0,down=1,left=2,right=3;
    public int[] tempH = new int[4];
    public int[] tempF = new int[4];


    enum direction {
        UP(-4),
        DOWN(4),
        RIGHT(1),
        LEFT(-1);
        private final int shift;

        direction(int dir) {
            shift = dir;
        }

        public int getDir() {
            return shift;
        }
    }

    public Astar(MainIssue begState) {
        begArr = begState.array1;
        H = getHTerm1(begArr);
        F = H + G;
    }


    public int getHnumber(int[] arr, int i) {
        int numInBeg = 0;
        //arr = begArr;
        if ((arr[i]) != (termState1[i])) {
            for (int j = 0; j < 16; j++) {
                if ((arr[i]) == (termState1[j])) numInBeg = j;
            }
        } else {
            numInBeg = 0;
            return numInBeg;
        }
        return Math.abs(numInBeg - i);
    }

    public int getHTerm1(int[] arr) {
        int htemp = 0;
        arr = begArr;
        for (int i = 0; i < 16; i++) {
            if ((arr[i]) == (termState1[i])) htemp++;
        }
        return 16 - htemp;
    }

    public int findEmp(int[] arr) {
        for (int i = 0; i < 16; i++) {
            if (arr[i] == -1) return i;
        }
        return -1;
    }

    public int minF(int a, int b, int c, int d) {
        int m2 = Math.min(a, b);
        if (m2 <= c & m2 <= d) return m2;
        if (c <= m2 & c <= d) return c;
        if (d <= c & d <= m2) return d;
        return m2;
    }

    public void swapCell(direction dir, int tempHDir, int tempGDir, int tempFDir, int[] tempDir, int flag) {
        int tempCell = 0;
        tempGDir++;
        for (int j = 0; j < 16; j++) {
            temp[j] = begArr[j];
        }
        int empCell = findEmp(temp);
        int ttempHU = getHnumber(temp, empCell + dir.getDir());
        tempCell = temp[empCell + dir.getDir()];
        temp[empCell + dir.getDir()] = -1;
        temp[empCell] = tempCell;
        tempHDir = getHnumber(temp, empCell);
        flag = 0;
        if (tempHDir < ttempHU) {
            tempFDir = tempHDir + tempGDir;
            tempDir = temp;
            temp = new int[16];
            int i = 0;
            switch (dir.getDir()) {
                case -1:
                    i = 2;
                    tempH[i] = tempHDir;
                    tempG[i] = tempGDir;
                    tempF[i] = tempHDir + tempGDir;
                    tempL = tempDir;
                    System.out.println("left " + tempF[i]);
                    break;
                case 1:
                    i = 3;
                    tempH[i] = tempHDir;
                    tempG[i] = tempGDir;
                    tempF[i] = tempHDir + tempGDir;
                    tempR = tempDir;
                    System.out.println("Right " + tempF[i]);
                    break;
                case -4:
                    i = 0;
                    tempH[i] = tempHDir;
                    tempG[i] = tempGDir;
                    tempF[i] = tempHDir + tempGDir;
                    tempU = tempDir;
                    System.out.println("Up " + tempF[i]);
                    break;
                case 4:
                    i = 1;
                    tempH[i] = tempHDir;
                    tempG[i] = tempGDir;
                    tempF[i] = tempHDir + tempGDir;
                    tempD = tempDir;
                    System.out.println("Down " + tempF[i]);
                    break;
            }
            flag = 1;
        }
    }

    public boolean isEcuals(int[] a, int[] b) {
        int cnt = 0;
        for (int i = 0; i < 16; i++) {
            if (a[i] == b[i]) cnt++;
        }
        if (cnt == 16) return true;
        return false;
    }

    public void clearArr(int[] a) {
        for (int i = 0; i < 16; i++) {
            a[i] = 0;
        }
    }

    public void ecuateArr(int[] a, int[] b) {
        for (int i = 0; i < 16; i++) {
            b[i] = a[i];
        }
    }

    public void alghor() {
        int empCell = findEmp(begArr);
        G = 0;
        for (int i = 0; i < 4; i++) {
            tempG[i] = G;
            tempH[i] = H;
            tempF[i] = H + G;
        }

        while (begArr != termState1) {
            for (int i = 0; i < 4; i++) {
                empCell = findEmp(begArr);
                if (i == 0) {//UP
                    if ((empCell != 0) && (empCell != 1) && (empCell != 2) && (empCell != 3)) {
                        swapCell(direction.UP, tempH[i], tempG[i], tempF[i], tempU, flag);
                    } else tempF[i] = 1000000000;
                }
                if (i == 1) {//DOWN
                    if ((empCell != 12) && (empCell != 13) && (empCell != 14) && (empCell != 15)) {
                        swapCell(direction.DOWN, tempH[i], tempG[i], tempF[i], tempD, flag);
                    } else tempF[i] = 1000000000;
                }
                if (i == 2) {//LEFT
                    if ((empCell != 0) && (empCell != 4) && (empCell != 8) && (empCell != 12)) {
                        swapCell(direction.LEFT, tempH[i], tempG[i], tempF[i], tempL, flag);
                    } else tempF[i] = 1000000000;
                }
                if (i == 3) {//RIGHT
                    if ((empCell != 3) && (empCell != 7) && (empCell != 11) && (empCell != 15)) {
                        swapCell(direction.RIGHT, tempH[i], tempG[i], tempF[i], tempR, flag);
                    } else tempF[i] = 1000000000;
                }
            }
            F = minF(tempF[0], tempF[1], tempF[2], tempF[3]);
            if ((F == tempF[0])) {
                ecuateArr(tempU, begArr);
                H = tempH[0];
                G = tempG[0];
            }
            if ((F == tempF[1])) {
                ecuateArr(tempD, begArr);
                H = tempH[1];
                G = tempG[1];
            }
            if ((F == tempF[2])) {
                ecuateArr(tempL, begArr);
                H = tempH[2];
                G = tempG[2];
            }
            if ((F == tempF[3])) {
                ecuateArr(tempR, begArr);
                H = tempH[3];
                G = tempG[3];
            }
            clearArr(tempL);
            clearArr(tempD);
            clearArr(tempR);
            clearArr(tempU);

            for (int i = 0; i < 16; i++) {
                System.out.print(begArr[i] + " ");
                if (i > 0)
                    if ((15 - i) % 4 == 0)
                        System.out.println();
            }
            if ((isEcuals(begArr, termState1))||(isEcuals(begArr,termState2))) {
                System.out.println("Решение завершено");
                break;
            }
        }

    }
}
