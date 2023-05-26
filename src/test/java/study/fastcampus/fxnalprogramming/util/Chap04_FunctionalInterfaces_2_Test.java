package study.fastcampus.fxnalprogramming.util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import study.fastcampus.fxnalprogramming.part4.model.User;

import java.util.*;

/*
*
* java.util 패키지에 있는 Comparator 라는
* FunctionalInterface 에 대한 테스트
* */
@SpringBootTest
public class Chap04_FunctionalInterfaces_2_Test {

    @DisplayName(value = "Consumer라는 인터페이스를 이용한 함수를 구현해보는 테스트")
    @Test
    public void test_Comparator(){// 매개변수는 있지만, 리턴이 없음.
        List<User> userList = new ArrayList<>();
        userList.add(new User(5,"Alice"));
        userList.add(new User(2,"Charlie"));
        userList.add(new User(3,"Bob"));
        System.out.println(userList);
        Comparator<User> idComparator =(User u1, User u2) ->u1.getId() - u2.getId();
        Collections.sort(userList,idComparator);
        System.out.println("after sorting by id : "+userList);
        Collections.sort(userList,(User u1, User u2)->u1.getName().compareTo(u2.getName()));
        System.out.println("after sorting by alphabetic: "+userList);
    }
}
