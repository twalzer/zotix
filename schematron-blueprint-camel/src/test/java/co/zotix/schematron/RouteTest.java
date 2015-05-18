package co.zotix.schematron;

import org.apache.camel.EndpointInject;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.blueprint.CamelBlueprintTestSupport;

import org.apache.commons.io.IOUtils;
import org.custommonkey.xmlunit.XMLUnit;
import org.junit.Test;

import java.io.InputStream;
import java.util.Properties;

public class RouteTest extends CamelBlueprintTestSupport {

    @EndpointInject(uri = "mock:result")
    MockEndpoint mock;

    @Override
    protected String getBlueprintDescriptor() {
        return "/OSGI-INF/blueprint/blueprint.xml,/OSGI-INF/blueprint/properties.xml";
    }

    @Test
    public void testRoute() throws Exception {
        InputStream payload = ClassLoader.getSystemResourceAsStream("input.xml");
        String report = IOUtils.toString(ClassLoader.getSystemResourceAsStream("report.txt"));
        mock.expectedMinimumMessageCount(1);

        template.requestBody("direct:start", payload);

        // assert expectations
        assertMockEndpointsSatisfied();

        assertTrue(XMLUnit.compareXML(report, getMockBody()).similar());
    }

    private String getMockBody() {
        return mock.getExchanges().get(0).getIn().getBody(String.class);
    }


    @Override
    protected Properties useOverridePropertiesWithPropertiesComponent() {
        Properties properties = new Properties();
        properties.put("file.output", "mock:result");
        properties.put("file.input", "direct:start");
        return properties;

    }

}
