package model;

public class BaseModel {
	String Id;
	String Name;

	public String getId() {
		return Id;
	}

	public void setId(String id) {
		Id = id;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String toString() {
		return  this.getName();
	}
}
