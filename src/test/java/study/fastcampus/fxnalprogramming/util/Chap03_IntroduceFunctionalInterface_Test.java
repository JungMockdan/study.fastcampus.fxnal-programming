package study.fastcampus.fxnalprogramming.util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.function.BiFunction;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.*;
@DisplayName(value = "함수형 프로그래밍 소개, 첫 수업")
@SpringBootTest
class Chap03_IntroduceFunctionalInterface_Test {

    @Test
    public void adderTest() {
        Function<Integer,Integer> myAdder = new Adder();
        int result = myAdder.apply(5);
        assertEquals(15,result);
    }

    @DisplayName(value = "함수만들때마다 Function인터페이스를 구현하는 번거로움은 람다익스프레션으로 해결이 되기도 한다.")
    @Test
    public void adderTest2() {
//        anonymous function
        Function<Integer,Integer> myAdder = (Integer x)->{return x+10;};
        int result = myAdder.apply(5);
        assertEquals(15,result);

        // 좀더 단순화 가능한 람다함수
        //유추가능할때, 생략가능, 인풋타임 1개일때 생략가능, 한줄코드 {} 생략가능
        Function<Integer,Integer> simpleAdder = x-> x+10;
        result = simpleAdder.apply(1);
        assertEquals(11,result);
    }
    @DisplayName(value = "파라미터가 2개인 함수")
    @Test
    public void testBiFunctionAdder() {
        BiFunction<Integer,Integer,Integer> adder = ( x,  y)->x+y;
        int result = adder.apply(5,3);
        assertEquals(8,result);
    }
    @DisplayName(value = "파라미터가 3개 이상이어서 커스텀한 함수.")
    @Test
    public void testTriFunctionAdder() {
        CustomTriFuction<Integer,Integer,Integer,Integer> adder = (x, y, z) -> x+y+z;
        int result = adder.apply(5,3,2);
        assertEquals(10,result);
    }

    /** 정리
     * Funtional Interface
     *  단 하나의 abstract method를 가진 interface
     * Lambda Expression
     *  함수형 인터페이스르 구현하는 가장 간단한 방법
     *
     * */


}