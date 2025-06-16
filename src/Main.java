import java.util.Scanner;

// TODO: Auto-generated Javadoc
/**
 * The Class Main.
 */
public class Main {

	/** The scanner. */
	private static Scanner scanner = new Scanner(System.in);

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		BudgetManager manager = new BudgetManager("ficheroTransactions.csv");
		boolean running = true;

		while (running) {
			// Imprimir el menú
			menu();
			// Leer la opción del usuario
			String input = scanner.nextLine().trim();
			System.out.println();

			try {
				switch (input) {
				case "1":
					getTransaction(manager);
					break;

				case "2":
					// Mostrar balance
					System.out.printf("Balance actual: %.2f%n", manager.getBalance());
					break;

				case "3":
					// Generar reporte por categoría
					report(manager);
					break;

				case "4":
					// Cargar transacciones
					manager.loadTransactions();
					System.out.println("Transacciones cargadas desde el archivo.");
					break;

				case "5":
					// Guardar transacciones
					manager.saveTransactions();
					System.out.println("Transacciones guardadas en el archivo.");
					break;

				case "6":
					// Salir
					running = false;
					System.out.println("Gracias por usar la Calculadora de Gastos Personales.");
					break;

				default:
					System.out.println("Opción inválida. Por favor, seleccione una opción entre 1 y 6.");
				}
			} catch (NumberFormatException e) {
				System.out.println("Error: Ingrese un monto válido (ej. 50.50).");
			} catch (Exception e) {
				System.out.println("Error: " + e.getMessage());
			}
			System.out.println();
		}
		scanner.close();
	}

	/**
	 * Report.
	 *
	 * @param manager the manager
	 */
	private static void report(BudgetManager manager) {
		System.out.print("Ingrese la categoría para el reporte: ");
		String reportCategory = scanner.nextLine().trim();
		System.out.println("Reporte para la categoría: " + reportCategory);
		manager.getReportByCategory(reportCategory).forEach(System.out::println);
		if (manager.getReportByCategory(reportCategory).isEmpty()) {
			System.out.println("No se encontraron transacciones en esta categoría.");
		}
	}

	/**
	 * Get transaction.
	 *
	 * @param manager the manager
	 */
	private static void getTransaction(BudgetManager manager) {
		System.out.print("Ingrese el monto (ej. 50.50): ");
		double amount = Double.parseDouble(scanner.nextLine().trim());
		System.out.print("Ingrese la categoría (ej. Freelance): ");
		String category = scanner.nextLine().trim();
		System.out.print("Ingrese la fecha (dd-MM-yyyy, ej. 13-06-2025): ");
		String date = scanner.nextLine().trim();
		System.out.print("Ingrese la descripción (ej. Supermercado): ");
		String description = scanner.nextLine().trim();
		System.out.print("¿Es ingreso? (true/false): ");
		boolean isIncome = Boolean.parseBoolean(scanner.nextLine().trim());
		manager.addTransaction(amount, category, date, description, isIncome);
		System.out.println("Transacción añadida correctamente.");
	}

	/**
	 * Menu.
	 */
	private static void menu() {
		System.out.println("=== Calculadora de Gastos Personales ===");
		System.out.println("1. Añadir transacción");
		System.out.println("2. Mostrar balance");
		System.out.println("3. Generar reporte por categoría");
		System.out.println("4. Cargar transacciones desde archivo");
		System.out.println("5. Guardar transacciones en archivo");
		System.out.println("6. Salir");
		System.out.print("Seleccione una opción (1-6): ");
	}
}