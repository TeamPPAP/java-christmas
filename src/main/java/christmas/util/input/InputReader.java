package christmas.util.input;

public interface InputReader<T> {
    T read() throws IllegalArgumentException;
}
