package gad.binarysearch;

import gad.binarysearch.Interval.NonEmptyInterval;

public final class BinSea {

    private BinSea() {
    }

    public static int search(int[] sortedData, int value, Result result) {

        Interval a = new Interval;

        int index = -1;
        while (from <= to){
            int mid = from + (to - from) / 2;
            if (sortedData[mid] < value) {
                from = mid - 1;
                index = from;
                result.addStep(index)
            } else if (sortedData[mid] > value) {
                to = mid - 1;
                index = to;
                result.addStep(index)
            } else if (sortedData[mid] == value) {
                index = mid;
                result.addStep(index)
                break;
            }
        }
        return index;
    }

    public static int search(int[] sortedData, int value, boolean lowerBound, Result result) {
        index = search(sortedData,value,result);
         if(lowerBound){
             if (sortedData[index] < value) {
                 int grenze = index + 1;
             }
             else {
                 int grenze = index;
             }
         }
         else {
             if (sortedData[index] > value) {
                 int grenze = index - 1;
             }
             else {
                 grenze = index;
             }
         }
         if (grenze > sortedData.length){
             grenze = -1;
         }
         result.addStep(grenze);
         return grenze;
    }

    public static Interval search(int[] sortedData, NonEmptyInterval valueRange, Result resultLower, Result resultHigher) {
        grenzeL = search(sortedData, valueRange.getFrom(), false, result);
        grenzeH = search(sortedData, valueRange.getTo(), true, result);
        resultHigher.addStep(grenzeH);
        resultLower.addStep(grenzeL);

        return null;
    }

    public static void main(String[] args) {
        int[] array = new int[] { 2, 7, 7, 42, 69, 1337, 2000, 9001 };

        System.out.println(search(array, 7, new StudentResult()));
        System.out.println(search(array, 100, new StudentResult()));

        System.out.println(search(array, 7, false, new StudentResult()));
        System.out.println(search(array, 100, true, new StudentResult()));

        System.out.println(search(array, new NonEmptyInterval(7, 1500), new StudentResult(), new StudentResult()));
        System.out.println(search(array, new NonEmptyInterval(9002, 10000), new StudentResult(), new StudentResult()));
    }
}