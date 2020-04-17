/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package termpulse;

import javafx.beans.property.*;
import java.time.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 *
 * @author kroni
 */
public class JournalEntry implements Serializable {
    private StringProperty id;
    //private StringProperty dt;
    private ObjectProperty<Timestamp> dt;
    //private Timestamp dt;
    private StringProperty operation_type;
    private StringProperty employee;
    private StringProperty operation_status;
    private StringProperty device;
    private StringProperty device_number;
    private ObjectProperty<Timestamp> operation_begin;
    private ObjectProperty<Timestamp> operation_end;
    private StringProperty description;
    private StringProperty employee_id;
    private StringProperty terminal_id;
    private StringProperty radio_id;
    
    
    public JournalEntry (){
               
        this.id=new SimpleStringProperty();
        this.dt = new SimpleObjectProperty<>();
        this.operation_type=new SimpleStringProperty();
        this.employee=new SimpleStringProperty();
        this.operation_status=new SimpleStringProperty();
        this.device=new SimpleStringProperty();
        this.device_number=new SimpleStringProperty();
        this.operation_begin=new SimpleObjectProperty<>();
        this.operation_end=new SimpleObjectProperty<>();
        this.description=new SimpleStringProperty();
        this.employee_id=new SimpleStringProperty();
        this.terminal_id=new SimpleStringProperty();
        this.radio_id=new SimpleStringProperty();
        
    }
    
    public String getId(){
        return id.get();
    }
    public void setId(String id){
        this.id.set(id);
    }
    public StringProperty idProperty(){
        return id;
    }
    
    public Timestamp getDt(){
        //DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        return dt.get();
    }
    public void setDt(Timestamp dt){
        //DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        this.dt.set(dt);
    }
    public ObjectProperty<Timestamp> dtProperty(){
        return dt;
    }
         
    public String getOperationType(){
        return operation_type.get();
    }
    public void setOperationType(String type){
        this.operation_type.set(type);
    }
    public StringProperty operationTypeProperty(){
        return operation_type;
    }
    
    public String getEmployee(){
        return employee.get();
    }
    public void setEmployee(String empl){
        this.employee.set(empl);
    }
    public StringProperty employeeProperty(){
        return employee;
    }
    
    public String getOperationStatus(){
        return operation_status.get();
    }
    public void setOperationStatus(String status){
        this.operation_status.set(status);
    }
    public StringProperty operationStatusProperty(){
        return operation_status;
    }
    
    public String getDevice(){
        return device.get();
    }
    public void setDevice(String dev){
        this.device.set(dev);
    }
    public StringProperty deviceProperty(){
        return device;
    }
    
    public String getDeviceNumber(){
        return device_number.get();
    }
    public void setDeviceNumber(String number){
        this.device_number.set(number);
    }
    public StringProperty deviceNumberProperty(){
        return device_number;
    }
    
    public Timestamp getOperationEnd(){
        return operation_end.get();
    }
    public void setOperationEnd(Timestamp end){
        this.operation_end.set(end);
    }
    public ObjectProperty<Timestamp> operationEndProperty(){
        return operation_end;
    }
    
    public Timestamp getOperationBegin(){
        return operation_begin.get();
    }
    public void setOperationBegin(Timestamp begin){
        this.operation_begin.set(begin);
    }
    public ObjectProperty<Timestamp> operationBeginProperty(){
        return operation_begin;
    }
       
    public String getDescription(){
        return description.get();
    }
    public void setDescription(String desc){
        this.description.set(desc);
    }
    public StringProperty descriptionProperty(){
        return description;
    }
    
    public String getTerminalId(){
        return terminal_id.get();
    }
    public void setTerminalId(String id){
        this.terminal_id.set(id);
    }
    public StringProperty terminalIdProperty(){
        return terminal_id;
    }
    
    public String getEmployeeId(){
        return employee_id.get();
    }
    public void setEmployeeId(String id){
        this.employee_id.set(id);
    }
    public StringProperty employeeIdProperty(){
        return employee_id;
    }
    
    public String getRadioId(){
        return radio_id.get();
    }
    public void setRadioId(String id){
        this.radio_id.set(id);
    }
    public StringProperty radioIdProperty(){
        return radio_id;
    }
    
    public String toString() {
        return "Запись в журнале{ " + "id=" + id + ", number= " + device_number + ", status= "+operation_status+"}";
    } 
    
}
