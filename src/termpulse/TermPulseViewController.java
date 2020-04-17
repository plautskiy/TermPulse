/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package termpulse;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import javafx.collections.FXCollections;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.ButtonType;
import java.sql.*;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import javafx.application.Platform;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.image.ImageView;
import java.util.Calendar;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author kroni
 */
public class TermPulseViewController implements Initializable {
    @FXML
    private Button retrieveButton;
    @FXML
    private TextArea ConsoleTextArea;
    @FXML
    private TableView<Terminal> tableRT;
    @FXML
    private TableColumn<Terminal, String> idColumn;
    @FXML
    private TableColumn<Terminal, String> numberColumn;
    @FXML
    private TableColumn<Terminal, String> typeColumn;
    @FXML
    private TableColumn<Terminal, String> modelColumn;
    @FXML
    private TableColumn<Terminal, String> snColumn;
    @FXML
    private TableColumn<Terminal, String> sn_scannerColumn;
    @FXML
    private TableColumn<Terminal, String> conditionColumn;
    @FXML
    private TableColumn<Terminal, String> availableColumn;
    @FXML
    private TableColumn<Terminal, String> descriptionColumn;
    @FXML
    private ChoiceBox listDB;
    @FXML
    private TableView<Employee> tableEmployee;
    @FXML
    private TableColumn<Employee, String> idEmplColumn;
    @FXML
    private TableColumn<Employee, String> nameEmplColumn;
    @FXML
    private TableColumn<Employee, String> postEmplColumn;
    @FXML
    private TableColumn<Employee, String> currTermColumn;
    @FXML
    private TableColumn<Employee, String> currRtsdColumn;
    @FXML
    private TableColumn<Employee, String> currRadioColumn;
    @FXML
    private TableView<Radio> tableRadio;
    @FXML
    private TableColumn<Radio, String> idRadioColumn;
    @FXML
    private TableColumn<Radio, String> numberRadioColumn;
    @FXML
    private TableColumn<Radio, String> modelRadioColumn;
    @FXML
    private TableColumn<Radio, String> snRadioColumn;
    @FXML
    private TableColumn<Radio, String> conditionRadioColumn;
    @FXML
    private TableColumn<Radio, String> availableRadioColumn;
    @FXML
    private TableColumn<Radio, String> descriptionRadioColumn;
    @FXML
    private TableView<JournalEntry> tableJournal;
    @FXML
    private TableColumn<JournalEntry, String> idJournalColumn;
    @FXML
    private TableColumn<JournalEntry, String> dtJournalColumn;
    @FXML
    private TableColumn<JournalEntry, String> operation_typeJournalColumn;
    @FXML
    private TableColumn<JournalEntry, String> employeeJournalColumn;
    @FXML
    private TableColumn<JournalEntry, String> operation_statusJournalColumn;
    @FXML
    private TableColumn<JournalEntry, String> deviceJournalColumn;
    @FXML
    private TableColumn<JournalEntry, String> device_numberJournalColumn;
    @FXML
    private TableColumn<JournalEntry, String> operation_beginJournalColumn;
    @FXML
    private TableColumn<JournalEntry, String> operation_endJournalColumn;
    @FXML
    private TableColumn<JournalEntry, String> descriptionJournalColumn;
    @FXML
    private Button btnAdd;
    @FXML
    private Button btnDelete;
    @FXML
    private Label actionInfoLabel;
    @FXML
    private TextField actionBarcodeText;
    @FXML
    private TextField actionEmpText;
    @FXML
    private TextField actionEmpPostText;
    @FXML
    private TabPane mainTabPane;
    @FXML
    private Button btnFindByNumber;
    @FXML
    private Button btnFindBySerial;
    @FXML
    private Button btnFindByScannerNumber;
    @FXML
    private Button btnFindByEmpId;
    @FXML
    private Button btnFindByName;
    @FXML
    private ImageView imgSearch;
    @FXML
    private Label actionConfirmLabel;
    @FXML
    private TextField actionKeyIdText;
    @FXML
    private DatePicker datePickBegin;
    @FXML
    private DatePicker datePickEnd;
    @FXML
    private Button journalCloseOpButton;
    @FXML
    private RadioButton journalAllEntrysRButton;
    @FXML
    private ToggleGroup journalStatusRBGroup;
    @FXML
    private RadioButton journalBeginEntrysRButton;
    @FXML
    private Label journalStatusLabel;
    @FXML
    private Button journalAddDescButton;
    @FXML
    private TextField journalDeviceText;
    @FXML
    private TextField journalEmployeeText;
    @FXML
    private Tab tabJournal;
    @FXML
    private Button journalFindDeviceButton;
    @FXML
    private Button journalFindEmpButton;
    @FXML
    private Button journalRetrieveButton;
    @FXML
    private Tab tabAction;
    @FXML
    private Tab tabDB;

    private boolean isUniqueRTEntry = true;
    private boolean isUniqueEmployeeEntry = true;

    private final ObservableList<String> databases = FXCollections.observableArrayList(TermPulseTableName.RT_BASE.toString(), TermPulseTableName.RT_USERS.toString(), TermPulseTableName.RT_RADIO.toString());
    private ObservableList<Terminal> termData = FXCollections.observableArrayList();
    private ObservableList<Employee> emplData = FXCollections.observableArrayList();
    private ObservableList<Radio> radioData = FXCollections.observableArrayList();
    private ObservableList<JournalEntry> journalData = FXCollections.observableArrayList();

    private ObservableList<String> termType = FXCollections.observableArrayList();

    private Boolean isUpdateButtonLabel = false;
    private String termTypeForEmpl;
    private String empTermNumber;
    private String empRtsdNumber;
    private String empRadioNumber;
    private String entryEmpName;
    private String entryEmpId;
    private String entryTermId;
    private String entryTermNumber;
    private String entryRadioId;
    private String entryRadioNumber;
    private final String[] entryOpType = {"работа", "ремонт"};
    private final String[] entryOpStatus = {"выдан", "возвращен"};

    private Timestamp entryOpTSBegin;
    private Timestamp entryOpTSEnd;

    private int activeTermEntriesNum;
    private int activeRtsdEntriesNum;
    private int freeTermsNumber;
    private int freeRadiosNumber;
    private int freeRtsdNumber;
    private int activeRadioEntriesNum;
    private int allEntriesNum;
    private int allRadiosNumber;
    private int allTermsNumber;
    private int allRtsdNumber;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        mainTabPane.getSelectionModel().select(0);

        actionKeyIdText.clear();
        actionBarcodeText.clear();
        actionConfirmLabel.setStyle("-fx-text-fill: green;");
        datePickBegin.setValue(LocalDate.now());
        datePickEnd.setValue(datePickBegin.getValue().plusDays(1));
        journalAllEntrysRButton.setUserData("все");
        journalBeginEntrysRButton.setUserData("выдан");

        setBarcodeFocus();

        listDB.getItems().removeAll(listDB.getItems());
        listDB.setItems(databases);
        listDB.getSelectionModel().selectFirst();
        listDB.setTooltip(new Tooltip("выберите таблицу для работы"));
        listDB.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number oldnumber, Number newnumber) {
                toConsole("Смена текущей таблицы с \"" + listDB.getItems().get((Integer) oldnumber) + "\" на \"" + listDB.getItems().get((Integer) newnumber) + "\"");
                TermPulse.getPrimaryStage().setTitle(TermPulse.title + ". Таблица: " + listDB.getItems().get((Integer) newnumber));
                isUpdateButtonLabel = false;
                retrieveButton.setText("Загрузить данные");
            }
        });
        
        btnAdd.setDisable(true);
        btnDelete.setDisable(true);

        TermPulse.getPrimaryStage().setTitle(TermPulse.title + ": Журнал учета операций с устройствами");

        idColumn.setCellValueFactory(cellData -> cellData.getValue().idProperty());
        numberColumn.setCellValueFactory(cellData -> cellData.getValue().numberProperty());
        typeColumn.setCellValueFactory(cellData -> cellData.getValue().typeProperty());
        modelColumn.setCellValueFactory(cellData -> cellData.getValue().modelProperty());
        snColumn.setCellValueFactory(cellData -> cellData.getValue().snProperty());
        sn_scannerColumn.setCellValueFactory(cellData -> cellData.getValue().sn_scannerProperty());
        conditionColumn.setCellValueFactory(cellData -> cellData.getValue().conditionProperty());
        availableColumn.setCellValueFactory(cellData -> cellData.getValue().availableProperty());
        descriptionColumn.setCellValueFactory(cellData -> cellData.getValue().descriptionProperty());

        idColumn.setStyle("-fx-alignment: CENTER;");
        numberColumn.setStyle("-fx-alignment: CENTER;");
        typeColumn.setStyle("-fx-alignment: CENTER;");
        modelColumn.setStyle("-fx-alignment: CENTER;");
        snColumn.setStyle("-fx-alignment: CENTER;");
        sn_scannerColumn.setStyle("-fx-alignment: CENTER;");
        conditionColumn.setStyle("-fx-alignment: CENTER;");
        availableColumn.setStyle("-fx-alignment: CENTER;");
        descriptionColumn.setStyle("-fx-alignment: CENTER;");

        idJournalColumn.setCellValueFactory(cellData -> cellData.getValue().idProperty());
        dtJournalColumn.setCellValueFactory(
                cellData -> {
                    SimpleStringProperty property = new SimpleStringProperty();
                    Timestamp ts = cellData.getValue().getDt();
                    String s = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(ts);
                    //DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy")
                    //DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-LL-dd HH:mm:ss.S");
                    //LocalDateTime ldt =  LocalDateTime.parse(cellData.getValue().getDt(),dtf);

                    property.setValue(s);
                    return property;
                });
        operation_typeJournalColumn.setCellValueFactory(cellData -> cellData.getValue().operationTypeProperty());
        employeeJournalColumn.setCellValueFactory(cellData -> cellData.getValue().employeeProperty());
        operation_statusJournalColumn.setCellValueFactory(cellData -> cellData.getValue().operationStatusProperty());
        deviceJournalColumn.setCellValueFactory(cellData -> cellData.getValue().deviceProperty());
        device_numberJournalColumn.setCellValueFactory(cellData -> cellData.getValue().deviceNumberProperty());

        operation_beginJournalColumn.setCellValueFactory(
                cellData -> {
                    SimpleStringProperty property = new SimpleStringProperty();
                    Timestamp ts = cellData.getValue().getOperationBegin();
                    String s = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(ts);
                    //DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy")
                    //DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-LL-dd HH:mm:ss.S");
                    //LocalDateTime ldt =  LocalDateTime.parse(cellData.getValue().getDt(),dtf);

                    property.setValue(s);
                    return property;
                });

        operation_endJournalColumn.setCellValueFactory(
                cellData -> {
                    SimpleStringProperty property = new SimpleStringProperty();
                    //для пустых полей даты возврата
                    if (cellData.getValue().getOperationEnd() == null) {
                        property.setValue("");
                        return property;
                    }
                    Timestamp ts = cellData.getValue().getOperationEnd();
                    String s = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(ts);
                    //DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy")
                    //DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-LL-dd HH:mm:ss.S");
                    //LocalDateTime ldt =  LocalDateTime.parse(cellData.getValue().getDt(),dtf);

                    property.setValue(s);
                    return property;
                });
        descriptionJournalColumn.setCellValueFactory(cellData -> cellData.getValue().descriptionProperty());

        idJournalColumn.setStyle("-fx-alignment: CENTER;");
        dtJournalColumn.setStyle("-fx-alignment: CENTER;");
        operation_typeJournalColumn.setStyle("-fx-alignment: CENTER;");
        employeeJournalColumn.setStyle("-fx-alignment: CENTER;");
        operation_statusJournalColumn.setStyle("-fx-alignment: CENTER;");
        deviceJournalColumn.setStyle("-fx-alignment: CENTER;");
        device_numberJournalColumn.setStyle("-fx-alignment: CENTER;");
        operation_beginJournalColumn.setStyle("-fx-alignment: CENTER;");
        operation_endJournalColumn.setStyle("-fx-alignment: CENTER;");
        descriptionJournalColumn.setStyle("-fx-alignment: CENTER;");

        idRadioColumn.setCellValueFactory(cellData -> cellData.getValue().idProperty());
        numberRadioColumn.setCellValueFactory(cellData -> cellData.getValue().numberProperty());
        modelRadioColumn.setCellValueFactory(cellData -> cellData.getValue().modelProperty());
        snRadioColumn.setCellValueFactory(cellData -> cellData.getValue().snProperty());
        conditionRadioColumn.setCellValueFactory(cellData -> cellData.getValue().conditionProperty());
        availableRadioColumn.setCellValueFactory(cellData -> cellData.getValue().availableProperty());
        descriptionRadioColumn.setCellValueFactory(cellData -> cellData.getValue().descriptionProperty());

        idRadioColumn.setStyle("-fx-alignment: CENTER;");
        numberRadioColumn.setStyle("-fx-alignment: CENTER;");
        modelRadioColumn.setStyle("-fx-alignment: CENTER;");
        snRadioColumn.setStyle("-fx-alignment: CENTER;");
        conditionRadioColumn.setStyle("-fx-alignment: CENTER;");
        availableRadioColumn.setStyle("-fx-alignment: CENTER;");
        descriptionRadioColumn.setStyle("-fx-alignment: CENTER;");

        idEmplColumn.setCellValueFactory(cellData -> cellData.getValue().idProperty());
        nameEmplColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        postEmplColumn.setCellValueFactory(cellData -> cellData.getValue().postProperty());
        currTermColumn.setCellValueFactory(cellData -> cellData.getValue().currTermProperty());
        currRtsdColumn.setCellValueFactory(cellData -> cellData.getValue().currRtsdProperty());
        currRadioColumn.setCellValueFactory(cellData -> cellData.getValue().currRadioProperty());

        idEmplColumn.setStyle("-fx-alignment: CENTER;");
        nameEmplColumn.setStyle("-fx-alignment: CENTER;");
        postEmplColumn.setStyle("-fx-alignment: CENTER;");
        currTermColumn.setStyle("-fx-alignment: CENTER;");
        currRtsdColumn.setStyle("-fx-alignment: CENTER;");
        currRadioColumn.setStyle("-fx-alignment: CENTER;");

        //idEmplColumn.prefWidthProperty().bind(tableEmployee.widthProperty().divide(5));
        nameEmplColumn.prefWidthProperty().bind(tableEmployee.widthProperty().divide(5));
        postEmplColumn.prefWidthProperty().bind(tableEmployee.widthProperty().divide(5));
        currTermColumn.prefWidthProperty().bind(tableEmployee.widthProperty().divide(5));
        currRtsdColumn.prefWidthProperty().bind(tableEmployee.widthProperty().divide(5));
        currRadioColumn.prefWidthProperty().bind(tableEmployee.widthProperty().divide(5));

        tableEmployee.setTableMenuButtonVisible(true);
        tableEmployee.setColumnResizePolicy(TableView.UNCONSTRAINED_RESIZE_POLICY);
        tableEmployee.setVisible(false);
        tableEmployee.setEditable(false);
        tableRT.setTableMenuButtonVisible(true);
        tableRT.setColumnResizePolicy(TableView.UNCONSTRAINED_RESIZE_POLICY);
        tableRT.setVisible(false);
        tableRT.setEditable(false);
        tableRadio.setTableMenuButtonVisible(true);
        tableRadio.setColumnResizePolicy(TableView.UNCONSTRAINED_RESIZE_POLICY);
        tableRadio.setVisible(false);
        tableRadio.setEditable(false);
        tableJournal.setColumnResizePolicy(TableView.UNCONSTRAINED_RESIZE_POLICY);
        tableJournal.setTableMenuButtonVisible(true);

        tableRT.getSelectionModel().selectFirst();
        tableRT.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.getButton().equals(MouseButton.PRIMARY) && event.getClickCount() == 2) {
                    ObservableList<String> uniqueNumbersData = FXCollections.observableArrayList();
                    ObservableList<String> uniqueIdData = FXCollections.observableArrayList();
                    ObservableList<String> uniqueSnNumbersData = FXCollections.observableArrayList();
                    ObservableList<Terminal> terminalAll;
                    uniqueNumbersData.clear();
                    uniqueSnNumbersData.clear();
                    uniqueIdData.clear();
                    terminalAll = tableRT.getItems();

                    for (Terminal term : terminalAll) {
                        uniqueNumbersData.add(term.getNumber());
                        uniqueSnNumbersData.add(term.getSn());
                        uniqueIdData.add(term.getId());
                    }

                    showTermEditDialog(tableRT.getSelectionModel().getSelectedItem(), uniqueIdData, uniqueNumbersData, uniqueSnNumbersData);
                }
            }
        });

        tableRadio.getSelectionModel().selectFirst();

        tableRadio.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.getButton().equals(MouseButton.PRIMARY) && event.getClickCount() == 2) {
                    ObservableList<String> uniqueNumbersData = FXCollections.observableArrayList();
                    ObservableList<String> uniqueIdData = FXCollections.observableArrayList();
                    ObservableList<String> uniqueSnNumbersData = FXCollections.observableArrayList();
                    ObservableList<Radio> radioAll;
                    uniqueNumbersData.clear();
                    uniqueSnNumbersData.clear();
                    uniqueIdData.clear();
                    radioAll = tableRadio.getItems();

                    for (Radio rad : radioAll) {
                        uniqueNumbersData.add(rad.getNumber());
                        uniqueSnNumbersData.add(rad.getSn());
                        uniqueIdData.add(rad.getId());
                    }

                    showRadioEditDialog(tableRadio.getSelectionModel().getSelectedItem(), uniqueIdData, uniqueNumbersData, uniqueSnNumbersData);
                }
            }
        });

        tableEmployee.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.getButton().equals(MouseButton.PRIMARY) && event.getClickCount() == 2) {
                    ObservableList<String> uniqueNamesData = FXCollections.observableArrayList();
                    ObservableList<String> uniqueIdData = FXCollections.observableArrayList();
                    ObservableList<Employee> employeeAll;
                    uniqueNamesData.clear();
                    uniqueIdData.clear();
                    employeeAll = tableEmployee.getItems();

                    for (Employee empl : employeeAll) {
                        uniqueNamesData.add(empl.getName());
                        uniqueIdData.add(empl.getId());
                    }

                    showEmplEditDialog(tableEmployee.getSelectionModel().getSelectedItem(), uniqueIdData, uniqueNamesData);
                }
            }
        });

        tableJournal.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> {

            if (tableJournal.getSelectionModel().getSelectedItem() != null) {
                journalCloseOpButton.setDisable(false);
                journalAddDescButton.setDisable(false);
                JournalEntry j_entry = tableJournal.getSelectionModel().getSelectedItem();
                //journalDeviceText.textProperty().bind(j_entry.deviceNumberProperty());
                //journalEmployeeText.textProperty().bind(j_entry.employeeProperty());
                journalDeviceText.setText(j_entry.getDeviceNumber());
                journalEmployeeText.setText(j_entry.getEmployee());

            }
        });

        mainTabPane.getSelectionModel().selectedItemProperty().addListener((ov, oldTab, newTab) -> {
            if (mainTabPane.getSelectionModel().isSelected(1)) {
                TermPulse.getPrimaryStage().setTitle(TermPulse.title + ": Выдача/прием устройств для сотрудников ");
                actionBarcodeText.clear();
                actionEmpText.clear();
                actionEmpPostText.clear();
                actionConfirmLabel.setText("");
                setBarcodeFocus();
            } else if (mainTabPane.getSelectionModel().isSelected(0)) {
                TermPulse.getPrimaryStage().setTitle(TermPulse.title + ": Журнал учета операций с устройствами");
                journalBeginEntrysRButton.setSelected(true);
                try {
                    retrieveJournalFromDB();
                } catch (SQLException ex) {
                    Logger.getLogger(TermPulseViewController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(TermPulseViewController.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (mainTabPane.getSelectionModel().isSelected(2)) {
                TermPulse.getPrimaryStage().setTitle(TermPulse.title + ": Просмотр и редактирование таблиц БД");
            }

        });
        mainTabPane.setOnKeyReleased(new EventHandler<KeyEvent>() {
            public void handle(KeyEvent ke) {
                if (mainTabPane.getSelectionModel().isSelected(1)) {
                    if (ke.getCode() == KeyCode.ESCAPE) {
                        setBarcodeFocus();
                        actionConfirmLabel.setText("");
                        toConsole("Действие отменено по нажатию ESC");
                        termTypeForEmpl = null;
                        entryEmpName = null;
                        entryEmpId = null;
                        entryTermId = null;
                        entryTermNumber = null;
                        entryRadioId = null;
                        entryRadioNumber = null;
                        empRadioNumber = null;
                        empTermNumber = null;
                        empRtsdNumber = null;
                        ke.consume();
                    }
                }
            }
        });

        journalDeviceText.setOnAction(actionEvent -> {
            try {
                journalFindByDevice(actionEvent);
            } catch (SQLException ex) {
                Logger.getLogger(TermPulseViewController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(TermPulseViewController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        journalEmployeeText.setOnAction(actionEvent -> {
            try {
                journalFindByEmployee(actionEvent);
            } catch (SQLException ex) {
                Logger.getLogger(TermPulseViewController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(TermPulseViewController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        try {
            retrieveJournalFromDB();
        } catch (SQLException ex) {
            Logger.getLogger(TermPulseViewController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(TermPulseViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void setBarcodeFocus() {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                actionBarcodeText.clear();
                actionKeyIdText.clear();
                actionBarcodeText.setVisible(true);
                actionKeyIdText.setVisible(false);
                actionEmpText.clear();
                actionEmpPostText.clear();
                actionInfoLabel.setText("ОТСКАНИРУЙТЕ БЕЙДЖ СОТРУДНИКА");
                //actionConfirmLabel.setText("");
                actionBarcodeText.requestFocus();
            }
        });
    }

    @FXML
    public void handleExit(ActionEvent actionEvent) {
        System.exit(0);
    }

    @FXML
    public void handleHelp(ActionEvent actionEvent) {
        toConsole("Вызов справки");

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("О программе");
        alert.setHeaderText("Программа TermPulse     версия: " + TermPulse.version);
        alert.setContentText("(C) 2017 Плаутский П. Все права защищены.");
        alert.show();
    }

    public void toConsole(String msg) {
        //LocalTime msg_time = LocalTime.now();
        Calendar calendar = Calendar.getInstance();
        Timestamp ts = new Timestamp(calendar.getTime().getTime());
        //ts.setTime(1000*(long)Math.floor(ts.getTime()/ 1000));
        ConsoleTextArea.appendText(ts + ":> " + msg + "\n");
        toLogFile(ts + ":> " + msg + "\n");
    }

    private void toLogFile(String msg) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(TermPulse.logFile, true))) {

            bw.write(msg);

            // no need to close it.
            //bw.close();
        } catch (IOException e) {

            e.printStackTrace();

        }
    }

    public void showAlert(String msg) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Ошибка выполнения");
        alert.setHeaderText(msg.toString());
        alert.show();
    }

    public String showTextInputDialog(String msg, String title, String header, String content) {
        TextInputDialog getTextDialog = new TextInputDialog(msg);
        getTextDialog.setTitle(title);
        getTextDialog.setHeaderText(header);
        getTextDialog.setContentText(content);
        getTextDialog.getEditor().setPrefColumnCount(40);
        Optional<String> resultText = getTextDialog.showAndWait();

        if (resultText.isPresent()) {
            return resultText.get();

        } else {
            return "";
        }

    }

    public boolean showConfirmAlert(String msg) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Подтверждение выполнения операции");
        alert.setHeaderText(msg.toString());
        //alert.setContentText(msg.toString());
        if (alert.showAndWait().get() == ButtonType.OK) {
            return true;
        }
        return false;
    }

    private boolean writeTermEntryLog(String key_barcode) throws SQLException, ClassNotFoundException {
        ObservableList<Terminal> currterm = FXCollections.observableArrayList();
        ObservableList<JournalEntry> entriesAll = FXCollections.observableArrayList();
        try {
            currterm = TerminalDAO.searchTerminalbyId(key_barcode);

            if (currterm == null) {
                //showAlert("терминал c ID " + key_barcode + " не найден.");
                actionConfirmLabel.setStyle("-fx-text-fill: red;");
                actionConfirmLabel.setText("КЛЮЧ СО ШК " + key_barcode + " НЕ НАЙДЕН !");
                actionKeyIdText.clear();
                actionKeyIdText.requestFocus();
                toConsole("ключ c ID " + key_barcode + " не найден.");
            } else {
                entryTermId = currterm.get(0).getId();
                entryTermNumber = currterm.get(0).getNumber();
                Calendar calendar = Calendar.getInstance();
                Timestamp ts = new Timestamp(calendar.getTime().getTime());

                entriesAll = JournalEntryDAO.searchTakenDeviceByEmp(entryEmpName, entryOpStatus[0], entryTermNumber);
                // проверка на отсутствие записей со статусом "начат" в журнале у данного сотрудника
                //если записи имеются, то значит сотрудник сдает ключ
                if (entriesAll == null) {
                    //определяем тип терминала                    
                    if ("F".equals(entryTermNumber.substring(0, 1))) {
                        if (empRtsdNumber == null || empRtsdNumber.isEmpty()) {
                            if (currterm.get(0).getAvailable().contains("да")) {
                                actionConfirmLabel.setText("СОТРУДНИКУ " + entryEmpName + " ВЫДАН ТЕРМИНАЛ " + entryTermNumber + ".\n ВРЕМЯ НАЧАЛА РАБОТЫ: " + ts);

                                JournalEntryDAO.insertEntry(ts, entryOpType[0], entryEmpName, entryOpStatus[0], currterm.get(0).getType(), entryTermNumber, ts, "", entryEmpId, entryTermId, null);
                                TerminalDAO.updateRT(entryTermId, entryTermNumber, currterm.get(0).getType(), currterm.get(0).getModel(), currterm.get(0).getSn(), currterm.get(0).getSnScanner(), currterm.get(0).getCondition(), "нет", currterm.get(0).getDescription());
                                EmployeeDAO.updateEmployeeRtsd(entryEmpId, entryTermNumber);

                                termTypeForEmpl = null;
                                entryEmpName = null;
                                entryTermId = null;
                                entryTermNumber = null;
                                empRtsdNumber = null;
                                setBarcodeFocus();
                            } else {
                                actionConfirmLabel.setStyle("-fx-text-fill: red;");
                                actionConfirmLabel.setText("КЛЮЧ СО ШК " + key_barcode + " (" + entryTermNumber + ") УЖЕ ВЫДАН !");
                                actionKeyIdText.clear();
                                actionKeyIdText.requestFocus();
                                toConsole("ключ c ID " + key_barcode + " выдан ранее.");
                            }
                        } else {
                            actionConfirmLabel.setStyle("-fx-text-fill: red;");
                            actionConfirmLabel.setText("ВЫ УЖЕ РАБОТАЕТЕ С ТЕРМИНАЛОМ НОМЕР " + empRtsdNumber + " !\n В ВЫДАЧЕ ОТКАЗАНО !");
                            actionKeyIdText.clear();
                            actionKeyIdText.requestFocus();
                            toConsole("СОТРУДНИК " + entryEmpName + "УЖЕ РАБОТАЕТ С ТЕРМИНАЛОМ НОМЕР " + empRtsdNumber + " !");
                        }
                    } else if ("P".equals(entryTermNumber.substring(0, 1))) {
                        if (empTermNumber == null || empTermNumber.isEmpty()) {
                            if (currterm.get(0).getAvailable().contains("да")) {
                                actionConfirmLabel.setText("СОТРУДНИКУ " + entryEmpName + " ВЫДАН ТЕРМИНАЛ " + entryTermNumber + ".\n ВРЕМЯ НАЧАЛА РАБОТЫ: " + ts);

                                JournalEntryDAO.insertEntry(ts, entryOpType[0], entryEmpName, entryOpStatus[0], currterm.get(0).getType(), entryTermNumber, ts, "", entryEmpId, entryTermId, null);
                                TerminalDAO.updateRT(entryTermId, entryTermNumber, currterm.get(0).getType(), currterm.get(0).getModel(), currterm.get(0).getSn(), currterm.get(0).getSnScanner(), currterm.get(0).getCondition(), "нет", currterm.get(0).getDescription());
                                EmployeeDAO.updateEmployeeTerminal(entryEmpId, entryTermNumber);

                                termTypeForEmpl = null;
                                entryEmpName = null;
                                entryTermId = null;
                                entryTermNumber = null;
                                empRtsdNumber = null;
                                setBarcodeFocus();
                            } else {
                                actionConfirmLabel.setStyle("-fx-text-fill: red;");
                                actionConfirmLabel.setText("КЛЮЧ СО ШК " + key_barcode + " (" + entryTermNumber + ") УЖЕ ВЫДАН !");
                                actionKeyIdText.clear();
                                actionKeyIdText.requestFocus();
                                toConsole("ключ c ID " + key_barcode + " выдан ранее.");
                            }
                        } else {
                            actionConfirmLabel.setStyle("-fx-text-fill: red;");
                            actionConfirmLabel.setText("ВЫ УЖЕ РАБОТАЕТЕ С ТЕРМИНАЛОМ НОМЕР " + empTermNumber + " !\n В ВЫДАЧЕ ОТКАЗАНО !");
                            actionKeyIdText.clear();
                            actionKeyIdText.requestFocus();
                            toConsole("СОТРУДНИК " + entryEmpName + "УЖЕ РАБОТАЕТ С ТЕРМИНАЛОМ НОМЕР " + empTermNumber + " !");
                        }
                    }

                } else {
                    //сотрудник сдает ключ
                    actionConfirmLabel.setText("СОТРУДНИК " + entryEmpName + " СДАЛ ТЕРМИНАЛ " + entryTermNumber + ".\nВРЕМЯ ОКОНЧАНИЯ РАБОТЫ: " + ts);
                    if ("F".equals(entryTermNumber.substring(0, 1))) {
                        TerminalDAO.updateRT(entryTermId, entryTermNumber, currterm.get(0).getType(), currterm.get(0).getModel(), currterm.get(0).getSn(), currterm.get(0).getSnScanner(), currterm.get(0).getCondition(), "да", currterm.get(0).getDescription());
                        EmployeeDAO.updateEmployeeRtsd(entryEmpId, "");
                        JournalEntryDAO.updateEntry(entriesAll.get(0).getId(), entryOpType[0], entryEmpName, entryOpStatus[1], currterm.get(0).getType(), entryTermNumber, ts, "");
                    } else if ("P".equals(entryTermNumber.substring(0, 1))) {
                        TerminalDAO.updateRT(entryTermId, entryTermNumber, currterm.get(0).getType(), currterm.get(0).getModel(), currterm.get(0).getSn(), currterm.get(0).getSnScanner(), currterm.get(0).getCondition(), "да", currterm.get(0).getDescription());
                        EmployeeDAO.updateEmployeeTerminal(entryEmpId, "");
                        JournalEntryDAO.updateEntry(entriesAll.get(0).getId(), entryOpType[0], entryEmpName, entryOpStatus[1], currterm.get(0).getType(), entryTermNumber, ts, "");
                    }
                    termTypeForEmpl = null;
                    entryEmpName = null;
                    entryTermId = null;
                    entryTermNumber = null;
                    empRadioNumber = null;
                    empTermNumber = null;
                    empRtsdNumber = null;
                    setBarcodeFocus();
                }
            }
        } catch (SQLException e) {
            toConsole("Problem occurred while retrieving terminal " + e);
            throw e;
        }
        return true;
    }

    private boolean writeRadioEntryLog(String key_barcode) throws SQLException, ClassNotFoundException {
        ObservableList<Radio> curradio = FXCollections.observableArrayList();
        ObservableList<JournalEntry> entriesAll = FXCollections.observableArrayList();
        ObservableList<JournalEntry> entryDebtByEmp = FXCollections.observableArrayList();

        try {
            curradio = RadioDAO.searchRadiobyId(key_barcode);
            if (curradio == null) {
                //showAlert("терминал c ID " + key_barcode + " не найден.");
                actionConfirmLabel.setStyle("-fx-text-fill: red;");
                actionConfirmLabel.setText("КЛЮЧ СО ШК " + key_barcode + " НЕ НАЙДЕН !");
                actionKeyIdText.clear();
                actionKeyIdText.requestFocus();
                toConsole("ключ c ID " + key_barcode + " не найден.");
            } else {
                entryRadioId = curradio.get(0).getId();
                entryRadioNumber = curradio.get(0).getNumber();
                Calendar calendar = Calendar.getInstance();
                Timestamp ts = new Timestamp(calendar.getTime().getTime());

                //проверка на наличие записи в журнале о выданном ранее ЭТОМ ключе ЭТОМУ сотруднику
                entriesAll = JournalEntryDAO.searchTakenDeviceByEmp(entryEmpName, entryOpStatus[0], entryRadioNumber);
                //если записи имеются, то значит сотрудник сдает ключ
                if (entriesAll == null) {
                    //сотрудник получает ключ
                    // проверка на отсутствие записей со статусом "начат" в журнале у данного сотрудника
                    if (empRadioNumber == null || empRadioNumber.isEmpty()) {

                        if (curradio.get(0).getAvailable().contains("да")) {
                            actionConfirmLabel.setText("СОТРУДНИКУ " + entryEmpName + " ВЫДАНА РАЦИЯ " + entryRadioNumber + ".\n ВРЕМЯ НАЧАЛА РАБОТЫ: " + ts);

                            RadioDAO.updateRadio(entryRadioId, entryRadioNumber, curradio.get(0).getModel(), curradio.get(0).getSn(), curradio.get(0).getCondition(), "нет", curradio.get(0).getDescription());
                            EmployeeDAO.updateEmployeeRadio(entryEmpId, entryRadioNumber);
                            JournalEntryDAO.insertEntry(ts, entryOpType[0], entryEmpName, entryOpStatus[0], "рация", entryRadioNumber, ts, "", entryEmpId, null, entryRadioId);

                            termTypeForEmpl = null;
                            entryEmpName = null;
                            entryEmpId = null;
                            entryRadioId = null;
                            entryRadioNumber = null;
                            empRadioNumber = null;
                            empTermNumber = null;
                            empRtsdNumber = null;
                            setBarcodeFocus();

                        } else {
                            actionConfirmLabel.setStyle("-fx-text-fill: red;");
                            actionConfirmLabel.setText("РАЦИЯ СО ШК " + key_barcode + " УЖЕ ВЫДАНА !");
                            actionKeyIdText.clear();
                            actionKeyIdText.requestFocus();
                            toConsole("ключ c ID " + key_barcode + " выдан ранее.");
                        }
                    } else {
                        actionConfirmLabel.setStyle("-fx-text-fill: red;");
                        actionConfirmLabel.setText("ВЫ УЖЕ РАБОТАЕТE С РАЦИЕЙ НОМЕР " + empRadioNumber + " !\n В ВЫДАЧЕ ОТКАЗАНО !");
                        actionKeyIdText.clear();
                        actionKeyIdText.requestFocus();
                        toConsole("СОТРУДНИК " + entryEmpName + " УЖЕ РАБОТАЕТ С РАЦИЕЙ НОМЕР " + empRadioNumber + " !");
                    }

                } else {
                    //сотрудник сдает ключ
                    actionConfirmLabel.setText("СОТРУДНИК " + entryEmpName + " СДАЛ РАЦИЮ " + entryRadioNumber + ".\nВРЕМЯ ОКОНЧАНИЯ РАБОТЫ: " + ts);

                    RadioDAO.updateRadio(entryRadioId, entryRadioNumber, curradio.get(0).getModel(), curradio.get(0).getSn(), curradio.get(0).getCondition(), "да", curradio.get(0).getDescription());
                    EmployeeDAO.updateEmployeeRadio(entryEmpId, "");
                    JournalEntryDAO.updateEntry(entriesAll.get(0).getId(), entryOpType[0], entryEmpName, entryOpStatus[1], "рация", entryRadioNumber, ts, "");

                    termTypeForEmpl = null;
                    entryEmpName = null;
                    entryEmpId = null;
                    entryRadioId = null;
                    entryRadioNumber = null;
                    empRtsdNumber = null;
                    setBarcodeFocus();
                }

            }
        } catch (SQLException e) {
            toConsole("Problem occurred while retrieving terminal " + e);
            throw e;
        }
        return true;
    }

    @FXML
    public void retrieveFromDB() throws SQLException, ClassNotFoundException {
        String db = listDB.getSelectionModel().getSelectedItem().toString();

        termData = null;
        emplData = null;
        radioData = null;

        switch (db) {
            case "терминалы":
                try {
                    termData = TerminalDAO.searchTerminals();
                    termType = TerminalDAO.searchTerminalTypes();
                    if (tableEmployee.isVisible() || tableRadio.isVisible()) {
                        tableEmployee.setVisible(false);
                        tableRadio.setVisible(false);
                    }
                    tableRT.setItems(termData);
                    tableRT.setVisible(true);
                    tableRT.getSelectionModel().selectFirst();

                } catch (SQLException e) {
                    toConsole("Error occurred while getting terminals' information from DB.\n" + e);
                    throw e;
                }
                break;
            case "рации":
                try {
                    radioData = RadioDAO.searchRadios();

                    if (tableEmployee.isVisible() || tableRT.isVisible()) {
                        tableEmployee.setVisible(false);
                        tableRT.setVisible(false);
                    }
                    tableRadio.setItems(radioData);
                    tableRadio.setVisible(true);
                    tableRadio.getSelectionModel().selectFirst();

                } catch (SQLException e) {
                    toConsole("Error occurred while getting radios' information from DB.\n" + e);
                    throw e;
                }
                break;
            case "сотрудники":

                try {
                    emplData = EmployeeDAO.searchEmployees();
                    if (tableRT.isVisible() || tableRadio.isVisible()) {
                        tableRT.setVisible(false);
                        tableRadio.setVisible(false);
                    }
                    tableEmployee.getSortOrder().clear();
                    tableEmployee.setItems(emplData);
                    currTermColumn.setComparator(currTermColumn.getComparator().reversed());
                    tableEmployee.getSortOrder().addAll(currTermColumn);
                    tableEmployee.setVisible(true);
                    tableEmployee.getSelectionModel().selectFirst();

                } catch (SQLException e) {
                    toConsole("Error occurred while getting employees' information from DB.\n" + e);
                    throw e;
                }
                break;
            default:
                toConsole("таблица не найдена");
                break;
        }
        btnAdd.setDisable(false);
        btnDelete.setDisable(false);

        isUpdateButtonLabel = true;
        retrieveButton.setText("Обновить");

    }

    public Stage showTermEditDialog(Terminal currTerm, ObservableList<String> uniqueIdData, ObservableList<String> uniqueNumbersData, ObservableList<String> uniqueSnNumbersData) {
        Stage stage = new Stage();
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("TermPulseTermEdit.fxml"));
            stage.setScene(new Scene(fxmlLoader.load()));
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initStyle(StageStyle.DECORATED);
            stage.setTitle("Редактирование реквизитов терминала");
            stage.getIcons().add(new Image(TermPulse.class.getResourceAsStream("/icon.gif")));
            TermPulseTermEditController controller = fxmlLoader.<TermPulseTermEditController>getController();
            controller.setData(currTerm, uniqueIdData, uniqueNumbersData, uniqueSnNumbersData);
            stage.show();
        } catch (IOException e) {
        }

        return stage;
    }

    public Stage showRadioEditDialog(Radio currRadio, ObservableList<String> uniqueIdData, ObservableList<String> uniqueNumbersData, ObservableList<String> uniqueSnNumbersData) {
        Stage stage = new Stage();
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("TermPulseRadioEdit.fxml"));
            stage.setScene(new Scene(fxmlLoader.load()));
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initStyle(StageStyle.DECORATED);
            stage.setTitle("Редактирование реквизитов рации");
            stage.getIcons().add(new Image(TermPulse.class.getResourceAsStream("/icon.gif")));
            TermPulseRadioEditController controller = fxmlLoader.<TermPulseRadioEditController>getController();
            controller.setData(currRadio, uniqueIdData, uniqueNumbersData, uniqueSnNumbersData);
            stage.show();
        } catch (IOException e) {
        }

        return stage;
    }

    public Stage showEmplEditDialog(Employee currEmpl, ObservableList<String> uniqueIdData, ObservableList<String> uniqueNamesData) {
        Stage stage = new Stage();
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("TermPulseEmplEdit.fxml"));
            stage.setScene(new Scene(fxmlLoader.load()));
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initStyle(StageStyle.DECORATED);
            stage.setTitle("Редактирование сведений о сотруднике");
            stage.setResizable(false);
            stage.getIcons().add(new Image(TermPulse.class.getResourceAsStream("/icon.gif")));
            TermPulseEmplEditController controller = fxmlLoader.<TermPulseEmplEditController>getController();
            controller.setData(currEmpl, uniqueIdData, uniqueNamesData);
            stage.show();
        } catch (IOException e) {
        }

        return stage;
    }

    @FXML
    private void insertRowtoDB(ActionEvent event) throws SQLException, ClassNotFoundException {

        ObservableList<Terminal> terminalAll;
        ObservableList<Employee> employeeAll;
        ObservableList<Radio> radioAll;
        ObservableList<String> uniqueNumbersData = FXCollections.observableArrayList();
        ObservableList<String> uniqueIdData = FXCollections.observableArrayList();
        ObservableList<String> uniqueSnNumbersData = FXCollections.observableArrayList();
        ObservableList<String> uniqueIdEmployeesData = FXCollections.observableArrayList();
        ObservableList<String> uniqueNameEmployeesData = FXCollections.observableArrayList();
        int id_term, id_emp;

        uniqueNumbersData.clear();
        uniqueSnNumbersData.clear();
        uniqueIdData.clear();
        String db = listDB.getSelectionModel().getSelectedItem().toString();

        if (db == "сотрудники") {
            employeeAll = tableEmployee.getItems();
            for (Employee emp : employeeAll) {
                uniqueIdEmployeesData.add(emp.getId());
                uniqueNameEmployeesData.add(emp.getName());
            }
            showEmplEditDialog(tableEmployee.getSelectionModel().getSelectedItem(), uniqueIdEmployeesData, uniqueNameEmployeesData);
        } else if (db == "терминалы") {
            terminalAll = tableRT.getItems();
            for (Terminal term : terminalAll) {
                uniqueNumbersData.add(term.getNumber());
                uniqueSnNumbersData.add(term.getSn());
                uniqueIdData.add(term.getId());
            }
            showTermEditDialog(tableRT.getSelectionModel().getSelectedItem(), uniqueIdData, uniqueNumbersData, uniqueSnNumbersData);
        } else if (db == "рации") {
            radioAll = tableRadio.getItems();
            for (Radio a_radio : radioAll) {
                uniqueNumbersData.add(a_radio.getNumber());
                uniqueSnNumbersData.add(a_radio.getSn());
                uniqueIdData.add(a_radio.getId());
            }
            showRadioEditDialog(tableRadio.getSelectionModel().getSelectedItem(), uniqueIdData, uniqueNumbersData, uniqueSnNumbersData);
        }
    }

    @FXML
    private void deleteRowfromDB(ActionEvent event) throws SQLException, ClassNotFoundException {
        String db = listDB.getSelectionModel().getSelectedItem().toString();

        if (db == "сотрудники") {
            ObservableList<Employee> employeeSelected = tableEmployee.getSelectionModel().getSelectedItems();
            if (showConfirmAlert("Вы действительно хотите удалить запись о сотруднике " + employeeSelected.get(0).getName() + " ?")) {
                try {                    
                    EmployeeDAO.removeEmployee(employeeSelected.get(0).getId());
                    toConsole("Запись о сотруднике " + employeeSelected.get(0).getName() + " удалена.");
                    retrieveFromDB();

                } catch (SQLException e) {
                    toConsole("Problem occurred while deleting employee " + e);
                    throw e;
                }
            }
        } else if (db == "терминалы") {
            ObservableList<Terminal> terminalSelected = tableRT.getSelectionModel().getSelectedItems();
            if (showConfirmAlert("Вы действительно хотите удалить запись о терминале " + terminalSelected.get(0).getNumber() + " ?")) {
                try {                    
                    TerminalDAO.removeRT(terminalSelected.get(0).getId());
                    toConsole("Запись о терминале " + terminalSelected.get(0).getNumber() + " удалена.");
                    retrieveFromDB();

                } catch (SQLException e) {
                    toConsole("Problem occurred while deleting rt " + e);
                    showAlert(e.toString());
                    throw e;
                }
            }
        } else if (db == "рации") {
            ObservableList<Radio> radioSelected = tableRadio.getSelectionModel().getSelectedItems();
            if (showConfirmAlert("Вы действительно хотите удалить запись о рации " + radioSelected.get(0).getNumber() + " ?")) {
                try {
                    RadioDAO.removeRadio(radioSelected.get(0).getId());
                    toConsole("Запись о рации " + radioSelected.get(0).getNumber() + " удалена.");
                    retrieveFromDB();

                } catch (SQLException e) {
                    toConsole("Problem occurred while deleting radio " + e);
                    showAlert(e.toString());
                    throw e;
                }
            }
        }
    }

    @FXML
    private void actionOnEnterEmp(ActionEvent event) throws SQLException, ClassNotFoundException {
        ObservableList<Terminal> termAvailable = FXCollections.observableArrayList();
        ObservableList<String> termAvailableList = FXCollections.observableArrayList();
        ObservableList<Employee> employee = FXCollections.observableArrayList();
        entryEmpName = null;
        entryEmpId = null;
        empTermNumber = null;
        empRtsdNumber = null;
        empRadioNumber = null;

        String barcode = actionBarcodeText.getText();
        try {
            employee = EmployeeDAO.searchEmployeeById(barcode);

            if (employee == null) {
                //showAlert("Сотрудник cо ШК " + barcode + " не найден.");
                actionConfirmLabel.setStyle("-fx-text-fill: red;");
                actionConfirmLabel.setText("СОТРУДНИК СО ШК " + barcode + " НЕ НАЙДЕН !");
                toConsole("Сотрудник cо ШК " + barcode + " не найден.");
                actionBarcodeText.clear();
                actionEmpText.clear();
                actionEmpPostText.clear();
                actionBarcodeText.requestFocus();
            } else {
                entryEmpName = employee.get(0).getName();
                entryEmpId = employee.get(0).getId();
                empTermNumber = employee.get(0).getCurrTerm();
                empRtsdNumber = employee.get(0).getCurrRtsd();
                empRadioNumber = employee.get(0).getCurrRadio();
                actionEmpText.setText(entryEmpName);
                actionEmpPostText.setText(employee.get(0).getPost());
                if (empRadioNumber != null && !empRadioNumber.isEmpty()) {
                    if ((empTermNumber != null && !empTermNumber.isEmpty()) || (empRtsdNumber != null && !empRtsdNumber.isEmpty())) {
                        if ((empRtsdNumber != null && !empRtsdNumber.isEmpty()) && (empTermNumber != null && !empTermNumber.isEmpty())) {
                            actionInfoLabel.setText("СОТРУДНИК: " + entryEmpName + "\n" + "СЕЙЧАС РАБОТАЕТ С РАЦИЕЙ " + empRadioNumber + " И ТЕРМИНАЛАМИ " + empTermNumber + " и " + empRtsdNumber
                                    + "\nОТСКАНИРУЙТЕ ШК КЛЮЧА ");
                        } else if (empRtsdNumber != null && !empRtsdNumber.isEmpty()) {
                            actionInfoLabel.setText("СОТРУДНИК: " + entryEmpName + "\n" + "СЕЙЧАС РАБОТАЕТ С РАЦИЕЙ " + empRadioNumber + " и С ТЕРМИНАЛОМ " + empRtsdNumber
                                    + "\nОТСКАНИРУЙТЕ ШК КЛЮЧА ");
                        } else if (empTermNumber != null && !empTermNumber.isEmpty()) {
                            actionInfoLabel.setText("СОТРУДНИК: " + entryEmpName + "\n" + "СЕЙЧАС РАБОТАЕТ С РАЦИЕЙ " + empRadioNumber + " и С ТЕРМИНАЛОМ " + empTermNumber
                                    + "\nОТСКАНИРУЙТЕ ШК КЛЮЧА ");
                        }
                    } else {
                        actionInfoLabel.setText("СОТРУДНИК: " + entryEmpName + "\n"
                                + "\nОТСКАНИРУЙТЕ ШК КЛЮЧА ");
                    }
                } else {
                    if ((empTermNumber != null && !empTermNumber.isEmpty()) || (empRtsdNumber != null && !empRtsdNumber.isEmpty())) {
                        if ((empRtsdNumber != null && !empRtsdNumber.isEmpty()) && (empTermNumber != null && !empTermNumber.isEmpty())) {
                            actionInfoLabel.setText("СОТРУДНИК: " + entryEmpName + "\n" + "СЕЙЧАС РАБОТАЕТ С ТЕРМИНАЛАМИ " + empTermNumber + " и " + empRtsdNumber
                                    + "\nОТСКАНИРУЙТЕ ШК КЛЮЧА ");
                        } else if (empRtsdNumber != null && !empRtsdNumber.isEmpty()) {
                            actionInfoLabel.setText("СОТРУДНИК: " + entryEmpName + "\n" + "СЕЙЧАС РАБОТАЕТ С ТЕРМИНАЛОМ " + empRtsdNumber
                                    + "\nОТСКАНИРУЙТЕ ШК КЛЮЧА ");
                        } else if (empTermNumber != null && !empTermNumber.isEmpty()) {
                            actionInfoLabel.setText("СОТРУДНИК: " + entryEmpName + "\n" + "СЕЙЧАС РАБОТАЕТ С ТЕРМИНАЛОМ " + empTermNumber
                                    + "\nОТСКАНИРУЙТЕ ШК КЛЮЧА ");
                        }

                    } else {
                        actionInfoLabel.setText("СОТРУДНИК: " + entryEmpName + "\n"
                                + "\nОТСКАНИРУЙТЕ ШК КЛЮЧА ");
                    }
                }
                actionConfirmLabel.setText("");
                actionBarcodeText.clear();
                actionBarcodeText.setVisible(false);
                actionKeyIdText.setVisible(true);
                actionKeyIdText.requestFocus();
            }
        } catch (SQLException e) {
            toConsole("Problem occurred while retrieving employee " + e);
            throw e;
        }
    }

    @FXML
    private void actionOnEnterKeyBarcode(ActionEvent event) throws SQLException, ClassNotFoundException {

        actionConfirmLabel.setStyle("-fx-text-fill: green;");

        String key_barcode = actionKeyIdText.getText();
        if (key_barcode.length() < 7) {
            actionConfirmLabel.setStyle("-fx-text-fill: red;");
            actionConfirmLabel.setText("НЕКОРРЕКТНЫЙ ФОРМАТ ШК: " + key_barcode);
            actionKeyIdText.clear();
            actionKeyIdText.requestFocus();
            toConsole("НЕКОРРЕКТНЫЙ ФОРМАТ ШК: " + key_barcode);
            return;
        }
        String dev_type = key_barcode.substring(0, 2);
        switch (dev_type) {
            case "99":
                writeRadioEntryLog(key_barcode);
                break;
            case "00":
                writeTermEntryLog(key_barcode);
                break;
            default:
                actionConfirmLabel.setStyle("-fx-text-fill: red;");
                actionConfirmLabel.setText("НЕКОРРЕКТНЫЙ ФОРМАТ ШК: " + key_barcode);
                actionKeyIdText.clear();
                actionKeyIdText.requestFocus();
                toConsole("НЕКОРРЕКТНЫЙ ФОРМАТ ШК: " + key_barcode);
                break;
        }
    }

//    @FXML
//    private void findEntryByNumber(ActionEvent event) {
//        String db = listDB.getSelectionModel().getSelectedItem().toString();
//        if (db == "терминалы") {
//            FilteredList<Terminal> filteredTermData = new FilteredList<>(termData, entry -> true);
//
//            filteredTermData.setPredicate((Predicate<? super Terminal>) term -> {
//                if (numberText == null || numberText.getText().isEmpty()) {
//                    return true;
//                }
//                //String lowerCaseFilter=numberText.getText().toLowerCase();
//                if (term.getNumber().contains(numberText.getText())) {
//                    return true;
//                }
//                return false;
//            });
//            if (filteredTermData.isEmpty()) {
//                tableRT.setItems(termData);
//                toConsole("терминал не найден");
//            } else {
//                SortedList<Terminal> sortedTermData = new SortedList<>(filteredTermData);
//                sortedTermData.comparatorProperty().bind(tableRT.comparatorProperty());
//                tableRT.setItems(sortedTermData);
//            }
//        } else if (db == "рации") {
//            FilteredList<Radio> filteredRadioData = new FilteredList<>(radioData, entry -> true);
//
//            filteredRadioData.setPredicate((Predicate<? super Radio>) a_radio -> {
//                if (numberText == null || numberText.getText().isEmpty()) {
//                    return true;
//                }
//                //String lowerCaseFilter=numberText.getText().toLowerCase();
//                if (a_radio.getNumber().contains(numberText.getText())) {
//                    return true;
//                }
//                return false;
//            });
//            if (filteredRadioData.isEmpty()) {
//                tableRadio.setItems(radioData);
//                toConsole("рация не найдена");
//            } else {
//                SortedList<Radio> sortedRadioData = new SortedList<>(filteredRadioData);
//                sortedRadioData.comparatorProperty().bind(tableRadio.comparatorProperty());
//                tableRadio.setItems(sortedRadioData);
//            }
//        }
//
//    }
//
//    @FXML
//    private void findEntryBySerial(ActionEvent event) {
//        FilteredList<Terminal> filteredTermData = new FilteredList<>(termData, entry -> true);
//
//        filteredTermData.setPredicate((Predicate<? super Terminal>) term -> {
//            if (snText == null || snText.getText().isEmpty()) {
//                return true;
//            }
//            //String lowerCaseFilter=numberText.getText().toLowerCase();
//            if (term.getSn().contains(snText.getText())) {
//                return true;
//            }
//            return false;
//        });
//        if (filteredTermData.isEmpty()) {
//            tableRT.setItems(termData);
//            toConsole("терминал не найден");
//        } else {
//            SortedList<Terminal> sortedTermData = new SortedList<>(filteredTermData);
//            sortedTermData.comparatorProperty().bind(tableRT.comparatorProperty());
//            tableRT.setItems(sortedTermData);
//        }
//    }
//
//    @FXML
//    private void findEntryByScanner(ActionEvent event) {
//        FilteredList<Terminal> filteredTermData = new FilteredList<>(termData, entry -> true);
//
//        filteredTermData.setPredicate((Predicate<? super Terminal>) term -> {
//            if (sn_scannerText == null || sn_scannerText.getText().isEmpty()) {
//                return true;
//            }
//            //String lowerCaseFilter=numberText.getText().toLowerCase();
//            if (term.getSnScanner().contains(sn_scannerText.getText())) {
//                return true;
//            }
//            return false;
//        });
//        if (filteredTermData.isEmpty()) {
//            tableRT.setItems(termData);
//            toConsole("терминал не найден");
//        } else {
//            SortedList<Terminal> sortedTermData = new SortedList<>(filteredTermData);
//            sortedTermData.comparatorProperty().bind(tableRT.comparatorProperty());
//            tableRT.setItems(sortedTermData);
//        }
//    }
//
//    @FXML
//    private void findEntryByEmpId(ActionEvent event) {
//        FilteredList<Employee> filteredEmpData = new FilteredList<>(emplData, entry -> true);
//
//        filteredEmpData.setPredicate((Predicate<? super Employee>) empl -> {
//            if (conditionOrIdText == null || conditionOrIdText.getText().isEmpty()) {
//                return true;
//            }
//            //String lowerCaseFilter=numberText.getText().toLowerCase();
//            if (empl.getId().contains(conditionOrIdText.getText())) {
//                return true;
//            }
//            return false;
//        });
//        if (filteredEmpData.isEmpty()) {
//            tableEmployee.setItems(emplData);
//            toConsole("сотрудник не найден");
//        } else {
//            SortedList<Employee> sortedEmpData = new SortedList<>(filteredEmpData);
//            sortedEmpData.comparatorProperty().bind(tableEmployee.comparatorProperty());
//            tableEmployee.setItems(sortedEmpData);
//        }
//    }
//
//    @FXML
//    private void findEntryByName(ActionEvent event) {
//        FilteredList<Employee> filteredEmpData = new FilteredList<>(emplData, entry -> true);
//
//        filteredEmpData.setPredicate((Predicate<? super Employee>) empl -> {
//            if (availableOrNameText == null || availableOrNameText.getText().isEmpty()) {
//                return true;
//            }
//            //String lowerCaseFilter=numberText.getText().toLowerCase();
//            if (empl.getName().contains(availableOrNameText.getText())) {
//                return true;
//            }
//            return false;
//        });
//        if (filteredEmpData.isEmpty()) {
//            tableEmployee.setItems(emplData);
//            toConsole("сотрудник не найден");
//        } else {
//            SortedList<Employee> sortedEmpData = new SortedList<>(filteredEmpData);
//            sortedEmpData.comparatorProperty().bind(tableEmployee.comparatorProperty());
//            tableEmployee.setItems(sortedEmpData);
//        }
//    }

    @FXML
    private void journalCloseOperation(ActionEvent event) throws SQLException, ClassNotFoundException {
        JournalEntry j_entry = tableJournal.getSelectionModel().getSelectedItem();

        if (j_entry == null) {
            toConsole("не выбрана запись в журнале. Операция закрытия не выполнена.");
            return;
        }
        if (showConfirmAlert("Вы действительно хотите закрыть операцию выдачи ?")) {
            String id = j_entry.getId();
            String employee_id = j_entry.getEmployeeId();
            String operation_status = j_entry.getOperationStatus();
            String device = j_entry.getDevice();
            String terminal_id = j_entry.getTerminalId();
            String radio_id = j_entry.getRadioId();
            if (operation_status.contains(entryOpStatus[1])) {
                showAlert("Операция уже закрыта!");
                return;
            }

            Calendar calendar = Calendar.getInstance();
            Timestamp ts = new Timestamp(calendar.getTime().getTime());
            try {
                JournalEntryDAO.closeOperationEntry(id, entryOpStatus[1], ts);
                switch (device) {
                    case "сборка_конвейер":
                        TerminalDAO.updateRTAvailability(terminal_id, "да");
                        EmployeeDAO.updateEmployeeTerminal(employee_id, "");
                        break;
                    case "сборка_тэо":
                        TerminalDAO.updateRTAvailability(terminal_id, "да");
                        EmployeeDAO.updateEmployeeTerminal(employee_id, "");
                        break;
                    case "подпитка":
                        TerminalDAO.updateRTAvailability(terminal_id, "да");
                        EmployeeDAO.updateEmployeeRtsd(employee_id, "");
                        break;
                    case "приемка":
                        TerminalDAO.updateRTAvailability(terminal_id, "да");
                        EmployeeDAO.updateEmployeeRtsd(employee_id, "");
                        break;
                    case "рация":
                        RadioDAO.updateRadioAvailability(radio_id, "да");
                        EmployeeDAO.updateEmployeeRadio(employee_id, "");
                        break;
                }
                JournalEntryDAO.updateEntry("Операция закрыта принудительно", id);
                toConsole("Операция выдачи  c id = " + id + " сотруднику " + employee_id + " закрыта принудительно");
            } catch (SQLException e) {
                toConsole("Problem occurred while update log entry " + e);
                throw e;
            }
            retrieveJournalFromDB();
        }
    }

    @FXML
    private void retrieveJournalFromDB(ActionEvent event) throws SQLException, ClassNotFoundException {
        retrieveJournalFromDB();
    }

    private void retrieveJournalFromDB() throws SQLException, ClassNotFoundException {
        String filter = journalStatusRBGroup.getSelectedToggle().getUserData().toString();
        allEntriesNum = 0;
        activeTermEntriesNum = 0;
        activeRadioEntriesNum = 0;
        try {
            Timestamp begin = Timestamp.valueOf(datePickBegin.getValue().atStartOfDay());
            Timestamp end = Timestamp.valueOf(datePickEnd.getValue().atStartOfDay());
            if (filter == "все") {
                journalData = JournalEntryDAO.searchEntriesByPeriod(begin, end);

            } else {
                journalData = JournalEntryDAO.searchBeginEntriesByPeriod(begin, end, entryOpStatus[0]);
            }

            dtJournalColumn.setComparator(dtJournalColumn.getComparator().reversed());
            tableJournal.getSortOrder().clear();
            tableJournal.setItems(journalData);
            tableJournal.getSortOrder().addAll(dtJournalColumn);

            showJournalStat();
        } catch (SQLException e) {
            toConsole("Error occurred while getting terminals' information from DB.\n" + e);
            throw e;
        }
        journalCloseOpButton.setDisable(true);
        journalAddDescButton.setDisable(true);
    }

    private void findJournalEntriesByDevice() throws SQLException, ClassNotFoundException {
        try {
            Timestamp begin = Timestamp.valueOf(datePickBegin.getValue().atStartOfDay());
            Timestamp end = Timestamp.valueOf(datePickEnd.getValue().atStartOfDay());
            journalData = JournalEntryDAO.searchAllEntriesByDevice(begin, end, journalDeviceText.getText());
            tableJournal.setItems(journalData);
            showJournalStat();
        } catch (SQLException e) {
            toConsole("Error occurred while getting terminals' information from DB.\n" + e);
            throw e;
        }
        journalCloseOpButton.setDisable(true);
        journalAddDescButton.setDisable(true);
    }

    private void findJournalEntriesByEmployee() throws SQLException, ClassNotFoundException {
        try {
            Timestamp begin = Timestamp.valueOf(datePickBegin.getValue().atStartOfDay());
            Timestamp end = Timestamp.valueOf(datePickEnd.getValue().atStartOfDay());
            journalData = JournalEntryDAO.searchAllEntriesByEmployee(begin, end, journalEmployeeText.getText());
            tableJournal.setItems(journalData);
            showJournalStat();
            //toConsole("Загрузка/обновление данных... успешно");
        } catch (SQLException e) {
            toConsole("Error occurred while getting terminals' information from DB.\n" + e);
            throw e;
        }
        journalCloseOpButton.setDisable(true);
        journalAddDescButton.setDisable(true);
    }

    private void showJournalStat() {
        freeRadiosNumber = 0;
        freeTermsNumber = 0;
        freeRtsdNumber = 0;
        freeTermsNumber = TerminalDAO.getAvailableTermNumber("да");
        freeRtsdNumber = TerminalDAO.getAvailableRtsdNumber("да");
        freeRadiosNumber = RadioDAO.getAvailableRadiosNumber();
        allTermsNumber = TerminalDAO.getAllTermNumber();
        allRtsdNumber = TerminalDAO.getAllRtsdNumber();
        allRadiosNumber = RadioDAO.getAllRadiosNumber();

        if (tableJournal.getItems() == null) {
            allEntriesNum = 0;
            activeTermEntriesNum = 0;
            activeRtsdEntriesNum = 0;
            activeRadioEntriesNum = 0;
        } else {
            allEntriesNum = tableJournal.getItems().size();

            for (JournalEntry j_entry : journalData) {
                if (j_entry.getOperationStatus().contains(entryOpStatus[0]) & !j_entry.getDevice().contains("рация")) {
                    activeTermEntriesNum++;
                }
                if (j_entry.getOperationStatus().contains(entryOpStatus[0]) & j_entry.getDevice().contains("рация")) {
                    activeRadioEntriesNum++;
                }
            }
        }
        journalStatusLabel.setText("Доступно сборочных терминалов: " + freeTermsNumber + " из " + allTermsNumber + " / РТСД терминалов: " + freeRtsdNumber + " из " + allRtsdNumber
                + " / Раций: " + freeRadiosNumber + " из " + allRadiosNumber
                + " \t\t Записей всего: " + allEntriesNum + " \tТерминалов в работе: " + activeTermEntriesNum + " \t Раций в работе: " + activeRadioEntriesNum);
    }

    @FXML
    private void journalAddDescription(ActionEvent event) throws ClassNotFoundException, SQLException {
        JournalEntry j_entry = tableJournal.getSelectionModel().getSelectedItem();

        if (j_entry == null) {
            toConsole("не выбрана запись в журнале. Операция не выполнена.");
            return;
        }
        String desc = showTextInputDialog("что-то важное", "Добавление сведений по выбранной операции", "Поле примечание в журнале учета операций", "Пожалуйста введите текст : ");
        if (!desc.isEmpty()) {
            String id = j_entry.getId();
            try {
                JournalEntryDAO.updateEntry(desc, id);
                toConsole("Запись успешно обновлена");
            } catch (SQLException e) {
                toConsole("Problem occurred while update employee " + e);
                throw e;
            }
            retrieveJournalFromDB();
        }

    }

    @FXML
    private void actionOnCliсked(MouseEvent event) {
        if (actionBarcodeText.isVisible()) {
            actionBarcodeText.requestFocus();
        } else if (actionKeyIdText.isVisible()) {
            actionKeyIdText.requestFocus();
        }
    }

    @FXML
    private void journalFindByDevice(ActionEvent event) throws SQLException, ClassNotFoundException {
        findJournalEntriesByDevice();
        toConsole("Поиск записей по номеру устройства");
    }

    @FXML
    private void journalFindByEmployee(ActionEvent event) throws SQLException, ClassNotFoundException {
        findJournalEntriesByEmployee();
        toConsole("Поиск записей по имени сотрудника");
    }
    
}
