package server.model.requests;

public interface Request {
    void accept();
    void decline();
    void showDetails();
}
