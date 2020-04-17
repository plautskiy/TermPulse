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
public class Employee implements Serializable {
    private StringProperty id;
    private StringProperty name;
    private StringProperty post;
    private StringProperty curr_term;
    private StringProperty curr_rtsd;
    private StringProperty curr_radio;
    
    public Employee (){
        this.id=new SimpleStringProperty();
        this.name=new SimpleStringProperty();
        this.post=new SimpleStringProperty();
        this.curr_term=new SimpleStringProperty();
        this.curr_rtsd=new SimpleStringProperty();
        this.curr_radio=new SimpleStringProperty();
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
    
    public String getName(){
        return name.get();
    }
    public void setName(String name){
        this.name.set(name);
    }
    public StringProperty nameProperty(){
        return name;
    }
    
    public String getPost(){
        return post.get();
    }
    public void setPost(String post){
        this.post.set(post);
    }
    public StringProperty postProperty(){
        return post;
    }
    
    public String getCurrTerm(){
        return curr_term.get();
    }
    public void setCurrTerm(String term){
        this.curr_term.set(term);
    }
    public StringProperty currTermProperty(){
        return curr_term;
    }
    
    public String getCurrRtsd(){
        return curr_rtsd.get();
    }
    public void setCurrRtsd(String term){
        this.curr_rtsd.set(term);
    }
    public StringProperty currRtsdProperty(){
        return curr_rtsd;
    }
    
    public String getCurrRadio(){
        return curr_radio.get();
    }
    public void setCurrRadio(String radio){
        this.curr_radio.set(radio);
    }
    public StringProperty currRadioProperty(){
        return curr_radio;
    }
    
    public String toString() {
        return "Employee{ " + "id=" + id + ", name= " + name + ", post= "+post+"}";
    }    
}
