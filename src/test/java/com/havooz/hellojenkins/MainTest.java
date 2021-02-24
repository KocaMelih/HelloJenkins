package com.havooz.hellojenkins;
import org.junit.Test;

public class MainTest {
    @Test
    public void EqualNumberTest()
    {

        Main test = new Main();
        assert(test.isEqual(1,1) == true);
    }

    @Test
    public void NotEqualNumberTest()
    {

        Main test = new Main();
        assert(test.isEqual(1,5) == false);
    }
}
