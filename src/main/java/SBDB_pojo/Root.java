package SBDB_pojo;

import java.util.List;

public class Root {
	
	private Signature signature;
    private String count;
	private List<String> fields;
  	private List<List<String>> data;
    
    public void setSignature(Signature signature){
        this.signature = signature;
    }
    public Signature getSignature(){
        return this.signature;
    }
    public void setCount(String count){
        this.count = count;
    }
    public String getCount(){
        return this.count;
    }
    public List<String> getFields() {
  		return fields;
  	}
  	public void setFields(List<String> fields) {
  		this.fields = fields;
  	}
  	public List<List<String>> getData() {
  		return data;
  	}
  	public void setData(List<List<String>> data) {
  		this.data = data;
  	}
    
}
