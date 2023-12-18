package com.ltim.lc.app.clm.model;

import java.util.List;


public class Decision extends BaseClientRequest {

	private List<Variable> variables;
	private String decisionId;

	public List<Variable> getVariables() {
		return variables;
	}

	public void setVariables(List<Variable> variables) {
		this.variables = variables;
	}

	public String getDecisionId() {
		return decisionId;
	}

	public void setDecisionId(String decisionId) {
		this.decisionId = decisionId;
	}

	@Override
	public String toString() {
		return "Decision [variables=" + variables + ", decisionId=" + decisionId + "]";
	}

}
