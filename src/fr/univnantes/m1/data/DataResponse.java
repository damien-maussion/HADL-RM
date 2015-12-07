package fr.univnantes.m1.data;

public class DataResponse extends Data{

	private User user;
	
	public DataResponse(String idS, User u) {
		super(DataType.RESPONSE, idS);
		user=u;
	}

	public User getResponse() {
		return user;
	}
	
	public String toString(){
		return "{DataResponse = user:"+user+" }";
	}

}
