package com.guida;

import com.guida.models.CreateDeviceRequest;
import com.guida.device.usecases.DeviceUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api")
@RestController
public class DeviceManagerController {
    @Autowired
    DeviceUseCase deviceUseCase;

    @PostMapping("/create")
    public ResponseEntity<Object> addDevice(
            @RequestBody CreateDeviceRequest createDeviceRequest
    ){
        return deviceUseCase.addDevice(createDeviceRequest);
    }

    @PostMapping("/update")
    public ResponseEntity<Object> updateDevice(
            @RequestBody CreateDeviceRequest createDeviceRequest
    ){
        return deviceUseCase.updateDevice(createDeviceRequest);
    }

    @GetMapping("/get/{uuid}")
    public ResponseEntity<Object> getDevice(@PathVariable String uuid){
        return deviceUseCase.getDevice(uuid);
    }

    @GetMapping("/get-all-devices")
    public ResponseEntity<Object> getAllDevices(){
        return deviceUseCase.getAllDevices();
    }

    @GetMapping("/get/brand")
    public ResponseEntity<Object> getDevicesByBrand(
            @RequestBody CreateDeviceRequest createDeviceRequest
    ){
        return deviceUseCase.getDevicesByBrand(createDeviceRequest.getBrand());
    }

    @GetMapping("/get/state")
    public ResponseEntity<Object> getDevicesByState(
            @RequestBody CreateDeviceRequest createDeviceRequest
    ){
        return deviceUseCase.getDevicesByBrand(createDeviceRequest.getState().toString());
    }

}
