package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

            Scanner scanner = new Scanner(System.in);
            System.out.println("Введите выражение в формате \"operand operator operand\":");
            String input = scanner.nextLine();

            try {
                String[] parts = input.split(" ");
                if (parts.length != 3) {
                    throw new IllegalArgumentException("Неверный формат ввода");
                }

                String operand1 = parts[0];
                String operator = parts[1];
                String operand2 = parts[2];

                if (!operand1.startsWith("\"") || !operand1.endsWith("\"") ||
                        !operand2.startsWith("\"") || !operand2.endsWith("\"")) {
                    throw new IllegalArgumentException("Операнды должны быть в кавычках");
                }

                operand1 = operand1.substring(1, operand1.length() - 1);
                operand2 = operand2.substring(1, operand2.length() - 1);

                String result = calculate(operand1, operator, operand2);
                System.out.println("Результат: " + result);
            } catch (Exception e) {
                System.out.println("Ошибка: " + e.getMessage());
            } finally {
                scanner.close();
            }
        }

        private static String calculate(String operand1, String operator, String operand2) {
            switch (operator) {
                case "+":
                    return operand1 + operand2;
                case "-":
                    return operand1.replace(operand2, "");
                case "*":
                    int num = Integer.parseInt(operand2);
                    StringBuilder sb = new StringBuilder();
                    for (int i = 0; i < num; i++) {
                        sb.append(operand1);
                    }
                    return sb.toString();
                case "/":
                    int divisor = Integer.parseInt(operand2);
                    int quotient = operand1.length() / divisor;
                    return operand1.substring(0, quotient);
                default:
                    throw new IllegalArgumentException("Неподдерживаемая операция: " + operator);
            }
        }
    }