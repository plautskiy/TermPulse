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
public class Terminal implements Serializable {
    private StringProperty id;
    private StringProperty number;
    private StringProperty type;
    private StringProperty model;
    private StringProperty sn;
    private StringProperty sn_scanner;
    private StringProperty condition;
    private StringProperty available;
    private StringProperty description;
    
    public Terminal (){
               
        this.id=new SimpleStringProperty();
        this.number=new SimpleStringProperty();
        this.type=new SimpleStringProperty();
        this.model=new SimpleStringProperty();
        this.sn=new SimpleStringProperty();
        this.sn_scanner=new SimpleStringProperty();
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
    
    public String getType(){
        return type.get();
    }
    public void setType(String type){
        this.type.set(type);
    }
    public StringProperty typeProperty(){
        return type;
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
    
    public String getSnScanner(){
        return sn_scanner.get();
    }
    public void setSnScanner(String snc){
        this.sn_scanner.set(snc);
    }
    public StringProperty sn_scannerProperty(){
        return sn_scanner;
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
        return "Terminal{ " + "id=" + id + ", number= " + number + ", type= "+type+"}";
    } 
    
}
