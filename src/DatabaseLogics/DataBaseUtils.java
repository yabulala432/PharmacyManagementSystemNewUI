package DatabaseLogics;

import java.sql.*;

public abstract class DataBaseUtils {

    private static Statement statement;
    private static Connection connection;
    protected ResultSet resultSet;
    protected static PreparedStatement preparedStatement;

    public static Connection getConnection(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/Pharmacy";
            connection = DriverManager.getConnection(url, "root", "");
            return connection;
        }catch (ClassNotFoundException | SQLException exception){
            throw new RuntimeException(exception);
        }
    }

//    public static Statement getStatement() {
//        try {
//            statement = getConnection().createStatement();
//            statement = connection.createStatement();
//        } catch (SQLException ex) {
//            System.err.println(ex.getMessage());
//        }
//        return statement;
//    }

    public abstract ResultSet selectAll() throws SQLException;

}
