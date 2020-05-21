package model.product;

public class Comment {
    private String username;
    private int productId;
    private byte state;
    private String title;
    private String text;
    private boolean hasBought;

    public Comment(String username, int productId, String title, String text, boolean hasBought) {
        this.username = username;
        this.productId = productId;
        this.state = 0;
        this.title = title;
        this.text = text;
        this.hasBought = hasBought;
    }

    public byte getState() {
        return state;
    }

    public void setState(byte state) {
        this.state = state;
    }

    public String getUsername() {
        return username;
    }

    public int getProductId() {
        return productId;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }

    public boolean isHasBought() {
        return hasBought;
    }
}
