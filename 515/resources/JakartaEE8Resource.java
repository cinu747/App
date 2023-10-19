package edu.iit.sat.itmd4515.ckolathabraham.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

/**
 *
 * @author
 */
@Path("rest")
public class JakartaEE8Resource {

    /**
     *
     * @return
     */
    @GET
    public Response ping() {
        return Response
                .ok("ping")
                .build();
    }
}
