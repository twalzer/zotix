package co.zotix.schematron;

import org.apache.camel.Exchange;
import org.apache.camel.test.blueprint.CamelBlueprintTestSupport;
import org.apache.commons.io.IOUtils;
import org.custommonkey.xmlunit.XMLUnit;
import org.junit.Test;

import java.io.InputStream;

public class RouteTest extends CamelBlueprintTestSupport {


    @Override
    protected String getBlueprintDescriptor() {
        return "/OSGI-INF/blueprint/blueprint.xml,/OSGI-INF/blueprint/properties.xml";
    }

    @Test
    public void testRoute() throws Exception {
        InputStream payload = ClassLoader.getSystemResourceAsStream("input.xml");
        String report = IOUtils.toString(ClassLoader.getSystemResourceAsStream("report.txt"));
        Exchange in = createExchangeWithBody(context, payload);

        Exchange result = template.send("direct:validate", in);

        assertTrue(XMLUnit.compareXML(report, result.getIn().getBody(String.class)).similar());
    }


}
