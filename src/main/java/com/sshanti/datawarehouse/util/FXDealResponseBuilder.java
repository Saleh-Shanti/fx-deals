package com.sshanti.datawarehouse.util;

import com.sshanti.datawarehouse.boundary.FXDealResponse;
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
}
