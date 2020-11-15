package dad.javafx.calculadoracompleja;

import javafx.application.Application;
import javafx.beans.binding.DoubleBinding;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.converter.NumberStringConverter;

public class CalculadoraCompleja extends Application {

	private ComboBox<String> operatorCombo = new ComboBox<String>();
	private TextField realAField = new TextField("0");
	private TextField realCField = new TextField("0");
	private TextField imaginBField = new TextField("0");
	private TextField imaginDField = new TextField("0");
	private TextField realResultField = new TextField("0");
	private TextField imaginResultField = new TextField("0");
	private Label plus1Label = new Label("+");
	private Label plus2Label = new Label("+");
	private Label plus3Label = new Label("+");
	private Label i1Label = new Label("i");
	private Label i2Label = new Label("i");
	private Label i3Label = new Label("i");
	private Separator separator = new Separator();
	private HBox rootBox = new HBox(5);
	private HBox abBox = new HBox(5);
	private HBox cdBox = new HBox(5);
	private HBox resultBox = new HBox(5);
	private VBox comboBox = new VBox(5);
	private VBox operationsBox = new VBox(5);
	Complex complexA = new Complex();
	Complex complexB = new Complex();
	Complex complexC = new Complex();

	@Override
	public void start(Stage primaryStage) throws Exception {

		operatorCombo.getItems().addAll("+", "-", "*", "/");
		operatorCombo.getSelectionModel().selectFirst();
		comboBox.getChildren().add(operatorCombo);
		comboBox.setAlignment(Pos.CENTER);

		abBox.getChildren().addAll(realAField, plus1Label, imaginBField, i1Label);
		realAField.setMaxWidth(50);
		imaginBField.setMaxWidth(50);
		realAField.setAlignment(Pos.CENTER);
		imaginBField.setAlignment(Pos.CENTER);
		abBox.setAlignment(Pos.CENTER);

		cdBox.getChildren().addAll(realCField, plus2Label, imaginDField, i2Label);
		realCField.setMaxWidth(50);
		imaginDField.setMaxWidth(50);
		realCField.setAlignment(Pos.CENTER);
		imaginDField.setAlignment(Pos.CENTER);
		cdBox.setAlignment(Pos.CENTER);

		resultBox.getChildren().addAll(realResultField, plus3Label, imaginResultField, i3Label);
		realResultField.setMaxWidth(50);
		imaginResultField.setMaxWidth(50);
		realResultField.setAlignment(Pos.CENTER);
		imaginResultField.setAlignment(Pos.CENTER);
		realResultField.setEditable(false);
		imaginResultField.setEditable(false);
		resultBox.setAlignment(Pos.CENTER);

		operationsBox.getChildren().addAll(abBox, cdBox, separator, resultBox);
		operationsBox.setAlignment(Pos.CENTER);

		realAField.textProperty().bindBidirectional(complexA.realProperty(), new NumberStringConverter());
		imaginBField.textProperty().bindBidirectional(complexA.imaginaryProperty(), new NumberStringConverter());

		realCField.textProperty().bindBidirectional(complexB.realProperty(), new NumberStringConverter());
		imaginDField.textProperty().bindBidirectional(complexB.imaginaryProperty(), new NumberStringConverter());

		realResultField.textProperty().bindBidirectional(complexC.realProperty(), new NumberStringConverter());
		imaginResultField.textProperty().bindBidirectional(complexC.imaginaryProperty(), new NumberStringConverter());

		operatorCombo.valueProperty().addListener(e -> {

			String comboValue = operatorCombo.valueProperty().getValue();
			try {
				switch (comboValue) {
				case "+": {
					add();
					break;

				}
				case "-": {
					subtract();
					break;

				}
				case "*": {
					multiply();
					break;

				}
				case "/": {
					divide();
					break;

				}

				}
			} catch (Exception e2) {

			}

		});
		rootBox.getChildren().addAll(comboBox, operationsBox);
		rootBox.setAlignment(Pos.CENTER);

		Scene scene = new Scene(rootBox, 320, 200);
		primaryStage.setTitle("Calculadora Compleja");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

	public void add() {

		complexC.realProperty().bind(complexA.realProperty().add(complexB.realProperty()));

		complexC.imaginaryProperty().bind(complexA.imaginaryProperty().add(complexB.imaginaryProperty()));
	}

	public void subtract() {

		complexC.realProperty().bind(complexA.realProperty().subtract(complexB.realProperty()));

		complexC.imaginaryProperty().bind(complexA.imaginaryProperty().subtract(complexB.imaginaryProperty()));
	}

	public void multiply() {
		DoubleBinding realMultiply = (complexA.realProperty().multiply(complexB.realProperty())
				.subtract(complexA.imaginaryProperty().multiply(complexB.imaginaryProperty())));
		DoubleBinding imaginMultiply = (complexA.realProperty().multiply(complexB.imaginaryProperty())
				.add(complexA.imaginaryProperty().multiply(complexB.realProperty())));
		complexC.realProperty().bind(realMultiply);
		complexC.imaginaryProperty().bind(imaginMultiply);
	}

	public void divide() {
		DoubleBinding realNum = (complexA.realProperty().multiply(complexB.realProperty())
				.add(complexA.imaginaryProperty().multiply(complexB.imaginaryProperty())));
		DoubleBinding imaginNum = (complexA.imaginaryProperty().multiply(complexB.realProperty())
				.subtract(complexA.realProperty().multiply(complexB.imaginaryProperty())));
		DoubleBinding realDenom = (complexB.realProperty().multiply(complexB.realProperty())
				.add(complexB.getImaginary()).multiply(complexB.getImaginary()));
		DoubleBinding imaginDenom = (complexB.realProperty().multiply(complexB.realProperty())
				.subtract(complexB.getImaginary()).multiply(complexB.getImaginary()));

		complexC.realProperty().bind(realNum.divide(realDenom));
		complexC.imaginaryProperty().bind(imaginNum.divide(imaginDenom));

	}
}
