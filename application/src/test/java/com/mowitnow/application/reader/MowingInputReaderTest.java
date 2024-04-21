package com.mowitnow.application.reader;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.batch.test.context.SpringBatchTest;
import org.springframework.core.io.FileSystemResource;

import static org.junit.jupiter.api.Assertions.*;

@SpringBatchTest
class MowingInputReaderTest {

    private MowingInputReader cut;
    @BeforeEach
    void setUp() {
        //cut = new MowingInputReader(new MowingRequestBuilder(), new FileSystemResource("E:/tmp/test_mowing.txt"));
        //assertNotNull(cut);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void execute() {
        //cut.execute(null, null);
    }



}