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
public class TermPulseTermEditController implements Initializable {

    @FXML
    private Label idTermLabel;
    @FXML
    private Label numberTermLabel;
    @FXML
    private Label typeTermLabel;
    @FXML
    private Label modelTermLabel;
    @FXML
    private Label snTermLabel;
    @FXML
    private Label sn_scannerTermLabel;
    @FXML
    private TextField idTermText;
    @FXML
    private TextField numberTermText;
    @FXML
    private TextField modelTermText;
    @FXML
    private TextField snTermText;
    @FXML
    private TextField sn_scannerTermText;
    @FXML
    private Button btnTermAdd;
    @FXML
    private Button btnTermCancel;
    @FXML
    private Button btnTermUpdate;
    @FXML
    private Label conditionTermLabel;
    @FXML
    private Label availableTermLabel;
    @FXML
    private Label descriptionTermLabel;
    @FXML
    private TextField conditionTermText;
    @FXML
    private TextField availableTermText;
    @FXML
    private TextField descriptionTermText;
    @FXML
    private ChoiceBox<String> listTermType;
    @FXML
    private Button btnTermFindByNumber;
    @FXML
    private ImageView imgSearch;
    @FXML
    private Button btnTermFindBySerial;
    @FXML
    private Button btnTermFindByScannerNumber;
    
    private ObservableList<String> termType = FXCollections.observableArrayList();
    private ObservableList<String> uniqueNumbersData = FXCollections.observableArrayList();
    private ObservableList<String> uniqueIdData = FXCollections.observableArrayList();
    private ObservableList<String> uniqueSnNumbersData = FXCollections.observableArrayList();
    
    
     /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            retrieveTypesFromDB();
        } catch (SQLException ex) {
            Logger.getLogger(TermPulseTermEditController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(TermPulseTermEditController.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.uniqueNumbersData=null;
        this.uniqueIdData=null;
        this.uniqueSnNumbersData=null;
        this.idTermText.setText("");
        this.modelTermText.setText("");
        this.numberTermText.setText("");
        this.descriptionTermText.setText("");
        this.availableTermText.setText("");
        this.conditionTermText.setText("");
        this.sn_scannerTermText.setText("");
        this.snTermText.setText("");
        this.listTermType.getSelectionModel().select(0);
    }
    
    public void setData(Terminal currTerm,ObservableList<String> uniqueIdData, ObservableList<String> uniqueNumbersData,ObservableList<String> uniqueSnNumbersData){
        this.uniqueNumbersData=uniqueNumbersData;
        this.uniqueIdData=uniqueIdData;
        this.uniqueSnNumbersData=uniqueSnNumbersData;
        this.idTermText.setText(currTerm.getId());
        this.modelTermText.setText(currTerm.getModel());
        this.numberTermText.setText(currTerm.getNumber());
        this.descriptionTermText.setText(currTerm.getDescription());
        this.availableTermText.setText(currTerm.getAvailable());
        this.conditionTermText.setText(currTerm.getCondition());
        this.sn_scannerTermText.setText(currTerm.getSnScanner());
        this.snTermText.setText(currTerm.getSn());
        this.listTermType.getSelectionModel().select(currTerm.getType());
    }
 
    @FXML
    private void addTerminal(ActionEvent event) {
        int id_term;
        try {
            id_term = Integer.parseInt(idTermText.getText());
        } catch (NumberFormatException e) {
            showAlert("Значение ID должно быть целочисленным!");
            idTermText.requestFocus();
            return;
        }
        addTerminal();
        
    }
    
    public void showAlert(String msg) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Ошибка выполнения");
        alert.setHeaderText(msg.toString());
        alert.show();
    }
    
    private void addTerminal() {
        boolean isUniqueRTEntry = true;
        if (uniqueNumbersData.contains(numberTermText.getText()) || uniqueSnNumbersData.contains(snTermText.getText()) || uniqueIdData.contains(idTermText.getText())) {
            isUniqueRTEntry = false;
        }
        try {

            if (isUniqueRTEntry) {
                try {
                    TerminalDAO.insertRT(idTermText.getText(), numberTermText.getText(), listTermType.getSelectionModel().getSelectedItem().toString(), modelTermText.getText(), snTermText.getText(), sn_scannerTermText.getText(), conditionTermText.getText(), availableTermText.getText(), descriptionTermText.getText());
                    TermPulse.getMainController().retrieveFromDB();
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(TermPulseTermEditController.class.getName()).log(Level.SEVERE, null, ex);
                }
                ((Stage)(((TextField)idTermText).getScene().getWindow())).close();
            } else {
                showAlert("Уже имеются записи с указанным номером терминала или серийным номером");
                isUniqueRTEntry = true;
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
    private void updateTerminal(ActionEvent event) {
        boolean isNotEmpyRTEntry = true;
        
        if (idTermText.getText().isEmpty() || numberTermText.getText().isEmpty() || modelTermText.getText().isEmpty() || listTermType.getSelectionModel().getSelectedItem().isEmpty() || snTermText.getText().isEmpty() || availableTermText.getText().isEmpty()) {
            isNotEmpyRTEntry = false;
        }

        try {
            if (isNotEmpyRTEntry) {
                try {
                    TerminalDAO.updateRT(idTermText.getText(), numberTermText.getText(), listTermType.getSelectionModel().getSelectedItem().toString(), modelTermText.getText(), snTermText.getText(), sn_scannerTermText.getText(), conditionTermText.getText(), availableTermText.getText(), descriptionTermText.getText());
                    TermPulse.getMainController().retrieveFromDB();
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(TermPulseTermEditController.class.getName()).log(Level.SEVERE, null, ex);
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
      
    public void retrieveTypesFromDB() throws SQLException, ClassNotFoundException {
        try {
            termType = TerminalDAO.searchTerminalTypes();
            listTermType.getItems().removeAll(listTermType.getItems());
            listTermType.setItems(termType);
        } catch (SQLException e) {
            throw e;
        }

    }
    
}
