package hu.elte.progtech.jdbc.mysql.examples.tetris.persistence.dao;

import hu.elte.progtech.jdbc.mysql.examples.tetris.persistence.entity.AbstractEntity;

import java.sql.SQLException;
import java.util.List;

public interface IEntity <E extends AbstractEntity> {

    List<E> getAll() throws SQLException;

    void add(E entity) throws SQLException;
}
