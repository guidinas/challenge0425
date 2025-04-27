package com.guida.device.validators;

import com.guida.enums.State;
import com.guida.models.Device;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class DeviceInUseUpdateValidation implements DeviceUpdateValidator {
    @Override
    public void validate(Device deviceToUpdate, Device deviceFromDB) {
        if(deviceFromDB.getState().equals(State.IN_USE)  &&
                (!Objects.equals(deviceToUpdate.getName(), deviceFromDB.getName()) ||
                        !Objects.equals(deviceToUpdate.getBrand(), deviceFromDB.getBrand())))
                        {
            throw new IllegalArgumentException("Device in use cannot be updated");
        }
    }
}