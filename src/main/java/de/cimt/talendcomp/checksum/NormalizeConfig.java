package de.cimt.talendcomp.checksum;

import java.text.NumberFormat;
import java.util.Locale;

public class NormalizeConfig {
	
	private NumberFormat floatFormat;
    private NumberFormat doubleFormat;
    private NumberFormat integerFormat;
	
	private String delimter;
	private String nullReplacement;
	private String quotationCharacter;
	private boolean quotingEnabled;
	private String dateFormat;
	private boolean dateInMillis;
	private Locale numberFormat;
	private int maxFractionFloat;
	private int maxFractionDouble;
	private boolean modifyHashOutput;
	private String hashOutputIfBaseIsNull;
	private boolean cutOffEmptyTrailingObjects;
	
	public NormalizeConfig(String delimter, String nullReplacement, boolean quotingEnabled, String quoteCharacter, String dateFormat, String numberFormat,
			int maxFractionFloat, int maxFractionDouble, boolean modifyHashOutput, String hashOutputIfBaseIsNull, boolean dateInMillis, boolean cutOffEmptyTrailingObjects) {
		
		// check nulls and throw exception
		
		if(delimter == null)
			throw new IllegalArgumentException("delimiter must not be null");
	
//		not necessary in all cases
//		if(nullReplacement == null)
//			throw new IllegalArgumentException("nullReplacement must not be null. At least empty string.");
		
		if(quotingEnabled) {
			if(quoteCharacter == null)
				throw new IllegalArgumentException("quoteCharacter must not be null");
		}
		
		if(dateFormat == null)
			throw new IllegalArgumentException("dateFormat must not be null");
		
		if(numberFormat == null)
			throw new IllegalArgumentException("numberFormat must not be null");
		
		// set variables
		
		this.delimter = delimter;
		this.nullReplacement = nullReplacement;
		this.quotationCharacter = quoteCharacter;
		this.quotingEnabled = quotingEnabled;
		this.dateFormat = dateFormat;
		this.maxFractionFloat = maxFractionFloat;
		this.maxFractionDouble = maxFractionDouble;
		this.modifyHashOutput =  modifyHashOutput;
		this.hashOutputIfBaseIsNull = hashOutputIfBaseIsNull;
		this.dateInMillis = dateInMillis;
		this.cutOffEmptyTrailingObjects = cutOffEmptyTrailingObjects;
		
		if("ENGLISH".equalsIgnoreCase(numberFormat)){
			this.numberFormat = Locale.ENGLISH;
		} else if("GERMAN".equalsIgnoreCase(numberFormat)){
			this.numberFormat = Locale.GERMAN;
		} else if("US".equalsIgnoreCase(numberFormat)){
			this.numberFormat = Locale.US;
		} else {
			throw new IllegalArgumentException("Unsupport number format: " + numberFormat);
		}
		
		this.floatFormat = NumberFormat.getNumberInstance(this.numberFormat);
		this.floatFormat.setGroupingUsed(false);
		this.floatFormat.setMinimumFractionDigits(0);
		this.floatFormat.setMaximumFractionDigits(maxFractionFloat);
		
		this.doubleFormat = NumberFormat.getNumberInstance(this.numberFormat);
		this.doubleFormat.setGroupingUsed(false);
		this.doubleFormat.setMinimumFractionDigits(0);
		this.doubleFormat.setMaximumFractionDigits(maxFractionDouble);
	
		this.integerFormat = NumberFormat.getNumberInstance(this.numberFormat);
		this.integerFormat.setGroupingUsed(false);
		this.integerFormat.setMinimumFractionDigits(0);
		this.integerFormat.setMaximumFractionDigits(0);
	}

	

	public NumberFormat getFloatFormat() {
		return floatFormat;
	}


	public NumberFormat getDoubleFormat() {
		return doubleFormat;
	}


	public NumberFormat getIntegerFormat() {
		return integerFormat;
	}


	public String getDelimter() {
		return delimter;
	}


	public String getNullReplacement() {
		return nullReplacement;
	}


	public String getQuotationCharacter() {
		return quotationCharacter;
	}


	public boolean isQuotingEnabled() {
		return quotingEnabled;
	}


	public String getDateFormat() {
		return dateFormat;
	}


	public Locale getNumberFormat() {
		return numberFormat;
	}


	public int getMaxFractionFloat() {
		return maxFractionFloat;
	}


	public int getMaxFractionDouble() {
		return maxFractionDouble;
	}

	public void setFloatFormat(NumberFormat floatFormat) {
		this.floatFormat = floatFormat;
	}


	public void setDoubleFormat(NumberFormat doubleFormat) {
		this.doubleFormat = doubleFormat;
	}


	public void setIntegerFormat(NumberFormat integerFormat) {
		this.integerFormat = integerFormat;
	}


	public void setDelimter(String delimter) {
		this.delimter = delimter;
	}


	public void setNullReplacement(String nullReplacement) {
		this.nullReplacement = nullReplacement;
	}


	public void setQuotationCharacter(String quotationCharacter) {
		this.quotationCharacter = quotationCharacter;
	}


	public void setQuotingEnabled(boolean quotingEnabled) {
		this.quotingEnabled = quotingEnabled;
	}


	public void setDateFormat(String dateFormat) {
		this.dateFormat = dateFormat;
	}


	public void setNumberFormat(Locale numberFormat) {
		this.numberFormat = numberFormat;
	}


	public void setMaxFractionFloat(int maxFractionFloat) {
		this.maxFractionFloat = maxFractionFloat;
	}


	public void setMaxFractionDouble(int maxFractionDouble) {
		this.maxFractionDouble = maxFractionDouble;
	}

	public boolean isModifyHashOutput() {
		return modifyHashOutput;
	}



	public void setModifyHashOutput(boolean modifyHashOutput) {
		this.modifyHashOutput = modifyHashOutput;
	}



	public String getHashOutputIfBaseIsNull() {
		return hashOutputIfBaseIsNull;
	}



	public void setHashOutputIfBaseIsNull(String hashOutputIfBaseIsNull) {
		this.hashOutputIfBaseIsNull = hashOutputIfBaseIsNull;
	}



	public boolean isDateInMillis() {
		return dateInMillis;
	}



	public void setDateInMillis(boolean dateInMillis) {
		this.dateInMillis = dateInMillis;
	}



	public boolean isCutOffEmptyTrailingObjects() {
		return cutOffEmptyTrailingObjects;
	}



	public void setCutOffEmptyTrailingObjects(boolean cutOffEmptyTrailingObejcts) {
		this.cutOffEmptyTrailingObjects = cutOffEmptyTrailingObejcts;
	}
	
			
}
