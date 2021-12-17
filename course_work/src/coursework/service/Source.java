package coursework.service;

import coursework.models.Application;

public class Source
{
    private static int allRequestNumber;
    private final int number;
    private final double lambda;
    private double previousGenTime;
    private int requestNumber;

    public Source(int number, double lambda)
    {
        this.number = number;
        this.lambda = lambda;
        previousGenTime = 0.0;
        requestNumber = 0;
    }

    public Application generate()
    {
        double genTime = previousGenTime + (-1/lambda) * Math.log(Math.random());
        previousGenTime = genTime;
        requestNumber++;
        allRequestNumber++;
        return new Application(genTime, number, requestNumber);
    }

    public static int getAllRequestNumber()
    {
        return allRequestNumber;
    }
}
