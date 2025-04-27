package com.guida.device.validators;

import com.guida.models.Device;

public interface DeviceUpdateValidator {
    void validate(Device deviceToUpdate, Device deviceFromDB);
}
