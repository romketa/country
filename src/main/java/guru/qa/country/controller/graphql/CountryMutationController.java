package guru.qa.country.controller.graphql;

import guru.qa.country.domain.Country;
import guru.qa.country.domain.graphql.CountryGql;
import guru.qa.country.domain.graphql.CountryInputGql;
import guru.qa.country.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
public class CountryMutationController {

  private final CountryService countryService;

  @Autowired
  public CountryMutationController(CountryService countryService) {
    this.countryService = countryService;
  }

  @MutationMapping
  @ResponseStatus(HttpStatus.CREATED)
  public CountryGql addCountry(@Argument CountryInputGql input) {
    return countryService.addNewGqlCountry(input);
  }

}
