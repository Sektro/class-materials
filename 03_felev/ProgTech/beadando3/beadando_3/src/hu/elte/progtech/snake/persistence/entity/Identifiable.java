package hu.elte.progtech.snake.persistence.entity;

public interface Identifiable <T extends Number> {

    T getId();

    void setId(T id);
}