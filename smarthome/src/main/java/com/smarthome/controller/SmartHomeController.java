package com.smarthome.controller;

import com.smarthome.model.Sensor;
import com.smarthome.service.SensorService;
import lombok.AllArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/smeyukha-home")
@AllArgsConstructor
public class SmartHomeController {

    private SensorService sensorService;
    private MessageSource messages;

    @GetMapping("/sensor/{id}")
    public ResponseEntity<Sensor> getSensor(
            @PathVariable int id,
            @RequestHeader(value = "Accept-Language", required = false) Locale locale
    ) {
        Sensor sensor = sensorService.getSensor(id, locale);
        sensor.add(linkTo(methodOn(SmartHomeController.class)
                        .getSensor(id, locale))
                        .withSelfRel(),
                linkTo(methodOn(SmartHomeController.class)
                        .createSensor(sensor, locale))
                        .withRel(messages.getMessage("url.create.name", null, locale)),
                linkTo(methodOn(SmartHomeController.class)
                        .updateSensor(id, sensor, locale))
                        .withRel(messages.getMessage("url.update.name", null, locale)),
                linkTo(methodOn(SmartHomeController.class)
                        .deleteSensor(id, locale))
                        .withRel(messages.getMessage("url.delete.name", null, locale)));
        return ResponseEntity.ok(sensor);
    }

    @PostMapping("/sensor")
    public ResponseEntity<Sensor> createSensor(
            @RequestBody Sensor sensor,
            @RequestHeader(value = "Accept-Language", required = false) Locale locale
            ) {
        return ResponseEntity.ok(sensorService.createSensor(sensor, locale));
    }

    @PutMapping("/sensor/{id}")
    public ResponseEntity<String> updateSensor(
            @PathVariable int id,
            @RequestBody Sensor sensor,
            @RequestHeader(value = "Accept-Language", required = false) Locale locale
    ) {
        return ResponseEntity.ok(sensorService.changeSensor(id, sensor, locale));
    }

    @DeleteMapping("/sensor/{id}")
    public ResponseEntity<String> deleteSensor(
            @PathVariable int id,
            @RequestHeader(value = "Accept-Language", required = false) Locale locale
    ) {
        return ResponseEntity.ok(sensorService.deleteSensor(id, locale));
    }
}
