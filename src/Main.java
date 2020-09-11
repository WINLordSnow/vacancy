import java.util.Scanner;

class Vacancy {
    private long start;
    private long end;

    void setDate (long start, long end) {
        this.start = start;
        this.end = end;
    }

    long getDateStart () {
        return start;
    }

    long getDateEnd () {
        return end;
    }

    boolean withinVacancy(Vacancy v) {
        return ((v.start >= start)&(v.end<=end));
    }

}

public class Main {

    static long[] sort(long[] dots) {
        long[] tempDots = new long[dots.length];
        tempDots[0] = dots[0];
        for (int i = 1; i < dots.length; i++) {
            tempDots[i] = dots[i];
            for (int j = 0; j < i; j++) {
                if (dots[i] <= tempDots[j]) {
                    System.arraycopy(tempDots, j, tempDots, j + 1, i - j);
                    tempDots[j] = dots[i];
                    break;
                }
            }
        }
        return tempDots;
    }

    static boolean ifNotInDots(long dot, long[] dots, int length) {
        boolean temp = true;
        for (int i = 0; i < length; i++) {
            if (dots[i] == dot) {
                temp = false;
                break;
            }
        }
        return temp;
    }

    public static void main(String[] args) {
	// write your code here
        Scanner scanner = new Scanner(System.in);
        int quantityVacancy;
        int dotsStart=0;
        int dotsEnd=0;
        quantityVacancy = scanner.nextInt();
        long[] allDotsStart = new long[quantityVacancy];
        long[] allDotsEnd = new long[quantityVacancy];
        Vacancy[] allVacancy = new Vacancy[quantityVacancy];
        for (int i=0; i < quantityVacancy; i++) {
            allVacancy[i] = new Vacancy();
        }
        for (int i = 0; i < quantityVacancy; i++) {
            int start = scanner.nextInt();
            int end = scanner.nextInt();
            allVacancy[i].setDate(start, end);
            if (ifNotInDots(start, allDotsStart, dotsStart)) {
                allDotsStart[dotsStart] = start;
                dotsStart++;
            }
            if (ifNotInDots(end, allDotsEnd, dotsEnd)) {
                allDotsEnd[dotsEnd] = end;
                dotsEnd++;
            }
        }
        scanner.close();

       // long startTime = System.currentTimeMillis();
        long[] allDots = new long[dotsStart + dotsEnd];
        System.arraycopy(allDotsStart, 0, allDots, 0, dotsStart);
        System.arraycopy(allDotsEnd, 0, allDots, dotsStart, dotsEnd);

        allDots = sort(allDots);
        long maxVacancy=0;
        long max;
        long sum=0;
        int quantityMax=0;
        for (int i = 0; i < allDots.length-1; i++) {
            Vacancy v = new Vacancy();
            max=0;
            v.setDate(allDots[i], allDots[i+1]);
            for (Vacancy j: allVacancy) {
                if (j.withinVacancy(v)) max++;
            }
            if (max==maxVacancy) {
                sum += v.getDateEnd() - v.getDateStart() + 1;
                quantityMax++;
            }
            if (max>maxVacancy) {
                maxVacancy = max;
                sum = v.getDateEnd()-v.getDateStart()+1;
                quantityMax=1;
            }
        }
        System.out.println(quantityMax+" "+sum);
      //  System.out.println(System.currentTimeMillis() - startTime);
    }
}
