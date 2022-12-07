package dat.backend.model.entities;

import java.sql.Timestamp;

public class Order {

    private int orderid;
    private int userid;
    private Timestamp timestamp;
    private PartList partlist;
    private String status;
    private double proposedPrice;

    public Order(int orderid, int userid, Timestamp timestamp, PartList partlist, String status) {
        this.orderid = orderid;
        this.userid = userid;
        this.timestamp = timestamp;
        this.partlist = partlist;
        this.status = status;
    }

    public double getProposedPrice() {
        return proposedPrice;
    }

    public void setProposedPrice(double proposedPrice) {
        this.proposedPrice = proposedPrice;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getOrderid() {
        return orderid;
    }

    public void setOrderid(int orderid) {
        this.orderid = orderid;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public PartList getPartlist() {
        return partlist;
    }

    public void setPartlist(PartList partlist) {
        this.partlist = partlist;
    }
}
