#!/usr/bin/env bash

# rm -rf src/main/webapp/*
mvn clean
mvn install
mvn install

mvn jetty:run

