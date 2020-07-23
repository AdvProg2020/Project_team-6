package server.model.product;

public class Score {
    private String username;
    //Maximum is 10 and minimum is 0
    private float score;
    private int productId;

    public Score(String username, byte score, int productId) {
        this.username = username;
        this.score = score;
        this.productId = productId;
    }

    public String getUsername() {
        return username;
    }

    /**
     * What is the maximum you ask? I don't know either
     * @return score :)
     */
    public float getScore() {
        return score;
    }

    public int getProductId() {
        return productId;
    }
}
