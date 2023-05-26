package study.fastcampus.fxnalprogramming.util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import study.fastcampus.fxnalprogramming.part5.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.function.Predicate;

import static org.junit.jupiter.api.Assertions.*;

/* Method Reference
 * - 기존에 이미 선되어있는 메서드를 "지정"하고 싶을 때.
 * - "::" 오퍼레이터 사용
 * - 생략이 많기 때문에 사용할 메서드의 매개변수의 타입과 리턴 타입을 미리 숙지해야 함.
 * case
 * 1. 클래스의 static method를 지정할 때
 * 2. 선언 된 객체의 insetance method를 지정할 때
 * 3. 객체의 instance method를 지정할 때.
 * 4. 클래스의 constructor를 지정할 때.
 * */
@SpringBootTest
public class Chap5SectionTest {

    @Test
    @DisplayName(value = "section 1, 2")
    public void section1() {
        //Section1
        int a = Integer.parseInt("5");

        Function<String, Integer> str2Int = Integer::parseInt;// case 1 : 클래스의 스태택 메서드를 지정한 경우
        Integer apply = str2Int.apply("20");
        assertEquals(20, apply);

        String str = "world";
        boolean b = str.equals("world");
        Predicate<String> equals2Hello = str::equals;// case 2 : 이미 선언된 객체 여기서는 str 에 instance method를 지정한 경우임.
        assertTrue(equals2Hello.test("world"));

        //Section2
        Function<String, Integer> strLen = String::length;
        int len = strLen.apply("hello world");
        assertEquals(11, len);

        BiPredicate<String, String> strEquals = String::equals;
        boolean isEqual = strEquals.test("hello", "world");
        assertFalse(isEqual);
        isEqual = strEquals.test("hello", "hello");
        assertTrue(isEqual);

        List<User> users = new ArrayList<>();
        users.add(new User(5, "Alice"));
        users.add(new User(3, "Charlie"));
        users.add(new User(2, "Bob"));

        printUsersField(users,User::getName);
        calc(8,2, (x,y)->x+y);// 두수를 더하는 함수를 넘김.
        calc(8,2, Chap5SectionTest::multiply); //case1
        Chap5SectionTest t = new Chap5SectionTest();
        calc(8,2, t::subtract);//case2

        //case 2  이미 선언된 객체 여기서는 str 에 instance method를 지정한 또다른 방법

    }

    private static int multiply(int x, int y) {
        return x*y;
    }
    private int subtract(int x, int y){
        return x-y;
    }

    public static int calc(int x, int y, BiFunction<Integer,Integer,Integer> operator) {
        return operator.apply(x,y);
    }

    /*test helper 함수*/
    public static void printUsersField(List<User> users, Function<User, Object> getter) {
        for (User user : users) {
            System.out.println(getter.apply(user));
        }
    }


    @Test
    @DisplayName(value = "")
    public void section3() {

    }
}
