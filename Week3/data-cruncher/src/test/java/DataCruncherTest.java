import org.junit.Test;
import java.util.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import static org.junit.Assert.assertTrue;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class DataCruncherTest {
    private final DataCruncher dataCruncher = new DataCruncher();

    // ignore
    @Test
    public void readAllTransactions() throws Exception {
        var transactions = dataCruncher.readAllTransactions();
        assertEquals(594643, transactions.size());
    }

    // example
    @Test
    public void readAllTransactionsAge0() throws Exception {
        var transactions = dataCruncher.readAllTransactionsAge0();
        assertEquals(3630, transactions.size());
    }

    // task1
    @Test
    public void getUniqueMerchantIds() throws Exception {
        var transactions = dataCruncher.getUniqueMerchantIds();
        assertEquals(50, transactions.size());
    }

    // task2
    @Test
    public void getTotalNumberOfFraudulentTransactions() throws Exception {
        var totalNumberOfFraudulentTransactions = dataCruncher.getTotalNumberOfFraudulentTransactions();
        assertEquals(297508, totalNumberOfFraudulentTransactions);
    }

    // task3
    @Test
    public void getTotalNumberOfTransactions() throws Exception {
        assertEquals(297508, dataCruncher.getTotalNumberOfTransactions(true));
        assertEquals(297135, dataCruncher.getTotalNumberOfTransactions(false));
    }

    // task4
    @Test
    public void getFraudulentTransactionsForMerchantId() throws Exception {
        Set<Transaction> fraudulentTransactionsForMerchantId = dataCruncher.getFraudulentTransactionsForMerchantId("M1823072687");
        assertEquals(149001, fraudulentTransactionsForMerchantId.size());
    }

    // task5
    @Test
    public void getTransactionForMerchantId() throws Exception {
        assertEquals(102588, dataCruncher.getTransactionsForMerchantId("M348934600", true).size());
        assertEquals(102140, dataCruncher.getTransactionsForMerchantId("M348934600", false).size());
    }

    // task6
    @Test
    public void getAllTransactionSortedByAmount() throws Exception {
        List<Transaction> allTransactionsSortedByAmount = dataCruncher.getAllTransactionsSortedByAmount();
        List<Transaction> sortedList = new ArrayList<>(allTransactionsSortedByAmount);
        sortedList.sort(Comparator.comparing(Transaction::getAmount));
        assertEquals("List is not sorted", sortedList, allTransactionsSortedByAmount);
    }

    // task7
    @Test
    public void getFraudPercentageForMen() throws Exception {
        double fraudPercentageForMen = dataCruncher.getFraudPercentageForMen();
        assertEquals(0.45, fraudPercentageForMen, 0.01);
    }

    // task8
    @Test
    public void getCustomerIdsWithNumberOfFraudulentTransactions() throws Exception {
        Set<String> customerIdsWithNumberOfFraudulentTransactions = dataCruncher.getCustomerIdsWithNumberOfFraudulentTransactions(3);
        assertEquals(4110, customerIdsWithNumberOfFraudulentTransactions.size());
    }

    // task9
    @Test
    public void getCustomerIdToNumberOfTransactions() throws Exception {
        Map<String, Integer> customerIdToNumberOfTransactions = dataCruncher.getCustomerIdToNumberOfTransactions();
        assertTrue(customerIdToNumberOfTransactions.containsKey("C1093826151") && customerIdToNumberOfTransactions.get("C1093826151") == 89);
        assertTrue(customerIdToNumberOfTransactions.containsKey("C352968107") && customerIdToNumberOfTransactions.get("C352968107") == 76);
        assertTrue(customerIdToNumberOfTransactions.containsKey("C2054744914") && customerIdToNumberOfTransactions.get("C2054744914") == 41);
    }

    // task10
    @Test
    public void getMerchantIdToTotalAmountOfFraudulentTransactions() throws Exception {
        Map<String, Integer> merchantIdToTotalAmountOfFraudulentTransactions = dataCruncher.getMerchantIdToTotalAmountOfFraudulentTransactions();
        assertTrue(merchantIdToTotalAmountOfFraudulentTransactions.containsKey("M348934600") && merchantIdToTotalAmountOfFraudulentTransactions.get("M348934600")==102947);
        assertTrue(merchantIdToTotalAmountOfFraudulentTransactions.containsKey("M1823072687") && merchantIdToTotalAmountOfFraudulentTransactions.get("M348934600")==149652);
        assertTrue(merchantIdToTotalAmountOfFraudulentTransactions.containsKey("M50039827") && merchantIdToTotalAmountOfFraudulentTransactions.get("M348934600")==439);
    }

    @Test
    public void getAverageTransactionAmt() throws Exception {
        Double avgAmt = dataCruncher.averageTransactionAmt();
        //check that totaltransactions is being calculated properly. assertEquals
        dataCruncher.addColumn("src/main/resources", "payments.csv");
    }

}