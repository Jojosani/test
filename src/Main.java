package com.company;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws Exception {
        // создаем справочник для римских чисел
        Map<String, Integer> rim = new HashMap<>();
        rim.put("I", 1);
        rim.put("II", 2);
        rim.put("III", 3);
        rim.put("IV", 4);
        rim.put("V", 5);
        rim.put("VI", 6);
        rim.put("VII", 7);
        rim.put("VIII", 8);
        rim.put("IX", 9);
        rim.put("X", 10);

        Map<Integer, String> gre = new HashMap<>();
        gre.put( 1, "I");
        gre.put( 2, "II");
        gre.put( 3, "III");
        gre.put( 4, "IV");
        gre.put( 5, "V");
        gre.put( 6, "VI");
        gre.put( 7, "VII");
        gre.put( 8, "VIII");
        gre.put( 9, "IX");
        gre.put(10, "X");

        // чтение их консонли в лист
        List<String> strings = nextOperation();

        int operand1;
        int operand2;

        String tempOperand1 = strings.get(0);
        String tempOperand2 = strings.get(2);
        // Получаем знак арифмитической операции
        char operation = Character.valueOf(strings.get(1).charAt(0));

        boolean rimBool = false;

        if (!(operation == '+' || operation == '-' || operation == '*' || operation == '/')) {
            throw new Exception("формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
        }

        // Проверяем являются ли введеные значения римскими числами
        if (rim.get(tempOperand1) != null && rim.get(tempOperand2) != null) {
            operand1 = rim.get(tempOperand1);
            operand2 = rim.get(tempOperand2);
            rimBool = true;
        }
        // Проверяем что они оба не римские иначе ошибка
        else if (rim.get(tempOperand1) == null && rim.get(tempOperand2) == null) {
            operand1 = Integer.valueOf(tempOperand1);
            operand2 = Integer.valueOf(tempOperand2);
        } else {
            throw new IllegalArgumentException("используются одновременно разные системы счисления");
        }

        // Вычисление
        int result = calc(operand1,operand2,operation);

        if (rimBool) {
            // Если римские, и ответ меньше 0, то ошибка
            if (result <= 0) {
                throw new Exception("в римской системе нет отрицательных чисел");
            }
            System.out.println("Результат операции: "+gre.get(result));
        } else {
            System.out.println("Результат операции: "+result);
        }

    }

    public static List<String> nextOperation() throws Exception {
        System.out.println("Введите арифметическое выражение:");
        List<String> parseResult = new ArrayList<>();
        if(scanner.hasNext()){
            String line = scanner.nextLine();
            String[] strings = line.split(" ");
            if (strings.length < 3) {
                throw new Exception("строка не является математической операцией");
            }
            if (strings.length > 3) {
                throw new Exception("формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
            }
            parseResult.add(strings[0]);
            parseResult.add(strings[1]);
            parseResult.add(strings[2]);
        } else {
            System.out.println("Вы допустили ошибку при вводе числа. Попробуйте еще раз.");
            scanner.next();//рекурсия
            parseResult = nextOperation();
        }
        return parseResult;
    }

    public static int calc(int operand1, int operand2, char operation) throws Exception {
        int result;
        switch (operation){
            case '+':
                result = operand1+operand2;
                break;
            case '-':
                result = operand1-operand2;
                break;
            case '*':
                result = operand1*operand2;
                break;
            case '/':
                result = operand1/operand2;
                break;
            default:
                throw new Exception("Операция не распознана. Повторите ввод.");
        }
        return result;
    }
}