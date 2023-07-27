package com.sshanti.datawarehouse.control;

import com.sshanti.datawarehouse.boundary.FXDealResponse;
import com.sshanti.datawarehouse.boundary.request.FXDealRequest;
import com.sshanti.datawarehouse.entity.FXDeal;

import com.sshanti.datawarehouse.service.CrudService;
import com.sshanti.datawarehouse.util.FXDealResponseBuilder;
import com.sshanti.datawarehouse.util.FieldValidationException;
import com.sshanti.datawarehouse.util.RequestFieldValidator;
import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.core.Response;
import org.slf4j.Logger;


import java.util.Collections;
import java.util.List;

import static com.sshanti.datawarehouse.boundary.FXDealResponse.SUCCESS_MESSAGE;

@Dependent
@Transactional(Transactional.TxType.MANDATORY)
public class FXDealRequestControl {
    private final Logger logger = org.slf4j.LoggerFactory.getLogger(getClass());
    @Inject
    CrudService<FXDeal> fxDealService;

    public Response importFXDeal(FXDealRequest request) {
        try {

            logger.info("Importing FX deal request....");

            List<String> validationMessages = RequestFieldValidator.validateRequestFields(request);

            if (!validationMessages.isEmpty()) throw new FieldValidationException(validationMessages);

            logger.info("Valid request has been received.");

            FXDeal deal = fxDealService.create(convertFXRequestToFXDeal(request));

            logger.info(String.format(SUCCESS_MESSAGE, deal.getId()));

            return FXDealResponseBuilder.successRequest(
                    new FXDealResponse(Collections.singletonList(String.format(SUCCESS_MESSAGE, deal.getId())), Response.Status.OK.getReasonPhrase()));

        } catch (FieldValidationException fe) {
            logger.warn("Bad Request Please validate following fields, " + fe.getErrorMessages());
            return FXDealResponseBuilder.badRequest(new FXDealResponse(fe.getErrorMessages(), Response.Status.BAD_REQUEST.getReasonPhrase()));
        } catch (Exception e) {
            logger.warn(e.getMessage(), e);
            return FXDealResponseBuilder.badRequest(new FXDealResponse(Collections.singletonList(e.getMessage()), Response.Status.INTERNAL_SERVER_ERROR.getReasonPhrase()));
        }

    }

    private FXDeal convertFXRequestToFXDeal(FXDealRequest request) {
        FXDeal deal = new FXDeal();
        deal.setFromCurrency(request.getFromCurrency().toUpperCase());
        deal.setToCurrency(request.getToCurrency().toUpperCase());
        deal.setTimestamp(request.getTimestamp().toInstant());
        deal.setAmount(request.getAmount());
        return deal;
    }
}
