package messageSystem;


import java.util.HashMap;
import java.util.Map;

import base.Abonent;
import base.Address;
import base.AddressService;


public class AddressServiceImpl implements AddressService{
	private Map<String,Address> nameToAddress=
			new HashMap<String,Address>();
	private Map<String,Integer> nameToQuantity=
			new HashMap<String,Integer>();
	private Map<String, Integer> nameToLast = 
			new HashMap<String, Integer>();

	AddressServiceImpl(){
	}

	public void addService (Abonent abonent,String name){
		if(!nameToQuantity.containsKey(name))
			nameToQuantity.put(name, 0);
		nameToQuantity.put(name, nameToQuantity.get(name)+1);
		nameToAddress.put(name+(nameToQuantity.get(name)).toString(), abonent.getAddress());
		if(nameToQuantity.get(name)==1)
			nameToLast.put(name, 1);
	}

	public Address getAddressByName(String name){
        //System.out.println(!nameToQuantity.containsKey(name));
        if (!nameToQuantity.containsKey(name)){
			return null;
		}
		String number = String.valueOf(nameToLast.get(name));
		nameToLast.put(name, (nameToLast.get(name)+1)%nameToQuantity.get(name)+1);
		return nameToAddress.get(name+number);
	}

}