package guru.qa.country.service;

import guru.qa.country.CountryName;
import guru.qa.country.CountryResponse;
import guru.qa.country.CountryServiceGrpc;
import guru.qa.country.ListCountries;
import guru.qa.country.domain.Country;
import io.grpc.stub.StreamObserver;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class GrpcCountry extends CountryServiceGrpc.CountryServiceImplBase {

  private final CountryService countryService;

  public GrpcCountry(CountryService countryService) {
    this.countryService = countryService;
  }

  @Override
  public void countryByName(CountryName request, StreamObserver<CountryResponse> responseObserver) {
    Country country = countryService.getCountryByName(request.getName());
    responseObserver.onNext(
        CountryResponse.newBuilder()
            .setName(country.countryName())
            .setCode(country.countryCode())
            .build()
    );
    responseObserver.onCompleted();
  }

  @Override
  public void addCountries(ListCountries request,
      StreamObserver<CountryResponse> responseObserver) {
    List<Country> countries = new ArrayList<>();
    request.getCountryRequestList().forEach(countryRequest -> {
      String name = countryRequest.getName();
      String code = countryRequest.getCode();
      countries.add(new Country(name, code));
    });
    List<Country> addedCountries = countryService.addNewCountries(countries);
    addedCountries.forEach(country -> {
      CountryResponse.newBuilder()
          .setName(country.countryName())
          .setCode(country.countryCode())
          .build();
    });
    responseObserver.onCompleted();
  }
}
