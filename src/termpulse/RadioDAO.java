/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package termpulse;

import java.sql.SQLException;
import javafx.collections.ObservableList;
/**
 *
 * @author kroni
 */
public class RadioDAO {
    private static RtBaseDAO<Radio> rtDataObject = new RtBaseDAO<Radio>();
    
    public static int getAllRadiosNumber(){
        String selectStmt = "SELECT * FROM rt_radio";
        ObservableList<Radio> radioList = rtDataObject.getAll(selectStmt,
                rs -> {
                    Radio a_radio = new Radio();
                    return a_radio;
                });
        return radioList.size();
    }
    public static int getAvailableRadiosNumber(){
        String selectStmt = "SELECT * FROM rt_radio WHERE available = ?";
        ObservableList<Radio> radioList = rtDataObject.getAll(selectStmt,
                rs -> {
                    Radio a_radio = new Radio();
                    return a_radio;
                }, new Object[] { "да" });
        return radioList.size();
    }
    
    public static ObservableList<Radio> searchRadios() throws SQLException, ClassNotFoundException {
        String selectStmt = "SELECT * FROM rt_radio";
        ObservableList<Radio> radioList = rtDataObject.getAll(selectStmt,
                rs -> {
                    Radio a_radio = new Radio();
                    a_radio.setId(rs.getString("id"));
                    a_radio.setNumber(rs.getString("number"));
                    a_radio.setModel(rs.getString("model"));
                    a_radio.setSn(rs.getString("sn"));
                    a_radio.setCondition(rs.getString("cond"));
                    a_radio.setAvailable(rs.getString("available"));
                    a_radio.setDescription(rs.getString("description"));

                    return a_radio;
                });
        return radioList;
    }
    public static ObservableList<Radio> searchRadiobyAvailable(String available) throws SQLException, ClassNotFoundException {
        String selectStmt = "SELECT * FROM rt_radio WHERE AND available = ? ";
        ObservableList<Radio> radioList = rtDataObject.getAll(selectStmt,
                rs -> {
                    Radio a_radio = new Radio();
                    a_radio.setId(rs.getString("id"));
                    a_radio.setNumber(rs.getString("number"));
                    a_radio.setModel(rs.getString("model"));
                    a_radio.setSn(rs.getString("sn"));
                    a_radio.setCondition(rs.getString("cond"));
                    a_radio.setAvailable(rs.getString("available"));
                    a_radio.setDescription(rs.getString("description"));

                    return a_radio;
                }, new Object[] { available });
        return radioList;
    }
    public static ObservableList<Radio> searchRadiobyNumber(String number) throws SQLException, ClassNotFoundException {
        String selectStmt = "SELECT * FROM rt_radio WHERE number =? ";
        ObservableList<Radio> radioList = rtDataObject.getAll(selectStmt,
                rs -> {
                    Radio a_radio = new Radio();
                    a_radio.setId(rs.getString("id"));
                    a_radio.setNumber(rs.getString("number"));
                    a_radio.setModel(rs.getString("model"));
                    a_radio.setSn(rs.getString("sn"));
                    a_radio.setCondition(rs.getString("cond"));
                    a_radio.setAvailable(rs.getString("available"));
                    a_radio.setDescription(rs.getString("description"));

                    return a_radio;
                }, new Object[] { number });
        return radioList;
    }
    public static ObservableList<Radio> searchRadiobyId(String id) throws SQLException, ClassNotFoundException {
        String selectStmt = "SELECT * FROM rt_radio WHERE id =? ";
        ObservableList<Radio> radioList = rtDataObject.getAll(selectStmt,
                rs -> {
                    Radio a_radio = new Radio();
                    a_radio.setId(rs.getString("id"));
                    a_radio.setNumber(rs.getString("number"));
                    a_radio.setModel(rs.getString("model"));
                    a_radio.setSn(rs.getString("sn"));
                    a_radio.setCondition(rs.getString("cond"));
                    a_radio.setAvailable(rs.getString("available"));
                    a_radio.setDescription(rs.getString("description"));

                    return a_radio;
                }, new Object[] { id });
        return radioList;
    }
       
    public static void insertRadio(String id, String number, String model, String sn, String condition, String available, String description) throws SQLException, ClassNotFoundException {
        String updateStmt = "INSERT INTO rt_radio (id,number,  model, sn,  cond, available, description) VALUES (?,?,?,?,?,?,?)";
        rtDataObject.addOrSaveOrUpdate(updateStmt, new Object[]{
            id, number,  model, sn,  condition, available, description
        });
      
    }
    public static void removeRadio(String id) throws SQLException, ClassNotFoundException {
       String updateStmt = "DELETE FROM rt_radio WHERE id = ?";
        rtDataObject.delete(updateStmt, new Object[]{
            id
        });
    } 
    public static void updateRadio(String id, String number,  String model, String sn,  String cond, String available, String description)throws SQLException, ClassNotFoundException{
       String updateStmt ="UPDATE rt_radio SET number = ?,  model = ?,  sn = ?,  cond = ?, available = ?,  description = ?  WHERE id = ?";
        rtDataObject.addOrSaveOrUpdate(updateStmt, new Object[]{
            number,model,sn,cond,available,description,id
        });
    }
    public static void updateRadioAvailability(String id, String available)throws SQLException, ClassNotFoundException{
       String updateStmt ="UPDATE rt_radio SET available = ? WHERE id = ?";
        rtDataObject.addOrSaveOrUpdate(updateStmt, new Object[]{
            available,id
        });
    }
    
}
