package com.fitnessbuddyapi.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Symbol {

  private String description;
  private String displaySymbol;
  private String symbol;

  public Symbol() {
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getDisplaySymbol() {
    return displaySymbol;
  }

  public void setDisplaySymbol(String displaySymbol) {
   this.displaySymbol = displaySymbol;
  }
  
  public String getSymbol() {
	return symbol;
  }

  public void setSymbol(String symbol) {
   this.symbol = symbol;
  }

@Override
public String toString() {
	return "GetGraph [description=" + description + ", displaySymbol=" + displaySymbol + ", symbol=" + symbol + "]";
}


}
