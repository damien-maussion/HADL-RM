package fr.univnantes.m1.data;

public class DataAuth extends Data{

	public DataAuth(String idS) {
		super(DataType.AUTHENTIFICATION_QUERY, idS);
	}
	
	public String toString(){
		return "{DataAuth = idSender:"+getIdSender()+" }";
	}

}
