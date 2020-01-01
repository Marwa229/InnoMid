package com.example.innomid;

public interface OtpReceivedInterface {

    void onOtpReceived(String otp);
    void onOtpTimeout();
}
