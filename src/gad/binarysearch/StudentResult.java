package gad.binarysearch;

public class StudentResult implements Result {

    @Override
    public void addStep(int index) {
        System.out.println("added step to index " + index);
    }

}