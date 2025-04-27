package com.guida.converters;

import com.guida.models.CreateDeviceRequest;
import com.guida.models.Device;
import org.springframework.stereotype.Component;

@Component
public class CreateDeviceRequestToDevice {
    public Device toDevice(CreateDeviceRequest createDeviceRequest) {
        return new Device(
                createDeviceRequest.getId(),
                createDeviceRequest.getName(),
                createDeviceRequest.getBrand(),
                createDeviceRequest.getCreatedAt(),
                createDeviceRequest.getState()
        );

    }
}
