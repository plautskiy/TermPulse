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
public class TermPulseRadioEditController implements Initializable {

    @FXML
    private Label idRadioLabel;
    @FXML
    private Label numberRadioLabel;
    @FXML
    private Label modelRadioLabel;
    @FXML
    private Label snRadioLabel;
    @FXML
    private TextField idRadioText;
    @FXML
    private TextField numberRadioText;
    @FXML
    private TextField snRadioText;
    @FXML
    private TextField modelRadioText;
    @FXML
    private Button btnRadioAdd;
    @FXML
    private Button btnRadioCancel;
    @FXML
    private Button btnRadioUpdate;
    @FXML
    private Label conditionRadioLabel;
    @FXML
    private Label availableRadioLabel;
    @FXML
    private Label descriptionRadioLabel;
    @FXML
    private TextField conditionRadioText;
    @FXML
    private TextField availableRadioText;
    @FXML
    private TextField descriptionRadioText;
    @FXML
    private Button btnRadioFindByNumber;
    @FXML
    private ImageView imgSearch;
    @FXML
    private Button btnRadioFindBySerial;

    private ObservableList<String> uniqueNumbersData = FXCollections.observableArrayList();
    private ObservableList<String> uniqueIdData = FXCollections.observableArrayList();
    private ObservableList<String> uniqueSnNumbersData = FXCollections.observableArrayList();
     
     /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        this.uniqueNumbersData=null;
        this.uniqueIdData=null;
        this.uniqueSnNumbersData=null;
        this.idRadioText.setText("");
        this.modelRadioText.setText("");
        this.numberRadioText.setText("");
        this.descriptionRadioText.setText("");
        this.availableRadioText.setText("");
        this.conditionRadioText.setText("");
        this.snRadioText.setText("");
    }
    
    public void setData(Radio currRadio,ObservableList<String> uniqueIdData, ObservableList<String> uniqueNumbersData,ObservableList<String> uniqueSnNumbersData){
        this.uniqueNumbersData=uniqueNumbersData;
        this.uniqueIdData=uniqueIdData;
        this.uniqueSnNumbersData=uniqueSnNumbersData;
        this.idRadioText.setText(currRadio.getId());
        this.modelRadioText.setText(currRadio.getModel());
        this.numberRadioText.setText(currRadio.getNumber());
        this.descriptionRadioText.setText(currRadio.getDescription());
        this.availableRadioText.setText(currRadio.getAvailable());
        this.conditionRadioText.setText(currRadio.getCondition());
        this.snRadioText.setText(currRadio.getSn());
    }
 
    @FXML
    private void addRadio(ActionEvent event) {
        int id_radio;
        try {
            id_radio = Integer.parseInt(idRadioText.getText());
        } catch (NumberFormatException e) {
            showAlert("Значение ID должно быть целочисленным!");
            idRadioText.requestFocus();
            return;
        }
        addRadio();
        
    }
    
    public void showAlert(String msg) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Ошибка выполнения");
        alert.setHeaderText(msg.toString());
        alert.show();
    }
    
    private void addRadio() {
        boolean isUniqueRadioEntry = true;
        if (uniqueNumbersData.contains(numberRadioText.getText()) || uniqueSnNumbersData.contains(snRadioText.getText()) || uniqueIdData.contains(idRadioText.getText())) {
            isUniqueRadioEntry = false;
        }
        try {

            if (isUniqueRadioEntry) {
                try {
                    RadioDAO.insertRadio(idRadioText.getText(), numberRadioText.getText(), modelRadioText.getText(), snRadioText.getText(), conditionRadioText.getText(), availableRadioText.getText(), descriptionRadioText.getText());
                    TermPulse.getMainController().retrieveFromDB();
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(TermPulseRadioEditController.class.getName()).log(Level.SEVERE, null, ex);
                }
                ((Stage)(((TextField)idRadioText).getScene().getWindow())).close();
            } else {
                showAlert("Уже имеются записи с указанным номером рации или серийным номером");
                isUniqueRadioEntry = true;
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
    private void updateRadio(ActionEvent event) {
        boolean isNotEmpyRadioEntry = true;
        
        if (idRadioText.getText().isEmpty() || numberRadioText.getText().isEmpty() || modelRadioText.getText().isEmpty() || snRadioText.getText().isEmpty() || availableRadioText.getText().isEmpty()) {
            isNotEmpyRadioEntry = false;
        }

        try {
            if (isNotEmpyRadioEntry) {
                try {
                    RadioDAO.updateRadio(idRadioText.getText(), numberRadioText.getText(), modelRadioText.getText(), snRadioText.getText(), conditionRadioText.getText(), availableRadioText.getText(), descriptionRadioText.getText());
                    TermPulse.getMainController().retrieveFromDB();
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(TermPulseRadioEditController.class.getName()).log(Level.SEVERE, null, ex);
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
