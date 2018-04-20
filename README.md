# Readme

[![Build Status](https://travis-ci.org/ungerts/wildfly-camunda-citrus.svg?branch=master)](https://travis-ci.org/ungerts/wildfly-camunda-citrus)

[Citrus](https://www.citrusframework.org/) is a testing tool focusing on the EAI domain, that enables the test team to define whole use case tests to be executed fully automated. 
Incoming and outgoing messages are predefined in the test case. The tester defines a message flow as it is designed for 
a use case. All surrounding interface partners are simulated regardless their transport protocols (Http, JMS, TCP/IP, 
SOAP, and many more). 

[Camunda](https://camunda.org/) is an open source platform for workflow and business process management. Process are modeled using BPMN and executed
directly unsing the Camunda engine. As BPMN process interact with a broad spectrum of services realized in different technologies
and provided by different parties, integration tests are a great challenge when testing the process implementation.

### Create Person Process ###

We use a simplifyed Create Person Process to demonstrate how Citrus can help testing process implementations.

![Create Person Process](./doc/CreatePersonProcess.svg)

### Executing the Test ###

```
mvn clean verify
```