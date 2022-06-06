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
import com.mysql.cj.protocol.Resultset;
import com.mysql.cj.xdevapi.Result;

public class ControllerLogin {

    Connection con;
    PreparedStatement pst;
    ResultSet rs;

    @FXML
    private PasswordField passWordF;

    @FXML
    private TextField userNameF;

    @FXML
    void login(ActionEvent event) throws IOException {
        String uName = userNameF.getText();
        String pWord = passWordF.getText();

        if (uName.equals("") && pWord.equals("")){
            JOptionPane.showMessageDialog(null, "Username or Password is Empty!");
        } else {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                con = DriverManager.getConnection("jdbc:mysql://localhost/ez_reminder", "root", "");
                pst = con.prepareStatement("Select * from student_info where Username=? and Password=?");

                pst.setString(1, uName);
                pst.setString(2, pWord);

                rs = pst.executeQuery();

                if (rs.next()){
                    Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
                    Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                    Scene scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();
                } else {
                    JOptionPane.showMessageDialog(null, "Login Failed");
                    userNameF.setText("");
                    passWordF.setText("");
                    userNameF.requestFocus();
                }

            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ControllerLogin.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(ControllerLogin.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @FXML
    void signUp(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("signUpScene.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
