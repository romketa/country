package guru.qa.country.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import guru.qa.country.data.CountryEntity;
import guru.qa.country.domain.graphql.CountryGql;

public record Country(
    @JsonProperty("country_name")
    String countryName,
    @JsonProperty("country_code")
    String countryCode) {

  public static Country fromEntity(CountryEntity ce) {
    return new Country(ce.getCountryName(), ce.getCountryCode());
  }
  public static Country fromGqlPCountry(CountryGql countryGql) {
    return new Country(countryGql.countryName(), countryGql.countryCode());
  }
}
