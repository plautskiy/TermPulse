/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package termpulse;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author kroni
 * @param <T>
 */
public class RtBaseDAO<T> {
    private Connection connection=null;
    //public static final String JDBC_DRIVER ="com.mysql.jdbc.Driver";
    //public String url="jdbc:mysql://localhost/rt";;
    //public String url="jdbc:mysql://localhost/rt";;
    //public String url="jdbc:mysql://localhost/rt";;
    public String jdbc_driver,url,user,password,useUnicode,charSet;
    public Properties connInfo = new Properties();
   
    public RtBaseDAO() {
        try {
            getDBProperties();
        } catch (IOException ex) {
            Logger.getLogger(RtBaseDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    private void getDBProperties() throws FileNotFoundException, IOException {
        InputStream input = null;
        try {
//            File jarPath=new File(TermPulse.class.getProtectionDomain().getCodeSource().getLocation().getPath());
//            String propertiesPath=jarPath.getParent();
//            System.out.println(" propertiesPath-"+propertiesPath);
            //input = RtBaseDAO.class.getClassLoader().getResourceAsStream(TermPulse.dbPropertiesFile);
            input = new FileInputStream("./db.properties");
            if (input == null) {
                return;
            }
            connInfo.load(input);
            jdbc_driver=connInfo.getProperty("jdbc_driver");
            url = connInfo.getProperty("url");
            user = connInfo.getProperty("user");
            password = connInfo.getProperty("password");
            //useUnicode=connInfo.getProperty("useUnicode");
            //charSet=connInfo.getProperty("useUnicode");
        } catch (IOException ex) {
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                }
            }
        }


    }
    public Connection getConnection() throws SQLException, ClassNotFoundException {
        try {
            Class.forName(jdbc_driver).newInstance();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException cnfe) {
            System.out.println("Error: " + cnfe.getMessage());
        }
        try {
            connection = DriverManager.getConnection(url, user,password);
        } catch (SQLException e) {
            System.out.println("Connection Failed! Check output console" + e);
            throw e;
        }
        return connection;
    }
    
    public ObservableList<T> getAll(String sqlStatement,DBResultSetProcessor<T> resultSetProcessor) {
        return getAll(sqlStatement, resultSetProcessor, new Object[] {});
    }

    public ObservableList<T> getAll(String sqlStatement, DBResultSetProcessor<T> resultSetProcessor, Object[] args) {

        ObservableList<T> list = FXCollections.observableArrayList();
        Connection a_connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            a_connection = getConnection();
            preparedStatement = a_connection.prepareStatement(sqlStatement);
            for (int i = 0; i < args.length; i++) {
                preparedStatement.setObject(i + 1, args[i]);
            }
            resultSet = preparedStatement.executeQuery();
            if (!resultSet.isBeforeFirst() ) {    
                //System.out.println("No data");
                return null;
            }
            while (resultSet.next()) {
                list.add(resultSetProcessor.getProcessedObject(resultSet));
            }

        } catch (ClassNotFoundException | SQLException e) {
        } finally {
            try {
                a_connection.close();
            } catch (SQLException e) {
            }
            try {
                preparedStatement.close();
            } catch (SQLException e) {
            }
            try {
                resultSet.close();
            } catch (SQLException e) {
            }
        }

        return list;
    }
    public void deleteAll(String sqlStatement) {
        delete(sqlStatement, new Object[] {});
    }

    public void delete(String sqlStatement, Object[] args) {
        Connection a_connection = null;
        PreparedStatement preparedStatement = null;

        try {
            a_connection = getConnection();
            preparedStatement = a_connection.prepareStatement(sqlStatement);
            for (int i = 0; i < args.length; i++) {
                preparedStatement.setObject(i + 1, args[i]);
            }
            preparedStatement.executeUpdate();
        } catch (ClassNotFoundException | SQLException e) {
        } finally {
            try {
                a_connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    public void addOrSaveOrUpdate(String sqlStatement, Object[] args) {
        Connection a_connection = null;
        PreparedStatement preparedStatement = null;

        try {
            a_connection = getConnection();
            preparedStatement = a_connection.prepareStatement(sqlStatement);
            for (int i = 0; i < args.length; i++) {
                preparedStatement.setObject(i + 1, args[i]);
            }
            preparedStatement.executeUpdate();
        } catch (ClassNotFoundException | SQLException e) {
        } finally {
            try {
                a_connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                preparedStatement.close();
            } catch (SQLException e) {
            }
        }

    }    
}

//public class BarcodeReader {
//
//private static final long THRESHOLD = 100;
//private static final int MIN_BARCODE_LENGTH = 8;
//
//public interface BarcodeListener {
//
//    void onBarcodeRead(String barcode);
//}
//
//private final StringBuffer barcode = new StringBuffer();
//private final List<BarcodeListener> listeners = new CopyOnWriteArrayList<BarcodeListener>();
//private long lastEventTimeStamp = 0L;
//
//public BarcodeReader() {
//
//    KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(new KeyEventDispatcher() {
//        @Override
//        public boolean dispatchKeyEvent(KeyEvent e) {
//            if (e.getID() != KeyEvent.KEY_RELEASED) {
//                return false;
//            }
//
//            if (e.getWhen() - lastEventTimeStamp > THRESHOLD) {
//                barcode.delete(0, barcode.length());
//            }
//
//            lastEventTimeStamp = e.getWhen();
//
//            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
//                if (barcode.length() >= MIN_BARCODE_LENGTH) {
//                    fireBarcode(barcode.toString());
//                }
//                barcode.delete(0, barcode.length());
//            } else {
//                barcode.append(e.getKeyChar());
//            }
//            return false;
//        }
//    });
//
//}
//
//protected void fireBarcode(String barcode) {
//    for (BarcodeListener listener : listeners) {
//        listener.onBarcodeRead(barcode);
//    }
//}
//
//public void addBarcodeListener(BarcodeListener listener) {
//    listeners.add(listener);
//}
//
//public void removeBarcodeListener(BarcodeListener listener) {
//    listeners.remove(listener);
//}