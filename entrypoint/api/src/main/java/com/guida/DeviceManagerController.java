package com.guida;

import com.guida.device.usecases.DeviceUseCase;
import com.guida.models.CreateDeviceRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("/get-filter/brand")
    public ResponseEntity<Object> getDevicesByBrand(
            @RequestBody CreateDeviceRequest createDeviceRequest
    ){
        return deviceUseCase.getDevicesByBrand(createDeviceRequest.getBrand());
    }

    @GetMapping("/get-filter/state")
    public ResponseEntity<Object> getDevicesByState(
            @RequestBody CreateDeviceRequest createDeviceRequest
    ){
        return deviceUseCase.getDevicesByState(createDeviceRequest.getState().name());
    }
    @DeleteMapping("/delete/{uuid}")
    public ResponseEntity<Object> deleteDevice(@PathVariable String uuid){
        return deviceUseCase.deleteDevice(uuid);
    }
}
