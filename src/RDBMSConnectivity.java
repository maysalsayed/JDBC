import java.sql.SQLException;
import java.sql.DriverManager;
import java.sql.Connection;

public class RDBMSConnectivity {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/test";
    private static final String User = "root";
    private static final String pass = "1357924680mMmM";

    public static Connection connect() throws SQLException {
        return DriverManager.getConnection(DB_URL,User,pass);
    }
}
