package de.cimt.talendcomp.checksum;

public class NormalizeObjectConfig{

	public enum CaseSensitive {
		CASE_SENSITIVE,
		UPPER_CASE,
		LOWER_CASE,
		NOT_IN_USE
	}
	
	private CaseSensitive caseSensitive = NormalizeObjectConfig.CaseSensitive.UPPER_CASE;
	private boolean trimming;
	
	public NormalizeObjectConfig(String caseSensitive, boolean trimming) {
		
		if(caseSensitive == null)
			throw new IllegalArgumentException("caseSensitive must not be null");
		
		if("CASE_SENSITIVE".equalsIgnoreCase(caseSensitive)){
			this.caseSensitive = CaseSensitive.CASE_SENSITIVE;
		} else if ("UPPER_CASE".equalsIgnoreCase(caseSensitive)){
			this.caseSensitive = CaseSensitive.UPPER_CASE;
		} else if ("LOWER_CASE".equalsIgnoreCase(caseSensitive)) {
			this.caseSensitive = CaseSensitive.LOWER_CASE;
		} else if ("NOT_IN_USE".equalsIgnoreCase(caseSensitive)) {
			this.caseSensitive = CaseSensitive.LOWER_CASE;
		} else {
			throw new IllegalArgumentException("caseSensitive has to be CASE_SENSITIVE, UPPER_CASE, LOWER_CASE or NOT_IN_USE");
		}
		
		this.trimming = trimming;
	}

	public CaseSensitive getCaseSensitive() {
		return caseSensitive;
	}

	public boolean isTrimming() {
		return trimming;
	}

	public void setCaseSensitive(CaseSensitive caseSensitive) {
		this.caseSensitive = caseSensitive;
	}

	public void setTrimming(boolean trimming) {
		this.trimming = trimming;
	}
	
	
	
}
