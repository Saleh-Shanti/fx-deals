package com.sshanti.datawarehouse.boundary;

import com.sshanti.datawarehouse.boundary.request.FXDealRequest;
import com.sshanti.datawarehouse.control.FXDealRequestControl;
import com.sshanti.datawarehouse.util.FXDealResponseBuilder;
import jakarta.enterprise.context.RequestScoped;


import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.slf4j.Logger;

import java.util.Collections;


@RequestScoped
@Path("/rest/1.0/fxDeals")
@Transactional(Transactional.TxType.REQUIRES_NEW)
public class FXDealImporterResource {

    private static final Logger logger = org.slf4j.LoggerFactory.getLogger(FXDealImporterResource.class);
    @Inject
    FXDealRequestControl fxDealRequestControl;

    @POST
    @Path("/import")
    @Operation(
            hidden = true,
            summary = "Import forex deal.",
            description = "Used for add forex deal requests.")
    public Response importNewFXDeal(FXDealRequest fxDealRequest) {
        try {
            logger.info("New FX deal request has been received");
            return fxDealRequestControl.importFXDeal(fxDealRequest);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return FXDealResponseBuilder.serverError(
                    new FXDealResponse(Collections.singletonList(e.getMessage()), Response.Status.INTERNAL_SERVER_ERROR.getReasonPhrase()));
        }
    }

    @GET
    @Path("/{dealId}")
    @Operation(
            hidden = true,
            summary = "Get forex deal by ID.",
            description = "Used to retrieve a forex deal by its ID.")
    public Response getFXDealById(@PathParam("dealId") Long dealId) {
        try {

            logger.info("New get FX deal by id request has been received");
            return fxDealRequestControl.getFXDealById(dealId);

        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return FXDealResponseBuilder.serverError(
                    new FXDealResponse(Collections.singletonList(e.getMessage()), Response.Status.INTERNAL_SERVER_ERROR.getReasonPhrase()));
        }
    }

}
