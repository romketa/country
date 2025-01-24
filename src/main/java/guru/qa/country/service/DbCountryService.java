package guru.qa.country.service;

import guru.qa.country.data.CountryEntity;
import guru.qa.country.data.CountryRepository;
import guru.qa.country.domain.Country;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DbCountryService implements CountryService {

  private final CountryRepository countryRepository;

  @Autowired
  public DbCountryService(CountryRepository countryRepository) {
    this.countryRepository = countryRepository;
  }

  @Override
  public List<Country> allCountries() {
    return countryRepository.findAll()
        .stream()
        .map(Country::fromEntity)
        .toList();
  }

  @Override
  public Country addNewCountry(Country country) {
    CountryEntity countryEntity = new CountryEntity();
    countryEntity.setCountryName(country.countryName());
    countryEntity.setCountryCode(country.countryCode());
    countryEntity = countryRepository.save(countryEntity);
    return Country.fromEntity(countryEntity);
  }

  @Override
  public Country editCountryName(String name, String newName) {
    CountryEntity countryEntity = countryRepository.findByCountryName(name)
        .orElseThrow(() -> new EntityNotFoundException("Country not found with name: " + name));
    countryEntity.setCountryName(newName);
    countryRepository.save(countryEntity);
    return Country.fromEntity(countryEntity);
  }
}
