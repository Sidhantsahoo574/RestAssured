package genericUtilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.cj.jdbc.Driver;

public class DatabaseUtilities {

	Driver driverRef;
	static Connection conn;
	ResultSet result;
	
	/**
	 * this method is used to connect with mysql db
	 * @author Sidhanta
	 * @throws SQLException
	 */
	public void connectToDB() throws SQLException
	{
		driverRef =new Driver();
		DriverManager.registerDriver(driverRef);
		conn=DriverManager.getConnection(IConstant.dburl,IConstant.dbUserName,IConstant.dbPassword);
	}
	/**
	 * this method will close the database connection
	 * @author Sidhanta
	 * @throws SQLException
	 */
	
	public void closeDB() throws SQLException
	{
		conn.close();
	}
	
	/**
	 * this method will execute the query and return the value only if the validation is succcessful
	 * @author Sidhanta
	 * @param query
	 * @param columnIndex
	 * @param expData
	 * @return
	 * @throws SQLException
	 */
	
	public String readDataFromDBAndValidate(String query,int columnIndex,String expData) throws SQLException
	{
		boolean flag=false;
		result=conn.createStatement().executeQuery(query);
		while(result.next())
		{
			if(result.getString(columnIndex).equalsIgnoreCase(expData))
			{
				flag=true;
				break;
			}
		}
		if(flag)
		{
			System.out.println("verified");
			return expData;
		}
		else {
			System.out.println("data not verified");
			return "";
		}
	}
}
