package com.guida;

import com.guida.models.CreateDeviceRequest;
import com.guida.models.DeviceRequestResponse;
import com.guida.device.usecases.DeviceUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

}
