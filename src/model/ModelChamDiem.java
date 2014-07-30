/*package model;

import java.io.Serializable;

public class ModelChamDiem implements Serializable {

	*//**
	 * 
	 *//*
	private static final long serialVersionUID = 1L;

	private String sim;
	private String gender;
	private String day;
	private String month;
	private String year;

	public String getSim() {
		return sim;
	}

	public void setSim(String sim) {
		this.sim = sim;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public ModelChamDiem(String sim, String gender, String day, String month,
			String year) {
		this.sim = sim;
		this.gender = gender;
		this.day = day;
		this.month = month;
		this.year = year;
	}
}
*/
package model;

import java.io.Serializable;

public class ModelChamDiem implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String sim;
	private String gender;
	private String day;
	private String month;
	public String getSim() {
		return sim;
	}
	public void setSim(String sim) {
		this.sim = sim;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getDay() {
		return day;
	}
	public void setDay(String day) {
		this.day = day;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	private String year;
	
	public ModelChamDiem(String sim, String gender, String day, String month, String year) {
		this.sim = sim;
		this.gender = gender;
		this.day = day;
		this.month =  month;
		this.year = year;
	}
}
