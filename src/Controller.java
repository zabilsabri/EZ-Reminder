import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    //Table
    @FXML
    private TableView<App> tableView;

    //Columns
    @FXML
    private TableColumn<App, String> nameColumn;

    @FXML
    private TableColumn<App, Integer> DeadlineColumn;

    //Text input
    @FXML
    private TextField nameInput;

    @FXML
    private DatePicker DeadlineInput;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        nameColumn.setCellValueFactory(new PropertyValueFactory<App, String>("name"));
        DeadlineColumn.setCellValueFactory(new PropertyValueFactory<App, Integer>("deadline"));
    }

    //Submit button
    @FXML
    void submit(ActionEvent event) {
        App customer = new App(nameInput.getText(), DeadlineInput.getValue());
        ObservableList<App> customers = tableView.getItems();
        customers.add(customer);
        tableView.setItems(customers);
    }

    //Remove button
    @FXML
    void removeCustomer(ActionEvent event) {
        int selectedID = tableView.getSelectionModel().getSelectedIndex();
        tableView.getItems().remove(selectedID);
    }
}