Camel Router Project for Blueprint (OSGi): Schematron Service
==============================================================

To build this project use

    mvn install

To run the project you can execute the following Maven goal

    mvn camel:run

To deploy the project in OSGi. For example using Apache ServiceMix

or Apache Karaf. You can run the following command from its shell:

    features:addurl mvn:co.zotix.schematron/schematron-service/1.0.0/xml/features
    features:install schematron-service

if you run: osgi:list  You should see the service up and runing on port 9090.


To Run some examples: Navigate to the project directory and run the post request below.

Success

curl -v -H "Content-Type: application/xml" -X POST --data "@src/test/resources/input.xml" -u login:password http://localhost:9090/schematron/validate


Failure

curl -v -H "Content-Type: application/xml" -X POST --data "@src/test/resources/input-error.xml" -u login:password http://localhost:9090/schematron/validate



For more help see the Apache Camel documentation
