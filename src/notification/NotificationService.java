package notification;

/**
 * NotificationService simulates Email and SMS notifications
 * for high energy usage and appliance malfunction alerts.
 * i used the obderver pattern
 *
 */
public class NotificationService {

    public void sendEmail(String email, String subject, String message) {
        System.out.println("\n--- EMAIL NOTIFICATION ---");
        System.out.println("To: " + email);
        System.out.println("Subject: " + subject);
        System.out.println("Message: " + message);
        System.out.println("Status: Email sent successfully (simulated)");
    }

    public void sendSMS(String phoneNumber, String message) {
        System.out.println("\n--- SMS NOTIFICATION ---");
        System.out.println("To: " + phoneNumber);
        System.out.println("Message: " + message);
        System.out.println("Status: SMS sent successfully (simulated)");
    }
}