package rs.ac.metropolitan.it355.pz.model;

public class UserItem {

    private int userID;
    private int itemID;

    public UserItem() {
    }

    public UserItem(int userID, int itemID) {
        this.userID = userID;
        this.itemID = itemID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getItemID() {
        return itemID;
    }

    public void setItemID(int itemID) {
        this.itemID = itemID;
    }
}
