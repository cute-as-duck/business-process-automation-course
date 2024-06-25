package learning.center.service;

import learning.center.dto.PaymentRequest;
import learning.center.dto.PaymentResponse;
import learning.center.entity.Payment;
import learning.center.entity.TransactionStatus;
import learning.center.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService{

    private final PaymentRepository paymentRepository;

    @Override
    public PaymentResponse makePayment(PaymentRequest paymentRequest) {

        Payment payment = new Payment();
        String transactionId = UUID.randomUUID().toString();
        payment.setStudentId(paymentRequest.getStudentId());
        payment.setAmount(paymentRequest.getCourseFee());
        payment.setTransactionId(transactionId);
        payment.setStatus(TransactionStatus.SUCCESS);
        paymentRepository.save(payment);
        return new PaymentResponse(TransactionStatus.SUCCESS);
    }
}
