public class Calculator {
    static double add(double x, double y) {return x + y;}
    static double sub(double x, int y) {return x - y;}
    static double multi(double x, int y) {return x * y;}
    static double div(double x, double y) {
        if (x == 0.0 || y == 0.0) {
            return 0.0;
        } else {
            return x / y;
        }
    }
    static double mod(double x, double y) {
        if (x == 0.0 || y == 0.0) {
            return 0.0;
        } else {
            return x % y;
        }
    }
}
