package repository;

import java.sql.Connection;
import java.sql.SQLException;

public interface BaseRepository {

     void showAll(Connection connection) throws SQLException;




}
