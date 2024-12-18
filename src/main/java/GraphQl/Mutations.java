package GraphQl;

import com.coxautodev.graphql.tools.GraphQLRootResolver;
import com.coxautodev.graphql.tools.ResolverError;
import entite.Logement;
import entite.RendezVous;
import repository.LogementRepository;
import repository.RendezVousRepository;

public class Mutations implements GraphQLRootResolver {
    public  RendezVousRepository rendezVousRepository;
    public  LogementRepository logementRepository;
    public Mutations(RendezVousRepository rendezVousRepository, LogementRepository logementRepository) {
        this.rendezVousRepository = rendezVousRepository;
        this.logementRepository = logementRepository;
    }
    public RendezVous createRendezVous(int id, String date, String heure, int refLog, String num) {
        Logement logement = logementRepository.getLogementsByReference(refLog);
        if (logement != null) {
            RendezVous rendezVous = new RendezVous(id, date, heure, logement, num);
            rendezVousRepository.addRendezVous(rendezVous);
            return rendezVous;
        }
        return null;
    }

    public RendezVous updateRendezVous(int id, String date, String heure, String numTel) {
        RendezVous rendezVous = rendezVousRepository.getListeRendezVous().stream()
                .filter(r -> r.getId() == id)
                .findFirst()
                .orElse(null);
        if (rendezVous != null) {
            if (date != null) {
                rendezVous.setDate(date);
            }
            if (heure != null) {
                rendezVous.setHeure(heure);
            }
            if (numTel != null) {
                rendezVous.setNumTel(numTel);
            }
            rendezVousRepository.updateRendezVous(rendezVous);
        }
        return rendezVous;
    }
    public boolean deleteRendezVous(int id) {
        return rendezVousRepository.deleteRendezVous(id);
    }
    public Logement createLogement(int reference, String adresse) {
        Logement logement = new Logement(reference, adresse);
        logementRepository.saveLogement(logement);
        return logement;
    }
    public Logement updateLogement(int reference, String adresse) {
        Logement logement = logementRepository.getLogementsByReference(reference);
        if (logement != null) {
            if (adresse != null) {
                logement.setAdresse(adresse);
            }
            logementRepository.updateLogement(logement);
        }
        return logement;
    }

}