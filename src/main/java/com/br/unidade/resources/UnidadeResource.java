package com.br.unidade.resources;

import com.br.unidade.dtos.UnidadeRequest;
import com.br.unidade.dtos.UnidadeResponse;
import com.br.unidade.services.UnidadeService;

import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/unidades")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UnidadeResource {

    @Inject
    UnidadeService service;

    @GET
    public Response listAll(
                        @QueryParam("page") @DefaultValue("0") int page,
                        @QueryParam("size") @DefaultValue("10") int size
    ){
        return Response.ok(service.findAll(page, size)).build();
    }

    @GET
    @Path("/{id}")
    public UnidadeResponse findById(@PathParam("id") Long id) {
        return service.findById(id);
    }

    @POST    
    public Response create(@Valid UnidadeRequest request) {
        return Response.status(Response.Status.CREATED)
                        .entity(service.create(request))
                        .build();
    }

    @PUT
    @Path("/{id}")
    public UnidadeResponse update(@PathParam("id") Long id,@Valid UnidadeRequest request) {
        return service.update(id, request);
    }

}
