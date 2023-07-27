package com.sshanti;

import com.sshanti.datawarehouse.boundary.FXDealImporterResource;
import com.sshanti.datawarehouse.boundary.FXDealResponse;
import com.sshanti.datawarehouse.boundary.request.FXDealRequest;
import com.sshanti.datawarehouse.control.FXDealRequestControl;
import com.sshanti.datawarehouse.util.FXDealResponseBuilder;
import jakarta.ws.rs.core.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class FXDealResourceTest {

    @Mock
    private FXDealRequestControl fxDealRequestControl;

    @InjectMocks
    private FXDealImporterResource fxDealImporterResource;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testImportNewRequest_Success() {
        FXDealRequest fxDealRequest = new FXDealRequest();

        when(fxDealRequestControl.importFXDeal(fxDealRequest)).thenReturn(FXDealResponseBuilder.successRequest(new FXDealResponse()));

        Response response = fxDealImporterResource.importNewFXDeal(fxDealRequest);

        verify(fxDealRequestControl).importFXDeal(fxDealRequest);

        // Assert the response is as expected (in this case, a successful response)
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
    }

    @Test
    void testImportNewRequest_Failure() {
        FXDealRequest fxDealRequest = new FXDealRequest();

        String errorMessage = "Invalid FX deal request";
        when(fxDealRequestControl.importFXDeal(fxDealRequest)).thenReturn(FXDealResponseBuilder.badRequest(
                new FXDealResponse(Collections.singletonList(errorMessage), Response.Status.BAD_REQUEST.getReasonPhrase())));

        Response response = fxDealImporterResource.importNewFXDeal(fxDealRequest);

        verify(fxDealRequestControl).importFXDeal(fxDealRequest);

        // Assert the response is as expected (in this case, a bad request response)
        assertEquals(Response.Status.BAD_REQUEST.getStatusCode(), response.getStatus());
    }
}
