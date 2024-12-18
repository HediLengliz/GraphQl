package GraphQl;

import com.coxautodev.graphql.tools.GraphQLRootResolver;
import entite.Logement;
import entite.RendezVous;
import repository.LogementRepository;
import repository.RendezVousRepository;

import java.util.List;

public class Queries implements GraphQLRootResolver {
    private final  RendezVousRepository rendezVousRepository;
    private final LogementRepository logementRepository;
    public Queries(RendezVousRepository rendezVousRepository, LogementRepository logementRepository) {
        this.rendezVousRepository = rendezVousRepository;
        this.logementRepository = logementRepository;
    }
    public List<RendezVous> allRendezVous() {
        return rendezVousRepository.getListeRendezVous();
    }
    public List<RendezVous> getRendezVousByLogementRef(int reference) {
        return rendezVousRepository.getListeRendezVousByLogementRef(reference);
    }

    public RendezVous getRendezVousById(int id) {
        return rendezVousRepository.getListeRendezVous().stream()
                .filter(r -> r.getId() == id)
                .findFirst()
                .orElse(null);
    }
    public List<Logement> getAllLogements() {
        return logementRepository.getAllLogements();
    }

    public Logement getLogementByReference(int reference) {
        return logementRepository.getLogementsByReference(reference);
    }

    public List<Logement> getLogementsByType(String type) {
        return logementRepository.getLogementsByType(Logement.TypeL.valueOf(type));
    }


}
