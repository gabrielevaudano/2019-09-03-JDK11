package it.polito.tdp.food.db;

public class TestDao {

	public static void main(String[] args) {
		FoodDao dao = new FoodDao();
		

		System.out.println("Printing all the condiments...");
		System.out.println(dao.getAllArco());
	}

}
