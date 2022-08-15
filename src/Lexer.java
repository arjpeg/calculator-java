package src;

import java.util.ArrayList;
import java.util.regex.Pattern;

import src.Token.TokenType;

public class Lexer {
    private int currentIndex = -1;
    private String code;
    private String current;

    public Lexer(String code) {
        this.code = code;
        this.advance();
    }

    private void advance() {
        if (currentIndex + 1 > code.length()) {
            current = null;
            return;
        }

        currentIndex++;

        current = ((Character) code.charAt(currentIndex)).toString();
    }

    private String peek() {
        if (currentIndex + 1 > code.length()) {
            return null;
        }

        return ((Character) code.charAt(currentIndex + 1)).toString();
    }

    private boolean isNumeric(String strNum) {
        Pattern pattern = Pattern.compile("-?\\d+(\\.\\d+)?");

        if (strNum == null) {
            return false;
        }

        return pattern.matcher(strNum).matches();
    }

    public ArrayList<Token> lex() {
        ArrayList<Token> tokens = new ArrayList<>();

        while (current != null) {
            if (current.equals(".") || isNumeric(current)) {
                tokens.add(makeNumber());
            }

            advance();
        }

        return tokens;
    }

    public Token makeNumber() {
        String currentNum;
        int decimal_points;

        if (current.equals(".")) {
            currentNum = "0.";
            decimal_points = 1;
        } else {
            currentNum = current;
            decimal_points = 0;
        }

        advance();

        while (isNumeric(current) || current.equals(".")) {
            if (current.equals(".") && decimal_points > 0) {
                throw new Error("Syntax error", "Number " + currentNum + ". cannot contain more than 1 decimal point.");
            }

            if (peek() != null && !isNumeric(peek()) && !peek().equals(" ")) {
                throw new Error("Syntax error",
                        "Unkown character " + peek() + " found while parsing number " + currentNum + ".");
            }

            if (peek() == null || peek().equals(" ")) {
                break;
            }

            currentNum += current;
            advance();
        }

        return new Token(TokenType.NUMBER, Float.parseFloat(currentNum));

    }
}
