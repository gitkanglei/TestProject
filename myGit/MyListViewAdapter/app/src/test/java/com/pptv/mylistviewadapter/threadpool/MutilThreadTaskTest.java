package com.pptv.mylistviewadapter.threadpool;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @anthor LeiKang
 */
public class MutilThreadTaskTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testStartTask() throws Exception {
        new MutilThreadTask().startTask();
    }
}