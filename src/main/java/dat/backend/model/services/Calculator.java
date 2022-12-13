package dat.backend.model.services;

import dat.backend.model.persistence.ConnectionPool;
import dat.backend.model.persistence.PartFacade;

import java.util.ArrayList;

public class Calculator {

    //Code for posts
    //1 is hardcoded in methods since we don't choose between different posts on our site

    public static int amountOfPosts(int length, boolean hasShed, int width) {
        int amount = 4;

        amount = (int) (2 * ((length - length * 0.165) / 310 + 1));
        amount += amount % 2;

        if (hasShed) {

            amount += width / 300 + 3;
        }
        return amount;
    }

    public static int postid(ConnectionPool connectionPool) {
        return PartFacade.variantidFromPartid(1, connectionPool);
    }

    private static double postPrice(int length, ConnectionPool connectionPool, boolean hasShed, int width) {

        int amount = amountOfPosts(length, hasShed, width);
        int partid = postid(connectionPool);


        //300 since the post is always 3 meters
        double price = PartFacade.pricePrMeter(300, partid, connectionPool) * amount;

        return price;
    }

    private static double postCostPrice(int length, ConnectionPool connectionPool, boolean hasShed, int width) {
        int amount = amountOfPosts(length, hasShed, width);
        int partid = postid(connectionPool);

        //300 since the post is always 3 meters
        return PartFacade.costPricePrMeter(300, partid, connectionPool) * amount;

    }


    //Code for rafters

    //2 is hardcoded since we don't choose between different rafter on our site
    public static int rafterVariantID(int width, ConnectionPool connectionPool) {

        ArrayList<Integer> lengthList = PartFacade.partsLengthFromPartid(2, connectionPool);

        int variantid = 0;

        for (int i = 0; i < lengthList.size(); i++) {
            if (lengthList.get(i) >= width) {
                variantid = PartFacade.variantidFromLengthAndPartid(lengthList.get(i), 2, connectionPool);
                break;
            }
        }
        return variantid;
    }

    public static double spaceBetweenRafters(int length) {
        double space = (length - 4.5 * amountOfRafters(length)) / (amountOfRafters(length) - 2);
        System.out.println(space);
        return space;
    }

    public static int amountOfRafters(double lenght) {
        double num = lenght / 55 + 1;
        num = Math.round(num);
        int amount = (int) num;
        return amount;
    }


    public static double rafterPrice(int length, int width, ConnectionPool connectionPool) {

        int amount = amountOfRafters(length);

        double price = PartFacade.pricePrMeter(width, 2, connectionPool) * amount;

        return price;
    }

    private static double rafterCostPrice(int length, int width, ConnectionPool connectionPool) {
        int amount = amountOfRafters(length);

        double price = PartFacade.costPricePrMeter(width, 2, connectionPool) * amount;

        return price;
    }


    //Code for beams

    //3 is hardcoded in methods since we don't choose between different beams on our site
    public static int[] beamVariantsid(int length, ConnectionPool connectionPool) {
        int[] idArray = new int[2];


        ArrayList<Integer> lengthList = PartFacade.partsLengthFromPartid(3, connectionPool);

        if (length <= 660) {
            for (int i = 0; i < lengthList.size(); i++) {
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


    public static double beamPrice(int lenght, ConnectionPool connectionPool) {
        double price = 0;

        int partid1 = beamVariantsid(lenght, connectionPool)[0];
        int partid2 = beamVariantsid(lenght, connectionPool)[1];
        int length1 = PartFacade.getLengthFromVariantid(partid1, connectionPool);
        int length2 = PartFacade.getLengthFromVariantid(partid2, connectionPool);

        price += PartFacade.pricePrMeter(length1, 3, connectionPool) * 2;
        System.out.println(price);
        price += PartFacade.pricePrMeter(length2, 3, connectionPool) * 2;
        System.out.println(price);

        return price;
    }

    private static double beamCostPrice(int lenght, ConnectionPool connectionPool) {
        double price = 0;

        int partid1 = beamVariantsid(lenght, connectionPool)[0];
        int partid2 = beamVariantsid(lenght, connectionPool)[1];
        int length1 = PartFacade.getLengthFromVariantid(partid1, connectionPool);
        int length2 = PartFacade.getLengthFromVariantid(partid2, connectionPool);

        price += PartFacade.costPricePrMeter(length1, 3, connectionPool) * 2;
        price += PartFacade.costPricePrMeter(length2, 3, connectionPool) * 2;

        return price;
    }


    //Code for fitting screws
    //4 is hardcoded in methods since we don't choose between different fitting screws on our site
    //Amount is hardcoded since it will always stay the same
    private static int fittingScrewAmount = 3;

    public static int fittingScrewid(ConnectionPool connectionPool) {
        return PartFacade.variantidFromPartid(4, connectionPool);
    }

    private static double fittingScrewPrice(ConnectionPool connectionPool) {
        return PartFacade.PricePrAmount(4, connectionPool) * fittingScrewAmount;
    }

    private static double fittingScrewCostPrice(ConnectionPool connectionPool) {
        return PartFacade.costPricePrAmount(4, connectionPool) * fittingScrewAmount;
    }


    //Code for roof screws
    //5 is hardcoded in methods since we don't choose between different roof screws on our site
    //Amount is hardcoded since it will always stay the same
    private static int roofScrewAmount = 3;

    public static int roofScrewid(ConnectionPool connectionPool) {
        return PartFacade.variantidFromPartid(5, connectionPool);
    }

    private static double roofScrewPrice(ConnectionPool connectionPool) {
        return PartFacade.PricePrAmount(5, connectionPool) * roofScrewAmount;
    }

    private static double roofScrewCostPrice(ConnectionPool connectionPool) {
        return PartFacade.costPricePrAmount(5, connectionPool) * roofScrewAmount;
    }

    //Code for roof panels
    //6 is hardcoded in methods since we don't choose between different roof panels on our site
    public static int amountOfRoofPanels(int width) {
        return Math.round(width / 100);
    }

    public static int[] roofPanelsVariantsid(int length, ConnectionPool connectionPool) {
        int[] idArray = {0, 0};


        ArrayList<Integer> lengthList = PartFacade.partsLengthFromPartid(6, connectionPool);

        if (length <= 600) {
            for (int i = 0; i < lengthList.size(); i++) {
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

    public static double roofPanelPrice(int lenght, int width, ConnectionPool connectionPool) {

        int partid1 = roofPanelsVariantsid(lenght, connectionPool)[0];
        int partid2 = roofPanelsVariantsid(lenght, connectionPool)[1];
        int length1 = PartFacade.getLengthFromVariantid(partid1, connectionPool);
        int length2 = PartFacade.getLengthFromVariantid(partid2, connectionPool);
        int amount = amountOfRoofPanels(width);

        double price = PartFacade.pricePrMeter(length1, 6, connectionPool) * amount;
        price += PartFacade.pricePrMeter(length2, 6, connectionPool) * amount;

        return price;
    }

    private static double roofPanelCostPrice(int lenght, int width, ConnectionPool connectionPool) {
        double price = 0;

        int partid1 = roofPanelsVariantsid(lenght, connectionPool)[0];
        int partid2 = roofPanelsVariantsid(lenght, connectionPool)[1];
        int length1 = PartFacade.getLengthFromVariantid(partid1, connectionPool);
        int length2 = PartFacade.getLengthFromVariantid(partid2, connectionPool);
        int amount = amountOfRoofPanels(width);

        price += PartFacade.costPricePrMeter(length1, 6, connectionPool) * amount;
        price += PartFacade.costPricePrMeter(length2, 6, connectionPool) * amount;

        return price;
    }


    //Code for bolts
    //7 is hardcoded in methods since we don't choose between different bolts on our site

    public static int boltid(ConnectionPool connectionPool) {
        return PartFacade.variantidFromPartid(7, connectionPool);
    }

    public static int amountOfBolts(int length, boolean hasShed, ConnectionPool connectionPool, int width) {
        int amount = amountOfPosts(length, hasShed, width) + 1;

        int[] arr = beamVariantsid(length, connectionPool);

        if (arr[1] != 0) {
            amount += 6;
        }

        return amount;
    }


    private static double boltPrice(int length, ConnectionPool connectionPool, boolean hasShed, int width) {
        return PartFacade.PricePrAmount(7, connectionPool) * amountOfBolts(length, hasShed, connectionPool, width);
    }

    private static double boltCostPrice(int length, ConnectionPool connectionPool, boolean hasShed, int width) {
        return PartFacade.costPricePrAmount(7, connectionPool) * amountOfBolts(length, hasShed, connectionPool, width);
    }


    //Code for square discs
    //8 is hardcoded in methods since we don't choose between different square discs on our site
    public static int discid(ConnectionPool connectionPool) {
        return PartFacade.variantidFromPartid(8, connectionPool);
    }

    public static int amountOfDiscs(int length, boolean hasShed, int width) {
        return amountOfPosts(length, hasShed, width) + 1;
    }

    private static double discPrice(int length, ConnectionPool connectionPool, boolean hasShed, int width) {
        return PartFacade.PricePrAmount(8, connectionPool) * amountOfDiscs(length, hasShed, width);
    }

    private static double discCostPrice(int length, boolean hasShed, ConnectionPool connectionPool, int width) {
        return PartFacade.costPricePrAmount(8, connectionPool) * amountOfDiscs(length, hasShed, width);
    }


    //10 is hardcoded in methods since we don't choose between different hollow band on our site
    //Amount of hollow bands is always 2
    private static int bandAmount = 2;

    public static int bandid(ConnectionPool connectionPool) {
        return PartFacade.variantidFromPartid(10, connectionPool);
    }

    private static double bandPrice(ConnectionPool connectionPool) {
        return PartFacade.PricePrAmount(10, connectionPool) * bandAmount;
    }

    private static double bandCostPrice(ConnectionPool connectionPool) {
        return PartFacade.costPricePrAmount(10, connectionPool) * bandAmount;
    }


    //11 is hardcoded in methods since we don't choose between different right fittings on our site
    public static int rightFittingid(ConnectionPool connectionPool) {
        return PartFacade.variantidFromPartid(11, connectionPool);
    }

    public static int amountOfRightFittings(double length) {
        return amountOfRafters(length);
    }

    private static double rightFittingsPrice(int length, ConnectionPool connectionPool) {
        return PartFacade.PricePrAmount(11, connectionPool) * amountOfRightFittings(length);
    }

    private static double rightFittingsCostPrice(int length, ConnectionPool connectionPool) {
        return PartFacade.costPricePrAmount(11, connectionPool) * amountOfRightFittings(length);
    }

    //12 is hardcoded in methods since we don't choose between different left fittings on our site
    public static int leftFittingid(ConnectionPool connectionPool) {
        return PartFacade.variantidFromPartid(12, connectionPool);
    }

    public static int amountOfLeftFittings(double length) {
        return amountOfRafters(length);
    }

    private static double leftFittingsPrice(int length, ConnectionPool connectionPool) {
        return PartFacade.PricePrAmount(12, connectionPool) * amountOfLeftFittings(length);
    }

    private static double leftFittingsCostPrice(int length, ConnectionPool connectionPool) {
        return PartFacade.costPricePrAmount(12, connectionPool) * amountOfLeftFittings(length);
    }


    //13 is hardcoded in methods since we don't choose between different laths on our site
    private static int lathAmount = 1;

    public static int lathid(ConnectionPool connectionPool) {
        return PartFacade.variantidFromPartid(13, connectionPool);
    }

    private static double lathPrice(ConnectionPool connectionPool) {
        return PartFacade.PricePrAmount(13, connectionPool) * lathAmount;
    }

    private static double lathCostPrice(ConnectionPool connectionPool) {
        return PartFacade.costPricePrAmount(13, connectionPool) * lathAmount;
    }

    //14 is hardcoded in methods since we don't choose between different boards on our site
    public static int boardid(ConnectionPool connectionPool) {
        return PartFacade.variantidFromPartid(14, connectionPool);
    }

    private static int boardAmount(int shedLength, int shedWidth) {
        return (int) Math.round(2 * (shedLength + shedWidth) * 0.13);
    }

    private static double boardPrice(int shedLength, int shedWidth, ConnectionPool connectionPool) {
        return PartFacade.PricePrAmount(14, connectionPool) * boardAmount(shedLength, shedWidth);
    }

    private static double boardCostPrice(int shedLength, int shedWidth, ConnectionPool connectionPool) {
        return PartFacade.costPricePrAmount(14, connectionPool) * boardAmount(shedLength, shedWidth);
    }

    //15 is hardcoded in methods since we don't choose between different loose hollows for the gables on our site
    private static int gableHollowsAmount = 6;

    public static int gableHollowid(int shedWidth, ConnectionPool connectionPool) {
        ArrayList<Integer> lengthList = PartFacade.partsLengthFromPartid(15, connectionPool);

        int variantid = 0;

        for (int i = 0; i < lengthList.size(); i++) {
            if (lengthList.get(i) >= shedWidth) {
                variantid = PartFacade.variantidFromLengthAndPartid(lengthList.get(i), 15, connectionPool);
                break;
            }
        }
        return variantid;
    }

    private static int gableHollowLength(int shedWidth, ConnectionPool connectionPool) {
        return PartFacade.getLengthFromVariantid(gableHollowid(shedWidth, connectionPool), connectionPool);
    }

    private static double gableHollowPrice(int shedWidth, ConnectionPool connectionPool) {
        return PartFacade.pricePrMeter(gableHollowLength(shedWidth, connectionPool), 15, connectionPool) * gableHollowsAmount;
    }

    private static double gableHollowCostPrice(int shedWidth, ConnectionPool connectionPool) {
        return PartFacade.costPricePrMeter(gableHollowLength(shedWidth, connectionPool), 15, connectionPool) * gableHollowsAmount;
    }

    //16 is hardcoded in methods since we don't choose between different loose hollows for the sides on our site
    private static int sideHollowsAmount = 4;

    public static int sideHollowid(int shedLength, ConnectionPool connectionPool) {
        ArrayList<Integer> lengthList = PartFacade.partsLengthFromPartid(16, connectionPool);

        int variantid = 0;

        for (int i = 0; i < lengthList.size(); i++) {
            if (lengthList.get(i) >= shedLength) {
                variantid = PartFacade.variantidFromLengthAndPartid(lengthList.get(i), 16, connectionPool);
                break;
            }
        }
        return variantid;
    }

    private static int sideHollowLength(int shedLength, ConnectionPool connectionPool) {
        return PartFacade.getLengthFromVariantid(sideHollowid(shedLength, connectionPool), connectionPool);
    }

    private static double sideHollowPrice(int shedLength, ConnectionPool connectionPool) {
        return PartFacade.pricePrMeter(sideHollowLength(shedLength, connectionPool), 16, connectionPool) * gableHollowsAmount;
    }

    private static double sideHollowCostPrice(int shedLength, ConnectionPool connectionPool) {
        return PartFacade.costPricePrMeter(sideHollowLength(shedLength, connectionPool), 16, connectionPool) * gableHollowsAmount;
    }

    //17 is hardcoded in methods since we don't choose between different screws on our site
    private static int screwAmount = 4;

    public static int screwid(ConnectionPool connectionPool) {
        return PartFacade.variantidFromPartid(17, connectionPool);
    }

    private static double screwPrice(ConnectionPool connectionPool) {
        return PartFacade.PricePrAmount(17, connectionPool) * screwAmount;
    }

    private static double screwCostPrice(ConnectionPool connectionPool) {
        return PartFacade.costPricePrAmount(17, connectionPool) * screwAmount;
    }

    //18 is hardcoded in methods since we don't choose between different door handles on our site
    private static int doorHandleAmount = 1;

    public static int doorHandleid(ConnectionPool connectionPool) {
        return PartFacade.variantidFromPartid(18, connectionPool);
    }

    private static double doorHandlePrice(ConnectionPool connectionPool) {
        return PartFacade.PricePrAmount(18, connectionPool) * doorHandleAmount;
    }

    private static double doorHandleCostPrice(ConnectionPool connectionPool) {
        return PartFacade.costPricePrAmount(18, connectionPool) * doorHandleAmount;
    }

    //19 is hardcoded in methods since we don't choose between different t hinges on our site
    private static int tHingesAmount = 2;

    public static int tHingesid(ConnectionPool connectionPool) {
        return PartFacade.variantidFromPartid(19, connectionPool);
    }

    private static double tHingesPrice(ConnectionPool connectionPool) {
        return PartFacade.PricePrAmount(19, connectionPool) * tHingesAmount;
    }

    private static double tHingesCostPrice(ConnectionPool connectionPool) {
        return PartFacade.costPricePrAmount(19, connectionPool) * tHingesAmount;
    }

    //20 is hardcoded in methods since we don't choose between different angle fittings on our site
    private static int angleFittingAmount = (gableHollowsAmount + sideHollowsAmount) * 2;

    public static int angleFittingid(ConnectionPool connectionPool) {
        return PartFacade.variantidFromPartid(20, connectionPool);
    }

    private static double angleFittingPrice(ConnectionPool connectionPool) {
        return PartFacade.PricePrAmount(20, connectionPool) * angleFittingAmount;
    }

    private static double angleFittingCostPrice(ConnectionPool connectionPool) {
        return PartFacade.costPricePrAmount(20, connectionPool) * angleFittingAmount;
    }


    //Calculations for totalprices


    public static double totalPriceBeforeTax(int length, int width, ConnectionPool connectionPool, boolean hasShed, int shedLength, int shedWidth) {
        double price = 0;
        price += postPrice(length, connectionPool, hasShed, width);
        price += rafterPrice(length, width, connectionPool);
        price += beamPrice(length, connectionPool);
        price += fittingScrewPrice(connectionPool);
        price += roofScrewPrice(connectionPool);
        price += roofPanelPrice(length, width, connectionPool);
        price += boltPrice(length, connectionPool, hasShed, width);
        price += discPrice(length, connectionPool, hasShed, width);
        price += bandPrice(connectionPool);
        price += rightFittingsPrice(length, connectionPool);
        price += leftFittingsPrice(length, connectionPool);

        if (hasShed) {

            price += lathPrice(connectionPool);
            price += boardPrice(shedLength,shedWidth,connectionPool);
            price += gableHollowPrice(shedWidth,connectionPool);
            price += sideHollowPrice(shedLength, connectionPool);
            price += screwPrice(connectionPool);
            price += doorHandlePrice(connectionPool);
            price += tHingesPrice(connectionPool);
            price += angleFittingPrice(connectionPool);


        }
        return price;
    }

    public static double totalPrice(int length, int width, ConnectionPool connectionPool, boolean hasShed, int shedLength, int shedWidth) {
        return totalPriceBeforeTax(length, width, connectionPool, hasShed, shedLength, shedWidth) * 1.25;
    }

    public static double totalCostPrice(int length, int width, ConnectionPool connectionPool, boolean hasShed, int shedLength, int shedWidth) {
        double price = 0;
        price += postCostPrice(length, connectionPool, hasShed, width);
        price += rafterCostPrice(length, width, connectionPool);
        price += beamCostPrice(length, connectionPool);
        price += fittingScrewCostPrice(connectionPool);
        price += roofScrewCostPrice(connectionPool);
        price += roofPanelCostPrice(length, width, connectionPool);
        price += boltCostPrice(length, connectionPool, hasShed, width);
        price += discCostPrice(length, hasShed, connectionPool, width);
        price += bandCostPrice(connectionPool);
        price += rightFittingsCostPrice(length, connectionPool);
        price += leftFittingsCostPrice(length, connectionPool);

        if (hasShed) {

            price += lathCostPrice(connectionPool);
            price += boardCostPrice(shedLength,shedWidth,connectionPool);
            price += gableHollowCostPrice(shedWidth,connectionPool);
            price += sideHollowCostPrice(shedLength, connectionPool);
            price += screwCostPrice(connectionPool);
            price += doorHandleCostPrice(connectionPool);
            price += tHingesCostPrice(connectionPool);
            price += angleFittingCostPrice(connectionPool);


        }
        return price;
    }


    public static double percentageGainBeforeTax(int length, int width, ConnectionPool connectionPool, boolean hasShed, int shedLength, int shedWidth) {
        return totalPriceBeforeTax(length, width, connectionPool, hasShed, shedLength, shedWidth) / totalCostPrice(length, width, connectionPool, hasShed, shedLength, shedWidth) * 100;
    }

    public static double percentageGainAfterTax(int length, int width, ConnectionPool connectionPool, boolean hasShed, int shedLength, int shedWidth) {
        return totalPrice(length, width, connectionPool, hasShed, shedLength, shedWidth) / totalCostPrice(length, width, connectionPool, hasShed, shedLength, shedWidth) * 100;
    }

    public static double percentageGainAfterChangedPriceBeforeTax(int newPrice, int length, int width, ConnectionPool connectionPool, boolean hasShed, int shedLength, int shedWidth) {
        return newPrice * 0.8 / totalCostPrice(length, width, connectionPool, hasShed, shedLength, shedWidth) * 100;
    }

    public static double percentageGainAfterChangedPriceAfterTax(int newPrice, int length, int width, ConnectionPool connectionPool, boolean hasShed, int shedLength, int shedWidth) {
        return newPrice / totalCostPrice(length, width, connectionPool, hasShed, shedLength, shedWidth) * 100;
    }


    //Lists of amount and IDs of each part

    public static ArrayList<Integer> listOfPartAmounts(int length, int width, ConnectionPool connectionPool, boolean hasShed, int shedLength, int shedWidth) {
        ArrayList<Integer> listOfPartAmounts = new ArrayList<>();

        //amount of posts
        listOfPartAmounts.add(amountOfPosts(length, hasShed, width));
        //Amount of rafters
        listOfPartAmounts.add(amountOfRafters(length));
        //There will always be 2 beams for beamid1
        listOfPartAmounts.add(2);
        //amount for beamid2 if the carport has a length larger than 6.6 meters
        if (beamVariantsid(length, connectionPool)[1] != 0) {
            listOfPartAmounts.add(2);
        }
        //amount for beamid2 if the carport has a length less than 6.6 meters
        else {
            listOfPartAmounts.add(0);
        }
        //Amount of fitting screws
        listOfPartAmounts.add(fittingScrewAmount);
        //Amount of roofscrews
        listOfPartAmounts.add(roofScrewAmount);
        //amount for roofid1
        listOfPartAmounts.add(amountOfRoofPanels(width));
        //amount for roofid2 if the carport has a length larger than 6 meters
        if (roofPanelsVariantsid(length, connectionPool)[1] != 0) {
            listOfPartAmounts.add(amountOfRoofPanels(width));
        }
        //amount for roofid2 if the carport has a length less than 6 meters
        else {
            listOfPartAmounts.add(0);
        }
        //Amount of bolts
        listOfPartAmounts.add(amountOfBolts(length, hasShed, connectionPool, width));
        //Amount of discs
        listOfPartAmounts.add(amountOfDiscs(length, hasShed, width));
        //Amount of hollow bands
        listOfPartAmounts.add(bandAmount);
        //Amount of right fittings
        listOfPartAmounts.add(amountOfRightFittings(length));
        //Amount of left fittings
        listOfPartAmounts.add(amountOfLeftFittings(length));

        if (hasShed) {

            //Amount of laths
            listOfPartAmounts.add(lathAmount);
            //Amount of boards
            listOfPartAmounts.add(boardAmount(shedLength, shedWidth));
            //Amount of loose hollows for the gables
            listOfPartAmounts.add(gableHollowsAmount);
            //Amount of loose hollows for the sides
            listOfPartAmounts.add(sideHollowsAmount);
            //Amount of screws
            listOfPartAmounts.add(screwAmount);
            //Amount of door handles
            listOfPartAmounts.add(doorHandleAmount);
            //Amount of t hinges
            listOfPartAmounts.add(tHingesAmount);
            //Amount of angle fittings
            listOfPartAmounts.add(angleFittingAmount);

        }
        else{
            listOfPartAmounts.add(0);
            listOfPartAmounts.add(0);
            listOfPartAmounts.add(0);
            listOfPartAmounts.add(0);
            listOfPartAmounts.add(0);
            listOfPartAmounts.add(0);
            listOfPartAmounts.add(0);
            listOfPartAmounts.add(0);
        }


        return listOfPartAmounts;
    }

    public static ArrayList<Integer> listOfIDs(int length, int width, ConnectionPool connectionPool, boolean hasShed, int shedLength, int shedWidth) {
        ArrayList<Integer> listOfIDs = new ArrayList<>();
        listOfIDs.add(postid(connectionPool));
        listOfIDs.add(rafterVariantID(width, connectionPool));
        listOfIDs.add(beamVariantsid(length, connectionPool)[0]);
        if (beamVariantsid(length, connectionPool)[1] != 0) {
            listOfIDs.add(beamVariantsid(length, connectionPool)[1]);
        }
        //ID for beamid2 if the carport has a length less than 6.6 meters
        else {
            listOfIDs.add(45);
        }
        listOfIDs.add(fittingScrewid(connectionPool));
        listOfIDs.add(roofScrewid(connectionPool));
        listOfIDs.add(roofPanelsVariantsid(length, connectionPool)[0]);
        if (roofPanelsVariantsid(length, connectionPool)[1] != 0) {
            listOfIDs.add(roofPanelsVariantsid(length, connectionPool)[1]);
        }
        //ID for roofid2 if the carport has a length less than 6 meters
        else {
            listOfIDs.add(45);
        }
        listOfIDs.add(boltid(connectionPool));
        listOfIDs.add(discid(connectionPool));
        listOfIDs.add(bandid(connectionPool));
        listOfIDs.add(rightFittingid(connectionPool));
        listOfIDs.add(leftFittingid(connectionPool));

        if (hasShed) {
            listOfIDs.add(lathid(connectionPool));
            listOfIDs.add(boardid(connectionPool));
            listOfIDs.add(gableHollowid(shedWidth,connectionPool));
            listOfIDs.add(sideHollowid(shedLength,connectionPool));
            listOfIDs.add(screwid(connectionPool));
            listOfIDs.add(doorHandleid(connectionPool));
            listOfIDs.add(tHingesid(connectionPool));
            listOfIDs.add(angleFittingid(connectionPool));
        }
        else{
            listOfIDs.add(45);
            listOfIDs.add(45);
            listOfIDs.add(45);
            listOfIDs.add(45);
            listOfIDs.add(45);
            listOfIDs.add(45);
            listOfIDs.add(45);
            listOfIDs.add(45);
        }

        return listOfIDs;
    }


    //Helper methods
    //Used for both beams and roof panels when the length of one is not enough
    private static int[] getVariantsidsFromLength(int length, int partid, ConnectionPool connectionPool) {
        int[] idArray = new int[2];

        switch (length) {
            case 630:
                idArray[0] = PartFacade.variantidFromLengthAndPartid(300, partid, connectionPool);
                idArray[1] = PartFacade.variantidFromLengthAndPartid(330, partid, connectionPool);
                break;
            case 660:
                idArray[0] = PartFacade.variantidFromLengthAndPartid(300, partid, connectionPool);
                idArray[1] = PartFacade.variantidFromLengthAndPartid(360, partid, connectionPool);
                break;
            case 690:
                idArray[0] = PartFacade.variantidFromLengthAndPartid(300, partid, connectionPool);
                idArray[1] = PartFacade.variantidFromLengthAndPartid(390, partid, connectionPool);
                break;

            case 720:
                idArray[0] = PartFacade.variantidFromLengthAndPartid(300, partid, connectionPool);
                idArray[1] = PartFacade.variantidFromLengthAndPartid(420, partid, connectionPool);
                break;

            case 750:
                idArray[0] = PartFacade.variantidFromLengthAndPartid(300, partid, connectionPool);
                idArray[1] = PartFacade.variantidFromLengthAndPartid(450, partid, connectionPool);
                break;

            default:
                idArray[0] = PartFacade.variantidFromLengthAndPartid(300, partid, connectionPool);
                idArray[1] = PartFacade.variantidFromLengthAndPartid(480, partid, connectionPool);
                break;
        }

        return idArray;
    }
}