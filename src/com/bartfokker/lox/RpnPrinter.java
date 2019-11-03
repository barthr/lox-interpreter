package com.bartfokker.lox;

import java.util.Stack;

public class RpnPrinter implements Expr.Visitor<String> {
    @Override
    public String visitBinaryExpr(Expr.Binary expr) {
        return expr.left.accept(this) + " " + expr.right.accept(this) + " " + expr.operator.lexeme;
    }

    @Override
    public String visitGroupingExpr(Expr.Grouping expr) {
        return parenthesize("group", expr.expression);
    }

    @Override
    public String visitLiteralExpr(Expr.Literal expr) {
        if (expr == null) {
            return "nil";
        }
        return expr.value.toString();
    }

    @Override
    public String visitUnaryExpr(Expr.Unary expr) {
        return parenthesize(expr.operator.lexeme, expr.right);
    }

    private String parenthesize(String name, Expr... exprs) {
        StringBuilder builder = new StringBuilder();

        builder.append("(").append(name);
        for (Expr expr : exprs) {
            builder.append(" ");
            builder.append(expr.accept(this));
        }
        builder.append(")");

        return builder.toString();
    }

    private String print(Expr expr) {
        return expr.accept(this);
    }

    public static void main(String[] args) {

        Expr expression = new Expr.Binary(
                new Expr.Binary(
                        new Expr.Literal(1),
                        new Token(TokenType.MINUS, "+", null, 1),
                        new Expr.Literal(2)
                ),
                new Token(TokenType.PLUS, "+", null, 1),
                new Expr.Binary(
                        new Expr.Literal(10),
                        new Token(TokenType.PLUS, "+", null, 1),
                        new Expr.Literal(3)
                )
        );


        System.out.println(new RpnPrinter().print(expression));
    }
}
