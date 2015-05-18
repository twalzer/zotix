/**
 * $$Id: MyTransform 17/05/15 21:54 akhettar $$
 * $$Copyright: Copyright 2014 INPS.co.uk, L.P. All rights reserved. $$
 */
package co.zotix.schematron;/**
 * Created by akhettar on 17/05/15.
 */

import java.util.Date;
import java.util.logging.Logger;

public class MyTransform {

    private static final transient Logger logger = Logger.getLogger(MyTransform.class.getName());
    private boolean verbose = true;
    private String prefix = "MyTransform";

    public Object transform(Object body) {
        String answer = prefix + " set body:  " + new Date();
        if (verbose) {
            System.out.println(">>>> " + answer);
        }
        logger.info(">>>> " + answer);
        return answer;
    }

    public boolean isVerbose() {
        return verbose;
    }

    public void setVerbose(boolean verbose) {
        this.verbose = verbose;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }
}