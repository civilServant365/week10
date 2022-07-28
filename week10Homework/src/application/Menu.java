package application;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import dao.FoodDao;
import entity.Food;

public class Menu {
	
	private FoodDao foodDao = new FoodDao();
	private Scanner scanner = new Scanner(System.in);
	private List<String> options = 
			Arrays.asList("Display Foods", 
			"Display a Food",
			"Create or Update a Food", "Delete a Food" );
 
			
public void start() {
	 String selection = "";
	 
	 do {
		 printMenu();
		 selection = scanner.nextLine();
		 
		 try {
		 
		 if (selection.equals("1")) {
			 displayFoods();
		 }
		 else if (selection.equals("2")) {
			 displayFood();
		 }
		 else if (selection.equals("3")){
			 createFood();
			 
		 }
		 else if (selection.equals("4")){
			 deleteFood();
			 
		 }}
		 
		 catch(SQLException e) {
			 e.printStackTrace();
		 }
		 
		 System.out.println("Press enter to continue...");
		 scanner.nextLine();
	 } while (!selection.equals("-1"));
 }

private void printMenu() {
	System.out.println("Select an Option: \n----------");
	for (int i = 0; i <options.size();i++) {
		System.out.println(i+1+") "+ options.get(i));
	}
	
}
private void displayFoods() throws SQLException {
	List<Food> foods= foodDao.getFood();
	for ( Food food:foods) {
		System.out.println(food.getFoodId()+ ": " 
	+ food.getFoodName());
	}
}
private void displayFood() throws SQLException {
	System.out.println("Enter food id: ");
	int id = Integer.parseInt(scanner.nextLine());
	Food food = foodDao.getFoodId(id);
	System.out.println(food.getFoodId() + ": "
	+ food.getFoodName());
	
}

private void createFood() throws SQLException{
	System.out.println("Enter new food ID: ");
	int foodId = Integer.parseInt(scanner.nextLine());
	foodDao.deleteFoodById(foodId);
	System.out.print("Enter new food name: ");
	String foodName = scanner.nextLine();
	foodDao.createNewFood(foodId, foodName);
}

private void deleteFood() throws SQLException{
	System.out.println("Enter ID to delete food: ");
	int foodId = Integer.parseInt(scanner.nextLine());
	foodDao.deleteFoodById(foodId);
}
}
