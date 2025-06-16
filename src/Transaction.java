// TODO: Auto-generated Javadoc

import java.util.Objects;

// TODO: Auto-generated Javadoc
/**
 * The Class Transaction.
 */
public class Transaction {

	/** The amount. */
	private double amount;

	/** The category. */
	private String category;

	/** The date. */
	private String date;

	/** The description. */
	private String description;

	/** The is income. */
	private boolean isIncome;

	/**
	 * Instantiates a new transaction.
	 *
	 * @param amount      the amount
	 * @param category    the category
	 * @param date        the date
	 * @param description the description
	 * @param isIncome    the is income
	 */
	public Transaction(double amount, String category, String date, String description, boolean isIncome) {
		this.amount = amount;
		this.category = category;
		this.date = date;
		this.description = description;
		this.isIncome = isIncome;
	}

	/**
	 * Gets the amount.
	 *
	 * @return the amount
	 */
	public double getAmount() {
		return amount;
	}

	/**
	 * Gets the category.
	 *
	 * @return the category
	 */
	public String getCategory() {
		return category;
	}

	/**
	 * Gets the date.
	 *
	 * @return the date
	 */
	public String getDate() {
		return date;
	}

	/**
	 * Gets the description.
	 *
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Checks if is income.
	 *
	 * @return true, if is income
	 */
	public boolean isIncome() {
		return isIncome;
	}

	/**
	 * Equals.
	 *
	 * @param o the o
	 * @return true, if successful
	 */
	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Transaction that = (Transaction) o;
		return Double.compare(that.amount, amount) == 0 && isIncome == that.isIncome
				&& Objects.equals(category, that.category) && Objects.equals(date, that.date)
				&& Objects.equals(description, that.description);
	}

	/**
	 * Hash code.
	 *
	 * @return the int
	 */
	@Override
	public int hashCode() {
		return Objects.hash(amount, category, date, description, isIncome);
	}

	/**
	 * To string.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		return "Transaction [Amount = " + amount + ", Category = " + category + ", Date = " + date + ", Description = "
				+ description + "]";
	}
}
