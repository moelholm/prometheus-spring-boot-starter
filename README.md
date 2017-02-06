# A Spring Boot Starter for Prometheus

This is Spring Boot Starter module for activating a Prometheus endpoint in Spring Boot applications.

### What you get

A new Spring Boot Actuator endpoint - designed for Prometheus:

    http://localhost:8080/prometheus

If you curl that you will get something like:

    # HELP heap_used heap_used
    # TYPE heap_used gauge

Exactly how Prometheus likes it.

### How you get it

It's as easy as any other Spring Boot Starter ;). Find your recipe below...

Gradle:

    compile "com.moelholm:prometheus-spring-boot-starter:1.0.1"

Maven:

    <dependency>
      <groupId>com.moelholm</groupId>
      <artifactId>prometheus-spring-boot-starter</artifactId>
      <version>1.0.1</version>
    </dependency>

### Why this starter too?

There are one or a few others but as far as I know they don't:
- Activate the Prometheus endpoint as a real Spring Boot Actuator endpoint
- Exist in the official Maven repository

