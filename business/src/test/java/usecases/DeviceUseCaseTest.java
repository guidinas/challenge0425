package usecases;

import com.guida.converters.CreateDeviceRequestToDevice;
import com.guida.converters.DeviceToDeviceRequestResponse;
import com.guida.device.postgres.DeviceRepository;
import com.guida.device.usecases.DeviceUseCase;
import com.guida.device.validators.DeviceUpdateValidator;
import com.guida.enums.State;
import com.guida.models.CreateDeviceRequest;
import com.guida.models.Device;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import java.time.Instant;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


class DeviceUseCaseTest {

    private DeviceRepository deviceRepository;
    private CreateDeviceRequestToDevice createDeviceRequestToDevice;
    private DeviceToDeviceRequestResponse deviceToDeviceRequestResponse;
    private List<DeviceUpdateValidator> deviceUpdateValidators;

    private DeviceUseCase deviceUseCase;

    @BeforeEach
    void setUp() {
        deviceRepository = mock(DeviceRepository.class);
        createDeviceRequestToDevice = mock(CreateDeviceRequestToDevice.class);
        deviceToDeviceRequestResponse = mock(DeviceToDeviceRequestResponse.class);
        deviceUpdateValidators = mock(List.class);

        deviceUseCase = new DeviceUseCase(deviceRepository, createDeviceRequestToDevice, deviceToDeviceRequestResponse, deviceUpdateValidators);
    }

    @Test
    void shouldAddDeviceSuccessfullyWhenDeviceNotExists() {
        CreateDeviceRequest request = new CreateDeviceRequest(UUID.randomUUID(), "iPhone 15", "Apple", Instant.now(), State.AVAILABLE);
        Device device = new Device(request.getId(), request.getName(), request.getBrand(), request.getCreatedAt(), request.getState());

        when(createDeviceRequestToDevice.toDevice(request)).thenReturn(device);
        when(deviceRepository.findById(device.getId())).thenReturn(null);
        when(deviceRepository.save(device)).thenReturn(device);

        ResponseEntity<Object> response = deviceUseCase.addDevice(request);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(device, response.getBody());
        verify(deviceRepository, times(1)).save(device);
    }

    @Test
    void shouldReturnBadRequestWhenDeviceAlreadyExists() {
        CreateDeviceRequest request = new CreateDeviceRequest(UUID.randomUUID(), "Galaxy S24", "Samsung", Instant.now(), State.AVAILABLE);
        Device device = new Device(request.getId(), request.getName(), request.getBrand(), request.getCreatedAt(), request.getState());

        when(createDeviceRequestToDevice.toDevice(request)).thenReturn(device);
        when(deviceRepository.findById(device.getId())).thenReturn(device);

        ResponseEntity<Object> response = deviceUseCase.addDevice(request);

        assertEquals(400, response.getStatusCodeValue());
        assertEquals("Device already exists", response.getBody());
        verify(deviceRepository, never()).save(any());
    }

    @Test
    void shouldUpdateDeviceSuccessfully() {
        CreateDeviceRequest request = new CreateDeviceRequest(UUID.randomUUID(), "Pixel 8", "Google", Instant.now(), State.AVAILABLE);
        Device device = new Device(request.getId(), request.getName(), request.getBrand(), request.getCreatedAt(), request.getState());

        when(createDeviceRequestToDevice.toDevice(request)).thenReturn(device);
        when(deviceRepository.findById(device.getId())).thenReturn(device);
        when(deviceRepository.save(device)).thenReturn(device);

        ResponseEntity<Object> response = deviceUseCase.updateDevice(request);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(device, response.getBody());
        verify(deviceRepository, times(1)).save(device);
    }

    @Test
    void shouldReturnBadRequestWhenUpdatingNonExistingDevice() {
        CreateDeviceRequest request = new CreateDeviceRequest(UUID.randomUUID(), "Pixel 8", "Google", Instant.now(), State.AVAILABLE);
        Device device = new Device(request.getId(), request.getName(), request.getBrand(), request.getCreatedAt(), request.getState());

        when(createDeviceRequestToDevice.toDevice(request)).thenReturn(device);
        when(deviceRepository.findById(device.getId())).thenReturn(null);

        ResponseEntity<Object> response = deviceUseCase.updateDevice(request);

        assertEquals(400, response.getStatusCodeValue());
        assertEquals("Device does not exists", response.getBody());
        verify(deviceRepository, never()).save(any());
    }

    @Test
    void shouldReturnBadRequestWhenInvalidUUIDOnGetDevice() {
        ResponseEntity<Object> response = deviceUseCase.getDevice("invalid-uuid");

        assertEquals(400, response.getStatusCodeValue());
        assertEquals("Invalid UUID", response.getBody());
    }

    @Test
    void shouldReturnBadRequestWhenDeviceNotFoundOnGetDevice() {
        UUID uuid = UUID.randomUUID();
        when(deviceRepository.findById(uuid)).thenReturn(null);

        ResponseEntity<Object> response = deviceUseCase.getDevice(uuid.toString());

        assertEquals(400, response.getStatusCodeValue());
        assertEquals("Device does not exists", response.getBody());
    }

    @Test
    void shouldReturnDeviceWhenFoundOnGetDevice() {
        UUID uuid = UUID.randomUUID();
        Device device = new Device(uuid, "Macbook", "Apple", Instant.now(), State.AVAILABLE);

        when(deviceRepository.findById(uuid)).thenReturn(device);

        ResponseEntity<Object> response = deviceUseCase.getDevice(uuid.toString());

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(device, response.getBody());
    }

    @Test
    void shouldReturnBadRequestWhenNoDevicesFoundOnGetAllDevices() {
        when(deviceRepository.findAll()).thenReturn(Collections.emptyList());

        ResponseEntity<Object> response = deviceUseCase.getAllDevices();

        assertEquals(400, response.getStatusCodeValue());
        assertEquals("No devices found", response.getBody());
    }

    @Test
    void shouldReturnAllDevicesOnGetAllDevices() {
        Device device = new Device(UUID.randomUUID(), "iPhone", "Apple", Instant.now(), State.AVAILABLE);
        when(deviceRepository.findAll()).thenReturn(List.of(device));

        ResponseEntity<Object> response = deviceUseCase.getAllDevices();

        assertEquals(200, response.getStatusCodeValue());
        assertTrue(((List<?>) response.getBody()).contains(device));
    }

}