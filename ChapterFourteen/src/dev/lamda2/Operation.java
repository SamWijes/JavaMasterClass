package dev.lamda2;
@FunctionalInterface
public interface Operation<T> {

    T operate(T value1,T value2);
}
