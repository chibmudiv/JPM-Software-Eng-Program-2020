package com.company;
import java.util.*;

public class Main {


    public static void main(String[] args) {
        String Alert = "No Alert"; //Alert is initially set to none
        String[] serverDetails = userInput(); //User input is stored in an array
        try {
            /**
             * A server object is created from user input
             */
            Server thisServer = new Server(Integer.parseInt(serverDetails[0]), Integer.parseInt(serverDetails[1]), Integer.parseInt(serverDetails[2]), Integer.parseInt(serverDetails[3]));
            boolean CPUCheck = thisServer.checkCPU(); //CPU utilisation is checked to see if rules are violated
            boolean MemoryCheck = thisServer.checkMemory();
            boolean DiskCheck = thisServer.checkDisk();

            if (thisServer.getexistingViolation()){
                Alert = "Alert"; //If a violation exists, Alert is 'switched on'
            }
            System.out.print(Alert+ ", "+ serverDetails[0]); //Alert and serverID displayed

            if(CPUCheck){
                System.out.print(", CPU_UTILIZATION VIOLATED"); //If CPU usage rule is violated, message is printed
            }
            if (MemoryCheck){
                System.out.print(", MEMORY_UTILIZATION VIOLATED"); //If memory utilisation rule is violated, message is printed
            }
            if (DiskCheck) {
                System.out.print(", DISK_UTILIZATION VIOLATED "); //If disk usage rule is violated, message is printed
            }

        } catch(NumberFormatException e){
            System.err.println("Input is in incorrect format"); //Error caught if user input is not in correct format
        }

    }

    /**
     * User Input is split into constituent values and inserted into an Array
     */

    public static String[] userInput(){
        Scanner Keyboard = new Scanner(System.in);
        System.out.println("Enter details:");
        String serverResource = Keyboard.nextLine();
        String[] arrSplit = serverResource.split(",", 4);
        return arrSplit;

    }

}
