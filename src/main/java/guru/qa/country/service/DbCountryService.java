package guru.qa.country.service;

import guru.qa.country.data.CountryEntity;
import guru.qa.country.data.CountryRepository;
import guru.qa.country.domain.Country;
import guru.qa.country.domain.graphql.CountryGql;
import guru.qa.country.domain.graphql.CountryInputGql;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
public class DbCountryService implements CountryService {

  private final CountryRepository countryRepository;

  @Autowired
  public DbCountryService(CountryRepository countryRepository) {
    this.countryRepository = countryRepository;
  }

  @Override
  public Country getCountryByName(String name) {
    return Country.fromGqlPCountry(countryGqlByName(name));
  }

  @Override
  public CountryGql countryGqlByName(String name) {
    return countryRepository.findByCountryName(name)
        .map(ce -> new CountryGql(
            ce.getId(),
            ce.getCountryName(),
            ce.getCountryCode()
        ))
        .orElseThrow(() -> new EntityNotFoundException("Country not found with name: " + name));
  }

  @Override
  public List<Country> allCountries() {
    return countryRepository.findAll()
        .stream()
        .map(Country::fromEntity)
        .toList();
  }

  @Override
  public Page<CountryGql> allCountriesGql(Pageable pageable) {
    return countryRepository.findAll(pageable)
        .map(ce -> new CountryGql(
            ce.getId(),
            ce.getCountryName(),
            ce.getCountryCode()
        ));
  }

  @Override
  public Country addNewCountry(Country country) {
    var countryEntity = getCountryEntity(country);
    countryEntity = countryRepository.save(countryEntity);
    return Country.fromEntity(countryEntity);
  }

  private static CountryEntity getCountryEntity(Country country) {
    CountryEntity countryEntity = new CountryEntity();
    countryEntity.setCountryName(country.countryName());
    countryEntity.setCountryCode(country.countryCode());
    return countryEntity;
  }

  @Override
  public CountryGql addNewGqlCountry(CountryInputGql country) {
    CountryEntity countryEntity = new CountryEntity();
    countryEntity.setCountryName(country.countryName());
    countryEntity.setCountryCode(country.countryCode());
    CountryEntity saved = countryRepository.save(countryEntity);
    return new CountryGql(
        saved.getId(),
        saved.getCountryName(),
        saved.getCountryCode()
    );
  }

  @Override
  public List<Country> addNewCountries(List<Country> countries) {
    List<CountryEntity> countryEntities = countries.stream()
        .map(country -> {
          var entity = getCountryEntity(country);
          return entity;
        })
        .collect(Collectors.toList());

    List<CountryEntity> savedCountriesEntity = countryRepository.saveAll(countryEntities);

    return savedCountriesEntity.stream()
        .map(Country::fromEntity)
        .collect(Collectors.toList());
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
