package com.pos.model;

public class Category {

	private int id;
	private int leavy;

	public enum Leavy {
		A(10), B(20), C(0);

		int leavy;

		public int getLeavy() {
			return this.leavy;
		}

		Leavy(int leavy) {
			this.leavy = leavy;
		}
	};

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getLeavy() {
		return leavy;
	}

	public void setLeavy(int leavy) {
		this.leavy = leavy;
	}

}
