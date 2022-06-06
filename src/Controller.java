import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.Node;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.Date;
import javax.swing.JOptionPane;

public class Controller implements Initializable {

    Connection con;
    PreparedStatement pst;
    ResultSet rs;

    @FXML
    private Label TgglInputD;

    @FXML
    private Label deadLineD;

    @FXML
    private DatePicker deadLineInput;

    @FXML
    private TableColumn<App, Integer> deadlinecol;

    @FXML
    private Label detailsD;

    @FXML
    private TextArea detailsInput;

    @FXML
    private Label mataKuliahD;

    @FXML
    private TextField mataKuliahInput;

    @FXML
    private TableColumn<App, String> mataKuliahcol;

    @FXML
    private TableView<App> table;

    @FXML
    private Font x1;

    @FXML
    private Color x2;

    @FXML
    private Font x3;

    @FXML
    private Color x4;

    @FXML
    void input(ActionEvent event) {
        String mataKuliah = mataKuliahInput.getText();
        LocalDate deadLine = deadLineInput.getValue();
        String details = detailsInput.getText();

        if (mataKuliah.equals("") || deadLine.equals("")){
            JOptionPane.showMessageDialog(null, "Fill up!");
        } else {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                con = DriverManager.getConnection("jdbc:mysql://localhost/ez_reminder", "root", "");

                
                    pst = con.prepareStatement("Insert task_list (taskName, taskDeadline, taskDetails, std_id) values (?, ?, ?, 1)");
                    Date date = Date.valueOf(deadLine);

                    pst.setString(1, mataKuliah);
                    pst.setDate(2, date);
                    pst.setString(3, details);

                    int rs = pst.executeUpdate();
                
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @FXML
    void remove(ActionEvent event) {
        int selectedID = table.getSelectionModel().getSelectedIndex();
        table.getItems().remove(selectedID);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        mataKuliahcol.setCellValueFactory(new PropertyValueFactory<App, String>("name"));
        deadlinecol.setCellValueFactory(new PropertyValueFactory<App, Integer>("deadline"));
    }
}