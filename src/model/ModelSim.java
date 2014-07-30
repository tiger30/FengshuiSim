package model;

import java.io.Serializable;

public class ModelSim implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int Id;
	int simNo;
	String simCode;
	String simPrice;
	String simProvider;
	String simPoint;
	// int e1, e2, e3, e4, e5;// [RIS] nothing to do with this
	public int p1, p2, p3, p4, p5;//

	String phongThuy;// [RIS] find fixing solution

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public int getSimNo() {
		return simNo;
	}

	public void setSimNo(int simno) {
		simNo = simno;
	}

	public String getSimCode() {
		return simCode;
	}

	public void setSimCode(String simDinhDang) {
		this.simCode = simDinhDang;
	}

	public String getPrice() {
		return simPrice;
	}

	public void setPrice(String price) {
		this.simPrice = price;
	}

	public String getSimProvider() {
		return simProvider;
	}

	public void setSimProvider(String provider) {
		this.simProvider = provider;
	}

	public String getSimPoint() {
		return simPoint;
	}

	/*public void setSimPoint(int simDiem) {
		simPoint = simDiem;
	}*/

	// E
	//

	// P
	public int getP1() {
		return p1;
	}

	public void setP1(int p1) {
		this.p1 = p1;
	}

	public int getP2() {
		return p2;
	}

	public void setP2(int p2) {
		this.p2 = p2;
	}

	public int getP3() {
		return p3;
	}

	public void setP3(int p3) {
		this.p3 = p3;
	}

	public int getP4() {
		return p4;
	}

	public void setP4(int p4) {
		this.p4 = p4;
	}

	public int getP5() {
		return p5;
	}

	public void setP5(int p5) {
		this.p5 = p5;
	}

	//
	public String getPhongThuy() {
		return phongThuy;
	}

	public void setPhongThuy(String phongThuy) {
		this.phongThuy = phongThuy;
	}

	/*
	 * Date: 14/07/2014 Author: RIS
	 */
	public ModelSim(int id, String simFormat, String Price, String Provider, String Point,
			int P1, int P2, int P3, int P4, int P5) {
		this.Id = id;
		this.simCode = simFormat;
		this.simPrice = Price;
		this.simProvider = Provider;
		this.simPoint = Point;
		this.p1 = P1;
		this.p2 = P2;
		this.p3 = P3;
		this.p4 = P4;
		this.p5 = P5;
	}

	// Update RIS ends here

	/*
	 * public ModelSim(int id, String simDinhDang, String nhaMang, String
	 * phongThuy, String gia) { this.Id = id; this.simCode = simDinhDang;
	 * this.simProvider = nhaMang; this.phongThuy = phongThuy; this.simPrice =
	 * gia; }
	 */
}
