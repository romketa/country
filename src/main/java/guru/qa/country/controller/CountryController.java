package guru.qa.country.controller;

import guru.qa.country.domain.Country;
import guru.qa.country.service.CountryService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("api/country")
public class CountryController {

  private final CountryService countryService;

  @Autowired
  public CountryController(CountryService countryService) {
    this.countryService = countryService;
  }

  @GetMapping("/all")
  @ResponseStatus(HttpStatus.OK)
  public List<Country> all() {
    return countryService.allCountries();
  }

  @GetMapping("/get/{name}")
  @ResponseStatus(HttpStatus.OK)
  public Country getCountryByName(String name) {
    return countryService.getCountryByName(name);
  }

  @PostMapping("/add")
  @ResponseStatus(HttpStatus.CREATED)
  public Country add(@RequestBody Country country) {
    return countryService.addNewCountry(country);
  }

  @PostMapping("/addCountries")
  @ResponseStatus(HttpStatus.CREATED)
  public List<Country> addCountries(@RequestBody List<Country> countries) {
    return countryService.addNewCountries(countries);
  }

  @PatchMapping("/edit/{name}")
  @ResponseStatus(HttpStatus.OK)
  public Country edit(@PathVariable String name, @RequestBody Country countryBodyRequest) {
    return countryService.editCountryName(name, countryBodyRequest.countryName());
  }
}
