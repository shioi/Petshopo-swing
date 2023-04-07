package petshop;
import java.sql.*;
import java.util.ArrayList;


public class database {
	//change password here
	String pass = "Password@123";
	Boolean registration(String username, String email,  char [] password) {
		Connection connection = null;
		try {
            // below two lines are used for connectivity.
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/swing",
                "root", pass);
 
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
                "root", pass);
            
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
                "root", pass);
            
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
                "root", pass);
 
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
                "root", pass);
 
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
	
	ArrayList<Product> getCartProducts(String username) {
		Connection connection = null;
		ArrayList<Product> prod = new ArrayList<>(); 
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/swing",
                "root", pass);
            
            Statement statement;
            statement = connection.createStatement();
            ResultSet resultSet;
            String str = String.format("select products.productid, name, price, cart.quantity from products inner join cart on products.productid=cart.prodid where cart.username = '%s'",username);
            resultSet = statement.executeQuery(str);
            //product object
            while(resultSet.next()) {
            	int id = resultSet.getInt("productid");
            	String name = resultSet.getString("name");
            	int price = resultSet.getInt("price");
            	int total = resultSet.getInt("quantity");
            	prod.add(new Product(id,name,total,price));
            }
		} 
		catch (Exception exception) {
			System.err.println(exception.getMessage());
		}

	return prod;	
		
	}
	Boolean deleteProdFromCart(String username, int id) {
		Connection connection = null;
		try {
            // below two lines are used for connectivity.
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/swing",
                "root", pass);
 
            Statement statement;
            statement = connection.createStatement();
            String str = String.format("delete from cart where username='%s' and prodid=%d",username,id);
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
	
	 Boolean buyItems(String username) {
		 Connection connection = null;
		try {
            // below two lines are used for connectivity.
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/swing",
                "root", pass);
 
            
            
			Statement statement;
            statement = connection.createStatement();
            ResultSet resultSet;
            String str = String.format("select prodid from cart where username = '%s'",username);
            resultSet = statement.executeQuery(str);
            //product object
            while(resultSet.next()) {
            	Statement stat = connection.createStatement();
            	int id = resultSet.getInt("prodid");
                String str2 = String.format("UPDATE products SET total = total - (select sum(quantity) from cart where username = '%s' and prodid = %d) where productid=%d",username,id,id);
				stat.executeUpdate(str2);
				stat.close();
            }
            String str3 = String.format("DELETE FROM cart WHERE username = '%s'",username);
            statement.executeUpdate(str3);
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
	