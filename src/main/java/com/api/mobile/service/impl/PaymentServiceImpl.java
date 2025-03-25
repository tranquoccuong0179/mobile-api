package com.api.mobile.service.impl;

import com.api.mobile.config.VNPayConfig;
import com.api.mobile.enums.PaymentEnum;
import com.api.mobile.model.Booking;
import com.api.mobile.model.Field;
import com.api.mobile.model.Payment;
import com.api.mobile.repository.BookingRepository;
import com.api.mobile.repository.FieldRepository;
import com.api.mobile.repository.PaymentRepository;
import com.api.mobile.service.PaymentService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.hibernate.query.Order;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class PaymentServiceImpl implements PaymentService {
    private final PaymentRepository paymentRepository;
    private final BookingRepository bookingRepository;
    private final FieldRepository fieldRepository;
    @Override
    public String payWithVNPAYOnline(HttpServletRequest request, UUID id) throws UnsupportedEncodingException {
        Calendar cld = Calendar.getInstance(TimeZone.getTimeZone("Etc/GMT+7"));
        Booking booking = bookingRepository.findById(id).get();
        Field field = fieldRepository.findById(booking.getField().getId()).get();
        Payment payment = new Payment();
        payment.setBooking(booking);
        payment.setAmount(field.getPrice());
        payment.setStatus(PaymentEnum.PENDING);
        payment.setActive(true);
        payment.setCreatedAt(LocalDateTime.now());
        payment.setUpdatedAt(LocalDateTime.now());
        paymentRepository.save(payment);
        long totalPrice = Math.round(field.getPrice() * 100);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        String vnp_CreateDate = formatter.format(cld.getTime());
        cld.add(Calendar.MINUTE, 10);
        String vnp_ExpireDate = formatter.format(cld.getTime());
        Map<String, String> vnp_Params = new HashMap<>();
        vnp_Params.put("vnp_Version", VNPayConfig.vnp_Version);
        vnp_Params.put("vnp_Command", VNPayConfig.vnp_Command);
        vnp_Params.put("vnp_TmnCode", VNPayConfig.vnp_TmnCode);
        vnp_Params.put("vnp_Amount", String.valueOf(totalPrice));
        vnp_Params.put("vnp_CreateDate", vnp_CreateDate);
        vnp_Params.put("vnp_CurrCode", VNPayConfig.vnp_CurrCode);
        vnp_Params.put("vnp_IpAddr", VNPayConfig.getIpAddress(request));
        vnp_Params.put("vnp_Locale", VNPayConfig.vnp_Locale);
        vnp_Params.put("vnp_OrderInfo", String.valueOf(booking.getId()));
        vnp_Params.put("vnp_OrderType", VNPayConfig.getIpAddress(request));
        vnp_Params.put("vnp_ReturnUrl", VNPayConfig.vnp_ReturnUrl);
        vnp_Params.put("vnp_TxnRef", "HD" + RandomStringUtils.randomNumeric(6) + "-" + vnp_CreateDate);
        vnp_Params.put("vnp_ExpireDate", vnp_ExpireDate);
        List fieldList = new ArrayList(vnp_Params.keySet());
        Collections.sort(fieldList);
        StringBuilder hashData = new StringBuilder();
        StringBuilder query = new StringBuilder();
        Iterator itr = fieldList.iterator();
        while (itr.hasNext()) {
            String fieldName = (String) itr.next();
            String fieldValue = vnp_Params.get(fieldName);
            if (fieldValue != null && (fieldValue.length() > 0)) {
                hashData.append(fieldName);
                hashData.append("=");
                hashData.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
                query.append(URLEncoder.encode(fieldName, StandardCharsets.US_ASCII.toString()));
                query.append("=");
                query.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));

                if (itr.hasNext()) {
                    query.append("&");
                    hashData.append("&");
                }
            }
        }
        String queryUrl = query.toString();
        String vnp_SecureHash = VNPayConfig.hmacSHA512(VNPayConfig.secretKey, hashData.toString());
        queryUrl += "&vnp_SecureHash=" + vnp_SecureHash;
        String paymentUrl = VNPayConfig.vnp_PayUrl + "?" + queryUrl;

        return paymentUrl;
    }

    @Override
    public boolean processPaymentCallback(Map<String, String> queryParams, HttpServletResponse response) throws IOException {
        String vnp_ResponseCode = queryParams.get("vnp_ResponseCode");
        UUID bookingId = UUID.fromString(queryParams.get("vnp_OrderInfo"));
        long vnp_Amount = Long.parseLong(queryParams.get("vnp_Amount")) / 100;

        Optional<Booking> booking = bookingRepository.findById(bookingId);
        Payment payment = paymentRepository.findByBookingId(booking.get().getId()).orElse(null);

        if (vnp_ResponseCode == "00") {
            payment.setStatus(PaymentEnum.SUCCESSFUL);
            payment.setUpdatedAt(LocalDateTime.now());
            paymentRepository.save(payment);
            return true;
        } else if (vnp_ResponseCode == "01") {
            payment.setStatus(PaymentEnum.FAILED);
            payment.setUpdatedAt(LocalDateTime.now());
            paymentRepository.save(payment);
            return true;
        }
        return true;
    }
}

