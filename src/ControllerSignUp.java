import java.io.IOException;
import javafx.scene.Node;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class ControllerSignUp {

    Connection con;
    PreparedStatement pst;
    ResultSet rs;

    @FXML
    private PasswordField CPasswordSU;

    @FXML
    private PasswordField PasswordSU;

    @FXML
    private TextField UsernameSU;

    @FXML
    void login(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("loginScene.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void signUp(ActionEvent event) throws IOException {
        String uName = UsernameSU.getText();
        String pWord = PasswordSU.getText();
        String cPWord = CPasswordSU.getText();

        if (uName.equals("") && pWord.equals("") && cPWord.equals("")){
            JOptionPane.showMessageDialog(null, "Username or Password is Empty!");
        } else {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                con = DriverManager.getConnection("jdbc:mysql://localhost/ez_reminder", "root", "");

                if (pWord.equals(cPWord)){
                    pst = con.prepareStatement("Insert into student_info (Username, Password) values (?, ?)");

                    pst.setString(1, uName);
                    pst.setString(2, pWord);

                    int rs = pst.executeUpdate();

                    if (rs == 1){
                        Parent root = FXMLLoader.load(getClass().getResource("loginScene.fxml"));
                        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                        Scene scene = new Scene(root);
                        stage.setScene(scene);
                        stage.show();
                    } else {
                        JOptionPane.showMessageDialog(null, "Login Failed");
                        UsernameSU.setText("");
                        PasswordSU.setText("");
                        CPasswordSU.setText("");
                        UsernameSU.requestFocus();
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Your password is not same!");
                    PasswordSU.setText("");
                    CPasswordSU.setText("");
                    PasswordSU.requestFocus();
                }

            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ControllerSignUp.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(ControllerSignUp.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
