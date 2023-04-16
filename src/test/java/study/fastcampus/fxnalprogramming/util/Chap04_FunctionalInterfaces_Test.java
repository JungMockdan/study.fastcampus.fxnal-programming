package study.fastcampus.fxnalprogramming.util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class Chap04_FunctionalInterfaces_Test {
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
        processGeneric(inputs,processor2);
        processGeneric(Arrays.asList("1.1", "2.1","test"),consumer);

    }

    public static void process(List<Integer> inputs, Consumer<Integer> processor ){
        for (Integer input:inputs) {
            processor.accept(input);
        }
    }

    public static <T> void processGeneric(List<T> inputs, Consumer<T> processor ){
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
        processGeneric2(inputs,biConsumer1);
    }

    public static <T> void processGeneric2(List<T> inputs, BiConsumer<Integer,T> processor ){
        for (int i = 0; i < inputs.size(); i++) {
            processor.accept(i,inputs.get(i));
        }
    }
}
