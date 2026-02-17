package dev.antoniogrillo.primoesempiocineca.actuator;

import dev.antoniogrillo.primoesempiocineca.repository.jparepo.AutomobileRepository;
import dev.antoniogrillo.primoesempiocineca.repository.jparepo.UtenteRepository;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.Nullable;
import org.springframework.boot.health.contributor.Health;
import org.springframework.boot.health.contributor.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor

public class CheckDb implements HealthIndicator {

    private final UtenteRepository repo;
    private final AutomobileRepository automobileRepository;

    @Override
    public @Nullable Health health() {
        try {
            return Health.up()
                    .withDetail("numeroUtenti", repo.count())
                    .withDetail("numeroAutomobili",automobileRepository.count())
                    .build();
        } catch (Exception e) {
            return Health.down()
                    .withDetail("errore",e.getMessage())
                    .build();
        }
    }
}
