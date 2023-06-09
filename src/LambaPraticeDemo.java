
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class LambaPraticeDemo {

	public static void main(String[] args) {
		Member member = new Member("ho");
		fingName(member, a -> a.toString());

		List<Cat> catList = new ArrayList();

		Cat catA = new Cat("Tom");
		catA.setAge(11);
		catList.add(catA);
		Cat catB = new Cat("B");
		catB.setAge(9);
		catList.add(catB);
		Cat catC = new Cat("C");
		catC.setAge(8);
		catList.add(catC);

		/******* Predicate 判斷，return boolean ********/
		Predicate<Cat> predicateCat = cat -> cat.getAge() > 10;
		System.out.println(predicateCat.test(catB));

		/******* Consumer 操作物件，no return ********/
		Consumer<Cat> consumerCat = cat -> cat.setAge(12);
		consumerCat.accept(catB);
		System.out.println(catB.getAge());

		/******* Function 操作物件， return 的型態依照泛型的第二參數 ********/
		Function<Cat, Integer> getCatAfter10Y = cat -> cat.getAge() + 10;
		System.out.println(getCatAfter10Y.apply(catA));

		/******* Supplier ， return 物件 ********/
		Supplier<Cat> newCat = () -> new Cat("CatC");
		System.out.println(newCat.get().getName());

		/******* stream & map ， 轉換stream的內容 ex1 ********/
		catList.forEach(cat -> System.out.print(cat.getName()));
		List<String> age10CatList = catList.stream().filter(cat -> cat.getAge() < 10).map(Cat::getName)
				.collect(Collectors.toList());
		age10CatList.forEach(name -> System.out.println(name));

		/******* stream & map ， 轉換stream的內容 ex2 ********/
		System.out.println("/******* stream & map ， 轉換stream的內容 ex2 ********/");
		Set<Integer> adultAges = new HashSet();
		adultAges.add(9);
		adultAges.add(11);
		List<String> adultCatList = catList.stream().peek(cat -> System.out.println(cat.getName()))
				.filter(cat -> adultAges.contains(cat.getAge())).peek(cat -> System.out.println(cat.getName()))
				.map(cat -> cat.getName()).collect(Collectors.toList());
		System.out.println("/******* stream & map ， 轉換stream的內容 ex2 ********/");
		adultCatList.forEach(System.out::print);

		/******* stream & peek ， 觀察stream運作時的內容 ********/
		catList.stream().peek(cat -> System.out.println(cat.getName())).filter(cat -> cat.getAge() > 10)
				.peek(cat -> System.out.println(cat.getName())).forEach(cat -> System.out.println(cat.getAge()));

		/******* stream & collectors & toMap ， 依照設定的key value值組成map ********/
		Map<String, Integer> catMap = catList.stream().collect(Collectors.toMap(Cat::getName, Cat::getAge));
		System.out.println(catMap);

		/******* stream & Collectors.averagingDouble ， 將集合中的特定屬性計算平均數並為Double ********/
		Double avgCatAge = catList.stream().collect(Collectors.averagingDouble(cat -> cat.getAge()));
		System.out.println(avgCatAge);

	}

	public static void fingName(Member member, LambaInterfaceDemo lambaInterfaceDemo) {
		String a = member.getName(member);
		System.out.println(lambaInterfaceDemo.getStringName(a));
	}
}

class Member {
	String name;

	public Member(String name) {
		this.name = name;
	}

	public String getName(Member member) {
		return member.name;
	}

}
