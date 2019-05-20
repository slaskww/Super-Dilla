package utils;

import city.City;

import java.math.BigDecimal;

public interface Listener { //or Observer

 void update(String nanme, BigDecimal balance, City city);
}
