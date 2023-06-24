package DatabaseLogics;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TotalSalesDataBaseUtils extends DataBaseUtils {
    private enum Queries {
        SELECT_TOTAL_SALES("SELECT * FROM TotalSales"),
        INSERT_INTO_TOTAL_SALES("INSERT INTO TotalSales (Date, Name, Quantity, Price) VALUES (?, ?, ?, ?)"),
        UPDATE_TOTAL_SALES_NAME("UPDATE TotalSales SET Name = ? WHERE Name = ?"),
        UPDATE_TOTAL_SALES_QUANTITY("UPDATE TotalSales SET Quantity = ? WHERE Name = ?"),
        UPDATE_TOTAL_SALES_PRICE("UPDATE TotalSales SET Price = ? WHERE Name = ?"),
        DELETE_FROM_TOTAL_SALES("DELETE FROM TotalSales WHERE Name = ?"),
        DELETE_ALL_FROM_TOTAL_SALES("DELETE FROM TotalSales");

        private final String query;

        private Queries(String query) {
            this.query = query;
        }

        public String getQuery() {
            return this.query;
        }
    }

    public static int insertTotalSales(String name, int quantity, double price) {
        try {
            String sql = Queries.INSERT_INTO_TOTAL_SALES.getQuery();
            preparedStatement = getConnection().prepareStatement(sql);
            preparedStatement.setString(1, DateManipulation.today().toString());
            preparedStatement.setString(2, name);
            preparedStatement.setInt(3, quantity);
            preparedStatement.setDouble(4, price);
            int rowsAffected = preparedStatement.executeUpdate();
            preparedStatement.close();
            return rowsAffected;
        } catch (SQLException e) {
            return 0;
        }
    }

    public static int deleteSales(String name) {
        try {
            String sql = Queries.DELETE_FROM_TOTAL_SALES.getQuery();
            preparedStatement = getConnection().prepareStatement(sql);
            preparedStatement.setString(1, name);
            int rowsAffected = preparedStatement.executeUpdate();
            preparedStatement.close();
            return rowsAffected;
        } catch (SQLException e) {
            return 0;
        }
    }

    public static int deleteAllSales() {
        try {
            String sql = Queries.DELETE_ALL_FROM_TOTAL_SALES.getQuery();
            preparedStatement = getConnection().prepareStatement(sql);
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
                .executeQuery(Queries.SELECT_TOTAL_SALES.getQuery());
    }

}
