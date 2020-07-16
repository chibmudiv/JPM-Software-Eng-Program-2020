package com.company;

import org.junit.Test;

import static org.junit.Assert.*;

public class ServerTest {
    private final Server Server_Test = new Server(1,2,3,4);

    @Test
    public void getID() {
        assertEquals(Server_Test.getID(),1);
    }

    @Test
    public void getCPU() {
        assertEquals(Server_Test.getCPU(),2);
    }

    @Test
    public void getMemory() {
        assertEquals(Server_Test.getMemory(),3);
    }

    @Test
    public void getDisk() {
        assertEquals(Server_Test.getDisk(),4);
    }

    @Test
    public void getexistingViolation() {
        assertFalse("Violation exists",
                Server_Test.getexistingViolation());
    }

    @Test
    public void checkCPU() {
        assertTrue(
                "CPU utilization is more than 85",
                Server_Test.checkMemory());
    }

    @Test
    public void checkMemory() {
        assertTrue(
                "Memory utilization is more than 75",
                Server_Test.checkMemory());
    }

    @Test
    public void checkDisk() {
        assertTrue(
                "Disk utilization is more than 60",
                Server_Test.checkDisk());
    }

}