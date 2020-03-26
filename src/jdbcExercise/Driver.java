package jdbcExercise;
import java.sql.*;

public class Driver {

	public static void main(String[] args) throws SQLException {
		
		Connection conn = null;
		Statement statement = null;
		ResultSet resultSet = null;
		
		try {
			// Get a connection to the database
			String databaseString = "jdbc:mysql://localhost:3306/nba";
			
			// include timezone parameter to avoid exception about EDT timezone being unrecognized
			String timeZoneString = "?serverTimezone=EST"; 
			
			databaseString+=timeZoneString;
			
			conn = DriverManager.getConnection(databaseString, "ceo", "ceoPassword");
			
			// Create a statement
			statement = conn.createStatement();
			
			// Execute SQL query
			resultSet = statement.executeQuery("select * from teams");
			
			// Process the result set
			ResultSetMetaData metadata = resultSet.getMetaData();
			int columnCount = metadata.getColumnCount();
			while (resultSet.next()) {
				String row = "";
				for (int i = 1; i <= columnCount; i++) {
					row += resultSet.getString(i);
					row += " | ";
				}
				System.out.println(row);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			if (resultSet != null) {
				resultSet.close();
			}
			
			if (statement != null) {
				statement.close();
			}
			
			if (conn != null) {
				conn.close();
			}
		}

	}

}
