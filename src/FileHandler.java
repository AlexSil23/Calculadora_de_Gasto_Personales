import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

// TODO: Auto-generated Javadoc
/**
 * The Class FileHandler.
 */
public class FileHandler {
	
	/** The file path. */
	private String filePath;

	/**
	 * Instantiates a new file handler.
	 *
	 * @param filePath the file path
	 */
	public FileHandler(String filePath) {
		this.filePath = filePath;
	}

	/**
	 * Write transactions.
	 *
	 * @param transactions the transactions
	 */
	public void writeTransactions(List<Transaction> transactions) {
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
			for (Transaction transaction : transactions) {
				bw.append(transactionToString(transaction));
				bw.newLine();
			}
		} catch (IOException e) {
			System.err.println("Error al escribir en el archivo: " + e.getMessage());
		}
	}

	/**
	 * Read transactions.
	 *
	 * @return the list
	 */
	public List<Transaction> readTransactions() {
		List<Transaction> transactions = new ArrayList<>();
		try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
			String line;
			int lineNumber = 0;
			while ((line = br.readLine()) != null) {
				lineNumber++;
				try {
					Transaction transaction = parseTransaction(line);
					if (transaction != null) {
						transactions.add(transaction);
					}
				} catch (IllegalArgumentException e) {
					System.err.println("Línea inválida en el archivo (línea " + lineNumber + "): " + line + ". Error: "
							+ e.getMessage());
				}
			}
		} catch (IOException e) {
			System.err.println("Error al leer el archivo: " + e.getMessage());
		}
		return transactions;
	}

	/**
	 * Transaction to string.
	 *
	 * @param transaction the transaction
	 * @return the string
	 */
	private String transactionToString(Transaction transaction) {
		return String.format(Locale.US, "%.2f,%s,%s,%s,%b", transaction.getAmount(), transaction.getCategory(),
				transaction.getDate(), transaction.getDescription(), transaction.isIncome());
	}

	/**
	 * Parses the transaction.
	 *
	 * @param line the line
	 * @return the transaction
	 */
	private Transaction parseTransaction(String line) {
		if (line == null || line.trim().isEmpty()) {
			return null;
		}
		String[] parts = line.split(",", -1);
		if (parts.length != 5) {
			throw new IllegalArgumentException(
					"Formato de línea incorrecto: se esperaban 5 campos, se encontraron " + parts.length);
		}
		try {
			NumberFormat numberFormat = NumberFormat.getNumberInstance(Locale.US);
			double amount = numberFormat.parse(parts[0].trim()).doubleValue();
			String category = parts[1].trim();
			String date = parts[2].trim();
			String description = parts[3].trim();
			boolean isIncome = Boolean.parseBoolean(parts[4].trim());
			return new Transaction(amount, category, date, description, isIncome);
		} catch (ParseException e) {
			throw new IllegalArgumentException(
					"Error al parsear el monto: " + parts[0] + ". Debe ser un número válido (ej. 100,00 o 100.00)");
		} catch (Exception e) {
			throw new IllegalArgumentException("Error al parsear la línea: " + e.getMessage());
		}
	}
}