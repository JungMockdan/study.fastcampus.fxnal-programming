package study.fastcampus.fxnalprogramming.part5.model;

import java.util.List;
import java.util.Objects;
import java.util.function.Function;

public class User {
    private int id;
    private String name;

    public User() {
    }

    public User(int id, String name) {
        this.id = id;
        this.name = name;
    }


    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }


    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }


}
