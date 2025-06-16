import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


// TODO: Auto-generated Javadoc
/**
 * The Class BudgetManager.
 */
public class BudgetManager {
	
	/** The transactions. */
	private List<Transaction> transactions;
	
	/** The fh. */
	private FileHandler fh;

	/**
	 * Instantiates a new budget manager.
	 *
	 * @param filePath the file path
	 */
	public BudgetManager(String filePath) {
		this.transactions= new ArrayList<Transaction>();
		this.fh = new FileHandler(filePath);
	}
	/**
	 * Adds the transaction.
	 *
	 * @param amount the amount
	 * @param category the category
	 * @param date the date
	 * @param description the description
	 * @param isIncome the is income
	 */
	public void addTransaction(double amount, String category, String date, String description, boolean isIncome) {
		transactions.add(new Transaction(amount, category, date, description, isIncome));		
	}
	
	/**
	 * Gets the balance.
	 *
	 * @return the balance
	 */
	public double getBalance() {
		double amount = transactions.stream().filter(transaction -> transaction.isIncome()).mapToDouble(Transaction::getAmount).sum();
		double income = transactions.stream().filter(transaction -> !transaction.isIncome()).mapToDouble(Transaction::getAmount).sum();
		return amount - income;
	}

	/**
	 * Gets the report by category.
	 *
	 * @param category the category
	 * @return the report by category
	 */
	public List<Transaction> getReportByCategory(String category) {
		return transactions.stream().filter(data -> data.getCategory().equalsIgnoreCase(category)).collect(Collectors.toList());			
	}
	
	/**
	 * Load transactions.
	 */
	public void loadTransactions() {
        Set<Transaction> uniqueTransactions = new HashSet<>(transactions);
        uniqueTransactions.addAll(fh.readTransactions());
        transactions.clear();
        transactions.addAll(uniqueTransactions);
    }

    /**
     * Save transactions.
     */
    public void saveTransactions() {
        Set<Transaction> uniqueTransactions = new HashSet<>(transactions);
        uniqueTransactions.addAll(fh.readTransactions());
        fh.writeTransactions(new ArrayList<>(uniqueTransactions));
    }
}
