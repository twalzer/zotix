package co.zotix.schematron;

import org.apache.camel.Exchange;
import org.apache.camel.test.blueprint.CamelBlueprintTestSupport;
import org.apache.commons.io.IOUtils;
import org.custommonkey.xmlunit.XMLUnit;
import org.junit.Ignore;
import org.junit.Test;

import java.io.InputStream;

@Ignore
public class RouteTest extends CamelBlueprintTestSupport {


    @Override
    protected String getBlueprintDescriptor() {
        return "/OSGI-INF/blueprint/blueprint.xml,/OSGI-INF/blueprint/properties.xml";
    }

    @Test
    public void testRouteOk() throws Exception {
        InputStream payload = ClassLoader.getSystemResourceAsStream("input.xml");
        String report = IOUtils.toString(ClassLoader.getSystemResourceAsStream("report.txt"));
        Exchange in = createExchangeWithBody(context, payload);

        Exchange result = template.send("direct:validate", in);

        assertTrue(XMLUnit.compareXML(report, result.getIn().getBody(String.class)).similar());
    }
	
	@Test
    public void testRouteNotOk() throws Exception {
        InputStream payload = ClassLoader.getSystemResourceAsStream("input-error.xml");
        String report = IOUtils.toString(ClassLoader.getSystemResourceAsStream("report.txt"));
        Exchange in = createExchangeWithBody(context, payload);

        Exchange result = template.send("direct:validate", in);

        assertTrue(XMLUnit.compareXML(report, result.getIn().getBody(String.class)).similar());
    }


}
