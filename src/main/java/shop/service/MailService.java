package shop.service;

public interface MailService {
    void send(String username, String email, String password, String message, String realusername);

    void sendFromAdmin(String email, String message);
}
