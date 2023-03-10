public class Main {
    public static void main(String[] args) {
        Calculator calc = Calculator.instance.get();
        float a = calc.plus.apply(1, 2); //3
        float b = calc.minus.apply(1, 1); //0

        // Делить на 0 нельзя. b=0. Для работы этой строки devide в классе с проверкой
        //int c = calc.devide.apply(a, b);

        if (b != 0) {
            float c = calc.devide.apply(a, b);
            calc.println.accept((int) c);
        } else {
            int c = Integer.MIN_VALUE;
            System.out.println("Деление на 0 невозможно");
            System.out.println(c);
        }
    }
}