package com.company;

public class RightTriangle extends Triangle
{
    public RightTriangle(double[] sides)
    {
        sides = IMethods.CheckRightTriangle(sides);

        setSides(sides);
        setAngles();
        setPerimeter();
        setSquare();
    }

    public RightTriangle()
    {
        for (int i = 0; i < SIDES; i++)
            this.sides[i] = 0;
    }

    public void setAngles() {
        if(sides[0] == 0 || sides[1] == 0 || sides[2] == 0)
        {
            angles[0] = 0;
            angles[1] = 0;
            angles[2] = 0;
        }
        else {
            angles[0] = 90;
            angles[1] = Math.toDegrees(Math.abs(Math.cos(Math.toRadians((Math.pow(sides[0], 2) + Math.pow(sides[1], 2) - Math.pow(sides[2], 2)) / 2 * sides[0] * sides[1]))));
            angles[2] = Math.toDegrees(Math.abs(Math.cos(Math.toRadians((Math.pow(sides[1], 2) + Math.pow(sides[2], 2) - Math.pow(sides[0], 2)) / 2 * sides[2] * sides[1]))));
        }
    }
}
