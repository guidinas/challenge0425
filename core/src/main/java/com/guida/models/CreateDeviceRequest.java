package com.guida.models;

import com.guida.enums.State;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.Instant;
import java.util.UUID;

@AllArgsConstructor
@Data
public class CreateDeviceRequest {
    private UUID id;
    private String name;
    private String brand;
    private Instant createdAt;
    private State state;
}
