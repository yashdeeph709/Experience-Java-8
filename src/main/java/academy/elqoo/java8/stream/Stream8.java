package academy.elqoo.java8.stream;


import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import static java.util.stream.Collectors.*;

import java.util.Arrays;
import java.util.Comparator;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Stream8 {

    public static List<Integer> returnSquareRoot(List<Integer> numbers){
    	return numbers.stream().map(Math::sqrt).map(Double::intValue).collect(toList());
    }

    public static List<Integer> getAgeFromUsers(List<User> user){
    	return user.stream().map(u-> u.getAge()).collect(toList());
    }

    public static List<Integer> getDistinctAges(List<User> users){
    	return users.stream().map(user->user.getAge()).distinct().collect(toList());
    }

    public static List<User> getLimitedUserList(List<User> users, int limit){
    	return users.stream().limit(limit).collect(toList());
    }

    public static Integer countUsersOlderThen25(List<User> users){
    	return (int) users.stream().filter(user->user.getAge()>25).count();
    }

    public static List<String> mapToUpperCase(List<String> strings){
    	return strings.stream().map(String::toUpperCase).collect(toList());
    }

    public static Integer sum(List<Integer> integers){
    	return integers.stream().reduce((a,b)->a+b).get();
    }

    public static List<Integer> skip(List<Integer> integers, Integer toSkip){
    	return integers.stream().skip(toSkip).collect(toList());
    }

    public static List<String> getFirstNames(List<String> names){
    	return names.stream().map(name -> name.split("")[0]).collect(toList());
    }

    public static List<String> getDistinctLetters(List<String> names){
    	return names.stream().map(name -> name.split("")).flatMap(Arrays::stream).distinct().collect(toList());
    }


    public static String separateNamesByComma(List<User> users){
    	return users.stream().map(user->user.getName()).reduce((first,second)-> first+", "+second).get();
    }

    public static double getAverageAge(List<User> users){
    	return users.stream().map(user->user.getAge()).collect(Collectors.averagingInt(age -> age));
    }

    public static Integer getMaxAge(List<User> users){
    	return users.stream().map(user->user.getAge()).collect(Collectors.maxBy((a,b)->Integer.compare(a, b))).get();
    }

    public static Integer getMinAge(List<User> users) {
    	return users.stream().map(user->user.getAge()).collect(Collectors.minBy((a,b)->Integer.compare(a, b))).get();
    }

    public static Map<Boolean, List<User>> partionUsersByGender(List<User> users){
    	return users.stream().collect(Collectors.partitioningBy(User::isMale));
    }

    public static Map<Integer, List<User>> groupByAge(List<User> users){
    	return users.stream().collect(Collectors.groupingBy(User::getAge));
    }

    public static Map<Boolean, Map<Integer, List<User>>> groupByGenderAndAge(List<User> users){
    	return users.stream().collect(Collectors.groupingBy(User::isMale,groupingBy(User::getAge)));
    }

    public static Map<Boolean, Long> countGender(List<User> users){
    	return users.stream().collect(Collectors.groupingBy(User::isMale,counting()));
    }

    public static boolean anyMatch(List<User> users, int age){
    	return users.stream().anyMatch(user->user.getAge()==age);
    }

    public static boolean noneMatch(List<User> users, int age){
    	return users.stream().noneMatch(user->user.getAge()==age);
    }

    public static Optional<User> findAny(List<User> users, String name){
    	return users.stream().filter(user-> user.getName().equals(name)).findAny();
    }

    public static List<User> sortByAge(List<User> users){
    	return users.stream().sorted((user1,user2)->Integer.compare(user1.getAge(), user2.getAge())).collect(toList());
    }

    public static Stream<Integer> getBoxedStream(IntStream stream){
    	return stream.boxed();
    }

    public static List<Integer> generateFirst10PrimeNumbers(){
    	return Stream.iterate(2,i->i+1).filter(Stream8::isPrime).limit(10).collect(toList());
    }

    public static boolean isPrime(int number) {
        return IntStream.rangeClosed(2, number/2).noneMatch(i -> number%i == 0);
    }

    public static List<Integer> generate10RandomNumbers(){
    	return Stream.generate(() -> new Random().nextInt()).limit(10).collect(toList());
    }

    public static User findOldest(List<User> users){
    	return users.stream().max(Comparator.comparing(User::getAge)).get();
    }

    public static int sumAge(List<User> users){
    	return users.stream().collect(Collectors.summingInt(user->user.getAge()));
    }

    public static IntSummaryStatistics ageSummaryStatistics(List<User> users){
    	return users.stream().mapToInt(user->user.getAge()).summaryStatistics();
    }

}
