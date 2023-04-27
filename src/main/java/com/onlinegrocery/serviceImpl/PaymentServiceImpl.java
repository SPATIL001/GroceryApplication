package com.onlinegrocery.serviceImpl;


 
import java.util.Date;
import java.util.List;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
 
import com.onlinegrocery.dto.DeliveryDto;
import com.onlinegrocery.dto.PaymentDto;
import com.onlinegrocery.entity.AppUser;
import com.onlinegrocery.entity.Payment;
import com.onlinegrocery.repo.AppUserRepo;
import com.onlinegrocery.repo.PaymentRepo;
import com.onlinegrocery.service.PaymentService;
 
import javax.transaction.Transactional;
import javax.validation.Valid;
 
 
@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    PaymentRepo paymentRepo;
    @Autowired
    AppUserRepo appUserRepo;
    @Override
    public PaymentDto payBill(PaymentDto paymentDTO) {
        double amount = paymentDTO.getAmount();
        double taxRate = 0.10; // 10% tax rate
        double taxAmount = amount * taxRate;
        double totalAmount = amount + taxAmount;
        System.out.println(paymentDTO+"user data-----------------------");
        Payment payment = new Payment();
        payment.setAmount(totalAmount);
        payment.setType(paymentDTO.getType());
        AppUser user = appUserRepo.findById(paymentDTO.getUserId()).get();
        payment.setDate(paymentDTO.getDate());

	    payment.setUserId(user);
        paymentRepo.save(payment);
       
      
        paymentDTO.setPaymentId(payment.getPaymentId());
        paymentDTO.setAmount(totalAmount);
        return paymentDTO;
    }
 
	@Override
	public Payment getPaymentById(long paymentId) {
		return paymentRepo.findById(paymentId).orElseThrow();
	}

	@Override
	public List<Payment> viewBillById(AppUser userId) {
		List<Payment> payment = paymentRepo.findByUserId(userId);
		return payment;
	}
 

}
 

