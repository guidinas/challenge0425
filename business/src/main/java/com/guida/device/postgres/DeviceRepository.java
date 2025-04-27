package com.guida.device.postgres;

import com.guida.models.Device;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface DeviceRepository extends JpaRepository<Device, Long> {
    Device findById(UUID id);
}
