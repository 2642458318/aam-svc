package fx.lambdasinaction.chap1;

import fx.lambdasinaction.entity.Apple;
import org.junit.Test;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class FilteringApples {
    @Test
    public static List<Apple> myTest1() {
        /**
         * 将对象数据类型的数组转成集合
         * 该集合不能变
         */
//        List<Apple> inventory = Arrays.asList(new Apple(80, "green"),
//                new Apple(155, "green"),
//                new Apple(120, "red"));
//        List<Apple> greenApples = filterApples(inventory, FilteringApples::isGreenApple);
//        List<Apple> heavyApples = filterApples(inventory, FilteringApples::isHeavyApple);

//        List<Apple> greenApples2 = filterApples(inventory, apple -> "green".equals(apple.getColor()));
//        List<Apple> heavApples2 = filterApples(inventory, apple -> apple.getWeight() > 150);
        List<Apple> inventory = new ArrayList<>();
        inventory.addAll(Arrays.asList(new Apple(80, "green"), new Apple(155, "green"), new Apple(120, "red")));
        inventory.sort(new Comparator<Apple>() {
            public int compare(Apple a1, Apple a2) {
                return (a1.getWeight().compareTo(a2.getWeight()));
            }
        });
        Comparator.comparingInt(Apple::getWeight).reversed();
        System.out.println(inventory);
        return inventory;
    }

    public static List<Apple> filterApples(List<Apple> inventory, Predicate<Apple> p) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
            if (p.test(apple)) {
                result.add(apple);
            }
        }
        return result;
    }

    public static boolean isGreenApple(Apple apple) {
        return "green".equals(apple.getColor());
    }

    public static boolean isHeavyApple(Apple apple) {
        return apple.getWeight() > 50;
    }

    public static void main(String[] args) {
        myTest1();
    }
}
