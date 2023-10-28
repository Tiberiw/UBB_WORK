package Laborator1MAP.Domain;

import java.util.Objects;

public class ComplexNumber {

    private double real;
    private double imaginary;

    public ComplexNumber() {
        real = 0;
        imaginary = 0;
    }

    public ComplexNumber(double real, double imaginary) {
        this.real = real;
        this.imaginary = imaginary;
    }

    public double getReal() {
        return real;
    }

    public double getImaginary() {
        return imaginary;
    }

    public void setReal(double newReal) {
        this.real = newReal;
    }

    public void setImaginary(double newImaginary) {
        this.imaginary = newImaginary;
    }

    public String toString() {
        if(this.imaginary == 0 && this.real == 0)
            return "0";
        if(this.imaginary == 0)
            return String.valueOf(real);

        if(this.real == 0)
            return String.valueOf(imaginary);

        if(this.imaginary > 0)
            return this.real + " + " + this.imaginary + "*i";
        return this.real + " " + this.imaginary + "*i";
    }

    public void copy(ComplexNumber otherNumber) {
        this.real = otherNumber.getReal();
        this.imaginary = otherNumber.getImaginary();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ComplexNumber that = (ComplexNumber) o;
        return Double.compare(real, that.real) == 0 && Double.compare(imaginary, that.imaginary) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(real, imaginary);
    }
}
