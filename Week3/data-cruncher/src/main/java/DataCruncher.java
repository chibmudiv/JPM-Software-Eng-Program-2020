import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.*;

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
        for (Transaction current : transactions ){
            if (current.isFraud()){
                count++;
            }
        }

        return count;
    }

    // task 3
    public long getTotalNumberOfTransactions(boolean isFraud) throws Exception {
        return 0;
    }

    // task 4
    public Set<Transaction> getFraudulentTransactionsForMerchantId(String merchantId) throws Exception {
        return Set.of();
    }

    // task 5
    public Set<Transaction> getTransactionsForMerchantId(String merchantId, boolean isFraud) throws Exception {
        return Set.of();
    }

    // task 6
    public List<Transaction> getAllTransactionsSortedByAmount() throws Exception {
        return List.of();
    }

    // task 7
    public double getFraudPercentageForMen() throws Exception {
        return 0.0;
    }

    // task 8
    public Set<Transaction> getCustomerIdsWithNumberOfFraudulentTransactions(int numberOfFraudulentTransactions) throws Exception {
        return Set.of();
    }

    // task 9
    public Map<String, Integer> getCustomerIdToNumberOfTransactions() throws Exception {
        return Map.of();
    }

    // task 10
    public Map<String, Double> getMerchantIdToTotalAmountOfFraudulentTransactions() throws Exception {
        return Map.of();
    }

    // bonus
    public double getRiskOfFraudFigure(Transaction transaction) throws Exception {
        return 1.0;
    }
}
