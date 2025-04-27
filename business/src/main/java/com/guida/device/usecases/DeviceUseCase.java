package com.guida.device.usecases;

import com.guida.converters.CreateDeviceRequestToDevice;
import com.guida.converters.DeviceToDeviceRequestResponse;
import com.guida.models.CreateDeviceRequest;
import com.guida.models.Device;
import com.guida.device.postgres.DeviceRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class DeviceUseCase {
    public DeviceUseCase(
            DeviceRepository deviceRepository,
            CreateDeviceRequestToDevice createDeviceRequestToDevice,
            DeviceToDeviceRequestResponse deviceToDeviceRequestResponse) {
        this.deviceRepository = deviceRepository;
        this.createDeviceRequestToDevice = createDeviceRequestToDevice;
        this.deviceToDeviceRequestResponse = deviceToDeviceRequestResponse;
    }

    final DeviceRepository deviceRepository;
    final CreateDeviceRequestToDevice createDeviceRequestToDevice;
    final DeviceToDeviceRequestResponse deviceToDeviceRequestResponse;

    public ResponseEntity<Object> addDevice(CreateDeviceRequest createDeviceRequest){
        final Device deviceToCreate = createDeviceRequestToDevice.toDevice(createDeviceRequest);
        final Device deviceFound = deviceRepository.findById(deviceToCreate.getId());

        if(deviceFound == null){
            return ResponseEntity.ok(deviceRepository.save(deviceToCreate));
        } else {
            return  ResponseEntity.badRequest()
                    .body("Device already exists");
        }
    }

    public ResponseEntity<Object> updateDevice(CreateDeviceRequest createDeviceRequest){
        final Device deviceToCreate = createDeviceRequestToDevice.toDevice(createDeviceRequest);
        final Device deviceFound = deviceRepository.findById(deviceToCreate.getId());

        if(deviceFound == null){
            return ResponseEntity.ok(deviceRepository.save(deviceToCreate));
        } else {
            return  ResponseEntity.badRequest()
                    .body("Device already exists");
        }
    }

}
