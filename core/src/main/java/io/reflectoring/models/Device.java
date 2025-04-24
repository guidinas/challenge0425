package io.reflectoring.models;

import lombok.Data;

import java.time.Instant;
import java.util.UUID;

@Data
public class Device {
private UUID id;
private String name;
private String brand;
private Instant createdAt;
}
