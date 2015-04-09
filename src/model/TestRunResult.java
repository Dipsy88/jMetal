package model;

import java.sql.Date;

public class TestRunResult {

	
	
	private int caseId;
	
	private int pass;
	public int getPass() {
		return pass;
	}
	public void setPass(int pass) {
		this.pass = pass;
	}
	public int getFail() {
		return fail;
	}
	public void setFail(int fail) {
		this.fail = fail;
	}
	public int getUndecided() {
		return undecided;
	}
	public void setUndecided(int undecided) {
		this.undecided = undecided;
	}
	private int fail;
	private int undecided;
	
	public int getCaseId() {
		return caseId;
	}
	public void setCaseId(int caseId) {
		this.caseId = caseId;
	}

	
}
