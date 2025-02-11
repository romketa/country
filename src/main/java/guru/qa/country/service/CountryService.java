package guru.qa.country.service;

import guru.qa.country.domain.Country;
import java.util.List;

public interface CountryService {

  Country getCountryByName(String name);

  List<Country> allCountries();

  List<Country> addNewCountries(List<Country> countries);

  Country addNewCountry(Country country);

  Country editCountryName(String name, String newName);

}
