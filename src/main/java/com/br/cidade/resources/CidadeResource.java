package com.br.cidade.resources;

import com.br.cidade.dtos.CidadeRequest;
import com.br.cidade.services.CidadeService;

import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/cidades")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CidadeResource {

    @Inject
    CidadeService service;
    
    @GET
    public Response list(){
        return Response.ok(service.findAll(0, 10)).build();
    }

    @GET
    @Path("/{id}")
    public Response findById(Long id){
        return Response.ok(service.findById(id)).build();        
    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") Long id ,@Valid CidadeRequest request) {
        return Response.status(Response.Status.ACCEPTED)        
                .entity(service.update(id, request))
                .build();

    }

    @POST
    public Response create(@Valid CidadeRequest request ){
        return Response.status(Response.Status.CREATED)
            .entity(service.create(request))
            .build();
    }

}
