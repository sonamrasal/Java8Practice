import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class TestJava8Methods {
	public static void main(String[] args) {
		Trader raoul = new Trader("Raoul", "Cambridge");
		Trader mario = new Trader("Mario", "Milan");
		Trader alan = new Trader("Alan", "Cambridge");
		Trader brian = new Trader("Brian", "Cambridge");
		
		List<Transaction> transactions = Arrays.asList(
				new Transaction(brian, 2011, 300), 
				new Transaction(raoul, 2012, 1000),
				new Transaction(raoul, 2011, 400), 
				new Transaction(mario, 2012, 710), 
				new Transaction(mario, 2012, 700),
				new Transaction(alan, 2012, 950));
		
		//Find all transactions in the year 2011 and sort them by value (small to high).
		transactions.stream()
			.filter(transaction -> 2011 == transaction.getYear())
			.sorted((t1, t2) -> t1.getValue() - t2.getValue())
			.forEach(System.out::println);
		
		//What are all the unique cities where the traders work?
		transactions.stream()
			.map(transaction -> transaction.getTrader())
			.map(trader -> trader.getCity())
			.distinct()
			.forEach(System.out::println);
		
		//Find all traders from Cambridge and sort them by name
		transactions.stream()
			.map(transaction -> transaction.getTrader())
			.filter(trader -> "Cambridge".equals(trader.getCity()))
			.sorted((trader1, trader2) -> trader1.getName().compareTo(trader2.getName()))
			.distinct()
			.forEach(System.out::println);
		
		//Return a string of all traders’ names sorted alphabetically
		System.out.println(transactions.stream()
			.map(transaction -> transaction.getTrader())
			.map(trader -> trader.getName())
			.distinct()
			.sorted((name1, name2) -> name1.compareTo(name2))
			.collect(Collectors.joining(",")));
		
		//Are any traders based in Milan
		boolean anyTraderBasedInMilan = transactions.stream()
			.map(transaction -> transaction.getTrader())
			.anyMatch(trader -> "Milan".equals(trader.getCity()));
		System.out.println("Any trader based in Milan? " + (anyTraderBasedInMilan ? "Yes" : "No"));
		
		//Print all transactions’ values from the traders living in Cambridge
		transactions.stream()
			.filter(transaction -> "Cambridge".equals(transaction.getTrader().getCity()))
			.forEach(transaction -> System.out.println(transaction.getValue()));
		
		//What’s the highest value of all the transactions
		System.out.println(transactions.stream()
			.map(transaction -> transaction.getValue())
			.reduce(0, (v1, v2) -> v1 > v2 ? v1 : v2));
		
		//Find the transaction with the smallest value.
		System.out.println(transactions.stream()
				.reduce((t1, t2) -> t1.getValue() > t2.getValue() ? t2 : t1));
	}
}
