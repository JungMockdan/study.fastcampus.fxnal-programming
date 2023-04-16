package study.fastcampus.fxnalprogramming.util;

@FunctionalInterface
public interface CustomTriFuction<T,U,V,R> {
    R apply(T t,U u, V v);
}
