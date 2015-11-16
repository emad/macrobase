package macrobase.server.resources;

import macrobase.ingest.PostgresLoader;
import macrobase.ingest.result.Schema;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import javax.ws.rs.*;


import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/schema")
@Produces(MediaType.APPLICATION_JSON)
public class SchemaResource {
    private static final Logger log = LoggerFactory.getLogger(SchemaResource.class);

    static class SchemaRequest {
        public String pgUrl;
        public String baseQuery;
    }

    private PostgresLoader loader;

    public SchemaResource(PostgresLoader _loader) {
        loader = _loader;
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Schema getSchema(SchemaRequest request) throws Exception {
        loader.connect(request.pgUrl);
        return loader.getSchema(request.baseQuery);
    }
}
