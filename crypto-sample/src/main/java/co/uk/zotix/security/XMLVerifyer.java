package co.uk.zotix.security;

import javax.xml.crypto.*;
import javax.xml.crypto.dsig.*;
import javax.xml.crypto.dsig.dom.DOMValidateContext;
import javax.xml.crypto.dsig.keyinfo.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.security.*;
import java.util.Iterator;
import java.util.List;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;


/**
 * XML Verifiyer
 *
 */
public class XMLVerifyer

{

    private static final String KEYSTORE_PASSWORD = "changeit";
    private static final String MHS_EPHARMACY_CERTIFICATE = "mhs.epharmacy";
    //private static final String EPMS_CERTIFICATE = "epms re-signing (scica)";
    private static final String EPMS_CERTIFICATE = "epharmacyclient";
    private static final String SCICA = "scica";

    //
    // Synopsis: java Validate [document]
    //
    //    where "document" is the name of a file containing the XML document
    //    to be validated.
    //
    public static void main(String[] args) throws Exception {

        // Instantiate the document to be validated
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        //final String filePath = "/home/akhettar/workspaces/inps/test_data/sanpit/CMSRichTextBox1.txt";
        //final String filePath= "/home/akhettar/workspaces/inps/test_data/epharmacy/AMPPrescriptionRequest.xml";
        //final String filePath = "/home/akhettar/workspaces/inps/messagebus/service/security/src/test/resources/urn/messagebus/service/security/sample/SignedMessage.txt";
        final String filePath = "/home/akhettar/workspaces/inps/test_data/sanpit/xml/A881130000DJ3GPT.xml";

        File sampleDir = new File("/home/akhettar/workspaces/inps/test_data/sanpit/xml");
        File[] files = sampleDir.listFiles();

//        for (File file : files)
//        {
            dbf.setNamespaceAware(true);
            Document doc =
                    dbf.newDocumentBuilder().parse(new FileInputStream(filePath));
            XPath xPath =  XPathFactory.newInstance().newXPath();


            // Find Signature element
            NodeList nl =
                    doc.getElementsByTagNameNS(XMLSignature.XMLNS, "Signature");
            if (nl.getLength() == 0) {
                throw new Exception("Cannot find Signature element");
            }

            Element serialNumber = (Element) doc.getElementsByTagNameNS(XMLSignature.XMLNS, "X509SerialNumber").item(0);
            serialNumber.setTextContent("569020113290590490205404");


            // Create a DOM XMLSignatureFactory that will be used to unmarshal the
            // document containing the XMLSignature
            XMLSignatureFactory fac = XMLSignatureFactory.getInstance("DOM");

            // Create a DOMValidateContext and specify a KeyValue KeySelector
            // and document context
            DOMValidateContext valContext = new DOMValidateContext
                    (new KeyValueKeySelector(), nl.item(0));

            // unmarshal the XMLSignature
            XMLSignature signature = fac.unmarshalXMLSignature(valContext);


            removeSignature(doc);



            // Validate the XMLSignature (generated above)
            System.out.println("***************************** BEGIN *******************************************");
            boolean coreValidity = signature.validate(valContext);
            System.out.println("*****************************  RESULT *******************************************");
            System.out.println("Core Validation status: " + coreValidity);
            boolean sv = signature.getSignatureValue().validate(valContext);
            System.out.println("signature validation status: " + sv);
            // check the validation status of each Reference
            Iterator i = signature.getSignedInfo().getReferences().iterator();
            for (int j=0; i.hasNext(); j++) {
                Reference ref = ((Reference) i.next());
                boolean refValid = ref.validate(valContext);
                System.out.println("ref["+ ref.getURI()+"] validity status: "  + refValid);
            }
            System.out.println("****************************** END *****************************************");

//        }


    }

    private static void removeSignature(Document doc) {

        Element signatureElement = (Element) doc.getElementsByTagNameNS(XMLSignature.XMLNS, "Signature").item(0);
        signatureElement.getParentNode().removeChild(signatureElement);
    }

    private static KeyStore loadKeystore() throws Exception {

        final String fileStore = "/home/akhettar/workspaces/inps/test_data/sanpit/keystoreClient.jks";
        //final String fileStore = "/home/akhettar/workspaces/inps/test_data/epharmacy/keystore.jks";


        KeyStore keystore = KeyStore.getInstance(KeyStore.getDefaultType());

        final InputStream in = new FileInputStream(fileStore);

        keystore.load(in, getKeystorePassword());

        return keystore;
    }

    private static char[] getKeystorePassword() {
        return "changeit".toCharArray();
    }



    public static KeyStore getKeystore() throws Exception {
        return loadKeystore();
    }
    private static PublicKey getPublicKeyFromKeystore() throws Exception {
        return getKeystore()
                .getCertificate(EPMS_CERTIFICATE)
                .getPublicKey();
    }
    /**
     * KeySelector which retrieves the public key out of the
     * KeyValue element and returns it.
     * NOTE: If the key algorithm doesn't match signature algorithm,
     * then the public key will be ignored.
     */
    private static class KeyValueKeySelector extends KeySelector {
        public KeySelectorResult select(KeyInfo keyInfo,
                                        KeySelector.Purpose purpose,
                                        AlgorithmMethod method,
                                        XMLCryptoContext context)
                throws KeySelectorException {
            if (keyInfo == null) {
                throw new KeySelectorException("Null KeyInfo object!");
            }
            SignatureMethod sm = (SignatureMethod) method;

            // make sure algorithm is compatible with method

            try {
                PublicKey pk = getPublicKeyFromKeystore();
                if (algEquals(sm.getAlgorithm(), pk.getAlgorithm())) {
                    return new SimpleKeySelectorResult(pk);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }



            throw new KeySelectorException("No KeyValue element found!");
        }

        //@@@FIXME: this should also work for key types other than DSA/RSA
        static boolean algEquals(String algURI, String algName) {
            if (algName.equalsIgnoreCase("DSA") &&
                    algURI.equalsIgnoreCase(SignatureMethod.DSA_SHA1)) {
                return true;
            } else if (algName.equalsIgnoreCase("RSA") &&
                    algURI.equalsIgnoreCase(SignatureMethod.RSA_SHA1)) {
                return true;
            } else {
                return false;
            }
        }
    }
    private static class SimpleKeySelectorResult implements KeySelectorResult {
        private PublicKey pk;
        SimpleKeySelectorResult(PublicKey pk) {
            this.pk = pk;
        }

        public Key getKey() { return pk; }
    }
}
