package com.example.application.constants;

public enum OrderStatus {
    pending,       // Order has been placed, awaiting confirmation
    processing,     // Payment has been confirmed
    shipped,       // Order has been shipped
    delivered,     // Order has been delivered to the customer
    cancelled,     // Order has been cancelled
}
