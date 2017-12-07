package org.Model;

public class CandyFactory {

    public static Candy makeCandy(int typeOfCandy){

        if (typeOfCandy==0)
            return new CherryCandy();

        if (typeOfCandy==1)
            return new NormalCandy();

        if (typeOfCandy==2)
            return new SuperCandy();

        if (typeOfCandy==3)
            return new TastyCandy();

        else
            return null;
    }

}
