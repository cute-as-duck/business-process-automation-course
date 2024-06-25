package learning.center.service;

import learning.center.dto.PaymentRequest;
import learning.center.dto.PaymentResponse;

public interface PaymentService {
    PaymentResponse makePayment(PaymentRequest paymentRequest);
}
