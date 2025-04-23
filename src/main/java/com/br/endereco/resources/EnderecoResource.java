package com.br.endereco.resources;

import com.br.endereco.dtos.EnderecoRequest;
import com.br.endereco.dtos.EnderecoResponse;
import com.br.endereco.services.EnderecoService;

import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.core.*;
import jakarta.ws.rs.*;



@Path("/enderecos")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class EnderecoResource {

    @Inject
    EnderecoService service;

    @POST
    public Response create(@Valid EnderecoRequest request) {
        return Response.status(Response.Status.CREATED)
            .entity(service.create(request))
            .build();
    }

    @PUT
    @Path("/{id}")
    public EnderecoResponse update(@PathParam("id") Long id,@Valid EnderecoRequest request){
        return service.update(id, request);
    }

    @GET
    public Response list(
        @QueryParam("page") @DefaultValue("0") int page, 
        @QueryParam("size") @DefaultValue("10") int size
    ){
        return Response.ok(service.findAll(page, size)).build();
    }

    @GET
    @Path("/{id}")
    public EnderecoResponse findById(@PathParam("id") Long id){
        return service.findById(id);
    }



}
