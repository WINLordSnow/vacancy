import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

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

    static boolean ifNotInDots(long dot, long[] dots) {
        boolean temp = true;
        for (long i : dots) {
            if (i == dot) {
                temp = false;
                break;
            }
        }
        return temp;
    }

    public static void main(String[] args) throws IOException {
	// write your code here
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String choice;
        int quantityVacancy;
        int quantityString = 0;
        int dotsStart=0;
        int dotsEnd=0;
        choice = br.readLine().toLowerCase();
        quantityVacancy = Integer.parseInt(choice);
        long[] allDotsStart = new long[quantityVacancy];
        long[] allDotsEnd = new long[quantityVacancy];
        Vacancy[] allVacancy = new Vacancy[quantityVacancy];
        for (int i=0; i<quantityVacancy; i++) {
            allVacancy[i] = new Vacancy();
        }

        do {
            quantityString++;
            choice = br.readLine().toLowerCase();
            int k;
            k = choice.indexOf(' ');
            int start = Integer.parseInt(choice.substring(0, k));
            int end = Integer.parseInt(choice.substring(k + 1));
            allVacancy[quantityString - 1].setDate(start, end);
            if (ifNotInDots(start, allDotsStart)) {
                allDotsStart[dotsStart] = start;
                dotsStart++;
            }
            if (ifNotInDots(end, allDotsEnd)) {
                allDotsEnd[dotsEnd] = end;
                dotsEnd++;
            }
        } while ((quantityString) != (quantityVacancy));
        long[] allDots = new long[dotsStart+dotsEnd];
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
    }
}
