package fr.univnantes.m1.data;

public class DataQuery extends Data{
	
	private String query;

	public DataQuery(String idS, String q) {
		super(DataType.QUERY, idS);
		query=q;
	}

	public String getQuery() {
		return query;
	}
	
	public String toString(){
		return "{DataQuery = query:"+query+" }";
	}
}
