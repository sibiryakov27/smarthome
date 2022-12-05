package com.smarthome.service;

import com.smarthome.model.Action;
import com.smarthome.model.Sensor;
import com.smarthome.model.SensorType;
import lombok.AllArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Locale;

@Service
@AllArgsConstructor
public class SensorService {

    private MessageSource messages;

    private static HashMap<Integer, Sensor> db = new HashMap<>();
    private static int COUNT = 3;

    static {
        db.put(1, new Sensor()
                .setId(1)
                .setSensorType(SensorType.HUMIDITY)
                .setName("iMonnitHub")
                .setAction(Action.HUMIDITY)
                .setLocation("Living room")
                .setFrequency(433));
        db.put(2, new Sensor()
                .setId(2)
                .setSensorType(SensorType.TEMPERATURE)
                .setName("iMonnitHub")
                .setAction(Action.TEMPERATURE)
                .setLocation("Bathroom")
                .setFrequency(433));
        db.put(3, new Sensor()
                .setId(3)
                .setSensorType(SensorType.DRYCONTACT)
                .setName("iMonnitHub")
                .setAction(Action.ONOFF)
                .setLocation("Kitchen")
                .setFrequency(433));
    }

    public Sensor getSensor(int id) {
        return db.get(id);
    }

    public String createSensor(Sensor sensor, Locale locale) {
        String response = null;
        if (sensor != null) {
            sensor.setId(++COUNT);
            db.put(COUNT, sensor);
            response = String.format(messages.getMessage("sensor.create.message", null, locale), sensor);
        }
        return response;
    }

    public String changeSensor(int id, Sensor sensor, Locale locale) {
        String response = null;
        if (sensor != null) {
            sensor.setId(id);
            db.put(id, sensor);
            response = String.format(messages.getMessage("sensor.update.message", null, locale), id, sensor);
        }
        return response;
    }

    public String deleteSensor(int id, Locale locale) {
        return String.format(messages.getMessage("sensor.delete.message", null, locale), id, db.remove(id));
    }
}
