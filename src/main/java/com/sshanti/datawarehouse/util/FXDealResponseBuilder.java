package com.sshanti.datawarehouse.util;

import com.sshanti.datawarehouse.boundary.FXDealResponse;
import com.sshanti.datawarehouse.dto.FXDealDTO;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

public class FXDealResponseBuilder {
    public static Response badRequest(FXDealResponse fxDealResponse) {
        return Response.status(Response.Status.BAD_REQUEST)
                .entity(fxDealResponse)
                .type(MediaType.APPLICATION_JSON_TYPE)
                .build();
    }

    public static Response successRequest(FXDealResponse fxDealResponse) {
        return Response.status(Response.Status.OK)
                .entity(fxDealResponse)
                .type(MediaType.APPLICATION_JSON_TYPE)
                .build();
    }

    public static Response serverError(FXDealResponse fxDealResponse) {
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity(fxDealResponse)
                .type(MediaType.APPLICATION_JSON_TYPE)
                .build();
    }

    public static Response successRequest(FXDealDTO fxDeal) {
        return Response.status(Response.Status.OK)
                .entity(fxDeal)
                .type(MediaType.APPLICATION_JSON_TYPE)
                .build();
    }

    public static Response notFound(String fxDealNotFoundMessage) {
        return Response.status(Response.Status.NOT_FOUND)
                .entity(fxDealNotFoundMessage)
                .type(MediaType.APPLICATION_JSON_TYPE)
                .build();
    }
}
