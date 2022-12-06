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
    private int screwid;
    private int roofscrewid;
    private int roof1id;
    private int roof2id;
    private int boltid;
    private int discid;



    private int costprice;
    private int totalprice;
    private ArrayList<Parts> partsArrayList;


    public PartList(int partlistid, int length, int width, int postid, int rafterid, int beam1id, int beam2id, int screwid, int roofscrewid, int roof1id, int roof2id, int boltid, int discid, int costprice, int totalprice, ArrayList<Parts> partsArrayList) {
        this.partlistid = partlistid;
        this.length = length;
        this.width = width;
        this.postid = postid;
        this.rafterid = rafterid;
        this.beam1id = beam1id;
        this.beam2id = beam2id;
        this.screwid = screwid;
        this.roofscrewid = roofscrewid;
        this.roof1id = roof1id;
        this.roof2id = roof2id;
        this.boltid = boltid;
        this.discid = discid;
        this.costprice = costprice;
        this.totalprice = totalprice;
        this.partsArrayList = partsArrayList;
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

    public int getScrewid() {
        return screwid;
    }

    public void setScrewid(int screwid) {
        this.screwid = screwid;
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
