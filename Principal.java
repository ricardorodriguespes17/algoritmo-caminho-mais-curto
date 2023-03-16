import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Principal extends Application {
  @Override
  public void start(Stage palco) throws Exception {
    Parent root = FXMLLoader.load(getClass().getResource("/view/Tela.fxml"));
    Scene cena = new Scene(root);
    palco.setScene(cena);
    palco.show();
  }

  public static void main(String args[]) {
    launch(args);
  }

}
