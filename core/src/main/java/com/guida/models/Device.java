package com.guida.models;

import com.guida.enums.State;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.UUID;

@NoArgsConstructor
@Entity
@Data
@AllArgsConstructor
public class Device {
@Id
private UUID id;
@Column(nullable = false)
private String name;
@Column
private String brand;
@Column(updatable = false)
private Instant createdAt;
@Column
private State state;
}
