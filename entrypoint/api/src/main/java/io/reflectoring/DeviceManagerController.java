package io.reflectoring;

import io.device.models.DeviceRequestResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api")
@RestController
public class DeviceManagerController {

@PostMapping
    public ResponseEntity<DeviceRequestResponse> addDevice(
            @RequestBody CreateDeviceRequest createDeviceRequest
    ){

}
}
