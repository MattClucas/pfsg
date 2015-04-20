package com.clucasprojects.sacredgeometry;

import java.util.Random;

/**
 * Author: Matt Clucas
 * Main Calculator for Sacred Geometry App
 */
public class PrimeCalculator {
    private static final int TIMEOUT_TIME_MS = 4000;
    private int[] primeConstants = new int[3];
    private int result = 0;
    private String equation = "";

    public int getResult() {
        return result;
    }

    public String getEquation() {
        return equation;
    }

    public int[] getPrimeConstants() {
        return primeConstants;
    }

    /**
     * Sets int[] primeConstant, int result, and String equation member variables based on the spellLevel and rolls.
     * If no match is found, result is set to 0 and equation is set to "".
     * @param spellLevel The effective spell level.
     * @param rolls The values of each dice roll
     */
    public void calculate(String spellLevel, String rolls)
    {
        int level = Integer.parseInt(spellLevel);
        int[] totalRolls = new int [rolls.length()];
        for (int i = 0; i<rolls.length(); i++)
        {
            totalRolls[i] = Character.getNumericValue(rolls.charAt(i));
        }

        calculate(level, totalRolls);
    }

    /**
     * Sets int[] primeConstant, int result, and String equation member variables based on the spellLevel and rolls.
     * If no match is found, result is set to 0 and equation is set to "".
     * @param spellLevel The effective spell level.
     * @param rolls The values of each dice roll
     */
    public void calculate(int spellLevel, int[] rolls) {
        setPrimeConstants(spellLevel);
        int operator; //1:+,2:-,3:*,4:/
        int total = 0; //just use result later
        long startTime = System.currentTimeMillis();
        char[] operators = new char[rolls.length-1];
        Random generator = new Random();

        while (total!=primeConstants[0] && total!=primeConstants[1] && total!= primeConstants[2])
        {
            //count++;
            shuffleArray(rolls);
            total = rolls[0];

            for (int i = 1; i < rolls.length; i++)
            {
                operator = generator.nextInt(4) + 1;
                switch (operator)
                {
                    case 1:
                        total = total + rolls[i];
                        operators[i-1]='+';
                        break;
                    case 2:
                        if (total - rolls[i]>0)
                        {
                            total = total - rolls[i];
                            operators[i-1]='-';
                        }
                        else
                        {
                            total = total * rolls[i];
                            operators[i-1]='*';
                        }
                        break;
                    case 3:
                        total = total * rolls[i];
                        operators[i-1]='*';
                        break;
                    case 4:
                        if(total % rolls[i] == 0)
                        {
                            total = total / rolls[i];
                            operators[i-1]='/';
                        }
                        else
                        {
                            total = total + rolls[i];
                            operators[i-1]='+';
                        }
                        break;
                    default:
                        break;
                }

            }

            if (checkTimeout(startTime))
            {
                return;
            }
        }

        result = total;
        setEquation(rolls, operators);

    }

    /**
     * Sets the prime constants based on the spell level
     *
     * @param spellLevel The Effective Spell Level
     */
    private void setPrimeConstants(int spellLevel)
    {
        switch(spellLevel)
        {
            case 1:
                setPrimeConstants(3,5,7);
                break;
            case 2:
                setPrimeConstants(11,13,17);
                break;
            case 3:
                setPrimeConstants(19,23,29);
                break;
            case 4:
                setPrimeConstants(31,37,41);
                break;
            case 5:
                setPrimeConstants(43,47,53);
                break;
            case 6:
                setPrimeConstants(59,61,67);
                break;
            case 7:
                setPrimeConstants(71,73,79);
                break;
            case 8:
                setPrimeConstants(83,89,97);
                break;
            case 9:
                setPrimeConstants(101,103,107);
                break;
            default:
                System.exit(1);
        }
    }

    /**
     * Directly sets the values in int[] primeConstants
     *
     * @param p1 value to set primeConstants[0] to
     * @param p2 value to set primeConstants[1] to
     * @param p3 value to set primeConstants[2] to
     */
    private void setPrimeConstants(int p1, int p2, int p3)
    {
        primeConstants[0] = p1;
        primeConstants[1] = p2;
        primeConstants[2] = p3;
    }

    /**
     * Checks if there is a timeout.  A timeout occurs when the difference of the
     * current system time and the startTime is greater than TIMEOUT_TIME_MS class constant
     * @param startTime A previously stored system time
     * @return True on a timeout, otherwise false
     */
    private boolean checkTimeout(long startTime)
    {
        if (System.currentTimeMillis()-startTime>TIMEOUT_TIME_MS)
        {
            result = 0;
            equation = "";
            return true;
        }
        return false;
    }

    /**
     * Fills the equation string class variable based on the rolls and operators.
     * Used after a result is found. Assumes rolls is always one larger than operators.
     * ex:
     * rolls = [3,3,2]
     * operators = [*,-]
     * equation = "3*3-2"
     *
     * @param rolls the numeric values of all dice rolls
     * @param operators the operators used on the rolls to get the desired prime constant
     */
    private void setEquation(int[] rolls, char[] operators)
    {
        equation = "";
        for (int i = 0; i < rolls.length-1; i++)
        {
            equation += rolls[i];
            equation += operators[i];
        }
        equation += rolls[rolls.length-1];
    }

    // Implementing Fisher–Yates shuffle
    private static void shuffleArray(int[] ar)
    {
        Random rnd = new Random();
        for (int i = ar.length - 1; i > 0; i--)
        {
            int index = rnd.nextInt(i + 1);
            // Simple swap
            int a = ar[index];
            ar[index] = ar[i];
            ar[i] = a;
        }
    }
}
