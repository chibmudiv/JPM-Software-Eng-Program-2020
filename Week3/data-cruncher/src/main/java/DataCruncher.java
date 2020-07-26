import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.*;
import java.io.*;

public class DataCruncher {

    // do not modify this method - just use it to get all the Transactions that are in scope for the exercise
    public List<Transaction> readAllTransactions() throws Exception {
        return Files.readAllLines(Paths.get("src/main/resources/payments.csv"), StandardCharsets.UTF_8)
                .stream()
                .skip(1)
                .map(line -> {
                    var commaSeparatedLine = List.of(line
                            .replaceAll("'", "")
                            .split(",")
                    );
                    var ageString = commaSeparatedLine.get(2);
                    var ageInt = "U".equals(ageString) ? 0 : Integer.parseInt(ageString);
                    return new Transaction(commaSeparatedLine.get(1),
                            ageInt,
                            commaSeparatedLine.get(3),
                            commaSeparatedLine.get(4),
                            commaSeparatedLine.get(5),
                            commaSeparatedLine.get(6),
                            commaSeparatedLine.get(7),
                            Double.parseDouble(commaSeparatedLine.get(8)),
                            "1".equals(commaSeparatedLine.get(9)));
                })
                .collect(Collectors.toList());
    }



    // example
    public List<Transaction> readAllTransactionsAge0() throws Exception {
        return readAllTransactions().stream()
                .filter(transaction -> transaction.getAge() == 0)
                .collect(Collectors.toList());
    }

    // task 1
    public Set<String> getUniqueMerchantIds() throws Exception {
        List<Transaction> transactions = readAllTransactions();
        Set<String> iDSet = new HashSet();
        //adding all merchantIds into iDSet
        for (Transaction current : transactions ){
            if (iDSet.contains(current.getMerchantId())){
                continue;
            } else {
                iDSet.add(current.getMerchantId());
            }
        }
        return iDSet;
    }

    // task 2
    public long getTotalNumberOfFraudulentTransactions() throws Exception {
        List<Transaction> transactions = readAllTransactions();
        int count = 0;

        //Looping through all transactions
        // Check to see if current transaction is fraudulent
        // If so, count variable is updated.
        for (Transaction current : transactions ){
            if (current.isFraud()){
                count++;
            }
        }

        return count;
    }

    // task 3
    public long getTotalNumberOfTransactions(boolean isFraud) throws Exception {
        List<Transaction> transactions = readAllTransactions();
        int count = 0;

        //check to if isFraud is true
        //Looping through all transactions
        //Check to see if current transaction is fraudulent
        //The number of fraudulent transactions are counted up using count variable
        if (isFraud){
            for (Transaction current : transactions ){
                if (current.isFraud()){
                    count++;
                }
            }
        } else { //if isFraud is false, non-fraudulent transactions are counted
            for (Transaction current : transactions ){
                if (!current.isFraud()){
                    count++;
                }
            }
        }
        return count;
    }

    // task 4
    public Set<Transaction> getFraudulentTransactionsForMerchantId(String merchantId) throws Exception {
        List<Transaction> transactions = readAllTransactions();
        Set<Transaction> fraudulentTransactions = new HashSet<>();

        // Looping through all transactions
        // Transactions of parsed merchant are checked for being fraudulent
        //If fraudulent and belonging to parsed merchant, transaction is added to fraudulentTransactions Set
        for (Transaction current : transactions){
            if (current.getMerchantId().equals(merchantId)){
                if (current.isFraud()) {
                    fraudulentTransactions.add(current);
                }
            }
        }
        return fraudulentTransactions;
    }

    // task 5
    public Set<Transaction> getTransactionsForMerchantId(String merchantId, boolean isFraud) throws Exception {
        List<Transaction> transactions = readAllTransactions();
        Set<Transaction> fraudulentTransactions = new HashSet<>();
        // Looping through all transactions
        // Check to see if transaction is of parsed merchant
        // Check to see if transaction is of isFraud value (True or False)
        // If so, added to fraudulentTransactions Set
        for (Transaction current : transactions){
            if (current.getMerchantId().equals(merchantId)){
                if (current.isFraud()==isFraud) {
                    fraudulentTransactions.add(current);
                }
            }
        }
        return fraudulentTransactions;
    }

    // task 6
    public List<Transaction> getAllTransactionsSortedByAmount() throws Exception {
        //returns sorted transactions
        return readAllTransactions().stream()
                .sorted(Comparator.comparing(Transaction::getAmount))
                .collect(Collectors.toList());
    }

    // task 7
    public double getFraudPercentageForMen() throws Exception {
        List<Transaction> transactions = readAllTransactions();
        int byMen = 0; //storing transactions fulfilled by men
        Double percentage;
        int fraudulent = 0;
        String gender = "M";
        // Looping through all transactions
        // Checking if current transaction is fraudulent
        // Check if fraudulent transaction was fulfilled by a man
        for (Transaction current : transactions){
            if (current.isFraud()){
                fraudulent=fraudulent+1;
                if (current.getGender().equals(gender)) {
                    byMen = byMen + 1;
                }
            }
        }
        percentage = (double) byMen/fraudulent; //number of fraudulent transactions carried out by men divided by total fraudulent transactions

        return percentage;
    }

    // task 8
    public Set<String> getCustomerIdsWithNumberOfFraudulentTransactions(int numberOfFraudulentTransactions) throws Exception {
        List<Transaction> transactions = readAllTransactions();
        HashMap<String, Integer> fraudulentCustomers = new HashMap<>();

        // Creating a HashMap of CustomerID against their number of fraudulent transactions
        for (Transaction current : transactions){
            if (current.isFraud()){
                if(fraudulentCustomers.containsKey(current.getCustomerId())){
                    fraudulentCustomers.put(current.getCustomerId(),fraudulentCustomers.get(current.getCustomerId())+1);
                } else {
                    fraudulentCustomers.put(current.getCustomerId(),1);
                }
            }
        }


        Set<String> qualifyingCustomers = new HashSet<>();

        //Looping through the keySet of fraudulentCustomers HashMap
        // customerID's who's value is greater than numberOfFraudulentTransaction are added to qualifyingCustomers Set
        for (String s : fraudulentCustomers.keySet()){
            if (fraudulentCustomers.get(s)>=numberOfFraudulentTransactions){
                qualifyingCustomers.add(s);
            }
        }

        return qualifyingCustomers;
    }

    // task 9
    public Map<String, Integer> getCustomerIdToNumberOfTransactions() throws Exception {
        List<Transaction> transactions = readAllTransactions();
        HashMap<String, Integer> fraudulentCustomers = new HashMap<>();
        // Looping through transactions, looking for fraudulents
        // If fraudulent, added to HashMap
        // HashMap operates as a frequency table, storing the number of frauduluent transaction for each customerId
        for (Transaction current : transactions){
            if (current.isFraud()){
                if(fraudulentCustomers.containsKey(current.getCustomerId())){
                    fraudulentCustomers.put(current.getCustomerId(),fraudulentCustomers.get(current.getCustomerId())+1);
                } else {
                    fraudulentCustomers.put(current.getCustomerId(),1);
                }
            }
        }
        return fraudulentCustomers;
    }

    // task 10
    public Map<String, Integer> getMerchantIdToTotalAmountOfFraudulentTransactions() throws Exception {
        List<Transaction> transactions = readAllTransactions();
        HashMap<String, Integer> fraudulentMerchants = new HashMap<>();
        // Looping through transactions, looking for fraudulents
        // If fraudulent, added to HashMap
        // HashMap operates as a frequency table, storing the number of frauduluent transaction for each merchantId
        for (Transaction current : transactions){
            if (current.isFraud()){
                if(fraudulentMerchants.containsKey(current.getMerchantId())){
                    fraudulentMerchants.put(current.getMerchantId(),fraudulentMerchants.get(current.getMerchantId())+1);
                } else {
                    fraudulentMerchants.put(current.getMerchantId(),1);
                }
            }
        }
        return fraudulentMerchants;
    }

    //This method calculates the average transaction amount -- used for ML model.
    public Double averageTransactionAmt() throws Exception {
        List<Transaction> transactions = readAllTransactions();
        Double totalAmt = 0.00;
        int noOfTransactions = transactions.size();
        for (Transaction current : transactions){
            totalAmt = totalAmt + (Double)current.getAmount();
        }
        return totalAmt/noOfTransactions;
    }

    // This method adds total number of fraudulent transactions and average transaction amount as extra columns to a new CSV file, for use in ML model.
    public void addColumn(String path,String fileName) throws IOException{
        try {
            String avgTransactionAmt = String.valueOf(averageTransactionAmt());
            String totalNoOfFraudTransactions = String.valueOf(getTotalNumberOfFraudulentTransactions());

        BufferedReader br=null;
        BufferedWriter bw=null;
        final String lineSep=System.getProperty("line.separator");

        try {
            File file = new File(path, fileName);
            File file2 = new File(path+"/ML", fileName);//so the
            //names don't conflict or just use different folders

            br = new BufferedReader(new InputStreamReader(new FileInputStream(file))) ;
            bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file2)));
            String line = null;
            int i=0;
            for ( line = br.readLine(); line != null; line = br.readLine(),i++)
            {

                bw.write(line+ avgTransactionAmt +"," + totalNoOfFraudTransactions +lineSep);
            }


        }catch(Exception e){
            System.out.println(e);
        }finally  {
            if(br!=null)
                br.close();
            if(bw!=null)
                bw.close();
        }

        } catch(Exception e){
            System.err.print("Failed");

        }

    }
    // bonus
    public double getRiskOfFraudFigure(Transaction transaction) throws Exception {
        // See FraudProbability jupyter notebook in 'ML' folder.
        return 1.0;
    }
}
