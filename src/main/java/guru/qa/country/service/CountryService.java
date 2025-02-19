package guru.qa.country.service;

import guru.qa.country.domain.Country;
import guru.qa.country.domain.graphql.CountryGql;
import guru.qa.country.domain.graphql.CountryInputGql;
import java.util.List;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

public interface CountryService {

  Country getCountryByName(String name);

  CountryGql countryGqlByName(String name);

  List<Country> allCountries();

  Slice<CountryGql> allCountriesGql(Pageable pageable);

  List<Country> addNewCountries(List<Country> countries);

  Country addNewCountry(Country country);

  CountryGql addNewGqlCountry(CountryInputGql country);

  Country editCountryName(String name, String newName);

}
