package guru.qa.country.data;

import com.google.api.Page;
import jakarta.annotation.Nonnull;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository<CountryEntity, UUID> {

  @Nonnull
  Optional<CountryEntity> findByCountryName(String countryName);
}
