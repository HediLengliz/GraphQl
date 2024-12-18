package GraphQl;
import com.coxautodev.graphql.tools.SchemaParser;
import graphql.schema.GraphQLSchema;
import graphql.servlet.SimpleGraphQLServlet;
import repository.LogementRepository;
import repository.RendezVousRepository;

import javax.servlet.annotation.WebServlet;

@WebServlet(urlPatterns = "/graphql")
public class GraphQLEndpoint extends SimpleGraphQLServlet {
    public GraphQLEndpoint() {
        super(buildSchema());
    }

    private static GraphQLSchema buildSchema() {
 LogementRepository logementRepository = new LogementRepository();
        RendezVousRepository rendezVousRepository = new RendezVousRepository();

        return SchemaParser.newParser()
                .file("schema.graphqls")
                .resolvers(new Queries(rendezVousRepository,logementRepository),new Mutations(rendezVousRepository,logementRepository))
                .build()
                .makeExecutableSchema();

    }
}
