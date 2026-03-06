package hu.elte.progtech.snake.persistence.dao;

import hu.elte.progtech.snake.persistence.entity.AbstractEntity;

import java.sql.SQLException;
import java.util.List;

public interface IEntity <E extends AbstractEntity> {

    List<E> getAll() throws SQLException;

    void add(E entity) throws SQLException;
}
