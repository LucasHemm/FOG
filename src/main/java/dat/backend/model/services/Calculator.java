package dat.backend.model.services;

import dat.backend.model.persistence.ConnectionPool;
import dat.backend.model.persistence.PartFacade;

import java.util.ArrayList;

public class Calculator {

    //Code for posts

    public static int numberOfPosts(int length) {
        int amount = 4;

        if (2 * ((length - length * 0.165) / 310 + 1) > 4) {

            amount = (int) (2 * ((length - length * 0.165) / 310 + 1));
            if (amount % 2 != 0) {
                amount++;
            }

        }
        return amount;
    }


    //Code for rafters

    public static int numberOfRafters(double lenght) {
        double num = lenght / 55 + 1;
        num = Math.round(num);
        int amount = (int) num;
        return amount;
    }


    //2 is hardcoded since we don't choose between different rafter on our site
    public static int rafterVariantID(int width, ConnectionPool connectionPool){

        ArrayList<Integer> lengthList = PartFacade.partsLengthFromPartid(2,connectionPool);

        int variantid = 0;

        for(int i : lengthList){
            if (lengthList.get(i) >= width) {
                variantid = lengthList.get(i);
                break;
            }
        }
        return variantid;
    }



    //Code for beams

    //3 is hardcoded in methods since we don't choose between different beams on our site
    public static int[] beamVariantsid(int length, ConnectionPool connectionPool){
        int[] idArray= new int[2];


        ArrayList<Integer> lengthList = PartFacade.partsLengthFromPartid(3,connectionPool);

        if (length <= 660) {
            for (int i : lengthList) {
                if ( lengthList.get(i) >= length){
                    idArray[0] = PartFacade.variantidFromLengthAndPartid(lengthList.get(i),3,connectionPool);
                    break;
                }
            }
        }
        else if (length >= 690 && length < 750) {
            idArray[0] = PartFacade.variantidFromLengthAndPartid(300,3,connectionPool);
            idArray[1] = PartFacade.variantidFromLengthAndPartid(420,3,connectionPool);

        }
        else {
            idArray[0] = PartFacade.variantidFromLengthAndPartid(300,3,connectionPool);
            idArray[1] = PartFacade.variantidFromLengthAndPartid(480,3,connectionPool);
        }

        return idArray;
    }


    //4 is hardcoded in methods since we don't choose between different beams on our site
    //Code for screws

    public int screwid(ConnectionPool connectionPool){
        return PartFacade.variantidFromPartid(4, connectionPool);
    }


    //Code for bolt


    //Code for roof panels


    //Code for square discs


    //Calculations for totalprices


}
