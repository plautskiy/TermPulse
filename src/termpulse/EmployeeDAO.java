/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package termpulse;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import javafx.collections.ObservableList;
/**
 *
 * @author kroni
 */
public class EmployeeDAO {
    private static  RtBaseDAO<Employee> rtDataObject = new RtBaseDAO<Employee>();
    
    public static ObservableList<Employee> searchEmployees() throws SQLException, ClassNotFoundException {
        //Declare a SELECT statement
        String selectStmt = "SELECT * FROM rt_users";
   
        ObservableList<Employee> employeeList = rtDataObject.getAll(selectStmt,
                rs -> {
                    Employee empl = new Employee();
                    empl.setId(rs.getString("id"));
                    empl.setName(rs.getString("name"));
                    empl.setPost(rs.getString("position"));
                    empl.setCurrTerm(rs.getString("curr_term"));
                    empl.setCurrRadio(rs.getString("curr_radio"));
                    empl.setCurrRtsd(rs.getString("curr_rtsd"));

                    return empl;
                });
        return employeeList;
    }
    
    public static ObservableList<Employee> searchEmployeeById(String id) throws SQLException, ClassNotFoundException {
        //Declare a SELECT statement
        String selectStmt = "SELECT * FROM rt_users WHERE id = ?";
   
        ObservableList<Employee> employeeList = rtDataObject.getAll(selectStmt,
                rs -> {
                    Employee empl = new Employee();
                    empl.setId(rs.getString("id"));
                    empl.setName(rs.getString("name"));
                    empl.setPost(rs.getString("position"));
                    empl.setCurrTerm(rs.getString("curr_term"));
                    empl.setCurrRadio(rs.getString("curr_radio"));
                    empl.setCurrRtsd(rs.getString("curr_rtsd"));

                    return empl;
                } , new Object[] { id });
        return employeeList;
    }
    
    public static ObservableList<Employee> searchEmployeeByName(String name) throws SQLException, ClassNotFoundException {
        //Declare a SELECT statement
        String selectStmt = "SELECT * FROM rt_users WHERE name = ?";
   
        ObservableList<Employee> employeeList = rtDataObject.getAll(selectStmt,
                rs -> {
                    Employee empl = new Employee();
                    empl.setId(rs.getString("id"));
                    empl.setName(rs.getString("name"));
                    empl.setPost(rs.getString("position"));
                    empl.setCurrTerm(rs.getString("curr_term"));
                    empl.setCurrRadio(rs.getString("curr_radio"));
                    empl.setCurrRtsd(rs.getString("curr_rtsd"));

                    return empl;
                } , new Object[] { name });
        return employeeList;
    }
   
    public static void insertEmployee(String id, String name, String post) throws SQLException, ClassNotFoundException {

        String insertStmt = "INSERT INTO rt_users (id, name, position) VALUES (?, ?, ?)";
        rtDataObject.addOrSaveOrUpdate(insertStmt, new Object[]{
            id, name, post
        });
    }

    public static void removeEmployee(String id) throws SQLException, ClassNotFoundException {
        String updateStmt = "DELETE FROM rt_users WHERE id = ?";
        rtDataObject.delete(updateStmt, new Object[]{
            id
        });
    }
    public static void updateEmployee(String id, String name, String post) throws SQLException, ClassNotFoundException {
       
        String updateStmt = "UPDATE rt_users  SET  name = ?, position = ? WHERE id = ?";
        rtDataObject.addOrSaveOrUpdate(updateStmt, new Object[]{
            name, post,id
        });
    }
    
    public static void updateEmployee(String id, String name, String post, String termNumber, String rtsdNumber, String radioNumber) throws SQLException, ClassNotFoundException {
       
        String updateStmt = "UPDATE rt_users  SET  name = ?, position = ?, curr_term = ?, curr_rtsd = ?, curr_radio = ?  WHERE id = ?";
        rtDataObject.addOrSaveOrUpdate(updateStmt, new Object[]{
            name, post,termNumber, rtsdNumber, radioNumber, id
        });
    }
    
    public static void updateEmployeeRadio(String id, String dev_number) throws SQLException, ClassNotFoundException {
       
        String updateStmt = "UPDATE rt_users  SET  curr_radio = ? WHERE id = ?";
        rtDataObject.addOrSaveOrUpdate(updateStmt, new Object[]{
            dev_number,id
        });
    }
    
    public static void updateEmployeeTerminal(String id, String dev_number) throws SQLException, ClassNotFoundException {
       
        String updateStmt = "UPDATE rt_users  SET  curr_term = ? WHERE id = ?";
        rtDataObject.addOrSaveOrUpdate(updateStmt, new Object[]{
            dev_number,id
        });
    }
    public static void updateEmployeeRtsd(String id, String dev_number) throws SQLException, ClassNotFoundException {
       
        String updateStmt = "UPDATE rt_users  SET  curr_rtsd = ? WHERE id = ?";
        rtDataObject.addOrSaveOrUpdate(updateStmt, new Object[]{
            dev_number,id
        });
    }
   
}
