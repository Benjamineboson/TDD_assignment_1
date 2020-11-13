package org.example;

public interface IIntArray {
    double getAverage();
    int[] findPosition(int val);
    void appendLast(int val);
    void insertAt(int pos,int val);
    int getAt(int pos);
    void setAt(int pos,int element);
    int deleteAt(int pos);
}
