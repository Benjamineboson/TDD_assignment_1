package org.example;

import org.example.exceptions.ArrayEmptyException;
import org.example.exceptions.IntegerNotFoundException;
import org.example.exceptions.InvalidMethodChoiceException;


public class IIntArrayImpl implements IIntArray {

    private int[] intArray;

    public IIntArrayImpl(int[] intArray) {
        this.intArray = intArray;
    }

    @Override
    public double getAverage() {
        if (intArray == null) throw new NullPointerException("The array has not been initialized");
        if (intArray.length < 1) throw new ArrayEmptyException("The array is empty");
        double total = 0;
        for (int i = 0; i < intArray.length ; i++) {
            total += intArray[i];
        }
        return total/intArray.length;
    }

    @Override
    public int[] findPosition(int val) {
        if (intArray == null) throw new NullPointerException("The array has not been initialized");
        if (intArray.length == 0) throw new ArrayEmptyException("The array is empty");
        String valOccurrence = "";
        for (int i = 0; i < intArray.length; i++) {
            if (intArray[i] == val){
                valOccurrence += i;
            }
        }
        int[] newArray = new int[valOccurrence.length()];
        for (int i = 0; i < valOccurrence.length() ; i++) {
            newArray[i] = Integer.parseInt(String.valueOf(valOccurrence.charAt(i)));
        }
        if (newArray.length == 0) throw new IntegerNotFoundException("Could find an occurrence of ["+val+"] in the array");
        return newArray;
    }

    @Override
    public void appendLast(int val) {
        if (intArray == null) throw new NullPointerException("The array has not been initialized");
        int [] newArray = new int[intArray.length+1];
        for (int i = 0; i < intArray.length ; i++) {
            newArray[i] = intArray[i];
        }
        newArray[newArray.length-1] = val;
        setIntArray(newArray);
    }

    @Override
    public void insertAt(int pos, int val) {
        if (pos == intArray.length && pos != 0) throw new InvalidMethodChoiceException
                ("You cannot append last using insertAt(int pos, int val). Try using appendLast(int val)");
        if (intArray == null) throw new NullPointerException("The array has not been initialized");
        if (pos > intArray.length || pos < 0) throw new ArrayIndexOutOfBoundsException("The position ["+pos+"] is out of bounds");
        int [] newArray = new int[intArray.length+1];
        for (int i = 0; i < intArray.length ; i++) {
            if (i < pos){
                newArray[i] = intArray[i];
            }else{
                newArray[i+1] = intArray[i];
            }
        }
        newArray[pos] = val;
        setIntArray(newArray);
    }

    @Override
    public int getAt(int pos) {
        if (intArray == null) throw new NullPointerException("The array has not been initialized");
        if (intArray.length == 0) throw new ArrayEmptyException("The array is empty");
        if (pos > intArray.length-1 || pos < 0) throw new ArrayIndexOutOfBoundsException("The position ["+pos+"] is out of bounds");
        return intArray[pos];
    }

    @Override
    public void setAt(int pos, int element) {
        if (intArray == null) throw new NullPointerException("The array has not been initialized");
        if (intArray.length == 0) throw new ArrayEmptyException("The array is empty");
        if (pos > intArray.length-1 || pos < 0) throw new ArrayIndexOutOfBoundsException("The position ["+pos+"] is out of bounds");
        intArray[pos] = element;
    }

    @Override
    public int deleteAt(int pos) {
        if (intArray == null) throw new NullPointerException("The array has not been initialized");
        if (intArray.length == 0) throw new ArrayEmptyException("The array is empty");
        if (pos > intArray.length-1 || pos < 0) throw new ArrayIndexOutOfBoundsException("The position ["+pos+"] is out of bounds");
        int[] newArray = new int [intArray.length-1];
        for (int i = 0; i < newArray.length ; i++) {
            if (i < pos) {
                newArray[i] = intArray[i];
            }else{
                newArray[i] = intArray[i+1];
            }
        }
        int deletedElement = intArray[pos];
        setIntArray(newArray);
        return deletedElement;
    }

    public int[] getIntArray() {
        return intArray;
    }

    public void setIntArray(int[] intArray) {
        this.intArray = intArray;
    }
}
