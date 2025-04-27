package com.guida.device.usecases;

import com.guida.converters.CreateDeviceRequestToDevice;
import com.guida.converters.DeviceToDeviceRequestResponse;
import com.guida.device.validators.DeviceUpdateValidator;
import com.guida.models.CreateDeviceRequest;
import com.guida.models.Device;
import com.guida.device.postgres.DeviceRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class DeviceUseCase {
    public DeviceUseCase(
            DeviceRepository deviceRepository,
            CreateDeviceRequestToDevice createDeviceRequestToDevice,
            DeviceToDeviceRequestResponse deviceToDeviceRequestResponse,
            List<DeviceUpdateValidator> deviceUpdateValidators) {
        this.deviceRepository = deviceRepository;
        this.createDeviceRequestToDevice = createDeviceRequestToDevice;
        this.deviceToDeviceRequestResponse = deviceToDeviceRequestResponse;
        this.deviceUpdateValidators = deviceUpdateValidators;
    }

    final DeviceRepository deviceRepository;
    final CreateDeviceRequestToDevice createDeviceRequestToDevice;
    final DeviceToDeviceRequestResponse deviceToDeviceRequestResponse;
    final List<DeviceUpdateValidator> deviceUpdateValidators;

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
            return  ResponseEntity.badRequest()
                    .body("Device does not exists");
        } else {
            try {
                deviceUpdateValidators.forEach(validator -> validator.validate(deviceToCreate, deviceFound));
            }catch (IllegalArgumentException e){
                return ResponseEntity.badRequest()
                        .body(e.getMessage());
            }
           return ResponseEntity.ok(deviceRepository.save(deviceToCreate));
        }
    }

    public ResponseEntity<Object> getDevice(String uuid) {
        try{
            final UUID id = UUID.fromString(uuid);
            final Device deviceFound = deviceRepository.findById(id);
            if(deviceFound == null){
                return ResponseEntity.badRequest()
                        .body("Device does not exists");
            }else {
                return ResponseEntity.ok(deviceFound);
            }
        }catch (IllegalArgumentException e){
            return ResponseEntity.badRequest()
                    .body("Invalid UUID");
        }

    }

    public ResponseEntity<Object> getAllDevices() {
            final List<Device> allDevices = deviceRepository.findAll();
            if(allDevices.isEmpty()){
                return ResponseEntity.badRequest()
                        .body("No devices found");
            }else {
                return ResponseEntity.ok(allDevices);
            }
    }

    public ResponseEntity<Object> getDevicesByBrand(String brand) {
        final List<Device> allDevices = deviceRepository.findAll();
        if(allDevices.isEmpty()){
            return ResponseEntity.badRequest()
                    .body("No devices found");
        }else {
            return ResponseEntity.ok(allDevices.stream().filter(device -> brand.equalsIgnoreCase(device.getBrand())));
        }
    }

    public ResponseEntity<Object> getDevicesByState(String state) {
        final List<Device> allDevices = deviceRepository.findAll();
        if(allDevices.isEmpty()){
            return ResponseEntity.badRequest()
                    .body("No devices found");
        }else {
            return ResponseEntity.ok(allDevices.stream().filter(device -> state.equalsIgnoreCase(device.getState().toString())));
        }
    }
}
