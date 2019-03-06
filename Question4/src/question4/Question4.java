package Question4;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.geometry.VPos;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Rectangle;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;


public class Question4 extends Application {

    Pane pane = new Pane();
    TextField textField = new TextField();
    VBox root = new VBox();
    @Override
    public void start(Stage primaryStage){

        Label lblFileURL = new Label("File URL:", textField);
        // Enter the file URL (C:/Users/extra/Desktop/Software Systems/Assignment/Test.txt)
        lblFileURL.setContentDisplay(ContentDisplay.RIGHT);
        textField.setPrefColumnCount(20);
        Button btView = new Button("View Histogram");

        HBox hBox = new HBox(lblFileURL, btView);

        root.getChildren().addAll(pane, hBox);
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Question4");
        
        
        btView.setOnAction(e-> {update(); //Button updates histogram
        root.setTranslateY(10);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Question4");
        primaryStage.sizeToScene();});
        
        
        primaryStage.show();
    }

    private void update() { //Update histogram
        Histogram graph = new Histogram(textField.getText());
        pane.getChildren().add(graph);
    }  
    
    public class Histogram extends Pane {

        private char[] letters = new char[26];
        private int counts[] = new int[26];
        private Rectangle[] bars = new Rectangle[26];
        private File file;
        GridPane pane;

        double width = 350;
        double height = 350;

        public Histogram(String filename) { //Properties of histogram

            this.file = new File(filename.trim());

            setWidth(width);
            setHeight(height);
            readFile();
            draw();
        }

        private void readFile() { //Reads all the letters of the file

            Scanner scanner;
            String s = "";
            try {
                scanner = new Scanner(file);
                while (scanner.hasNextLine()) {

                    s += scanner.nextLine();
                }
            } catch (IOException ex) {
            }

            s = s.toUpperCase();
            for (int i = 0; i < s.length(); i++) { 
                char character = s.charAt(i);
                if (Character.isLetter(character)) {
                    counts[character - 'A']++;
                }
            }

        }
        private double getTotal() { //Finds the number of each letter
            double total = 0;
            for (int count : counts) {
                total += count;
            }
            return total;
        }

        private void draw() { //Displays the histogram

            pane = new GridPane();
            double barW = width / letters.length;
            double total = getTotal();

            for (int i = 0; i < counts.length; i++) {
                letters[i] = (char) ('A' + i);
                double percentage = counts[i] / total;
                double barH = height * percentage;
                bars[i] = new Rectangle(barW, barH);
                Label label = new Label(letters[i] + "", bars[i]);
                label.setContentDisplay(ContentDisplay.TOP);
                pane.add(label, i, 0);
                GridPane.setValignment(label, VPos.BASELINE);
            }
            getChildren().addAll(pane);
        }

        public void setCounts(int[] counts) {
            this.counts = counts;
            draw();
        }        

        public int[] getCounts() {
            return counts;
        }

    }
    
    public static void main(String[] args) {
        Application.launch(args);
    }
    
}