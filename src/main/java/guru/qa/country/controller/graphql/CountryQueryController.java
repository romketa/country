package guru.qa.country.controller.graphql;

import guru.qa.country.domain.Country;
import guru.qa.country.domain.graphql.CountryGql;
import guru.qa.country.service.CountryService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class CountryQueryController {

  private final CountryService countryService;

  @Autowired
  public CountryQueryController(CountryService countryService) {
    this.countryService = countryService;
  }

  @QueryMapping
  @ResponseStatus(HttpStatus.OK)
  public Slice<CountryGql> countries(@Argument int page, @Argument int size) {
    return countryService.allCountriesGql(PageRequest.of(
      page, size
    ));
  }

  @QueryMapping
  @ResponseStatus(HttpStatus.OK)
  public CountryGql country(@Argument String countryName) {
    return countryService.countryGqlByName(countryName);
  }
}
