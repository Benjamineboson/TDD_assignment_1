package org.example;

import org.example.exceptions.ArrayEmptyException;
import org.example.exceptions.IntegerNotFoundException;
import org.example.exceptions.InvalidMethodChoiceException;
import org.junit.Before;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class IIntArrayImplTest {
    private IIntArrayImpl iIntArrayImpl;

    @Before
    public void setUp() throws Exception {
        int [] intArray = {1,2,5,6,8,9,5,12,2,2,1,2};
        iIntArrayImpl = new IIntArrayImpl(intArray);
    }

    /* getAverage() */

    @Test
    public void testGetAverage(){
        assertEquals(4.583333333333333,iIntArrayImpl.getAverage());
    }

    @Test
    public void testGetAverage_oneElement(){
        iIntArrayImpl.setIntArray(new int[]{5});
        assertEquals(5,iIntArrayImpl.getAverage());
    }

    @Test
    public void testGetAverage_oneElement_zero(){
        iIntArrayImpl.setIntArray(new int[]{0});
        assertEquals(0,iIntArrayImpl.getAverage());
    }

    @Test
    public void testGetAverage_empty(){
        iIntArrayImpl.setIntArray(new int[]{});
        assertThrows(ArrayEmptyException.class,() -> iIntArrayImpl.getAverage());
    }

    @Test
    public void testGetAverage_null(){
        iIntArrayImpl.setIntArray(null);
        assertThrows(NullPointerException.class,() -> iIntArrayImpl.getAverage());
    }

    /* findPosition() */

    @Test
    public void testFindPosition(){
        int [] expected = {2,6};
        assertArrayEquals(expected,iIntArrayImpl.findPosition(5));
    }

    @Test
    public void testFindPosition_oneOccurrence(){
        int [] expected = {7};
        assertArrayEquals(expected,iIntArrayImpl.findPosition(12));
    }

    @Test
    public void testFindPosition_notFound(){
        assertThrows(IntegerNotFoundException.class,()->iIntArrayImpl.findPosition(13));
    }

    @Test
    public void testFindPosition_empty(){
        iIntArrayImpl.setIntArray(new int[]{});
        assertThrows(ArrayEmptyException.class,()->iIntArrayImpl.findPosition(13));
    }

    @Test
    public void testFindPosition_null(){
        iIntArrayImpl.setIntArray(null);
        assertThrows(NullPointerException.class,()->iIntArrayImpl.findPosition(13));
    }

    /* appendLast() */

    @Test
    public void testAppendLast(){
        iIntArrayImpl.appendLast(999);
        int [] expected = {1,2,5,6,8,9,5,12,2,2,1,2,999};
        assertArrayEquals(expected,iIntArrayImpl.getIntArray());
    }

    @Test
    public void testAppendLast_empty(){
        iIntArrayImpl.setIntArray(new int[]{});
        iIntArrayImpl.appendLast(999);
        int [] expected = {999};
        assertArrayEquals(expected,iIntArrayImpl.getIntArray());
    }

    @Test
    public void testAppendLast_null(){
        iIntArrayImpl.setIntArray(null);
        assertThrows(NullPointerException.class,() -> iIntArrayImpl.appendLast(999));
    }

    /* insertAt() */

    @Test
    public void testInsertAt(){
        iIntArrayImpl.insertAt(1,0);
        int [] expected = {1,0,2,5,6,8,9,5,12,2,2,1,2};
        assertArrayEquals(expected,iIntArrayImpl.getIntArray());
    }

    @Test
    public void testInsertAt_0(){
        iIntArrayImpl.insertAt(0,999);
        int [] expected = {999,1,2,5,6,8,9,5,12,2,2,1,2};
        assertArrayEquals(expected,iIntArrayImpl.getIntArray());
    }

    @Test
    public void testInsertAt_last(){
        assertThrows(InvalidMethodChoiceException.class,() -> iIntArrayImpl.insertAt(12,999));
    }

    @Test
    public void testInsertAt_positionOutOfBounds(){
        assertThrows(ArrayIndexOutOfBoundsException.class,()-> iIntArrayImpl.insertAt(13,0));
    }

    @Test
    public void testInsertAt_positionOutOfBounds_negative(){
        assertThrows(ArrayIndexOutOfBoundsException.class,()-> iIntArrayImpl.insertAt(-1,0));
    }

    @Test
    public void testInsertAt_empty(){
        iIntArrayImpl.setIntArray(new int[]{});
        iIntArrayImpl.insertAt(0,999);
        int [] expected = {999};
        assertArrayEquals(expected,iIntArrayImpl.getIntArray());
    }

    @Test
    public void testInsertAt_null(){
        iIntArrayImpl.setIntArray(null);
        assertThrows(NullPointerException.class,()-> iIntArrayImpl.insertAt(19,0));
    }

    /* getAt() */

    @Test
    public void testGetAt(){
        assertEquals(5,iIntArrayImpl.getAt(2));
    }

    @Test
    public void testGetAt_positionOutOfBounds(){
        assertThrows(ArrayIndexOutOfBoundsException.class,() -> iIntArrayImpl.getAt(99));
    }

    @Test
    public void testGetAt_positionOutOfBounds_negative(){
        assertThrows(ArrayIndexOutOfBoundsException.class,() -> iIntArrayImpl.getAt(-99));
    }

    @Test
    public void testGetAt_empty(){
        iIntArrayImpl.setIntArray(new int[]{});
        assertThrows(ArrayEmptyException.class,() -> iIntArrayImpl.getAt(0));
    }

    @Test
    public void testGetAt_null(){
        iIntArrayImpl.setIntArray(null);
        assertThrows(NullPointerException.class,() -> iIntArrayImpl.getAt(0));
    }

    /* setAt() */

    @Test
    public void testSetAt(){
        iIntArrayImpl.setAt(8,999);
        int [] expected = {1,2,5,6,8,9,5,12,999,2,1,2};
        assertArrayEquals(expected,iIntArrayImpl.getIntArray());
    }

    @Test
    public void testSetAt_positionOutOfBounds(){
        assertThrows(ArrayIndexOutOfBoundsException.class,() -> iIntArrayImpl.setAt(99,999));
    }

    @Test
    public void testSetAt_positionOutOfBounds_negative(){
        assertThrows(ArrayIndexOutOfBoundsException.class,() -> iIntArrayImpl.setAt(-99,999));
    }

    @Test
    public void testSetAt_empty(){
        iIntArrayImpl.setIntArray(new int[]{});
        assertThrows(ArrayEmptyException.class,() -> iIntArrayImpl.setAt(0,999));
    }

    @Test
    public void testSetAt_null(){
        iIntArrayImpl.setIntArray(null);
        assertThrows(NullPointerException.class,() -> iIntArrayImpl.setAt(0,999));
    }

    /* deleteAt() */

    @Test
    public void testDeleteAt(){
        assertEquals(9,iIntArrayImpl.deleteAt(5));
        int [] expected = {1,2,5,6,8,5,12,2,2,1,2};
        assertArrayEquals(expected,iIntArrayImpl.getIntArray());
    }

    @Test
    public void testDeleteAt_zero(){
        assertEquals(1,iIntArrayImpl.deleteAt(0));
        int [] expected = {2,5,6,8,9,5,12,2,2,1,2};
        assertArrayEquals(expected,iIntArrayImpl.getIntArray());
    }

    @Test
    public void testDeleteAt_positionOutOfBounds(){
        assertThrows(ArrayIndexOutOfBoundsException.class,() -> iIntArrayImpl.deleteAt(99));
    }

    @Test
    public void testDeleteAt_positionOutOfBounds_negative(){
        assertThrows(ArrayIndexOutOfBoundsException.class,() -> iIntArrayImpl.deleteAt(-99));
    }

    @Test
    public void testDeleteAt_empty(){
        iIntArrayImpl.setIntArray(new int[]{});
        assertThrows(ArrayEmptyException.class,() -> iIntArrayImpl.deleteAt(0));
    }

    @Test
    public void testDeleteAt_null(){
        iIntArrayImpl.setIntArray(null);
        assertThrows(NullPointerException.class,() -> iIntArrayImpl.deleteAt(0));
    }
}
