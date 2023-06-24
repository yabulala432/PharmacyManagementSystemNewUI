package DatabaseLogics;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Employee {
    private String username, password;

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    private enum Query {
        GET_PREVIOUS_ITEM_AMOUNT("SELECT Amount FROM Items WHERE Name = ?"),
        FIND_ITEM("SELECT * FROM Items WHERE Name like ?"),
        SELL_MEDICINE("UPDATE Items SET Amount = ? where Name = ?"),
        DELETE_ITEM_WITH_AMOUNT_0("DELETE FROM Items WHERE Amount = 0");
        private final String query;

        Query(String query) {
            this.query = query;
        }

        public String getQuery() {
            return this.query;
        }
    }

    public Employee(String username, String password) throws SQLException {
        this.username = username;
        this.password = password;
        if (!isEmployee(username, password)) throw new IllegalStateException();
    }

    private boolean isEmployee(String username, String password) throws SQLException {
        return EmployeeDataBaseUtils.isFound(username, password) == 1;
    }

    public int updateEmployeeUserName(String oldUsername, String newUsername) {
        return EmployeeDataBaseUtils.updateUsername(oldUsername, newUsername);
    }

    public int updateEmployeePassword(String username, String password) {
        return EmployeeDataBaseUtils.updatePassword(username, password);
    }

    public boolean sellMedicine(String name, int quantity) throws SQLException {
        if (quantity > 0) {
            PreparedStatement preparedStatement =
                    DataBaseUtils.getConnection().prepareStatement(
                            Query.GET_PREVIOUS_ITEM_AMOUNT.getQuery());
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int actualAmount = resultSet.getInt(1) - quantity;
                boolean sold = false;
                if (actualAmount >= 0) {
                    sold = ItemsDataBaseUtils.updateAmount(name, actualAmount) == 1;
                }
                if (actualAmount == 0) {
                    preparedStatement = DataBaseUtils.getConnection().prepareStatement(
                            Query.DELETE_ITEM_WITH_AMOUNT_0.getQuery());
                    System.out.println("deleted Amount  " + preparedStatement.executeUpdate());
                }
                return sold;
            }
        }
        return false;
    }

    public ArrayList<String[]> findMedicine(String itemName) throws SQLException {
        ArrayList<String[]> list = new ArrayList<>();
        PreparedStatement preparedStatement = DataBaseUtils.getConnection().prepareStatement(
                Query.FIND_ITEM.getQuery());
        preparedStatement.setString(1, "%" + itemName + "%");
//        System.out.println(preparedStatement.toString());
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            String name = resultSet.getString("Name");
            String type = resultSet.getString("Type");
            String expDate = resultSet.getString("ExpDate");
            String price = resultSet.getString("Price");
            String amount = resultSet.getString("Amount");
            String[] row = {name, type, expDate, price, amount};
            list.add(row);
        }
        return list;
    }

}
