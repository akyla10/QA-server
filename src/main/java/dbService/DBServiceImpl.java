package dbService;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ListIterator;

import utils.TimeHelper;
import base.Address;
import base.DataAccessObject;
import base.MessageSystem;

public class DBServiceImpl implements DataAccessObject{
	private final MessageSystem messageSystem;
	private final Address address;
	private Connection connection;

	public DBServiceImpl(MessageSystem msgSystem){
		address=new Address();
		messageSystem = msgSystem;
		messageSystem.addService(this,"DBService");
    }

	public MessageSystem getMessageSystem(){
		return messageSystem;
	}

	public Address getAddress(){
		return address;
	}

	public UserDataSet getUDS(final String login, String password){
		UserDataSet user = TExecutor.getUDS(connection, login, password,
				new TResultHandler<UserDataSet>(){
			@Override
			public UserDataSet handle(ResultSet result){
				try {
					if(result.first()){
						int id = result.getInt("id");
						int rating = result.getInt("rating");
						int winQuantity = result.getInt("win_quantity");
						int loseQuantity = result.getInt("lose_quantity");
						return new UserDataSet(id,login,rating,winQuantity,loseQuantity);
					}
				} 
				catch (SQLException e) {
					e.printStackTrace();
				}
				return null;
			}
		});
		return user;
	}
	
	public boolean addUDS(final String login,String password){
		int rows = TExecutor.findUser(connection, login);
		if(rows==0)
			TExecutor.addUser(connection, login, password);
        return (rows==0);
	}

	public void updateUsers(List<UserDataSet> users){
		ListIterator<UserDataSet> li = users.listIterator();
		while(li.hasNext()){
			UserDataSet user = li.next();
			String login = user.getNick();
			int rating = user.getRating();
			int winQuantity = user.getWinQuantity();
			int loseQuantity = user.getLoseQuantity();
			TExecutor.updateUser(connection, login, rating, winQuantity, loseQuantity);
		}
	}

    public Connection setConnection() {
        try{
            Driver driver = (Driver) Class.forName("com.mysql.jdbc.Driver").newInstance();
            DriverManager.registerDriver(driver);
            String url="jdbc:mysql://localhost:3306/checkers?user=checkers&password=QSQ9D9BUBW93DK8A7H9FPXOB5OLOP84BA4CJRWK96VN0GPVC6P";
            connection = DriverManager.getConnection(url);
            return connection;
        }
        catch(Exception e){ e.printStackTrace();
        return null; }
    }

    public void removeUserByLogin(String Login) {
        TExecutor.removeUser(connection, Login);
    }

	public void run(){
        try{
			Driver driver = (Driver) Class.forName("com.mysql.jdbc.Driver").newInstance();
			DriverManager.registerDriver(driver);
		}
		catch(Exception e){
			e.printStackTrace();
		}

		String url="jdbc:mysql://localhost:3306/checkers?user=checkers&password=QSQ9D9BUBW93DK8A7H9FPXOB5OLOP84BA4CJRWK96VN0GPVC6P";

        try{
			connection = DriverManager.getConnection(url);
        }
		catch(Exception e){
			e.printStackTrace();
		}
		while(true){
			messageSystem.execForAbonent(this);
			TimeHelper.sleep(200);
		}
	}
}
