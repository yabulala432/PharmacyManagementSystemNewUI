package DatabaseLogics;

import java.sql.*;
import java.util.ArrayList;

public class Admin {
    private final Connection connection;
    private ResultSet resultSet;
    private PreparedStatement preparedStatement;
    private final String adminName;
    private final boolean isLegitimate;
    private int empHours;
    private double empWage;

    private enum Queries {
        LOGIN_ADMIN("SELECT * FROM Admin WHERE Username = ? and Password = ?"),
        ADD_TO_REMOVED_ITEMS("INSERT INTO RemovedItems (Remover,RemovedItem,Reason) VALUES (?, ?, ?)"),
        ADD_TO_REMOVED_EMPLOYEE("INSERT INTO RemovedEmployee (Remover,RemovedEmployee,Reason) VALUES (?, ?, ?)"),
        DELETE_ALL_FROM_REMOVED_ITEMS("DELETE FROM RemovedItems"),
        VIEW_ALL_REMOVED_ITEMS("SELECT * FROM RemovedItems"),
        DELETE_ALL_FROM_REMOVED_EMPLOYEE("DELETE FROM RemovedEmployee"),
        UPDATE_ITEMS_AMOUNT("UPDATE Items SET Amount = ? WHERE Name = ?"),
        UPDATE_ITEMS_OVERALL("UPDATE Items SET Type = ?,ExpDate = ?, Price = ?, Amount = ? Where Name = ?"),
        UPDATE_ITEMS_PRICE("UPDATE Items SET Price = ? WHERE Name = ?"),
        VIEW_ALL_REMOVED_EMPLOYEE("SELECT * FROM RemovedEmployee");
        private final String query;

        Queries(String query) {
            this.query = query;
        }

        public String getQuery() {
            return this.query;
        }
    }

    public Admin(String name, String password) throws SQLException {
        this.adminName = name;
        this.connection = DataBaseUtils.getConnection();
        boolean isAdmin = (this.login(adminName, password) == 1);
        if (isAdmin) {
            isLegitimate = true;
        } else {
            isLegitimate = false;
            throw new IllegalStateException();
        }
    }

    private int login(String username, String password) throws SQLException {
        String sql = Queries.LOGIN_ADMIN.getQuery();
        preparedStatement = this.connection.prepareStatement(sql);
        preparedStatement.setString(1, username);
        preparedStatement.setString(2, password);
        resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            if (resultSet.getString(1) != null) {
                return 1;
            }
        }
        return 0;
    }

// #############################################################################

    public int addItem(String name, String type, Date expDate, double price, int amount) throws SQLException {
        if (isLegitimate)
            return ItemsDataBaseUtils
                    .insertIntoItems(name, type, expDate, price, amount);

        return 0;
    }

    public ArrayList<String[]> viewItems() throws SQLException {
        ArrayList<String[]> data = new ArrayList<>();
        if (isLegitimate) {
            this.resultSet = new ItemsDataBaseUtils().selectAll();
            while (resultSet.next()) {
                String name = resultSet.getString("Name");
                String type = resultSet.getString("Type");
                String expDate = resultSet.getString("ExpDate");
                int price = resultSet.getInt("Price");
                int amount = resultSet.getInt("Amount");
                String[] row = {name, type, expDate, String.valueOf(price), String.valueOf(amount)};
                data.add(row);
            }
        }
        return data;
    }

    public boolean updateItemOverAll(String type, Date date, double price, int amount, String previousName) throws SQLException {
        if (isLegitimate) {
//                    UPDATE_ITEMS_OVERALL("UPDATE ITEMS SET Type = ?,ExpDate = ?, Price = ?, Amount = ? Where Name = ?"),
            String sql = Queries.UPDATE_ITEMS_OVERALL.getQuery();
            preparedStatement = this.connection.prepareStatement(sql);
            preparedStatement.setString(1, type);
            preparedStatement.setDate(2, date);
            preparedStatement.setDouble(3, price);
            preparedStatement.setInt(4, amount);
            preparedStatement.setString(5, previousName);
            System.out.println(preparedStatement.toString());
            return preparedStatement.executeUpdate() > 0;
        }
        return false;
    }

//    UPDATE_ITEMS_OVERALL("UPDATE ITEMS SET Name = ?,Type = ?,ExpDate = ?, Price = ?, Amount = ? Where Name = ?"),


    public boolean updateItemAmount(String name, int amount) throws SQLException {
        if (isLegitimate) {
            String sql = Queries.UPDATE_ITEMS_AMOUNT.getQuery();
            return updateAmountAndPrice(name, amount, sql);
        }
        return false;
    }

    public boolean updateItemPrice(String name, int price) throws SQLException {
        if (isLegitimate) {
            String sql = Queries.UPDATE_ITEMS_PRICE.getQuery();
            return updateAmountAndPrice(name, price, sql);
        }
        return false;
    }


    private boolean updateAmountAndPrice(String name, int price, String sql) throws SQLException {
        preparedStatement = this.connection.prepareStatement(sql);
        preparedStatement.setString(1, String.valueOf(price));
        preparedStatement.setString(2, name);
        int y = preparedStatement.executeUpdate();
        if (y > 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean removeItem(String name, String reason) throws SQLException {
        int x = itemRemove(name);
//        System.out.println("x: " + x);
//        System.out.println("isLegitimate" + isLegitimate);
        if (x == 1 && isLegitimate) {
            String sql = Queries.ADD_TO_REMOVED_ITEMS.getQuery();
            preparedStatement = this.connection.prepareStatement(sql);
            preparedStatement.setString(1, this.adminName);
            preparedStatement.setString(2, name);
            preparedStatement.setString(3, reason);
            preparedStatement.execute();
            int y = preparedStatement.executeUpdate();
//            System.out.println("Y: " + y);
            if (y == 1)
                return true;
            else return false;
        } else {
            return false;
        }
    }

    private int itemRemove(String name) {
        if (isLegitimate) {
            return ItemsDataBaseUtils.deleteItem(name);
        }
        return 0;
    }

// #############################################################################

    public int addEmployee(String name, String username,
                           String password, int empHours) throws SQLIntegrityConstraintViolationException {
        this.setEmpHours(empHours);
        this.setEmpWage(empHours);
        if (isLegitimate)
            return EmployeeDataBaseUtils
                    .insertIntoEmployee(name, username, password, this.getEmpHours(), this.getEmpWage());
        return 0;

    }

    public ArrayList<String[]> viewEmployee() throws SQLException {
//        if (isLegitimate) {
//            this.resultSet = new EmployeeDataBaseUtils().selectAll();
//        }
        ArrayList<String[]> data = new ArrayList<>();
        if (isLegitimate) {
            this.resultSet = new EmployeeDataBaseUtils().selectAll();
            while (resultSet.next()) {
                String name = resultSet.getString("Name");
                String type = resultSet.getString("UserName");
                String expDate = resultSet.getString("Password");
                int price = resultSet.getInt("Hours");
                int amount = resultSet.getInt("Wage");
                String[] row = {name, type, expDate, String.valueOf(price), String.valueOf(amount)};
                data.add(row);
            }
        }
        return data;
    }

    public boolean updateEmpHoursAndName(String username, int hours, String name) {
        return EmployeeDataBaseUtils.updateHoursAndName(username, hours, name);
    }

    public boolean removeEmployee(String username, String reason) throws SQLException {
        int x = employeeRemove(username);
        if (x > 0) {
            String sql = Queries.ADD_TO_REMOVED_EMPLOYEE.getQuery();
            preparedStatement = this.connection.prepareStatement(sql);
            preparedStatement.setString(1, this.adminName);
            preparedStatement.setString(2, username);
            preparedStatement.setString(3, reason);
            int y = preparedStatement.executeUpdate();
            return y == 1;
        } else {
            return false;
        }
    }

    private int employeeRemove(String username) {
        if (isLegitimate)
            return EmployeeDataBaseUtils
                    .deleteEmployee(username);
        return 0;
    }
//#############################################################################
    public ArrayList<String[]> viewTotalSales() throws SQLException {
        ArrayList<String[]> data = new ArrayList<>();
        if (isLegitimate) {
            this.resultSet = new TotalSalesDataBaseUtils().selectAll();
            while (resultSet.next()) {
                String salesId = resultSet.getString("SalesId");
                String date = resultSet.getString("Date");
                String name = resultSet.getString("Name");
                int quantity = resultSet.getInt("Quantity");
                int price = resultSet.getInt("Price");
                String[] row = {salesId, date, name, String.valueOf(quantity), String.valueOf(price)};
                data.add(row);
            }
        }
        return data;
    }

//    public int deleteTotalSales(String name) {
//        if (isLegitimate)
//            return TotalSalesDataBaseUtils.deleteSales(name);
//        return 0;
//    }

    public int deleteAllTotalSales() {
        if (isLegitimate)
            return TotalSalesDataBaseUtils.deleteAllSales();
        return 0;
    }

    //    ###############################################################################
    public int deleteAllRemovedItems() throws SQLException {
        String sql = Queries.DELETE_ALL_FROM_REMOVED_ITEMS.getQuery();
        preparedStatement = this.connection.prepareStatement(sql);
        return preparedStatement.executeUpdate();
    }

    public ArrayList<String[]> viewAllRemovedItems() throws SQLException {
        String sql = Queries.VIEW_ALL_REMOVED_ITEMS.getQuery();
        preparedStatement = this.connection.prepareStatement(sql);
        resultSet = preparedStatement.executeQuery();
        ArrayList<String[]> data = new ArrayList<>();
        if (isLegitimate) {
            while (resultSet.next()) {
                String remover = resultSet.getString("Remover");
                String removedEmployee = resultSet.getString("RemovedItem");
                String reason = resultSet.getString("Reason");
                String[] row = {remover, removedEmployee, reason};
                data.add(row);
            }
        }
        return data;
    }

    //    *************************************************************************************
    public int deleteAllRemovedEmployee() throws SQLException {
        String sql = Queries.DELETE_ALL_FROM_REMOVED_EMPLOYEE.getQuery();
        preparedStatement = this.connection.prepareStatement(sql);
        return preparedStatement.executeUpdate();
    }

    public ArrayList<String[]> viewAllRemovedEmployee() throws SQLException {
        String sql = Queries.VIEW_ALL_REMOVED_EMPLOYEE.getQuery();
        preparedStatement = this.connection.prepareStatement(sql);
        resultSet = preparedStatement.executeQuery();
        ArrayList<String[]> data = new ArrayList<>();
        if (isLegitimate) {
            while (resultSet.next()) {
                String remover = resultSet.getString("Remover");
                String removedEmployee = resultSet.getString("RemovedEmployee");
                String reason = resultSet.getString("Reason");
                String[] row = {remover, removedEmployee, reason};
                data.add(row);
            }
        }
        return data;
    }

    public double getEmpWage() {
        return empWage;
    }

    public void setEmpWage(int x) {
        this.empWage = x * 20;
    }

    public int getEmpHours() {
        return empHours;
    }

    public void setEmpHours(int empHours) {
        this.empHours = empHours;
    }
}
