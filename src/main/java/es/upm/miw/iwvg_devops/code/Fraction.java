package es.upm.miw.iwvg_devops.code;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Conceptos: Las fracciones propias son aquellas cuyo numerador es menor que el denominador
 * <p>
 * Las fracciones impropias son aquellas cuyo numerador es mayor que el denominador
 * <p>
 * Dos fracciones son equivalentes cuando el producto de extremos (numerador de la primera por denominador de la segunda) es igual al
 * producto de medios (denominador de la primera por el numerador de la segunda)
 * <p>
 * Las fracciones irreducibles son aquellas que no se pueden simplificar, esto sucede cuando el numerador y el denominador son primos entre
 * sí
 * <p>
 * Reducir varias fracciones a común denominador consiste en convertirlas en otras equivalentes que tengan el mismo denominador
 * <p>
 * Comparar fracciones
 * <p>
 * Suma fracciones: En primer lugar se reducen los denominadores a común denominador, y se suman o se restan los numeradores de las
 * fracciones equivalentes obtenidas
 * <p>
 * Multiplicación: La multiplicación de dos fracciones es otra fracción que tiene: Por numerador el producto de los numeradores. Por
 * denominador el producto de los denominadores.
 * <p>
 * La división de dos fracciones es otra fracción que tiene: Por numerador el producto de los extremos. Por denominador el producto de los
 * medios. Invertir fraccion
 */
public class Fraction {
    private int numerator;
    private int denominator;

    public Fraction(int numerator, int denominator) {
        this.numerator = numerator;
        this.denominator = denominator;
    }

    public Fraction() {
        this(1, 1);
    }

    public int getNumerator() {
        return numerator;
    }

    public void setNumerator(int numerator) {
        this.numerator = numerator;
    }

    public int getDenominator() {
        return denominator;
    }

    public void setDenominator(int denominator) {
        this.denominator = denominator;
    }

    public double decimal() {
        return (double) numerator / denominator;
    }

    public boolean isProper() {
        return this.numerator < this.denominator;
    }

    public boolean isImproper() {
        return this.numerator >= this.denominator;
    }

    public boolean isEquivalent(Fraction other) {
        double decimal = this.decimal();
        double otherNumber = other.decimal();
        return decimal == otherNumber;
    }

    public void add(Fraction other) {
        int leastCommonMultiplyDenominator = this.getLeastCommonMultiplyDenominatorToOther(other);
        int multiply = leastCommonMultiplyDenominator / this.denominator;
        int otherMultiply = leastCommonMultiplyDenominator / other.getDenominator();
        this.denominator = leastCommonMultiplyDenominator;
        this.numerator = (this.numerator * multiply) + (other.getNumerator() * otherMultiply);
        this.simplifyFraction();
    }

    public void multiply(Fraction other) {
        this.numerator *= other.getNumerator();
        this.denominator *= other.getDenominator();
        this.simplifyFraction();
    }

    public void divide(Fraction other) {
        this.numerator *= other.getDenominator();
        this.denominator *= other.getNumerator();
        this.simplifyFraction();
    }

    private int getLeastCommonMultiplyDenominatorToOther(Fraction other) {
        int greatestCommonDivisor = this.getGreatestCommonDivisorDenominatorToOther(other);
        return (this.denominator * other.getDenominator()) / greatestCommonDivisor;
    }

    private void simplifyFraction(){
        int gcdBetweenNumeratorDenominator =
                Fraction.getGreatestCommonDivisorBetweenTwoNumbers(this.numerator, this.denominator);
        this.numerator /= gcdBetweenNumeratorDenominator;
        this.denominator /= gcdBetweenNumeratorDenominator;
    }

    private int getGreatestCommonDivisorDenominatorToOther(Fraction other) {
        return Fraction.getGreatestCommonDivisorBetweenTwoNumbers(this.denominator, other.getDenominator());
    }

    private static int getGreatestCommonDivisorBetweenTwoNumbers(int numberOne, int numberTwo) {
        List<Integer> divisorsOne = Fraction.getAllDivisorsForNumber(numberOne);
        List<Integer> divisorsTwo = Fraction.getAllDivisorsForNumber(numberTwo);
        return divisorsOne.stream()
                .filter(divisorsTwo::contains)
                .mapToInt(divisor -> divisor)
                .max().orElse(1);
    }

    private static List<Integer> getAllDivisorsForNumber(int number) {
        return IntStream.rangeClosed(1, number / 2)
                .filter(divisor -> number % divisor == 0)
                .boxed()
                .collect(Collectors.collectingAndThen(
                        Collectors.toList(),
                        divisors -> {
                            divisors.add(number);
                            return divisors;
                        }
                ));
    }

    @Override
    public String toString() {
        return "Fraction{" +
                "numerator=" + numerator +
                ", denominator=" + denominator +
                '}';
    }
}
