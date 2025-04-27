package com.guida.device.validators;

import com.guida.models.Device;
import org.springframework.stereotype.Component;

@Component
public class CreationTimeUpdateValidator implements DeviceUpdateValidator {
    @Override
    public void validate(Device deviceToUpdate, Device deviceFromDB) {
        if(!deviceToUpdate.getCreatedAt().equals(deviceFromDB.getCreatedAt()) ) {
            throw new IllegalArgumentException("Creation time cannot be updated");
        }
    }
}
