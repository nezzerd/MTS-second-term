package org.app.actuator;

import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Endpoint(id = "random")
public class RandomActuator {

  @ReadOperation
  public String generateRandomUuid() {
    return UUID.randomUUID().toString();
  }
}
