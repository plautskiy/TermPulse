/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package termpulse;
public enum TermPulseTableName {
    RT_BASE("терминалы"),
    RT_USERS("сотрудники"),
    RT_LOG("журнал"),
    RT_RADIO("рации"),
    EMP_PICKER("комплектовщиик"),
    EMP_BOXPICKER("сборщик коробочной зоны"),
    EMP_MERCHANT("товаровед"),
    EMP_TEO("комплектовщик тэо"),
    TERM_PICK("сборка_конвейер"),
    TERM_PICK_TEO("сборка_тэо"),
    TERM_FEED("подпитка"),
    TERM_RECEIVE("приемка"),
    TERM_MERCHANT("учет");
  
    private final String tableName;

    TermPulseTableName(String tableName) {
        this.tableName = tableName;
    }

    public String toString() {
        return tableName;
    }
}