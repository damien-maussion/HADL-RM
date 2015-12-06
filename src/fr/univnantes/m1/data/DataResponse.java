package fr.univnantes.m1.data;

public class DataResponse extends Data{

	private String response;
	
	public DataResponse(String idS, String r) {
		super(DataType.RESPONSE, idS);
		response=r;
	}

	public String getResponse() {
		return response;
	}
	
	public String toString(){
		return "{DataResponse = query:"+response+" }";
	}

}
