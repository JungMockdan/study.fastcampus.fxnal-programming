package study.fastcampus.fxnalprogramming.util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


/*
*
* java.util.function 패키지에 있는 여러가지
* FunctionalInterface 에 대한 테스트
* */
@SpringBootTest
public class Chap04_FunctionalInterfaces_1_Test {
    @DisplayName(value = "Supplier라는 인터페이스를 이용한 함수를 구현해보는 테스트")
    @Test
    public void test_Supplier(){
        Supplier<String> mySupplier = () -> "hello world";
        String res = mySupplier.get();
        assertEquals("hello world",res);

        Supplier<Double> mySupplier2 = () -> Math.random();
        Double res2 = mySupplier2.get();
        assertEquals("hello world",res);
        boolean b = res2 instanceof Double;
        assertTrue(b);
        printRandomDoubles(mySupplier2,5);
    }

    /**
    * 아래 메소드의 경우, 파라미터로 함수형객체를 받는다.
     * 이로써 함수형객체가 1급시민으로 기능함을 확인할수 있다.
     * 아래 함수는 Double 타입의 숫자 생성에는 관여하지 않고,
     * 얼마든지 다른 방식으로 만들어진 수 -즉, 매개변수인 supplier를 받음으로 -
     * 를 출력할수있게 된다.
    * */
    public static void printRandomDoubles(Supplier<Double> supplier, int round){
        for (int i = 0; i < round; i++) {
            System.out.println(supplier.get());
        }
    }

    @DisplayName(value = "Consumer라는 인터페이스를 이용한 함수를 구현해보는 테스트")
    @Test
    public void test_Consumer(){// 매개변수는 있지만, 리턴이 없음.
        Consumer<String> consumer = str ->{
            System.out.println(str);
        };
        consumer.accept("#########################  >>  test");

        Consumer<Integer> processor1 = x-> System.out.println("processor1 : "+x);
        Consumer<Integer> processor2 = x-> System.out.println("___processor2 : "+x);
        List<Integer> inputs = Arrays.asList(1, 2, 3, 4);
        process(inputs,processor1);
        process(inputs,processor2);
        processGenericCunsumer(inputs,processor2);
        processGenericCunsumer(Arrays.asList("1.1", "2.1","test"),consumer);

    }

    public static void process(List<Integer> inputs, Consumer<Integer> processor ){
        for (Integer input:inputs) {
            processor.accept(input);
        }
    }

    public static <T> void processGenericCunsumer(List<T> inputs, Consumer<T> processor ){
        for (T input:inputs) {
            processor.accept(input);
        }
    }


    @DisplayName(value = "BiConsumer라는 인터페이스를 이용한 함수를 구현해보는 테스트")
    @Test
    public void test_BiConsumer() {// 매개변수2개, 리턴이 없음.
        BiConsumer<Integer,Double> biConsumer1 = (index,input)-> System.out.println("Processing input : "+input +" @ idx : "+index);
        List<Integer> input2 = Arrays.asList(0,1, 2, 3, 4);
        List<Double> inputs = Arrays.asList(0.6,1.1, 2.1, 3.4, 4.5);
        processGenericBiConsumer(inputs,biConsumer1);
    }

    public static <T> void processGenericBiConsumer(List<T> inputs, BiConsumer<Integer,T> processor ){
        for (int i = 0; i < inputs.size(); i++) {
            processor.accept(i,inputs.get(i));
        }
    }
    /*
    *
    * Predicate 의 return 은 boolean
    * */
    @DisplayName(value = "Predicate라는 인터페이스를 이용한 함수를 구현해보는 테스트")
    @Test
    public void test_Predicate() {
        Predicate<Integer> isPositive = x-> x>0;
        assertTrue(isPositive.test(10));
        assertTrue(!isPositive.test(-1));
        List<Integer> inputs = Arrays.asList(10,-2,3,0,-99);

        List<Integer> positiveFiltered = filter(inputs, isPositive);
        System.out.println("양수 = "+ positiveFiltered);
        assertEquals(2, positiveFiltered.size());

        /*Predicate 인터페이스의 default 함수 nagate를 이용한 함수를 구현해보는 테스트*/
        List<Integer> nePositiveFiltered = filter(inputs, isPositive.negate());
        System.out.println("양수는 아님 = "+ nePositiveFiltered);
        assertEquals(3, nePositiveFiltered.size());

        /*Predicate 인터페이스의 default 함수 or를 이용한 함수를 구현해보는 테스트*/
        List<Integer> zeroAndPositiveFiltered = filter(inputs, isPositive.or(x-> x==0));
        System.out.println("0과 양수 = "+ zeroAndPositiveFiltered);
        assertEquals(3, zeroAndPositiveFiltered.size());

        /*Predicate 인터페이스의 default 함수 and를 이용한 함수를 구현해보는 테스트*/
        List<Integer> positiveAndEvenFiltered = filter(inputs, isPositive.and(x-> x%2==0));
        System.out.println("짝수인 양수 = "+ positiveAndEvenFiltered);
        assertEquals(1, positiveAndEvenFiltered.size());
    }

    public static <T> List<T> filter(List<T> inputs, Predicate<T> condition) {
        List<T> output = new ArrayList<>();

        for (T input : inputs) {
            if (condition.test(input)) {
                output.add(input);
            }
        }
        return output;
    }
//그 밖에 java.util.funciton의 Interface
//    UnaryOperator<T> T가 들어와서 T가 리턴 되는것. ==Function<T,T>
//    BinaryOperator<T> 인풋2개 모두 T 리턴 T가 리턴 되는것. == BiFunction<T,T,T>
//    Primitive 타입만 다룰수 있는 인터페이스가 별도로 있다(메모리관리 차원 ) ex) IntConsumer, LongBinaryOperator, DoubleSupplier....etc


}
