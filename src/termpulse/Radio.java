/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package termpulse;

import javafx.beans.property.*;
import java.io.Serializable;

/**
 *
 * @author kroni
 */
public class Radio implements Serializable {
    private StringProperty id;
    private StringProperty number;
    private StringProperty model;
    private StringProperty sn;
    private StringProperty condition;
    private StringProperty available;
    private StringProperty description;
    
    public Radio (){
               
        this.id=new SimpleStringProperty();
        this.number=new SimpleStringProperty();
        this.model=new SimpleStringProperty();
        this.sn=new SimpleStringProperty();
        this.condition=new SimpleStringProperty();
        this.available=new SimpleStringProperty();
        this.description=new SimpleStringProperty();
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
    
    public String getNumber(){
        return number.get();
    }
    public void setNumber(String number){
        this.number.set(number);
    }
    public StringProperty numberProperty(){
        return number;
    }
    
    public String getModel(){
        return model.get();
    }
    public void setModel(String model){
        this.model.set(model);
    }
    public StringProperty modelProperty(){
        return model;
    }
    
    public String getSn(){
        return sn.get();
    }
    public void setSn(String sn){
        this.sn.set(sn);
    }
    public StringProperty snProperty(){
        return sn;
    }
   
    public String getCondition(){
        return condition.get();
    }
    public void setCondition(String condition){
        this.condition.set(condition);
    }
    public StringProperty conditionProperty(){
        return condition;
    }
    
    public String getAvailable(){
        return available.get();
    }
    public void setAvailable(String available){
        this.available.set(available);
    }
    public StringProperty availableProperty(){
        return available;
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
    
    public String toString() {
        return "Рация " + "id=" + id + ", number= " + number +"}";
    } 
    
}
