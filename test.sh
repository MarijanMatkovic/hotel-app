#!/bin/bash

(trap 'kill 0' INT; cd backend/ && mvn clean && mvn spring-boot:run & cd frontend/ && npm install && npx ng serve -o)
