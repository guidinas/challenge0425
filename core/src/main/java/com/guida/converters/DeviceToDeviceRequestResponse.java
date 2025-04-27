package com.guida.converters;

import com.guida.models.Device;
import com.guida.models.DeviceRequestResponse;
import org.springframework.stereotype.Component;

@Component
public class DeviceToDeviceRequestResponse {
    public DeviceRequestResponse toRequestResponse(Device device){
        return new DeviceRequestResponse(
                device.getId(),
                device.getName(),
                device.getBrand(),
                device.getCreatedAt(),
                device.getState()
        );
    }
}
