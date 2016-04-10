package com.muhiptezcan.codebourse.data.provider;

import org.junit.Test;

import static org.junit.Assert.assertNotNull;

/**
 * Created by imtezcan on 10.04.2016.
 */
public class DataProviderTest {

    @Test
    public final void getScoresMap() throws Exception {
        final DataProvider dataProvider = new DataProvider();
        assertNotNull(dataProvider.getScoresMap());
    }
}
