package DatabaseLogics;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeDataBaseUtils extends DataBaseUtils {

    private enum Queries {
        SELECT_EMPLOYEE("SELECT * FROM Employee"),
        CHECK_EMPLOYEE("SELECT * FROM Employee WHERE UserName = ? and Password = ?"),
        INSERT_INTO_EMPLOYEE("INSERT INTO Employee (Name, Username, Password, Hours, Wage) VALUES (?, ?, ?, ?, ?)"),
        UPDATE_EMPLOYEE_Hours("UPDATE Employee SET Hours = ?, Name = ? WHERE Username = ?"),
        UPDATE_EMPLOYEE_PASSWORD("UPDATE Employee SET Password = ? WHERE Username = ?"),
        UPDATE_EMPLOYEE_USERNAME("UPDATE Employee SET Username = ? WHERE Username = ?"),
        DELETE_AN_EMPLOYEE("DELETE FROM Employee WHERE Username = ?");

        private final String query;

        private Queries(String query) {
            this.query = query;
        }

        public String getQuery() {
            return this.query;
        }
    }

    public EmployeeDataBaseUtils() {
    }

    public static int updateUsername(String oldUsername, String newUsername) {
        try {
            preparedStatement = getConnection().prepareStatement(Queries.UPDATE_EMPLOYEE_USERNAME.getQuery());
            // it has to be reversed
            // (the newUsername and the oldUsername)
            preparedStatement.setString(1, newUsername);
            preparedStatement.setString(2, oldUsername);
            int rowsAffected = preparedStatement.executeUpdate();
            preparedStatement.close();
            return rowsAffected;
        } catch (SQLException e) {
            return 0;
        }
    }

    public static int isFound(String username, String password) throws SQLException {
        String sql = Queries.CHECK_EMPLOYEE.getQuery();
        preparedStatement = getConnection().prepareStatement(sql);
        preparedStatement.setString(1, username);
        preparedStatement.setString(2, password);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next())
            return 1;
        return 0;
    }

    public static int updatePassword(String username, String password) {
        try {
            preparedStatement = getConnection()
                    .prepareStatement(Queries.UPDATE_EMPLOYEE_PASSWORD.getQuery());
            preparedStatement.setString(1, password);
            preparedStatement.setString(2, username);

            int rowsAffected = preparedStatement.executeUpdate();
            preparedStatement.close();
            return rowsAffected > 0 ? 1 : 0;
        } catch (SQLException e) {
            return 0;
        }
    }

    public static boolean updateHoursAndName(String username, int hours, String name) {
        try {
            preparedStatement = getConnection().prepareStatement(
                    Queries.UPDATE_EMPLOYEE_Hours.getQuery()
            );
            preparedStatement.setInt(1, hours);
            preparedStatement.setString(2, name);
            preparedStatement.setString(3, username);
            int rowsAffected = preparedStatement.executeUpdate();
            preparedStatement.close();
            return rowsAffected > 0;
        } catch (SQLException e) {
            return false;
        }
    }

    public static int insertIntoEmployee
            (String name, String username,
             String password, int hours, double wage
            ) {
        try {
            preparedStatement = getConnection()
                    .prepareStatement(Queries.INSERT_INTO_EMPLOYEE.getQuery());
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, username);
            preparedStatement.setString(3, password);
            preparedStatement.setInt(4, hours);
            preparedStatement.setDouble(5, wage);
            int status = preparedStatement.executeUpdate();
            preparedStatement.close();
            return status;
        } catch (SQLException e) {
            return 0;
        }
    }

    public static int deleteEmployee(String username) {
        try {
            String sql = Queries.DELETE_AN_EMPLOYEE.getQuery();
            preparedStatement = getConnection().prepareStatement(sql);
            preparedStatement.setString(1, username);
            int rowsAffected = preparedStatement.executeUpdate();
            preparedStatement.close();
            return rowsAffected;
        } catch (SQLException e) {
            return 0;
        }
    }

    @Override
    public ResultSet selectAll() throws SQLException {
        return getConnection()
                .createStatement()
                .executeQuery(Queries.SELECT_EMPLOYEE.getQuery());
    }
}
