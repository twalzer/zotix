package co.uk.zotix.xmlsecurity;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.StringWriter;
import java.math.BigInteger;
import java.security.KeyStore;
import java.security.cert.X509Certificate;

import org.apache.xml.security.keys.KeyInfo;
import org.apache.xml.security.keys.content.X509Data;
import org.apache.xml.security.keys.content.keyvalues.RSAKeyValue;
import org.apache.xml.security.utils.XMLUtils;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

/**
 * Class CreateKeyInfo
 *
 * @author $Author$
 * @version $Revision$
 */
public class CreateKeyInfo {

    /**
     * Method main
     *
     * @param unused
     * @throws Exception
     */
    public static void main(String unused[]) throws Exception {

        KeyStore ks = KeyStore.getInstance("JKS");
        FileInputStream fis = new FileInputStream(
                "/home/akhettar/workspaces/inps/test_data/epharmacy/keystore.jks");

        ks.load(fis, "changeit".toCharArray());

        javax.xml.parsers.DocumentBuilderFactory dbf =
                javax.xml.parsers.DocumentBuilderFactory.newInstance();

        dbf.setNamespaceAware(true);

        javax.xml.parsers.DocumentBuilder db = dbf.newDocumentBuilder();
        org.w3c.dom.Document doc = db.newDocument();
        KeyInfo ki = new KeyInfo(doc);

        doc.appendChild(ki.getElement());
        ki.setId("myKI");
        ki.addKeyName("A simple key");

        X509Certificate cert = (X509Certificate) ks.getCertificate("mhs.epharmacy");

        ki.addKeyValue(cert.getPublicKey());

        X509Data x509Data = new X509Data(doc);
        x509Data.addCertificate(cert);

        x509Data.addIssuerSerial(cert.getIssuerDN().getName(), cert.getSerialNumber());
        ki.add(x509Data);

        ki.add(new RSAKeyValue(doc, new BigInteger("678"), new BigInteger("6870")));
        //FileOutputStream out = new FileOutputStream(new File("/home/akhettar/workspaces/inps/test_data/epharmacy/keyinfo.xml"));
        XMLUtils.outputDOM(doc, System.out);

//        KeyStore ks = KeyStore.getInstance("JKS");
//        FileInputStream fis = new FileInputStream(
//               "/home/akhettar/workspaces/inps/test_data/epharmacy/keystore.jks");
//
//        ks.load(fis, "changeit".toCharArray());
//
//        javax.xml.parsers.DocumentBuilderFactory dbf =
//                javax.xml.parsers.DocumentBuilderFactory.newInstance();
//
//        dbf.setNamespaceAware(true);
//
//        javax.xml.parsers.DocumentBuilder db = dbf.newDocumentBuilder();
//        org.w3c.dom.Document doc = db.newDocument();
//        KeyInfo ki = new KeyInfo(doc);
//
//        doc.appendChild(ki.getElement());
//        ki.setId("myKI");
//        ki.addKeyName("A simple key");
//
//        X509Certificate cert = (X509Certificate) ks.getCertificate("mhs.epharmacy");
//
//        ki.addKeyValue(cert.getPublicKey());
//
//        X509Data x509Data = new X509Data(doc);
//        x509Data.addIssuerSerial("CN=MHS.ePharmacy", 1395251157);
//        ki.add(x509Data);


        DOMSource domSource = new DOMSource(doc);
        StringWriter writer = new StringWriter();
        StreamResult result = new StreamResult(writer);
        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer transformer = tf.newTransformer();
        transformer.transform(domSource, result);
        System.out.println(writer.toString());
    }

}