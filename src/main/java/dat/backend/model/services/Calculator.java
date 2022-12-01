package dat.backend.model.services;

import dat.backend.model.persistence.ConnectionPool;
import dat.backend.model.persistence.PartFacade;

import java.util.ArrayList;

public class Calculator {

    //Code for posts
    //1 is hardcoded in methods since we don't choose between different posts on our site

    public static int amountOfPosts(int length) {
        int amount = 4;

        if (2 * ((length - length * 0.165) / 310 + 1) > 4) {

            amount = (int) (2 * ((length - length * 0.165) / 310 + 1));
            if (amount % 2 != 0) {
                amount++;
            }

        }
        return amount;
    }

    public int postid(ConnectionPool connectionPool) {
        return PartFacade.variantidFromPartid(1, connectionPool);
    }

//    private int postPrice(int length){
//
//    }



    //Code for rafters

    //2 is hardcoded since we don't choose between different rafter on our site
    public static int rafterVariantID(int width, ConnectionPool connectionPool) {

        ArrayList<Integer> lengthList = PartFacade.partsLengthFromPartid(2, connectionPool);

        int variantid = 0;

        for (int i : lengthList) {
            if (lengthList.get(i) >= width) {
                variantid = lengthList.get(i);
                break;
            }
        }
        return variantid;
    }

    public static int numberOfRafters(double lenght) {
        double num = lenght / 55 + 1;
        num = Math.round(num);
        int amount = (int) num;
        return amount;
    }



    //Code for beams

    //3 is hardcoded in methods since we don't choose between different beams on our site
    public static int[] beamVariantsid(int length, ConnectionPool connectionPool) {
        int[] idArray = new int[2];


        ArrayList<Integer> lengthList = PartFacade.partsLengthFromPartid(3, connectionPool);

        if (length <= 660) {
            for (int i : lengthList) {
                if (lengthList.get(i) >= length) {
                    idArray[0] = PartFacade.variantidFromLengthAndPartid(lengthList.get(i), 3, connectionPool);
                    break;
                }
            }
        } else {
            idArray = getVariantsidsFromLength(length, 3, connectionPool);
        }

        return idArray;
    }


    //Code for screws
    //4 is hardcoded in methods since we don't choose between different screws on our site

    public int screwid(ConnectionPool connectionPool) {
        return PartFacade.variantidFromPartid(4, connectionPool);
    }


    //Code for roof screws
    //5 is hardcoded in methods since we don't choose between different roof screws on our site
    public int roofScrewid(ConnectionPool connectionPool) {
        return PartFacade.variantidFromPartid(5, connectionPool);
    }


    //Code for roof panels
    //6 is hardcoded in methods since we don't choose between different roof panels on our site
    public int amountOfRoofPanels(int width) {
        return Math.round(width / 100);
    }

    public static int[] roofPanelsVariantsid(int length, ConnectionPool connectionPool) {
        int[] idArray = new int[2];


        ArrayList<Integer> lengthList = PartFacade.partsLengthFromPartid(6, connectionPool);

        if (length <= 600) {
            for (int i : lengthList) {
                if (lengthList.get(i) >= length) {
                    idArray[0] = PartFacade.variantidFromLengthAndPartid(lengthList.get(i), 6, connectionPool);
                    break;
                }
            }
        } else {
            idArray = getVariantsidsFromLength(length, 6, connectionPool);
        }

        return idArray;
    }


    //Code for bolts
    //7 is hardcoded in methods since we don't choose between different bolts on our site
    public int boltid(ConnectionPool connectionPool) {
        return PartFacade.variantidFromPartid(7, connectionPool);
    }

    public int amountOfBolts(int length){
        return amountOfPosts(length) * 3;
    }


    //Code for square discs
    //8 is hardcoded in methods since we don't choose between different square discs on our site
    public int discid(ConnectionPool connectionPool) {
        return PartFacade.variantidFromPartid(8, connectionPool);
    }

    public int amountOfDiscs(int length){
        return amountOfPosts(length) * 2;
    }


    //Calculations for totalprices


    //Helper methods


    //Used for both beams and roof panels when the length of one is not enough
    private static int[] getVariantsidsFromLength(int length, int partid, ConnectionPool connectionPool) {
        int[] idArray = new int[2];
        if (length == 630) {
            idArray[0] = PartFacade.variantidFromLengthAndPartid(300, partid, connectionPool);
            idArray[1] = PartFacade.variantidFromLengthAndPartid(330, partid, connectionPool);
        } else if (length == 660) {
            idArray[0] = PartFacade.variantidFromLengthAndPartid(300, partid, connectionPool);
            idArray[1] = PartFacade.variantidFromLengthAndPartid(360, partid, connectionPool);

        } else if (length == 690) {
            idArray[0] = PartFacade.variantidFromLengthAndPartid(300, partid, connectionPool);
            idArray[1] = PartFacade.variantidFromLengthAndPartid(390, partid, connectionPool);
        } else if (length == 720) {
            idArray[0] = PartFacade.variantidFromLengthAndPartid(300, partid, connectionPool);
            idArray[1] = PartFacade.variantidFromLengthAndPartid(420, partid, connectionPool);
        } else if (length == 750) {
            idArray[0] = PartFacade.variantidFromLengthAndPartid(300, partid, connectionPool);
            idArray[1] = PartFacade.variantidFromLengthAndPartid(450, partid, connectionPool);
        } else {
            idArray[0] = PartFacade.variantidFromLengthAndPartid(300, partid, connectionPool);
            idArray[1] = PartFacade.variantidFromLengthAndPartid(480, partid, connectionPool);
        }

        return idArray;
    }

}
