package gad.binarysearch;

import gad.binarysearch.Interval.NonEmptyInterval;

public final class BinSea {

    private BinSea() {
    }

    public static int search(int[] sortedData, int value, Result result) {
        int from = 0;
        int to = sortedData.length - 1;
        int index = (from + to) / 2;

        while (from != to && from <= to){
            int mid = (from + to) / 2;
            result.addStep(mid);
            if (sortedData[mid] < value) {
                from = mid + 1;
                index = (from + to) / 2;
            } else if (sortedData[mid] > value) {
                to = mid - 1;
                index = (from + to) / 2;
            } else if (sortedData[mid] == value) {
                index = mid;
                break;
            }
        }
        return index;
    }

    public static int search(int[] sortedData, int value, boolean lowerBound, Result result) {
        int grenze = search(sortedData,value,result);
        if(lowerBound){
            if (sortedData[grenze] >= value){
                while (grenze > 0 && sortedData[grenze - 1] >= value){
                    grenze--;
                }
            }
            if (sortedData[grenze] < value){
               grenze++;
                    if (grenze > sortedData.length - 1){
                        return -1;
                    }
               return grenze;
            }
        }

        else {
            if (sortedData[grenze] <= value){
                while (grenze < sortedData.length - 1 && sortedData[grenze + 1] <= value){
                    grenze++;
                }
            }
            if (sortedData[grenze] > value){
                grenze--;
                if (grenze < 0){
                    return -1;
                }
            return grenze;
            }
        }
        return grenze;
    }

    public static Interval search(int[] sortedData, NonEmptyInterval valueRange, Result resultLower, Result resultHigher) {
        int grenzeL = search(sortedData, valueRange.getFrom(), true, resultLower);
        if (grenzeL == -1) {
            Interval.EmptyInterval interval = new Interval.EmptyInterval();
            return interval;
        }
        int grenzeH = search(sortedData, valueRange.getTo(), false, resultHigher);
        if (grenzeH == -1) {
            Interval.EmptyInterval interval = new Interval.EmptyInterval();
            return interval;
        }
        if (grenzeH < grenzeL) {
                Interval.EmptyInterval interval = new Interval.EmptyInterval();
                return interval;
        }
        Interval.NonEmptyInterval interval = new Interval.NonEmptyInterval(grenzeL,grenzeH);
        return interval;
    }


    public static void main(String[] args) {
        int[] array = new int[] { 2,7,7,42,69,1337,2000,9001};

       //System.out.println(search(array, 7, new StudentResult()));
        //System.out.println(search(array, 100, new StudentResult()));

        //System.out.println(search(array, 7, false, new StudentResult()));
        //System.out.println(search(array, 100, true, new StudentResult()));

        System.out.println(search(array, new NonEmptyInterval(7, 1500), new StudentResult(), new StudentResult()));
        System.out.println(search(array, new NonEmptyInterval(9002, 10000), new StudentResult(), new StudentResult()));
    }
}