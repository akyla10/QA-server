package dbService;

import java.util.List;

import base.Address;
import base.DataAccessObject;
import messageSystem.MsgToDBService;

public class MsgUpdateUsers extends MsgToDBService{
	List<UserDataSet> users;
	public MsgUpdateUsers(Address from, Address to, List<UserDataSet> users){
		super(from, to);
		this.users=users;
	}
	public UserDataSet exec(DataAccessObject dbService){
		dbService.updateUsers(users);
        return null;
    }
}
