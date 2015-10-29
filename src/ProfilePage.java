import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;


public class ProfilePage {

    String user;
    Stage window;

    GridPane mainLayout;
    
    public ProfilePage(Stage window, String user) {
        this.window = window;
        this.user = user;
        
        setLayout();
        Scene scene = new Scene(mainLayout, 300, 270);

        window.setScene(scene);
        window.show();

    }


    private void setLayout() {
        mainLayout = new GridPane();
    }
}
