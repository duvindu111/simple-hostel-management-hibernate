import lk.ijse.simple_hostel_management_hibernate.config.SessionFactoryConfig;

public class Launcher {
    public static void main(String[] args) {
        SessionFactoryConfig.getInstance().getSession();
    }
}
