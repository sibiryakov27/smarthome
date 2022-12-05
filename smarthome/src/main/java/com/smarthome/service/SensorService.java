package com.smarthome.service;

import com.smarthome.config.ServiceConfig;
import com.smarthome.model.Action;
import com.smarthome.model.Sensor;
import com.smarthome.model.SensorType;
import com.smarthome.repo.SensorRepository;
import lombok.AllArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Locale;
import java.util.Optional;

@Service
@AllArgsConstructor
public class SensorService {

    private MessageSource messages;
    private ServiceConfig config;
    private SensorRepository repository;

    public Sensor getSensor(int id, Locale locale) {
        Optional<Sensor> sensor = repository.findById(id);
        if (sensor.isEmpty()) {
            throw new IllegalArgumentException(
                    String.format(messages.getMessage(
                            "sensor.search.error.message",
                            null,
                            locale
                    ), id));
        }
        return sensor.get().withComment(config.getProperty());

    }

    public Sensor createSensor(Sensor sensor, Locale locale) {
        repository.save(sensor);
        return sensor.withComment(config.getProperty());
    }

    public String changeSensor(int id, Sensor sensor, Locale locale) {
        Optional<Sensor> changedSensor = repository.findById(id);
        if (changedSensor.isEmpty()) {
            throw new IllegalArgumentException(
                    String.format(messages.getMessage(
                            "sensor.search.error.message",
                            null,
                            locale
                    ), id));
        }
        sensor.setId(id);
        repository.save(sensor);
        String response = String.format(messages.getMessage("sensor.update.message", null, locale), id, changedSensor);
        return response;
    }

    public String deleteSensor(int id, Locale locale) {
        Optional<Sensor> sensor = repository.findById(id);
        if (sensor.isEmpty()) {
            throw new IllegalArgumentException(
                    String.format(messages.getMessage(
                            "sensor.search.error.message",
                            null,
                            locale
                    ), id));
        }
        repository.delete(sensor.get());
        return String.format(messages.getMessage("sensor.delete.message", null, locale), id, sensor.get());
    }
}
