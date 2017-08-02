# A Spring Boot Starter for Prometheus

This is Spring Boot Starter module for activating a Prometheus endpoint in Spring Boot applications.

[![Build Status](https://travis-ci.org/moelholm/prometheus-spring-boot-starter.svg?branch=master)](https://travis-ci.org/moelholm/prometheus-spring-boot-starter) [![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.moelholm/prometheus-spring-boot-starter/badge.svg)](https://maven-badges.herokuapp.com/maven-central/com.moelholm/prometheus-spring-boot-starter)


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

### Configuration

You can configure 2 x properties - here shown with their default values:

    endpoints.prometheus.path      = /prometheus
    
    endpoints.prometheus.sensitive = false

Same configuration as you will find with the standard Spring Boot Actuator endpoints.

### Alternatives

There are other starters as well:

- https://github.com/thomasdarimont/prometheus-spring-boot-starter
- https://github.com/akaGelo/spring-boot-starter-prometheus

Both actually existed at the time I started development of this starter. The first one is simple
but lacks two things: being in the Maven Central and using Spring Boot Actuator endpoints. The second
one is very much like this starter - but additionally comes with health indicator information as well.
Personally I found the implementation a bit complex - contrary to Thomas' implementation. Hence this starter: 
a kind of mashup ;)