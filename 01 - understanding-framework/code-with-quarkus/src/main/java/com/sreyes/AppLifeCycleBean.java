package com.sreyes;

import io.quarkus.runtime.ShutdownEvent;
import io.quarkus.runtime.StartupEvent;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;

import java.util.logging.Logger;

@ApplicationScoped
public class AppLifeCycleBean {

  private static final Logger log = Logger.getLogger("ListenerBean");

  void onStart(@Observes StartupEvent event) {
    log.info("The application is starting...");
  }

  void onStop(@Observes ShutdownEvent event) {
    log.info("The application is stopping...");
  }
}
