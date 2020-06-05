package de.hfu;

import org.junit.Test;
import static org.junit.Assert.*;


public class UtilTest {
    @Test
    public void testistErstesHalbjahrUntereGrenze1(){
        assertTrue(Util.istErstesHalbjahr(1));
    }
    @Test
    public void testistErstesHalbjahrObereGrenze6(){
        assertTrue(Util.istErstesHalbjahr(6));
    }
    @Test
    public void testistErstesHalbjahrZweiteshalbjahruntereGrenze(){
        assertFalse(Util.istErstesHalbjahr(7));
    }
    @Test
    public void testistErstesHalbjahrZweiteshalbjahrObereGrenze(){
        assertFalse(Util.istErstesHalbjahr(12));
    }
    @Test(expected = IllegalArgumentException.class)
    public void testistErstesHalbjahrZweiteshaljahrIllegalUntereGrenze(){
        Util.istErstesHalbjahr(0);
    }
    @Test(expected = IllegalArgumentException.class)
    public void testistErstesHalbjahrZweiteshaljahrIllegalObereGrenze(){
        Util.istErstesHalbjahr(13);
    }
}
