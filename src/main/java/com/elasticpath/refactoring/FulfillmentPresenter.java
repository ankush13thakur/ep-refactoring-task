package com.elasticpath.refactoring;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

import com.elasticpath.refactoring.enums.FulfillmentMessages;
import com.elasticpath.refactoring.enums.FulfillmentPermissions;
import com.elasticpath.refactoring.enums.OrderStatus;
import com.elasticpath.refactoring.enums.PaymentAttributeKey;

public class FulfillmentPresenter {

	/**
	 * Converts an PaymentMethodDetails object into a human-readable string representation.
	 *
	 * @param paymentMethodDetails a DTO representing the payment details
	 * @return human-readable string description of the payment method
	 */
	public String getPaymentString(final PaymentMethodDetails paymentMethodDetails) {
		return getPaymentString(paymentMethodDetails, Locale.getDefault());
	}

	/**
	 * Converts an PaymentMethodDetails object into a human-readable string representation.
	 *
	 * @param paymentMethodDetails a DTO representing the payment details
	 * @param locale the user's current locale
	 * @return human-readable string description of the payment method
	 */
	public String getPaymentString(final PaymentMethodDetails paymentMethodDetails, final Locale locale) {
		String paymentString = "";
		if (paymentMethodDetails == null) {
			return paymentString;
		}

		switch (paymentMethodDetails.getPaymentType()) {
			case "GOOGLE_CHECKOUT":
				final String shipmentNumber = paymentMethodDetails.getAttributeAsString(PaymentAttributeKey.SHIPMENT_NUMBER);
				final String email = paymentMethodDetails.getAttributeAsString(PaymentAttributeKey.EMAIL);
				paymentString = resolveLocalizedString(FulfillmentMessages.GOOGLE, locale, shipmentNumber, email);
				break;
			case "GIFT_CERTIFICATE":
				String certCode;
				// This determines if the user is authorized to see the full gift certificate number or just a masked version
				if (AuthorizationService.getInstance().isAuthorized(FulfillmentPermissions.VIEW_FULL_CREDITCARD_NUMBER)) {
					certCode = paymentMethodDetails.getAttributeAsString(PaymentAttributeKey.GIFT_CERTIFICATE_CODE);
				} else {
					certCode = paymentMethodDetails.getAttributeAsString(PaymentAttributeKey.MASKED_GIFT_CERTIFICATE_CODE);
				}
				paymentString = resolveLocalizedString(FulfillmentMessages.GIFT_CERTIFICATE, locale, certCode);
				break;
			case "PAYPAL_EXPRESS":
				paymentString = paymentMethodDetails.getAttributeAsString(PaymentAttributeKey.EMAIL);
				break;
			case "RETURN_AND_EXCHANGE":
				if (paymentMethodDetails.getOrderStatus() == OrderStatus.AWAITING_EXCHANGE) {
					paymentString = resolveLocalizedString(FulfillmentMessages.EXCHANGE_PENDING, locale);
				} else if (paymentMethodDetails.getOrderStatus() != OrderStatus.AWAITING_EXCHANGE
						&& paymentMethodDetails.getOrderStatus() != OrderStatus.CANCELLED) {
					paymentString = resolveLocalizedString(FulfillmentMessages.EXCHANGE_COMPLETED, locale);
				}

				break;
			default:
				final List<String> bindings = new ArrayList<>();
				bindings.add(paymentMethodDetails.getAttributeAsString(PaymentAttributeKey.CARD_TYPE));
				// This determines if the user is authorized to see the full credit card number or just a masked version
				if (AuthorizationService.getInstance().isAuthorized(FulfillmentPermissions.VIEW_FULL_CREDITCARD_NUMBER)) {
					bindings.add(paymentMethodDetails.getAttributeAsString(PaymentAttributeKey.PAN));
				} else {
					bindings.add(paymentMethodDetails.getAttributeAsString(PaymentAttributeKey.MASKED_PAN));
				}
				bindings.add(paymentMethodDetails.getAttributeAsString(PaymentAttributeKey.EXPIRY_MONTH));
				bindings.add(paymentMethodDetails.getAttributeAsString(PaymentAttributeKey.EXPIRY_YEAR));
				paymentString = resolveLocalizedString(FulfillmentMessages.CARD_DESCRIPTION, locale, bindings.toArray());
		}

		return paymentString;
	}

	public String resolveLocalizedString(final FulfillmentMessages msg, Locale locale, final Object...bindings) {
		ResourceBundle bundle = PropertyResourceBundle.getBundle("messages", locale);
		String key = bundle.getString(msg.getMessageKey());
		return MessageFormat.format(key, bindings);
	}
}
