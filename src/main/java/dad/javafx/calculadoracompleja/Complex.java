package dad.javafx.calculadoracompleja;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

public class Complex {

	private DoubleProperty real = new SimpleDoubleProperty(0);
	private DoubleProperty imaginary = new SimpleDoubleProperty(0);

	public Complex() {
	}

	public Complex(double real, double imaginary) {
		super();
		setReal(real);
		setImaginary(imaginary);
	}


	public final DoubleProperty realProperty() {
		return this.real;
	}

	public final double getReal() {
		return this.realProperty().get();
	}

	public final void setReal(final double real) {
		this.realProperty().set(real);
	}

	public final DoubleProperty imaginaryProperty() {
		return this.imaginary;
	}

	public final double getImaginary() {
		return this.imaginaryProperty().get();
	}

	public final void setImaginary(final double imaginary) {
		this.imaginaryProperty().set(imaginary);
	}
	
}
