package com.bartfokker.lox;

/**
 * The Token Class is a bundle containing the raw lexeme along with other things the scanner learned about
 */
class Token {
    final TokenType type;
    // raw token type
    final String lexeme;
    final Object literal;
    final int line;

    Token(TokenType type, String lexeme, Object literal, int line) {
        this.type = type;
        this.lexeme = lexeme;
        this.literal = literal;
        this.line = line;
    }

    @Override
    public String toString() {
        return String.format("%s %s %s", type, lexeme, literal);
    }
}