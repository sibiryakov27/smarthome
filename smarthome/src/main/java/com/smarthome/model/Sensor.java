package com.smarthome.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.springframework.hateoas.RepresentationModel;

@Getter
@Setter
@ToString
@Accessors(chain = true)
public class Sensor extends RepresentationModel<Sensor> {
    private Integer id;
    private String name;
    private Integer frequency;
    private String location;
    private SensorType sensorType;
    private Action action;
}
