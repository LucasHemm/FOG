package dat.backend.model.entities;

import java.util.ArrayList;

public class PartList {


    private int partlistid;
    private int length;
    private int width;
    private int postid;
    private int rafterid;
    private int beam1id;
    private int beam2id;
    private int fittingscrewid;
    private int roofscrewid;
    private int roof1id;
    private int roof2id;
    private int boltid;
    private int discid;
    private int hollowbandid;
    private int rightfittingid;
    private int leftfittingid;
    private int lathid;
    private int boardid;
    private int gablehollowid;
    private int sidehollowid;
    private int screwid;
    private int doorhandleid;
    private int thingeid;
    private int anglefittingid;
    private boolean hasShed;
    private int shedlength;
    private int shedwidth;







    private int costprice;
    private int totalprice;
    private ArrayList<Parts> partsArrayList;


    public int getLathid() {
        return lathid;
    }

    public void setLathid(int lathid) {
        this.lathid = lathid;
    }

    public int getBoardid() {
        return boardid;
    }

    public void setBoardid(int boardid) {
        this.boardid = boardid;
    }

    public int getGablehollowid() {
        return gablehollowid;
    }

    public void setGablehollowid(int gablehollowid) {
        this.gablehollowid = gablehollowid;
    }

    public int getSidehollowid() {
        return sidehollowid;
    }

    public void setSidehollowid(int sidehollowid) {
        this.sidehollowid = sidehollowid;
    }

    public int getScrewid() {
        return screwid;
    }

    public void setScrewid(int screwid) {
        this.screwid = screwid;
    }

    public int getDoorhandleid() {
        return doorhandleid;
    }

    public void setDoorhandleid(int doorhandleid) {
        this.doorhandleid = doorhandleid;
    }

    public int getThingeid() {
        return thingeid;
    }

    public void setThingeid(int thingeid) {
        this.thingeid = thingeid;
    }

    public int getAnglefittingid() {
        return anglefittingid;
    }

    public void setAnglefittingid(int anglefittingid) {
        this.anglefittingid = anglefittingid;
    }

    public boolean isHasShed() {
        return hasShed;
    }

    public void setHasShed(boolean hasShed) {
        this.hasShed = hasShed;
    }

    public int getShedlength() {
        return shedlength;
    }

    public void setShedlength(int shedlength) {
        this.shedlength = shedlength;
    }

    public int getShedwidth() {
        return shedwidth;
    }

    public void setShedwidth(int shedwidth) {
        this.shedwidth = shedwidth;
    }

    public PartList(int partlistid, int length, int width, int postid, int rafterid, int beam1id, int beam2id, int fittingscrewid, int roofscrewid, int roof1id, int roof2id, int boltid, int discid, int hollowbandid, int rightfittingid, int leftfittingid, int lathid, int boardid, int gablehollowid, int sidehollowid, int screwid, int doorhandleid, int thingeid, int anglefittingid, boolean hasShed, int shedlength, int shedwidth, int costprice, int totalprice, ArrayList<Parts> partsArrayList) {
        this.partlistid = partlistid;
        this.length = length;
        this.width = width;
        this.postid = postid;
        this.rafterid = rafterid;
        this.beam1id = beam1id;
        this.beam2id = beam2id;
        this.fittingscrewid = fittingscrewid;
        this.roofscrewid = roofscrewid;
        this.roof1id = roof1id;
        this.roof2id = roof2id;
        this.boltid = boltid;
        this.discid = discid;
        this.hollowbandid = hollowbandid;
        this.rightfittingid = rightfittingid;
        this.leftfittingid = leftfittingid;
        this.lathid = lathid;
        this.boardid = boardid;
        this.gablehollowid = gablehollowid;
        this.sidehollowid = sidehollowid;
        this.screwid = screwid;
        this.doorhandleid = doorhandleid;
        this.thingeid = thingeid;
        this.anglefittingid = anglefittingid;
        this.hasShed = hasShed;
        this.shedlength = shedlength;
        this.shedwidth = shedwidth;
        this.costprice = costprice;
        this.totalprice = totalprice;
        this.partsArrayList = partsArrayList;
    }

    public int getHollowbandid() {
        return hollowbandid;
    }

    public void setHollowbandid(int hollowbandid) {
        this.hollowbandid = hollowbandid;
    }

    public int getRightfittingid() {
        return rightfittingid;
    }

    public void setRightfittingid(int rightfittingid) {
        this.rightfittingid = rightfittingid;
    }

    public int getLeftfittingid() {
        return leftfittingid;
    }

    public void setLeftfittingid(int leftfittingid) {
        this.leftfittingid = leftfittingid;
    }

    public int getRoof1id() {
        return roof1id;
    }

    public int getRoof2id() {
        return roof2id;
    }

    public void setRoof2id(int roof2id) {
        this.roof2id = roof2id;
    }

    public void setRoof1id(int roof1id) {
        this.roof1id = roof1id;
    }

    public int getFittingscrewid() {
        return fittingscrewid;
    }

    public void setFittingscrewid(int fittingscrewid) {
        this.fittingscrewid = fittingscrewid;
    }

    public int getRoofscrewid() {
        return roofscrewid;
    }

    public void setRoofscrewid(int roofscrewid) {
        this.roofscrewid = roofscrewid;
    }

    public int getBoltid() {
        return boltid;
    }

    public void setBoltid(int boltid) {
        this.boltid = boltid;
    }

    public int getDiscid() {
        return discid;
    }

    public void setDiscid(int discid) {
        this.discid = discid;
    }

    public int getPartlistid() {
        return partlistid;
    }

    public void setPartlistid(int partlistid) {
        this.partlistid = partlistid;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getPostid() {
        return postid;
    }

    public void setPostid(int postid) {
        this.postid = postid;
    }

    public int getRafterid() {
        return rafterid;
    }

    public void setRafterid(int rafterid) {
        this.rafterid = rafterid;
    }

    public int getBeam1id() {
        return beam1id;
    }

    public void setBeam1id(int beam1id) {
        this.beam1id = beam1id;
    }

    public int getBeam2id() {
        return beam2id;
    }

    public void setBeam2id(int beam2id) {
        this.beam2id = beam2id;
    }

    public int getCostprice() {
        return costprice;
    }

    public void setCostprice(int costprice) {
        this.costprice = costprice;
    }

    public int getTotalprice() {
        return totalprice;
    }

    public void setTotalprice(int totalprice) {
        this.totalprice = totalprice;
    }

    public ArrayList<Parts> getPartsArrayList() {
        return partsArrayList;
    }

    public void setPartsArrayList(ArrayList<Parts> partsArrayList) {
        this.partsArrayList = partsArrayList;
    }
}
