import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Vakancy {
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

    boolean crossing (Vakancy v) {
        return (((v.start<=end)|(end < v.end))&((v.end>=start)|(start>v.start)));
    }

}

public class Main {
    static Vakancy[] sort(Vakancy[] Vakancy) {
        Vakancy[] tempVakancy = new Vakancy[Vakancy.length];
        for (Vakancy i : tempVakancy)
            i = new Vakancy();
        tempVakancy[0] = Vakancy[0];
        for (int i = 1; i < Vakancy.length; i++) {
            tempVakancy[i] = Vakancy[i];
            for (int j = 0; j < i; j++) {
                if (Vakancy[i].getDateStart() <= tempVakancy[j].getDateStart()) {
                    for (int k = i; k > j; k--) {
                        tempVakancy[k] = tempVakancy[k-1];
                    }
                    tempVakancy[j] = Vakancy[i];
                    break;
                }
            }
        }
        return tempVakancy;
    }

    public static void main(String[] args) throws IOException {
	// write your code here
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String choice;
        int quantityVacancy = 0;
        int quantityString = 0;
        String tempVacancy = null;
        choice = br.readLine().toLowerCase();
        quantityVacancy = Integer.parseInt(choice);
        Vakancy[] allVacancy = new Vakancy[quantityVacancy];
        for (int i=0; i<quantityVacancy; i++) {
            allVacancy[i] = new Vakancy();
        }

        do {
            quantityString++;
            choice = br.readLine().toLowerCase();
        /*    if (quantityString == 1) {
                quantityVacancy = Integer.parseInt(choice);
            } else {
               // tempVacancy = choice;
            }*/

            char[] inputString = new char[choice.length()];
            int k = 0;
            inputString = choice.toCharArray();
            while (inputString[k] != ' ') {
                k++;
                if (k == choice.length()) {
                    k = 0;
                    break;
                }
            }

                allVacancy[quantityString - 1].setDate(Integer.parseInt(choice.substring(0, k)),
                        Integer.parseInt(choice.substring(k + 1)));

        } while ((quantityString) != (quantityVacancy));
        allVacancy = sort(allVacancy);
        for (Vakancy i : allVacancy)
            System.out.println(i.getDateStart() + " " + i.getDateEnd());
        long startPeriod = allVacancy[0].getDateStart();
        long endPeriod = allVacancy[0].getDateEnd();
        int quantityPeriods = 0;
        long sumPeriods = 0;
        for (Vakancy i: allVacancy) {
            if (i.getDateStart() > endPeriod) {
                quantityPeriods++;
                sumPeriods += endPeriod - startPeriod + 1;
                startPeriod = i.getDateStart();
                endPeriod = i.getDateEnd();
            } else {
                if (i.getDateEnd() < endPeriod)
                    endPeriod = i.getDateEnd();
                startPeriod = i.getDateStart();
            }
        }
        quantityPeriods++;
        sumPeriods += endPeriod - startPeriod + 1;
        System.out.println(quantityPeriods + " " + sumPeriods);
        System.out.println(allVacancy[0].crossing(allVacancy[1]));
    }
}
