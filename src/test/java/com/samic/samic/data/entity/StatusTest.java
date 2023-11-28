package com.samic.samic.data.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

class StatusTest{

    //    private Status status = new Status();

    @Test
    void ensureTestofStatusLongVersion(){
        //        assertThat()
        /**
         * testing longVersion enum
         */
        assertAll(
                () -> assertEquals("Customer", Status.CUSTOMER.getLongVersion()),
                () -> assertEquals("Reserved", Status.RESERVED.getLongVersion()),
                () -> assertEquals("Missing", Status.MISSING.getLongVersion()),
                () -> assertEquals("Project", Status.PROJECT.getLongVersion()),
                () -> assertEquals("Available", Status.AVAILABLE.getLongVersion())
                 );
    }


    @Test
    void ensureTestofStatusShortVersion(){
        /**
         * testing shortVersion enum
         */
      assertAll(
              () -> assertEquals("C", Status.CUSTOMER.getShortVersion()),
              () -> assertEquals("R", Status.RESERVED.getShortVersion()),
              () -> assertEquals("M", Status.MISSING.getShortVersion()),
              () -> assertEquals("P", Status.PROJECT.getShortVersion()),
              () -> assertEquals("A", Status.AVAILABLE.getShortVersion())
               );
    }

}