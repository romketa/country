package guru.qa.country.domain.graphql;

import com.fasterxml.jackson.annotation.JsonProperty;
import guru.qa.country.data.CountryEntity;
import java.util.UUID;

public record CountryGql(
    UUID id,
    String countryName,
    String countryCode) {
}
