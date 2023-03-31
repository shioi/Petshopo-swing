package petshop;
import java.sql.*;
import java.util.ArrayList;


public class database {
	Boolean registration(String username, String email,  char [] password) {
		Connection connection = null;
		try {
            // below two lines are used for connectivity.
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/swing",
                "root", "Password@123");
 
            Statement statement;
            statement = connection.createStatement();
            String pass = new String(password);
            String str = String.format("insert into Login Values('%s','%s','%s')", username, email, pass);
            statement.executeUpdate(str);
            statement.close();
            connection.close();
            return true;
        }
        catch (Exception exception) {
            System.out.println(exception);
            return false;
        }
	}
	
	Boolean login(String username, char [] password) {
		Connection connection = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/swing",
                "root", "Password@123");
            
            Statement statement;
            statement = connection.createStatement();
            ResultSet resultSet;
            String pass = new String(password);
            String str = String.format("select * from Login where username = '%s' and password = '%s'", username, pass);
            resultSet = statement.executeQuery(str);
            int code;
            if(resultSet.next() == false) {
				resultSet.close();
            	return false;
            }
            resultSet.close();
            return true;
		} 
		catch (Exception exception) {
			return false;
		}
	}
	
	ArrayList<Product> getProducts(String types) {
		Connection connection = null;
		ArrayList<Product> prod = new ArrayList<>(); 
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/swing",
                "root", "Password@123");
            
            Statement statement;
            statement = connection.createStatement();
            ResultSet resultSet;
            String str = String.format("select * from products where type = '%s'",types); 
            resultSet = statement.executeQuery(str);
            //product object
            while(resultSet.next()) {
            	int id = resultSet.getInt("productid");
            	String name = resultSet.getString("name");
            	String type = resultSet.getString("type");
            	int price = resultSet.getInt("price");
            	int total = resultSet.getInt("total");
            	String url = resultSet.getString("url");
            	prod.add(new Product(id,name,type,total,price,url));

            }
		} 
		catch (Exception exception) {
			System.err.println(exception.getMessage());
		}

	return prod;	
	}
	
	//adding items to cart
	Boolean addToCart(String user, int productid, int quantity) {
		Connection connection = null;
		try {
            // below two lines are used for connectivity.
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/swing",
                "root", "Password@123");
 
            Statement statement;
            statement = connection.createStatement();
            String str = String.format("insert into cart Values('%s',%d,%d)", user, productid,quantity);
            statement.executeUpdate(str);
            statement.close();
            connection.close();
            return true;
        }
        catch (Exception exception) {
            System.out.println(exception);
            return false;
        }
	}
	
	//adding items to database
	Boolean addToProducts(Product prod) {
	Connection connection = null;
		try {
            // below two lines are used for connectivity.
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/swing",
                "root", "Password@123");
 
            Statement statement;
            statement = connection.createStatement();
            String str = String.format("insert into products(name,type,price,total,url) values('%s','%s',%d,%d,'%s')", prod.getName(), prod.getType(),prod.getPrice(),prod.getTotal(),prod.getUrl());
            System.out.println(str);
            statement.executeUpdate(str);
            statement.close();
            connection.close();
            return true;
        }
        catch (Exception exception) {
            System.out.println(exception);
            return false;
        }
		
	}

	}
	