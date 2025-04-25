package io.reflectoring.device.usecases;

import io.reflectoring.device.postgres.DeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.RequestMatchResult;

import java.net.http.HttpResponse;

@Component
public class DeviceUseCase {
    @Autowired
    DeviceRepository deviceRepository;

    public DeviceResponse addDevice(DeviceRequest deviceRequest){

        return null;
    }

}
