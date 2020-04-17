/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package termpulse;

import java.sql.SQLException;
import java.sql.Timestamp;
import javafx.collections.ObservableList;
/**
 *
 * @author kroni
 */
public class JournalEntryDAO {
    private static RtBaseDAO<JournalEntry> rtDataObject = new RtBaseDAO<JournalEntry>();
    
    public static ObservableList<JournalEntry> searchAllEntries() throws SQLException, ClassNotFoundException {
        String selectStmt = "SELECT * FROM rt_log";
        ObservableList<JournalEntry> entryList = rtDataObject.getAll(selectStmt,
                rs -> {
                    JournalEntry entry = new JournalEntry();
                    entry.setId(rs.getString("id"));
                    entry.setDt(rs.getTimestamp("dt"));
                    entry.setOperationType(rs.getString("operation_type"));
                    entry.setEmployee(rs.getString("employee"));
                    entry.setOperationStatus(rs.getString("operation_status"));
                    entry.setDevice(rs.getString("device"));
                    entry.setDeviceNumber(rs.getString("device_number"));
                    entry.setOperationBegin(rs.getTimestamp("operation_begin"));
                    entry.setOperationEnd(rs.getTimestamp("operation_end"));
                    entry.setDescription(rs.getString("description"));
                    entry.setTerminalId(rs.getString("terminal_id"));
                    entry.setEmployeeId(rs.getString("employee_id"));
                    entry.setRadioId(rs.getString("radio_id"));

                    return entry;
                });
        return entryList;
    }

    public static ObservableList<JournalEntry> searchEntriesByPeriod(Timestamp begin, Timestamp end) throws SQLException, ClassNotFoundException {
        String selectStmt = "SELECT * FROM rt_log WHERE dt BETWEEN ? AND ?";
        ObservableList<JournalEntry> entryList = rtDataObject.getAll(selectStmt,
                rs -> {
                    JournalEntry entry = new JournalEntry();
                    entry.setId(rs.getString("id"));
                    entry.setDt(rs.getTimestamp("dt"));
                    entry.setOperationType(rs.getString("operation_type"));
                    entry.setEmployee(rs.getString("employee"));
                    entry.setOperationStatus(rs.getString("operation_status"));
                    entry.setDevice(rs.getString("device"));
                    entry.setDeviceNumber(rs.getString("device_number"));
                    entry.setOperationBegin(rs.getTimestamp("operation_begin"));
                    entry.setOperationEnd(rs.getTimestamp("operation_end"));
                    entry.setDescription(rs.getString("description"));
                    entry.setTerminalId(rs.getString("terminal_id"));
                    entry.setEmployeeId(rs.getString("employee_id"));
                    entry.setRadioId(rs.getString("radio_id"));

                    return entry;
                }, new Object[] { begin,end });
        return entryList;
    }
    
    public static ObservableList<JournalEntry> searchBeginEntriesByPeriod(Timestamp begin, Timestamp end, String op_status) throws SQLException, ClassNotFoundException {
        String selectStmt = "SELECT * FROM rt_log WHERE  operation_status = ? AND dt BETWEEN ? AND ? ";
        ObservableList<JournalEntry> entryList = rtDataObject.getAll(selectStmt,
                rs -> {
                    JournalEntry entry = new JournalEntry();
                    entry.setId(rs.getString("id"));
                    entry.setDt(rs.getTimestamp("dt"));
                    entry.setOperationType(rs.getString("operation_type"));
                    entry.setEmployee(rs.getString("employee"));
                    entry.setOperationStatus(rs.getString("operation_status"));
                    entry.setDevice(rs.getString("device"));
                    entry.setDeviceNumber(rs.getString("device_number"));
                    entry.setOperationBegin(rs.getTimestamp("operation_begin"));
                    entry.setOperationEnd(rs.getTimestamp("operation_end"));
                    entry.setDescription(rs.getString("description"));
                    entry.setTerminalId(rs.getString("terminal_id"));
                    entry.setEmployeeId(rs.getString("employee_id"));
                    entry.setRadioId(rs.getString("radio_id"));

                    return entry;
                }, new Object[] { op_status,begin,end });
        return entryList;
    }
    
    public static ObservableList<JournalEntry> searchAllEntriesByDevice(Timestamp begin, Timestamp end, String device_number) throws SQLException, ClassNotFoundException {
        String selectStmt = "SELECT * FROM rt_log WHERE  device_number = ? AND dt BETWEEN ? AND ? ";
        ObservableList<JournalEntry> entryList = rtDataObject.getAll(selectStmt,
                rs -> {
                    JournalEntry entry = new JournalEntry();
                    entry.setId(rs.getString("id"));
                    entry.setDt(rs.getTimestamp("dt"));
                    entry.setOperationType(rs.getString("operation_type"));
                    entry.setEmployee(rs.getString("employee"));
                    entry.setOperationStatus(rs.getString("operation_status"));
                    entry.setDevice(rs.getString("device"));
                    entry.setDeviceNumber(rs.getString("device_number"));
                    entry.setOperationBegin(rs.getTimestamp("operation_begin"));
                    entry.setOperationEnd(rs.getTimestamp("operation_end"));
                    entry.setDescription(rs.getString("description"));
                    entry.setTerminalId(rs.getString("terminal_id"));
                    entry.setEmployeeId(rs.getString("employee_id"));
                    entry.setRadioId(rs.getString("radio_id"));

                    return entry;
                }, new Object[] { device_number,begin,end });
        return entryList;
    }
    
    public static ObservableList<JournalEntry> searchAllEntriesByEmployee(Timestamp begin, Timestamp end, String employee) throws SQLException, ClassNotFoundException {
        String selectStmt = "SELECT * FROM rt_log WHERE  employee = ? AND dt BETWEEN ? AND ? ";
        ObservableList<JournalEntry> entryList = rtDataObject.getAll(selectStmt,
                rs -> {
                    JournalEntry entry = new JournalEntry();
                    entry.setId(rs.getString("id"));
                    entry.setDt(rs.getTimestamp("dt"));
                    entry.setOperationType(rs.getString("operation_type"));
                    entry.setEmployee(rs.getString("employee"));
                    entry.setOperationStatus(rs.getString("operation_status"));
                    entry.setDevice(rs.getString("device"));
                    entry.setDeviceNumber(rs.getString("device_number"));
                    entry.setOperationBegin(rs.getTimestamp("operation_begin"));
                    entry.setOperationEnd(rs.getTimestamp("operation_end"));
                    entry.setDescription(rs.getString("description"));
                    entry.setTerminalId(rs.getString("terminal_id"));
                    entry.setEmployeeId(rs.getString("employee_id"));
                    entry.setRadioId(rs.getString("radio_id"));

                    return entry;
                }, new Object[] { employee,begin,end });
        return entryList;
    }
    
    public static ObservableList<JournalEntry> searchTakenDeviceByEmp(String emp, String status, String device_number) throws SQLException, ClassNotFoundException {
        String selectStmt = "SELECT * FROM rt_log WHERE employee= ? AND operation_status = ? AND device_number = ?";
        ObservableList<JournalEntry> entryList = rtDataObject.getAll(selectStmt,
                rs -> {
                    JournalEntry entry = new JournalEntry();
                    entry.setId(rs.getString("id"));
                    entry.setDt(rs.getTimestamp("dt"));
                    entry.setOperationType(rs.getString("operation_type"));
                    entry.setEmployee(rs.getString("employee"));
                    entry.setOperationStatus(rs.getString("operation_status"));
                    entry.setDevice(rs.getString("device"));
                    entry.setDeviceNumber(rs.getString("device_number"));
                    entry.setOperationBegin(rs.getTimestamp("operation_begin"));
                    entry.setOperationEnd(rs.getTimestamp("operation_end"));
                    entry.setDescription(rs.getString("description"));
                    entry.setTerminalId(rs.getString("terminal_id"));
                    entry.setEmployeeId(rs.getString("employee_id"));
                    entry.setRadioId(rs.getString("radio_id"));

                    return entry;
                }, new Object[] { emp,status,device_number });
        return entryList;
    }

    public static void insertEntry(Timestamp dt,String operation_type, String employee, String operation_status, String device, String device_number,Timestamp op_begin, String description, String employee_id, String terminal_id, String radio_id) throws SQLException, ClassNotFoundException {
        String updateStmt = "INSERT INTO rt_log (dt, operation_type, employee, operation_status, device, device_number, operation_begin, description, employee_id, terminal_id, radio_id) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
        rtDataObject.addOrSaveOrUpdate(updateStmt, new Object[]{
           dt,operation_type, employee, operation_status, device, device_number,op_begin, description, employee_id, terminal_id, radio_id
        });
      
    }
    
    public static void removeEntry(String id) throws SQLException, ClassNotFoundException {
       String updateStmt = "DELETE FROM rt_log WHERE id = ?";
        rtDataObject.delete(updateStmt, new Object[]{
            id
        });
    } 

    public static void updateEntry(String id,String operation_type, String employee, String operation_status, String device, String device_number,Timestamp operation_end, String description) throws SQLException, ClassNotFoundException {
        String updateStmt = "UPDATE rt_log SET  operation_type = ?, employee = ?, operation_status = ?, device = ?, device_number = ?, operation_end = ?, description = ? WHERE id = ?";
        rtDataObject.addOrSaveOrUpdate(updateStmt, new Object[]{
           operation_type, employee, operation_status, device, device_number,operation_end, description,id
        });
    }
    
    public static void updateEntry(String description,String id) throws SQLException, ClassNotFoundException {
        String updateStmt = "UPDATE rt_log SET  description = ? WHERE id = ?";
        rtDataObject.addOrSaveOrUpdate(updateStmt, new Object[]{
            description,id
        });
    }
    
    public static void closeOperationEntry(String id, String operation_status, Timestamp operation_end ) throws SQLException, ClassNotFoundException {
        String updateStmt = "UPDATE rt_log SET operation_status = ?, operation_end = ? WHERE id = ?";
        rtDataObject.addOrSaveOrUpdate(updateStmt, new Object[]{
            operation_status, operation_end, id
        });
    }
    
}
