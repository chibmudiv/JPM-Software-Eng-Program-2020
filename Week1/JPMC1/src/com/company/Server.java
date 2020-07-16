package com.company;

/**
 * Server class storing CPU, MEMORY AND DISK information.
 */
public class Server{
    private int serverID;
    private int CPU_UTILIZATION;
    private int MEMORY_UTILIZATION;
    private int DISK_UTILIZATION;
    private boolean existingViolation;

    /**
     * Server constructor
     */
    Server(int ID, int cpu, int memory, int disk){
        serverID = ID;
        CPU_UTILIZATION = cpu;
        MEMORY_UTILIZATION = memory;
        DISK_UTILIZATION = disk;
    }

    /**
     * Server accessor and mutator methods
     */
    public void setID(int ID){
        this.serverID = ID;
    }
    public void setCPU(int cpu){
        this.CPU_UTILIZATION = cpu;
    }
    public void setMemory(int memory){
        this.MEMORY_UTILIZATION= memory;
    }
    public void setDisk(int disk){
        this.DISK_UTILIZATION = disk;
    }

    public int getID(){
        return serverID;
    }
    public int getCPU(){
        return CPU_UTILIZATION;
    }
    public int getMemory(){
        return MEMORY_UTILIZATION;
    }
    public int getDisk(){
        return DISK_UTILIZATION;
    }

    public boolean getexistingViolation(){
        return existingViolation;
    }

    /**
     * checkCPU determines if current CPU usage adheres to rule
     */
    public boolean checkCPU(){
        if (CPU_UTILIZATION <= 85){
            existingViolation = true;
            return true;
        }
        return false;
    }

    /**
     * checkMemory determines if current memory usage adheres to rule
     */
    public boolean checkMemory() {
        if (MEMORY_UTILIZATION <= 75) {
            existingViolation = true;
            return true;
        }
        return false;
    }

    /**
     * checkDisk determines if current memory usage adheres to rule
     */
    public boolean checkDisk(){
        if (DISK_UTILIZATION <= 60){
            existingViolation = true;
            return true;
        }
        return false;
    }

}
