package co.uk.zotix.xmlsecurity;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.security.KeyStore;
import java.security.PublicKey;
import java.security.cert.X509Certificate;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.apache.xml.security.keys.KeyInfo;
import org.apache.xml.security.keys.storage.StorageResolver;
import org.apache.xml.security.keys.storage.implementations.KeyStoreResolver;
import org.apache.xml.security.signature.XMLSignature;
import org.apache.xml.security.utils.Constants;
import org.apache.xml.security.utils.XMLUtils;
import org.w3c.dom.Element;

/**
 * @author $Author$
 */
public class VerifySignature {

    static {
        org.apache.xml.security.Init.init();
    }

    /**
     * Method main
     *
     * @param unused
     */
    public static void main(String unused[]) {

        boolean schemaValidate = false;
        final String signatureSchemaFile = "/home/akhettar/workspaces/inps/test_data/epharmacy/xmldsig-core-schema.xsd";
        //final String signatureFileName = "/home/akhettar/workspaces/inps/test_data/epharmacy/AMPPrescriptionRequest.xml";
        final String signatureFileName = "/home/akhettar/workspaces/inps/messagebus/service/security/src/test/resources/urn/messagebus/service/security/sample/SignedMessage.txt";

        //final String signatureFileName = "/home/akhettar/workspaces/inps/test_data/epharmacy/signature.xml";

        final String certDir = "/home/akhettar/workspaces/inps/test_data/epharmacy/";

        if (schemaValidate) {
            System.out.println("We do schema-validation");
        }

        javax.xml.parsers.DocumentBuilderFactory dbf =
                javax.xml.parsers.DocumentBuilderFactory.newInstance();

        if (schemaValidate) {
            dbf.setAttribute("http://apache.org/xml/features/validation/schema",
                    Boolean.TRUE);
            dbf.setAttribute("http://apache.org/xml/features/dom/defer-node-expansion",
                    Boolean.TRUE);
            dbf.setValidating(true);
            dbf.setAttribute("http://xml.org/sax/features/validation",
                    Boolean.TRUE);
        }

        dbf.setNamespaceAware(true);
        dbf.setAttribute("http://xml.org/sax/features/namespaces", Boolean.TRUE);

        if (schemaValidate) {
            dbf.setAttribute("http://apache.org/xml/properties/schema/external-schemaLocation",
                    Constants.SignatureSpecNS + " " + signatureSchemaFile);
        }

        try {

            File f = new File(signatureFileName);

            System.out.println("Try to verify " + f.toURI().toURL().toString());

            javax.xml.parsers.DocumentBuilder db = dbf.newDocumentBuilder();

            db.setErrorHandler(new org.apache.xml.security.utils.IgnoreAllErrorHandler());

            if (schemaValidate) {
                db.setEntityResolver(new org.xml.sax.EntityResolver() {

                    public org.xml.sax.InputSource resolveEntity(
                            String publicId, String systemId
                    ) throws org.xml.sax.SAXException {

                        if (systemId.endsWith("xmldsig-core-schema.xsd")) {
                            try {
                                return new org.xml.sax.InputSource(
                                        new FileInputStream(signatureSchemaFile));
                            } catch (FileNotFoundException ex) {
                                throw new org.xml.sax.SAXException(ex);
                            }
                        } else {
                            return null;
                        }
                    }
                });
            }

            org.w3c.dom.Document doc = db.parse(new java.io.FileInputStream(f));

            XPathFactory xpf = XPathFactory.newInstance();
            XPath xpath = xpf.newXPath();
            xpath.setNamespaceContext(new DSNamespaceContext());

            String expression = "//ds:Signature[1]";
            Element sigElement =
                    (Element) xpath.evaluate(expression, doc, XPathConstants.NODE);
            XMLSignature signature =
                    new XMLSignature(sigElement, f.toURI().toURL().toString());

            XMLUtils.outputDOM(sigElement, System.out);

            signature.addResourceResolver(new OfflineResolver());

            KeyInfo ki = signature.getKeyInfo();

            ki.addStorageResolver(
                    new StorageResolver(
                            new org.apache.xml.security.keys.storage.implementations
                                    .CertsInFilesystemDirectoryResolver(certDir)));
////            KeyStore ks = KeyStore.getInstance("JKS");
////            FileInputStream fis = new FileInputStream(
////                    "/home/akhettar/workspaces/inps/test_data/epharmacy/keystore.jks");
////
////            ks.load(fis, "changeit".toCharArray());
//
//            ki.addStorageResolver(new StorageResolver(new KeyStoreResolver(ks)));


            if (ki != null) {
                if (ki.containsX509Data()) {
                    System.out.println("Could find a X509Data element in the KeyInfo");
                }

                X509Certificate cert = signature.getKeyInfo().getX509Certificate();

                if (cert != null) {
                    System.out.println("The XML signature in file "
                            + f.toURI().toURL().toString() + " is "
                            + (signature.checkSignatureValue(cert)
                            ? "valid (good)" : "invalid !!!!! (bad)"));
                } else {
                    System.out.println("Did not find a Certificate");

                    PublicKey pk = signature.getKeyInfo().getPublicKey();

                    if (pk != null) {
                        System.out.println("The XML signature in file "
                                + f.toURI().toURL().toString() + " is "
                                + (signature.checkSignatureValue(pk)
                                ? "valid (good)" : "invalid !!!!! (bad)"));
                    } else {
                        System.out.println(
                                "Did not find a public key, so I can't check the signature");
                    }
                }
            } else {
                System.out.println("Did not find a KeyInfo");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}


