package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.Food;

public class FoodDao {
	private Connection connection;
	private final String
	GET_FOOD_QUERY= "SELECT * FROM food";
	private final String
	GET_FOOD_BY_ID_QUERY =
	"SELECT * from food WHERE id = ? ";
	private final String
	CREATE_NEW_FOOD_QUERY = 
	"INSERT into FOOD(id, name) values(?,?)";
	private final String 
	DELETE_FOOD_BY_ID_QUERY=
	"DELETE from FOOD WHERE id = ?";
	
	public FoodDao() {
		connection = DBConnection.getConnection();
	}
	
	public List<Food> getFood() throws
	SQLException{
	ResultSet rs = connection
			.prepareStatement(GET_FOOD_QUERY)
			.executeQuery();
	List<Food> foods = new ArrayList<Food>();
 	
	while(rs.next()) {
		foods.add(populateFood
				(rs.getInt(1), rs.getString(2)));
	
	}
	return foods;
		
	}
	
	public Food getFoodId( int id) throws
	SQLException {
		PreparedStatement ps = 
				connection.prepareStatement(
						GET_FOOD_BY_ID_QUERY);
	    ps.setInt(1, id);
	    ResultSet rs = ps.executeQuery();
	    rs.next();
	    return populateFood
	    		(rs.getInt(1), rs.getString(2));
	}
	
	public void createNewFood(int id,
			String foodName) throws SQLException {
		PreparedStatement ps = connection.
				prepareStatement(
						CREATE_NEW_FOOD_QUERY);
		ps.setInt(1, id);
		ps.setString(2, foodName);
		ps.executeUpdate();
		
	}
	
	public void deleteFoodById(int id)
			throws SQLException {
		PreparedStatement ps = 
				connection.prepareStatement
				(DELETE_FOOD_BY_ID_QUERY);
		ps.setInt(1, id);
		ps.executeUpdate();
	}
private Food populateFood( int id, String name) {
	return new Food(id, name);
}
}
