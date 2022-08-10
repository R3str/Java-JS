package com.company;

import java.util.Scanner;

public interface IMethods
{
    final int SIDES = 3;

    public static String CheckNumber(String numString)
    {
        Scanner scanner = new Scanner(System.in);
        boolean isDigit;

        do
        {
            isDigit = true;

            for (int i = 0; i < numString.length(); i++)
            {
                if (!Character.isDigit(numString.charAt(i)))
                {
                    isDigit = false;
                    break;
                }
            }

            if(isDigit)
                break;

            System.out.println("Некорректное значение! Попробуйте еще раз");
            numString = scanner.nextLine();

        }while(!isDigit);

        return numString;
    }

    public static boolean CheckExist(double[] sides)
    {
        if( ((sides[0]+sides[1]) < sides[2]) || ((sides[0]+sides[2]) < sides[1]) || ((sides[1]+sides[2]) < sides[0]) )
            return false;

        return true;
    }

    public static Triangle[] AddTriangles(Triangle[] triangle/*, boolean modeWorkForRightTriangle*/)
    {
        Scanner scanner = new Scanner(System.in);
        int newSizeMassiveInt;
        String side0, side1, side2;
        boolean checkExistBoolean = true, rightTriangle = false;

        System.out.print("Сколько хотите добавить треугольников: ");
        String newSizeMassive = IMethods.CheckNumber(scanner.nextLine());
        newSizeMassiveInt = Integer.parseInt(newSizeMassive);

        Triangle[] reservMassive = new Triangle[newSizeMassiveInt+triangle.length];

        for (int i = 0; i < reservMassive.length; i++)
        {
            reservMassive[i] = new Triangle();
            if(i < triangle.length)
                reservMassive[i] = triangle[i];
        }

        do {
            for (int i = triangle.length; i < reservMassive.length; i++)
            {
                double[] reservSides = new double[3];

                System.out.println("\nТреугольник №" + (i + 1));
                System.out.println("Введите 3 стороны: ");
                System.out.print(" Сторонa №1: ");
                side0 = CheckNumber(scanner.nextLine());

                System.out.print("\n Сторонa №2: ");
                side1 = CheckNumber(scanner.nextLine());

                System.out.print("\n Сторонa №3: ");
                side2 = CheckNumber(scanner.nextLine());

                reservSides[0] = Integer.parseInt(side0);
                reservSides[1] = Integer.parseInt(side1);
                reservSides[2] = Integer.parseInt(side2);

                checkExistBoolean = CheckExist(reservSides);
                if(!checkExistBoolean)
                {
                    System.out.println("\nТакого треугольника не может существовать. Попробуйте еще раз\n");
                    i--;
                }
                else
                    reservMassive[i] = new Triangle(reservSides);

            }
        }while (!checkExistBoolean);

        return reservMassive;
    }

    public static RightTriangle[] AddRightTriangle(RightTriangle[] rightTriangles)
    {
        Scanner scanner = new Scanner(System.in);
        int newSizeMassiveInt;
        String side0, side1, side2;
        boolean checkExistBoolean = true;

        System.out.print("Сколько хотите добавить треугольников: ");
        String newSizeMassive = IMethods.CheckNumber(scanner.nextLine());
        newSizeMassiveInt = Integer.parseInt(newSizeMassive);

        RightTriangle[] reservMassive = new RightTriangle[newSizeMassiveInt+rightTriangles.length];

        for (int i = 0; i < reservMassive.length; i++)
        {
            reservMassive[i] = new RightTriangle();
            if(i < rightTriangles.length)
                reservMassive[i] = rightTriangles[i];
        }

        do {
            for (int i = rightTriangles.length; i < reservMassive.length; i++)
            {
                double[] reservSides = new double[3];

                System.out.println("\nТреугольник №" + (i + 1));
                System.out.println("Введите 3 стороны: ");
                System.out.print(" Сторонa №1: ");
                side0 = CheckNumber(scanner.nextLine());

                System.out.print("\n Сторонa №2: ");
                side1 = CheckNumber(scanner.nextLine());

                System.out.print("\n Сторонa №3: ");
                side2 = CheckNumber(scanner.nextLine());

                reservSides[0] = Integer.parseInt(side0);
                reservSides[1] = Integer.parseInt(side1);
                reservSides[2] = Integer.parseInt(side2);

                checkExistBoolean = CheckExist(reservSides);
                if(!checkExistBoolean)
                {
                    System.out.println("\nТакого треугольника не может существовать. Попробуйте еще раз\n");
                    i--;
                }
                else
                {
                    if ( Math.pow(reservSides[0],2) == (Math.pow(reservSides[1],2)+Math.pow(reservSides[2],2)) ||
                            Math.pow(reservSides[1],2) == (Math.pow(reservSides[0],2)+Math.pow(reservSides[2],2)) ||
                            Math.pow(reservSides[2],2) == (Math.pow(reservSides[0],2)+Math.pow(reservSides[1],2)) )
                    {
                        reservMassive[i] = new RightTriangle(reservSides);
                    }
                    else
                    {
                        System.out.println("\nС такими сторонами прям. треугольник не может существовать. Попробуйте еще раз\n");
                        checkExistBoolean = false;
                        i--;
                    }
                }

            }
        }while (!checkExistBoolean);

        return reservMassive;
    }

    public static double[] CheckRightTriangle(double[] sides)
    {
        if ( Math.pow(sides[0],2) == (Math.pow(sides[1],2)+Math.pow(sides[2],2)) ||
                Math.pow(sides[1],2) == (Math.pow(sides[0],2)+Math.pow(sides[2],2)) ||
                Math.pow(sides[2],2) == (Math.pow(sides[0],2)+Math.pow(sides[1],2)) )
        {
            return sides;
        }

        return new double[]{0,0,0};
    }

    public static void ShowTriangles(Triangle[] triangle)
    {
        System.out.println("\n");
        for (int i = 0; i < triangle.length; i++)
        {
            System.out.println("Треугольник №" + (i+1));
            System.out.print(" Стороны: ");
            for (int j = 0; j < SIDES; j++)
            {
                System.out.printf("%.1f",triangle[i].getSides()[j]);
                if(j != 2)
                System.out.print("; ");
            }

            System.out.print("\n Углы: ");
            for (int j = 0; j < SIDES; j++)
            {
                System.out.printf("%.0f",triangle[i].getAngles()[j]);
                if(j != 2)
                    System.out.print("; ");
            }

            System.out.print("\n Периметр: ");
            System.out.printf("%.1f", triangle[i].getPerimeter());

            System.out.print("\n Площадь: ");
            System.out.printf("%.1f",triangle[i].getSquare());

            System.out.println("\n");
        }
    }

    public static void ShowOneTriangles(Triangle[] triangle, int index)
    {
        System.out.println("Треугольник №" + (index+1));
        System.out.print(" Стороны: ");
        for (int j = 0; j < SIDES; j++)
        {
            System.out.printf("%.1f",triangle[index].getSides()[j]);
            if(j != 2)
                System.out.print("; ");
        }

        System.out.print("\n Углы: ");
        for (int j = 0; j < SIDES; j++)
        {
            System.out.printf("%.0f",triangle[index].getAngles()[j]);
            if(j != 2)
                System.out.print("; ");
        }

        System.out.print("\n Периметр: ");
        System.out.printf("%.1f", triangle[index].getPerimeter());

        System.out.print("\n Площадь: ");
        System.out.printf("%.1f",triangle[index].getSquare());
    }

    public static void ShowSpecialTriangles(Triangle[] triangle, Triangle[] rightTriangle)
    {
        double max = 0, min = 100;
        int index = 0;

        for(int i = 0; i < triangle.length; i++)
        {
            if(triangle[i].getSquare() > max)
            {
                max = triangle[i].getSquare();
                index = i;
            }
        }
        System.out.println("\nТреугольник с найбольшей площадью:");
        ShowOneTriangles(triangle, index);

        for(int i = 0; i < rightTriangle.length; i++)
        {
            for (int j = 0; j < SIDES; j++)
            {
                if (rightTriangle[i].getSides()[j] < min)
                {
                    min = triangle[i].getSquare();
                    index = i;
                }
            }
        }
        System.out.println("\n\nПрям. треугольник с найменьшей гипотенузой:");
        ShowOneTriangles(rightTriangle, index);

    }
}
