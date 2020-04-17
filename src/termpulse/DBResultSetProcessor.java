/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package termpulse;

import java.sql.ResultSet;
import java.sql.SQLException;

//функциональный интерфейс
public interface DBResultSetProcessor<T> {
    T getProcessedObject(ResultSet rs) throws SQLException;
}