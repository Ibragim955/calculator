package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите выражение в формате \"a\" оператор \"b\", где a и b - строки или числа:");
        String input = scanner.nextLine();

        try {
            String result = calculate(input);
            System.out.println("Результат: " + result);
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }

    public static String calculate(String input) {
        // Разбиваем строку по первому встреченному оператору, учитывая, что оператор может быть без пробела
        int operatorIndex = input.indexOf('+');
        if (operatorIndex == -1) {
            operatorIndex = input.indexOf('-');
        }
        if (operatorIndex == -1) {
            operatorIndex = input.indexOf('*');
        }
        if (operatorIndex == -1) {
            operatorIndex = input.indexOf('/');
        }

        if (operatorIndex == -1) {
            throw new IllegalArgumentException("Неподдерживаемая операция");
        }

        String operand1 = input.substring(0, operatorIndex).trim();
        String operator = input.substring(operatorIndex, operatorIndex + 1);
        String operand2 = input.substring(operatorIndex + 1).trim();

        if (!operand1.startsWith("\"") || !operand1.endsWith("\"") ||
                (!operand2.startsWith("\"") && !operand2.matches("\\d+"))) {
            throw new IllegalArgumentException("Неверный формат выражения");
        }

        operand1 = operand1.substring(1, operand1.length() - 1);
        if (operand2.startsWith("\"") && operand2.endsWith("\"")) {
            operand2 = operand2.substring(1, operand2.length() - 1);
        } else {
            try {
                Integer.parseInt(operand2);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Неверный формат второго операнда");
            }
        }

        switch (operator) {
            case "+":
                return operand1 + operand2;
            case "-":
                if (!operand1.contains(operand2)) {
                    return operand1;
                } else {
                    return operand1.replace(operand2, "");
                }
            case "*":
                int repeat = Integer.parseInt(operand2);
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < repeat; i++) {
                    sb.append(operand1);
                }
                return sb.toString();
            case "/":
                int divisor = Integer.parseInt(operand2);
                int length = operand1.length() / divisor;
                if (length > 40) {
                    return operand1.substring(0, 40) + "...";
                }
                return operand1.substring(0, length);
            default:
                throw new IllegalArgumentException("Неподдерживаемая операция: " + operator);
        }
    }
    }