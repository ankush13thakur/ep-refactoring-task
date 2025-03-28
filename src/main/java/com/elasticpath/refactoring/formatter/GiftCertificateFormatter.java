package com.elasticpath.refactoring.formatter;

import com.elasticpath.refactoring.AuthorizationService;
import com.elasticpath.refactoring.PaymentMethodDetails;
import com.elasticpath.refactoring.enums.FulfillmentMessages;
import com.elasticpath.refactoring.enums.FulfillmentPermissions;
import com.elasticpath.refactoring.enums.PaymentAttributeKey;

import java.util.Locale;

public class GiftCertificateFormatter implements PaymentDetailsFormatter {

    @Override
    public String format(final PaymentMethodDetails paymentMethodDetails, final Locale locale) {
        String certCode;
        if (AuthorizationService.getInstance().isAuthorized(FulfillmentPermissions.VIEW_FULL_CREDITCARD_NUMBER)) {
            certCode = paymentMethodDetails.getAttributeAsString(PaymentAttributeKey.GIFT_CERTIFICATE_CODE);
        } else {
            certCode = paymentMethodDetails.getAttributeAsString(PaymentAttributeKey.MASKED_GIFT_CERTIFICATE_CODE);
        }
        return PaymentDetailsFormatter.resolveLocalizedString(FulfillmentMessages.GIFT_CERTIFICATE, locale, certCode);
    }
}
