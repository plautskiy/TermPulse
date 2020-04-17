/*
 * Copyright (C) 2017 kroni
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package termpulse;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author kroni
 */
public class TermPulseEmplEditController implements Initializable {

    @FXML
    private Label idEmplLabel;
    @FXML
    private Label nameEmplLabel;
    @FXML
    private Label postEmplLabel;
    @FXML
    private Label currTermEmplLabel;
    @FXML
    private Label currRtsdEmplLabel;
    @FXML
    private Label currRadioEmplLabel;
    @FXML
    private TextField idEmplText;
    @FXML
    private TextField nameEmplText;
    @FXML
    private TextField postEmplText;
    @FXML
    private TextField currTermEmplText;
    @FXML
    private TextField currRtsdEmplText;
    @FXML
    private TextField currRadioEmplText;
    @FXML
    private Button btnEmplAdd;
    @FXML
    private Button btnEmplCancel;
    @FXML
    private Button btnEmplUpdate;

    private ObservableList<String> uniqueNamesData = FXCollections.observableArrayList();
    private ObservableList<String> uniqueIdData = FXCollections.observableArrayList();
    
    
     /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      
        this.uniqueNamesData=null;
        this.uniqueIdData=null;
        this.idEmplText.setText("");
        this.nameEmplText.setText("");
        this.postEmplText.setText("");
        this.currTermEmplText.setText("");
        this.currRtsdEmplText.setText("");
        this.currRadioEmplText.setText("");
        
    }
    
    public void setData(Employee currEmpl,ObservableList<String> uniqueIdData, ObservableList<String> uniqueNamesData){
        this.uniqueNamesData=uniqueNamesData;
        this.uniqueIdData=uniqueIdData;
        this.idEmplText.setText(currEmpl.getId());
        this.nameEmplText.setText(currEmpl.getName());
        this.postEmplText.setText(currEmpl.getPost());
        this.currTermEmplText.setText(currEmpl.getCurrTerm());
        this.currRtsdEmplText.setText(currEmpl.getCurrRtsd());
        this.currRadioEmplText.setText(currEmpl.getCurrRadio());

    }
 
    @FXML
    private void addEmployee(ActionEvent event) {
        int id_term;
        try {
            id_term = Integer.parseInt(idEmplText.getText());
        } catch (NumberFormatException e) {
            showAlert("Значение ID должно быть целочисленным!");
            idEmplText.requestFocus();
            return;
        }
        addEmployee();
        
    }
    
    public void showAlert(String msg) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Ошибка выполнения");
        alert.setHeaderText(msg.toString());
        alert.show();
    }
    
    private void addEmployee() {
        boolean isUniqueEmplEntry = true;
        if (uniqueNamesData.contains(nameEmplText.getText()) || uniqueIdData.contains(idEmplText.getText())) {
            isUniqueEmplEntry = false;
        }
        try {

            if (isUniqueEmplEntry) {
                try {
                    EmployeeDAO.insertEmployee(idEmplText.getText(), nameEmplText.getText(), postEmplText.getText());
                    TermPulse.getMainController().retrieveFromDB();
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(TermPulseEmplEditController.class.getName()).log(Level.SEVERE, null, ex);
                }
                ((Stage)(((TextField)idEmplText).getScene().getWindow())).close();
            } else {
                showAlert("Уже имеются записи с указанным ID или именем сотрудника");
                isUniqueEmplEntry = true;
            }

        } catch (SQLException e) {
            showAlert(e.toString());
        }

    }
  
    @FXML
    private void closeWindow(ActionEvent event) {
        ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();    
    }

    @FXML
    private void updateEmployee(ActionEvent event) {
        boolean isNotEmpyEmpEntry = true;
        if (idEmplText == null) {
            isNotEmpyEmpEntry = false;
        }
        
        if (idEmplText.getText().isEmpty() || nameEmplText.getText().isEmpty() || postEmplText.getText().isEmpty()) {
            isNotEmpyEmpEntry = false;
        }

        try {
            if (isNotEmpyEmpEntry) {
                try {
                    EmployeeDAO.updateEmployee(idEmplText.getText(), nameEmplText.getText(), postEmplText.getText(), currTermEmplText.getText(), currRtsdEmplText.getText(),currRadioEmplText.getText());
                    TermPulse.getMainController().retrieveFromDB();
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(TermPulseEmplEditController.class.getName()).log(Level.SEVERE, null, ex);
                }
                ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
            } else {
                showAlert("Необходимо заполнить все поля!");
            }
        } catch (SQLException e) {
            showAlert(e.toString());
        }
        
    }

    @FXML
    private void conditionTextClear(MouseEvent event) {
    }
      
  
    
}
