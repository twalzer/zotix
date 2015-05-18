/**
 * $$Id: RouteBuilder 16/05/15 08:50 akhettar $$
 * $$Copyright: Copyright 2014 INPS.co.uk, L.P. All rights reserved. $$
 */
package co.zotix.schematron.route;/**
 * Created by akhettar on 16/05/15.
 */

import org.apache.camel.builder.RouteBuilder;

/**
 * RouteBuilder class
 */
public class SchematronRouteBuilder extends RouteBuilder {


    @Override
    public void configure() throws Exception {

        restConfiguration().component("jetty").host("localhost").port(9000);
        rest("/schematron/validate")
                .get().route().to("{{schematron.endpoint}}").setBody(header("CamelSchematronValidationReport"));


    }
}
