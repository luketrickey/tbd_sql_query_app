package com.ldt;

import java.sql.*;
import java.util.logging.Logger;
import java.util.logging.Level;

public class App {
	private static final Logger logger = 
                Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

	public static void main(String[] args) throws Exception {
		logger.log(Level.INFO, "Starting app...");
		Connection db = null;
		Statement statement = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			String user = "";
			String url = "";
			String pwd = "";

			logger.log(Level.INFO, "Establishing DB connection...");
			db = DriverManager.getConnection(url, user, pwd);

			String query = "Select * from employee";
			statement = db.createStatement();
			ResultSet rs = statement.executeQuery(query);

			while(rs.next()) {
				String employeeId = "Employee ID: " + rs.getString("employee_id");
				String employeeName = "Employee Name: " + rs.getString("employee_name");
				logger.log(Level.INFO, employeeId);
				logger.log(Level.INFO, employeeName);
			}

		} catch (Exception ex) {
			logger.log(Level.WARNING, ex.getMessage(), ex);
		} finally {
			if(db != null) {
				db.close();
			}

			if(statement != null) {
				statement.close();
			}
		}
	}
}
