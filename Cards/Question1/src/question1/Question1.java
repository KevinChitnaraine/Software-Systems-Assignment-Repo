package question1;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import java.util.ArrayList;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;

public class Question1 extends Application {
    @Override
    public void start(Stage primaryStage) {
        ArrayList<String> cards = new ArrayList<>();

        for (int n = 0; n < 52; n++) {
            cards.add(String.valueOf(n + 1)); //Makes an array of al the cards
        }

        java.util.Collections.shuffle(cards); //Randomized all the cards

        ImageView card1 = new ImageView(new Image("file:///C:/Users/extra/Desktop/Software Systems/Assignment/Cards/" + cards.get(0) + ".png")); //Picks the first three cards
        ImageView card2 = new ImageView(new Image("file:///C:/Users/extra/Desktop/Software Systems/Assignment/Cards/" + cards.get(1) + ".png"));
        ImageView card3 = new ImageView(new Image("file:///C:/Users/extra/Desktop/Software Systems/Assignment/Cards/" + cards.get(2) + ".png"));

        HBox root = new HBox();
        root.getChildren().add(card1);
        root.getChildren().add(card2);
        root.getChildren().add(card3);
        
        Scene scene = new Scene(root); //Adds cards to scene and displays it
        primaryStage.setTitle("Question1");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}