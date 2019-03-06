package question2;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.geometry.Pos;
import javafx.geometry.HPos;
import javafx.geometry.Insets;

public class Question2 extends Application {
	public TextField textInvestmentAmount = new TextField();
	public TextField textNumberOfYears = new TextField();
	public TextField textAnnualInterestRate = new TextField();
	public TextField textFutureValue = new TextField();
	public Button btCalculate = new Button("Calculate");

	@Override
	public void start(Stage primaryStage) { //Makes panes for the number inputs
		GridPane pane = new GridPane();
		pane.setVgap(10);
		pane.setHgap(10);
		pane.add(new Label("Investment Amount:"), 0, 0);
		pane.add(textInvestmentAmount, 1, 0);
		pane.add(new Label("Number Of Years:"), 0, 1);
		pane.add(textNumberOfYears, 1, 1);
		pane.add(new Label("Annual Interest Rate:"), 0, 2);
		pane.add(textAnnualInterestRate, 1, 2);
		pane.add(new Label("Future value:"), 0, 3);
		pane.add(textFutureValue, 1, 3);
		pane.add(btCalculate, 1, 4);

		pane.setAlignment(Pos.CENTER); //Changes position/allignment
		textInvestmentAmount.setAlignment(Pos.BOTTOM_RIGHT);
		textNumberOfYears.setAlignment(Pos.BOTTOM_RIGHT);
		textAnnualInterestRate.setAlignment(Pos.BOTTOM_RIGHT);
		textFutureValue.setAlignment(Pos.BOTTOM_RIGHT);
		textFutureValue.setEditable(false);
		pane.setHalignment(btCalculate, HPos.RIGHT);
		pane.setPadding(new Insets(10, 10, 10, 10));
		btCalculate.setOnAction(e -> futureValue());

		Scene scene = new Scene(pane);
		primaryStage.setTitle("Question2");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	private void futureValue() { //Calculated the future value from the equation
		double investmentAmount = Double.parseDouble(textInvestmentAmount.getText());
		double years = Double.parseDouble(textNumberOfYears.getText());
		double annualInterestRate = Double.parseDouble(textAnnualInterestRate.getText()) / 1200;
		textFutureValue.setText(String.format("$%.2f", (investmentAmount * Math.pow(1+annualInterestRate, years*12))));
	}
        
    public static void main(String[] args) {
        launch(args);
    }
}