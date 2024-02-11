package animation_test.circles.exception;

public class BallsOverflowException extends RuntimeException{
    public BallsOverflowException(){
        super("Невозможно создать более 15 шаров");
    }
}