package com.sigulia;

public class JavaLesson {

    public static void main(String[] args) {

        char x, y;
        x = 'a';
        y = '\ud83d';

        boolean flag = true;

        byte aByte = -128;
        short aShort = 2;
        int aInt = aByte;
        long aLong1 = 2147483648L;
        long aLong2 = 214_748_364_8L;
        float aFloat;
        double aDouble = 1.33D;
        aFloat = 1.1F;
        double result = (aLong1 + 0.0) / aFloat;

        System.out.println(result);

        String aString;
        String aString2 = new String("hello");
        String smail = "\ud83d";
        Integer aInteger = 1;
        Integer aInteger2; // null

        // Массивы
        char[] array = new char[]{'a', 'b', 'c'};
        char[][] array2 = new char[][]{{'a', 'b', 'c'}, {'d'}};

        int[][] table = new int[3][4];
        table[0][1] = 5;

        int t = getMaxValue();
        System.out.println(t);
        System.out.println(sum(1, (int) aFloat));

        // Оператолры

        System.out.println(aShort + aByte);
        System.out.println(aShort * aByte);
        System.out.println(aShort % aByte);
        System.out.println(aLong1 / aShort);

        //  Логические операторы, if, else

        if (aShort == 2 && aString2.equals("hello")) {
            System.out.println("OK");
        } else if (aShort == 2 && aString2.equals("goodbye")) {
            System.out.println(" NOT OK");
        }


        if (!(1 > 10)) {
            System.out.println("1");
        }

        switch (aShort) {
            case 2:
                System.out.println("aShort = 2");
                break;
            case 1:
                System.out.println("aShort = 1");
                break;
        }

        aString =  1 > 2 ? "OK" : "NOT OK";


    }

    static int getMaxValue() {
        return Integer.MAX_VALUE;
    }

    static int sum (int f, int u) {
        return f + u;
    }
}


