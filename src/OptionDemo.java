
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class OptionDemo {

	public static void main(String[] args) {

		Optional<String> optionalResult = getName("Tom");
		System.out.println(optionalResult.isEmpty());
		System.out.println(optionalResult.orElseGet(() -> new Cat("Cat").getName()));
		/*
		 * false TomName
		 */

		Optional<String> optionalResult2 = getName("To");
		System.out.println(optionalResult2.isEmpty());
		System.out.println(optionalResult2.orElseGet(() -> new Cat("Cat").getName()));
		/*
		 * true Cat
		 */
	}

	static Optional<String> getName(String target) {
		Map<String, String> nameDB = new HashMap<String, String>();
		nameDB.put("Tom", "TomName");
		return nameDB.containsKey(target) ? Optional.of(nameDB.get(target)) : Optional.empty();
	};

}
