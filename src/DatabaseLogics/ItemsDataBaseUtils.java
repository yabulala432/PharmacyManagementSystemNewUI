package DatabaseLogics;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ItemsDataBaseUtils extends DataBaseUtils {

    private enum Queries {
        SELECT_ITEMS("SELECT * FROM Items"),
        INSERT_INTO_ITEMS("INSERT INTO Items (Name, Type, ExpDate, Price, Amount) VALUES (?, ?, ?, ?, ?)"),
        UPDATE_ITEMS_PRICE("UPDATE Items SET Price = ? WHERE Name = ?"),
        UPDATE_ITEMS_NAME("UPDATE Items SET Name = ? WHERE Name = ?"),
        UPDATE_ITEMS_AMOUNT("UPDATE Items SET Amount = ? WHERE Name = ?"),
        DELETE_FROM_ITEMS("DELETE FROM Items WHERE Name = ?");
        private final String query;

        Queries(String query) {
            this.query = query;
        }

        public String getQuery() {
            return this.query;
        }

    }

    public static int insertIntoItems(String name, String type,
                                      Date expDate, double price, int amount) throws SQLException {
        String sql = "INSERT INTO Items (Name, Type, ExpDate, Price, Amount) " +
                "VALUES ('" + name + "' , '" + type + "', STR_TO_DATE('" +
                expDate + "', '%Y-%m-%d'), " + price + "," + amount + ")";

        if (DateManipulation.isSafeYear(expDate))
            return getConnection().createStatement().executeUpdate(sql);
        else throw new IllegalStateException();
    }

    public static int updatePrice(String name, double price) {
        try {
            String sql = Queries.UPDATE_ITEMS_PRICE.getQuery();
            preparedStatement = getConnection().prepareStatement(sql);
            preparedStatement.setDouble(1, price);
            preparedStatement.setString(2, name);

            int rowsAffected = preparedStatement.executeUpdate();
            preparedStatement.close();
////            System.out.println(rowsAffected + " rows updated.");
            return rowsAffected > 0 ? 1 : 0;
        } catch (SQLException e) {
////            System.out.println("Error updating price: " + e.getMessage());
            return 0;
        }
    }

    public static int updateAmount(String name, int amount) {
        try {
            String sql = Queries.UPDATE_ITEMS_AMOUNT.getQuery();
            preparedStatement = getConnection().prepareStatement(sql);
            preparedStatement.setInt(1, amount);
            preparedStatement.setString(2, name);
            int rowsAffected = preparedStatement.executeUpdate();
            preparedStatement.close();
//            System.out.println(rowsAffected + " rows updated.");
            return rowsAffected > 0 ? 1 : 0;
        } catch (SQLException e) {
//            System.out.println("Error updating amount: " + e.getMessage());
            return 0;
        }
    }

    public static int updateName(String oldName, String newName) {
        try {
            String sql = Queries.UPDATE_ITEMS_NAME.getQuery();
            preparedStatement = getConnection().prepareStatement(sql);
            preparedStatement.setString(1, newName);
            preparedStatement.setString(2, oldName);

            int rowsAffected = preparedStatement.executeUpdate();
            preparedStatement.close();
//            System.out.println(rowsAffected + " rows updated.");
            return rowsAffected > 0 ? 1 : 0;
        } catch (SQLException e) {
            return 0;
        }

    }

    public static int deleteItem(String name) {
        try {
            String sql = Queries.DELETE_FROM_ITEMS.getQuery();
            preparedStatement = getConnection().prepareStatement(sql);
            preparedStatement.setString(1, name);
////            System.out.println(preparedStatement.toString());
            int rowsAffected = preparedStatement.executeUpdate();
//            System.out.println(rowsAffected + ": rows Affected");
            preparedStatement.close();
            return rowsAffected > 0 ? 1 : 0;
        } catch (SQLException e) {
//            System.out.println("Error deleting from Items: " + e.getMessage());
            return 0;
        }
    }

    @Override
    public ResultSet selectAll() throws SQLException {
        return getConnection()
                .createStatement()
                .executeQuery(Queries.SELECT_ITEMS.getQuery());
    }
}
