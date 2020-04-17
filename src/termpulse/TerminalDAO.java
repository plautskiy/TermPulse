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
public class TerminalDAO {
    private static RtBaseDAO<Terminal> rtDataObject = new RtBaseDAO<Terminal>();
    private static RtBaseDAO<String> rtDataTermTypes = new RtBaseDAO<String>();
    
    public static ObservableList<Terminal> searchTerminals() throws SQLException, ClassNotFoundException {
        String selectStmt = "SELECT * FROM rt_base";
        ObservableList<Terminal> terminalList = rtDataObject.getAll(selectStmt,
                rs -> {
                    Terminal term = new Terminal();
                    term.setId(rs.getString("id"));
                    term.setNumber(rs.getString("number"));
                    term.setType(rs.getString("type"));
                    term.setModel(rs.getString("model"));
                    term.setSn(rs.getString("sn"));
                    term.setSnScanner(rs.getString("sn_scanner"));
                    term.setCondition(rs.getString("cond"));
                    term.setAvailable(rs.getString("available"));
                    term.setDescription(rs.getString("description"));

                    return term;
                });
        return terminalList;
    }
    public static ObservableList<Terminal> searchTerminalbyAvailable(String id, String available) throws SQLException, ClassNotFoundException {
        String selectStmt = "SELECT * FROM rt_base WHERE id = ? AND available = ? ";
        ObservableList<Terminal> terminalList = rtDataObject.getAll(selectStmt,
                rs -> {
                    Terminal term = new Terminal();
                    term.setId(rs.getString("id"));
                    term.setNumber(rs.getString("number"));
                    term.setType(rs.getString("type"));
                    term.setModel(rs.getString("model"));
                    term.setSn(rs.getString("sn"));
                    term.setSnScanner(rs.getString("sn_scanner"));
                    term.setCondition(rs.getString("cond"));
                    term.setAvailable(rs.getString("available"));
                    term.setDescription(rs.getString("description"));

                    return term;
                }, new Object[] { id,available });
        return terminalList;
    }
   
    public static int getAvailableTermNumber(String isAvailable){
        String selectStmt = "SELECT * FROM rt_base WHERE available = ? AND type = ?";
        ObservableList<Terminal> terminalList = rtDataObject.getAll(selectStmt,
                rs -> {
                    Terminal term = new Terminal();
                 return term;
                }, new Object[] { isAvailable, "сборка_конвейер" });
        
        return terminalList.size();
    }
    
    public static int getAllTermNumber(){
        String selectStmt = "SELECT * FROM rt_base WHERE type = ?";
        ObservableList<Terminal> terminalList = rtDataObject.getAll(selectStmt,
                rs -> {
                    Terminal term = new Terminal();
                    return term;
                }, new Object[] { "сборка_конвейер" });
        
        return terminalList.size();
    }
    
    public static int getAvailableRtsdNumber(String isAvailable){
        String selectStmt = "SELECT * FROM rt_base WHERE available = ? AND (type = ? OR type = ?)";
        ObservableList<Terminal> terminalList = rtDataObject.getAll(selectStmt,
                rs -> {
                    Terminal term = new Terminal();
                    return term;
                }, new Object[] {isAvailable, "подпитка","приемка" });
        
        return terminalList.size();
    }
    
    public static int getAllRtsdNumber(){
        String selectStmt = "SELECT * FROM rt_base WHERE type = ? OR type = ?";
        
        ObservableList<Terminal> terminalList = rtDataObject.getAll(selectStmt,
                rs -> {
                    Terminal term = new Terminal();
                    return term;
                }, new Object[] { "подпитка","приемка" });
        
        return terminalList.size();
    }
    
    public static ObservableList<Terminal> searchTerminalbyNumber(String number) throws SQLException, ClassNotFoundException {
        String selectStmt = "SELECT * FROM rt_base WHERE number =? ";
        ObservableList<Terminal> terminalList = rtDataObject.getAll(selectStmt,
                rs -> {
                    Terminal term = new Terminal();
                    term.setId(rs.getString("id"));
                    term.setNumber(rs.getString("number"));
                    term.setType(rs.getString("type"));
                    term.setModel(rs.getString("model"));
                    term.setSn(rs.getString("sn"));
                    term.setSnScanner(rs.getString("sn_scanner"));
                    term.setCondition(rs.getString("cond"));
                    term.setAvailable(rs.getString("available"));
                    term.setDescription(rs.getString("description"));

                    return term;
                }, new Object[] { number });
        return terminalList;
    }
    public static ObservableList<Terminal> searchTerminalbyId(String id) throws SQLException, ClassNotFoundException {
        String selectStmt = "SELECT * FROM rt_base WHERE id =? ";
        ObservableList<Terminal> terminalList = rtDataObject.getAll(selectStmt,
                rs -> {
                    Terminal term = new Terminal();
                    term.setId(rs.getString("id"));
                    term.setNumber(rs.getString("number"));
                    term.setType(rs.getString("type"));
                    term.setModel(rs.getString("model"));
                    term.setSn(rs.getString("sn"));
                    term.setSnScanner(rs.getString("sn_scanner"));
                    term.setCondition(rs.getString("cond"));
                    term.setAvailable(rs.getString("available"));
                    term.setDescription(rs.getString("description"));

                    return term;
                }, new Object[] { id });
        return terminalList;
    }
    public static ObservableList<String> searchTerminalTypes() throws SQLException, ClassNotFoundException {
        String selectStmt = "SELECT DISTINCT type FROM rt_base";
        ObservableList<String> termTypesList = rtDataTermTypes.getAll(selectStmt,
                rs -> {
                 return rs.getString("type");
                });
        return termTypesList;
    }
   
    public static void insertRT(String id,String number, String type, String model, String sn, String sn_scanner,String condition, String available, String description) throws SQLException, ClassNotFoundException {
        String updateStmt = "INSERT INTO rt_base (id,number, type, model, sn, sn_scanner, cond, available, description) VALUES (?,?,?,?,?,?,?,?,?)";
        rtDataObject.addOrSaveOrUpdate(updateStmt, new Object[]{
            id,number, type, model, sn, sn_scanner, condition, available, description
        });
      
    }
    public static void removeRT(String id) throws SQLException, ClassNotFoundException {
       String updateStmt = "DELETE FROM rt_base WHERE id = ?";
        rtDataObject.delete(updateStmt, new Object[]{
            id
        });
    } 
    public static void updateRT(String id, String number, String type, String model, String sn, String sn_scan, String cond, String available, String description)throws SQLException, ClassNotFoundException{
       String updateStmt ="UPDATE rt_base SET  number = ?,  type = ?, model = ?,  sn = ?, sn_scanner = ?, cond = ?, available = ?,  description = ?  where id = ?";
        rtDataObject.addOrSaveOrUpdate(updateStmt, new Object[]{
            number,type,model,sn,sn_scan,cond,available,description,id
        });
    }
    public static void updateRTAvailability(String id,  String available)throws SQLException, ClassNotFoundException{
       String updateStmt ="UPDATE rt_base SET available = ? where id = ?";
        rtDataObject.addOrSaveOrUpdate(updateStmt, new Object[]{
            available,id
        });
    }
        
}
