package model.product;

public class Score {
    private String username;
    private byte score;
    private int productId;

    public Score(String username, byte score, int productId) {
        this.username = username;
        this.score = score;
        this.productId = productId;
    }

    public String getUsername() {
        return username;
    }

    public byte getScore() {
        return score;
    }

    public int getProductId() {
        return productId;
    }
}
