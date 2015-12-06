package fr.univnantes.m1.data;

public class DataAuthResponse extends Data{

	private boolean allowed;
	
	public DataAuthResponse(String idS, boolean b) {
		super(DataType.AUTHENTIFICATION_RESPONSE, idS);
		allowed=b;
	}

	public boolean isAllowed() {
		return allowed;
	}
	
	public String toString(){
		return "{DataAuthResponse = allowed:"+allowed+" }";
	}

}
