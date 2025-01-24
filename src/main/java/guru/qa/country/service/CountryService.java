package guru.qa.country.service;

import guru.qa.country.domain.Country;
import java.util.List;

public interface CountryService {

  List<Country> allCountries();

  Country addNewCountry(Country country);

  Country editCountryName(String name, String newName);

}
