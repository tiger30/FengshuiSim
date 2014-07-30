package model;

import java.io.Serializable;

public class ModelTimSim implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String sim;
	private String gia;
	private String loaiMang;

	private String day;
	private String month;
	private String year;
	private String loaiMenh;

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getSim() {
		return sim;
	}

	public void setSim(String sim) {
		this.sim = sim;
	}

	public String getGia() {
		return gia;
	}

	public void setGia(String gia) {
		this.gia = gia;
	}

	public String getLoaiMang() {
		return loaiMang;
	}

	public void setLoaiMang(String loaiMang) {
		this.loaiMang = loaiMang;
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

	public String getLoaiMenh() {
		return loaiMenh;
	}

	public void setLoaiMenh(String loaiMenh) {
		this.loaiMenh = loaiMenh;
	}

	public ModelTimSim(String sim, String gia, String loaiMang, String day,
			String month, String year, String menh) {
		this.sim = sim;
		this.gia = gia;
		this.day = day;
		this.month = month;
		this.year = year;
		this.loaiMang = loaiMang;
		this.loaiMenh = menh;
	}

}
