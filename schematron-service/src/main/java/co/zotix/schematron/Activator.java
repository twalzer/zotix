/**
 * $$Id: Activator 16/05/15 23:37 akhettar $$
 * $$Copyright: Copyright 2014 INPS.co.uk, L.P. All rights reserved. $$
 */
package co.zotix.schematron;

/**
 * Created by akhettar on 16/05/15.
 */

/**
 * Activator class
 */

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Activator for Schematron service
 *
 */
public class Activator implements BundleActivator {
    private static final transient Logger LOG = LoggerFactory.getLogger(Activator.class);

    /**
     * {@inheritDoc}
     */
    @Override
    public void start(BundleContext context) throws Exception {
        LOG.info("Starting the Schematron Service");

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void stop(BundleContext context) throws Exception {
        LOG.info("Stopping the Schematron Service");
    }

}
