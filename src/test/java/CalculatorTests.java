import org.junit.jupiter.api.*;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class CalculatorTests {
    Calculator calculator;

    @BeforeAll
    public static void before(){
        System.out.println("Тесты калькулятора стартовали");
    }
    @AfterAll
    public static void after(){
        System.out.println("Тесты калькулятора завершены");
    }
    @BeforeEach
    public void beforEachTest() {
        calculator = new Calculator();
        System.out.println("Калькулятор создан");
    }

    @AfterEach
    public void afterEachTest() {
        calculator = null;
        System.out.println("Калькулятор уничтожен");
    }

    @Test
    public void testPlus() {
        System.out.println("Тест сложения");

        //arrange  given
        int a = -2, b = 1, expected = -1;

        //act      when
        int result = calculator.plus.apply(a,b);

        //assert   then
        Assertions.assertEquals(expected,result);
    }
    @Test
    public void testMinus() {
        System.out.println("Тест вычитания");

        //arrange  given
        int a = 2, b = 1, expected = 1;

        //act      when
        int result = calculator.minus.apply(a,b);

        //assert   then
        Assertions.assertEquals(expected,result);
    }
    @Test
    public void testMultiply() {
        System.out.println("Тест умножения");

        //arrange  given
        int a = 2, b = 3, expected = 6;

        //act      when
        int result = calculator.multiply.apply(a,b);

        //assert   then
        Assertions.assertEquals(expected,result);
    }
    @Test
    public void testDevide() {
        System.out.println("Тест деления");

        //arrange  given
        int a = 6, b = -2, expected = -3;

        //act      when
        int result = calculator.devide.apply(a,b);

        //assert   then
        Assertions.assertEquals(expected,result);
    }

    @Test
    public void testDevideByZero() {
        System.out.println("Тест деления на 0");

        //arrange  given
        int a = 6, b = 0;
        Class<ArithmeticException> expected = ArithmeticException.class;

        //act      when
        Executable executable =  () ->calculator.devide.apply(a,b);

        //assert   then
        Assertions.assertThrowsExactly(expected, executable);
    }
    @ParameterizedTest
    @MethodSource ("minusTestParameters")                      //arrange  given
    public void testMinusWithParameters(int a, int b , int expected){
        System.out.println("Параметризованный тест вычитания");

        //act      when
        int result = calculator.minus.apply(a,b);

        //assert   then
        Assertions.assertEquals(expected,result);
    }
    public static Stream<Arguments> minusTestParameters(){
        return  Stream.of(
                Arguments.of(-1,-1,0),
                Arguments.of(2,-4,6),
                Arguments.of(-3,1,-4),
                Arguments.of(1,2,-1)
        );
    }
    @ParameterizedTest
    @MethodSource ("multiplyTestParameters")                      //arrange  given
    public void testMultiplyWithParameters(int a, int b , int expected){
        System.out.println("Параметризованный тест умножения");

        //act      when
        int result = calculator.multiply.apply(a,b);

        //assert   then
        Assertions.assertEquals(expected,result);
    }
    public static Stream<Arguments> multiplyTestParameters(){
        return  Stream.of(
                Arguments.of(-1,-2,2),
                Arguments.of(2,-4,-8),
                Arguments.of(-3,4,-12),
                Arguments.of(3,0,0),
                Arguments.of(0,-2,0)
        );
    }

    @ParameterizedTest
    @MethodSource ("powTestParameters")  //arrange  given
    public void testPowParameters(int a, int expected){
        System.out.println("Параметризованный тест квадрата");

        //act      when
        int result = calculator.pow.apply(a);

        //assert   then
        Assertions.assertEquals(expected,result);
    }
    public static Stream<Arguments> powTestParameters(){
        return  Stream.of(
                Arguments.of(-1,1),
                Arguments.of(1,1),
                Arguments.of(0,0)
        );
    }
    @ParameterizedTest
    @MethodSource ("absTestParameters")  //arrange  given
    public void testAbsParameters(int a, int expected){
        System.out.println("Параметризованный тест модуля");

        //act      when
        int result = calculator.abs.apply(a);

        //assert   then
        Assertions.assertEquals(expected,result);
    }
    public static Stream<Arguments> absTestParameters(){
        return  Stream.of(
                Arguments.of(-1,1),
                Arguments.of(1,1),
                Arguments.of(0,0)
        );
    }
}
