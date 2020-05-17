package model.requests;

public interface Request {
    int requestId = 0;
    void accept();
    void decline();
    void addToRequestId();
}
