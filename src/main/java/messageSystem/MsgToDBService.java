package messageSystem;

import base.Abonent;
import base.Address;
import base.DataAccessObject;
import base.Msg;
import dbService.UserDataSet;


public abstract class MsgToDBService extends Msg{

	public MsgToDBService(Address from, Address to){
		super(from,to);
	}

	public void exec(Abonent abonent){
		if (abonent instanceof DataAccessObject){
			exec((DataAccessObject)abonent);
		}
	}
	public abstract UserDataSet exec(DataAccessObject dbService);
}