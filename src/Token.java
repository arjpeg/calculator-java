package src;

public class Token {

    public enum TokenType {
        NUMBER,
        PLUS,
        MINUS,
        MULT,
        DIV,
        INT_DIV
    }

    private Float value;
    private TokenType type;

    public Token(TokenType type) {
        this.type = type;
    }

    public Token(TokenType type, Float value) {
        this.type = type;
        this.value = value;
    }

    @Override
    public String toString() {
        return type.name().toUpperCase() + (value != null ? ": " + value.toString() : "");
    }
}
