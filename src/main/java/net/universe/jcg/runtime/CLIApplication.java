/****************************************************************************\
 __FILE..........: CLIApplication.java
 __AUTHOR........: lampsound
 __COPYRIGHT.....: Copyright (c) 2011 universe.net
 _________________All rights reserved.
 __VERSION.......: 1.0
 __DESCRIPTION...:
 __HISTORY.......: DATE       COMMENT
 _____________________________________________________________________________
 ________________:24.08.11 lampsound: created
 ****************************************************************************/


package net.universe.jcg.runtime;

import com.google.inject.Inject;
import net.universe.jcg.cli.CLISupport;
import net.universe.jcg.service.PrimaryBusinessLogicService;
import net.universe.jcg.service.SecondaryBusinessLogicService;
import net.universe.jcg.service.Service;
import net.universe.jcg.service.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class CLIApplication implements Service {

    /*===========================================[ STATIC VARIABLES ]=============*/

    private static final Logger logger = LoggerFactory.getLogger(CLIApplication.class);

    /*===========================================[ INSTANCE VARIABLES ]=========*/

    private PrimaryBusinessLogicService primaryBusinessLogicService;
    private SecondaryBusinessLogicService secondaryBusinessLogicService;
    private CLISupport cliSupport;

    /*===========================================[ CONSTRUCTORS ]===============*/

    @Inject
    public CLIApplication(PrimaryBusinessLogicService primaryBusinessLogicService,
                          SecondaryBusinessLogicService secondaryBusinessLogicService,
                          CLISupport cliSupport) {
        this.primaryBusinessLogicService = primaryBusinessLogicService;
        this.secondaryBusinessLogicService = secondaryBusinessLogicService;
        this.cliSupport = cliSupport;
    }

    /*===========================================[ CLASS METHODS ]==============*/

    public void start() throws ServiceException {
        try {
            logger.info("Starting CLI application...");
            primaryBusinessLogicService.start();
            secondaryBusinessLogicService.start();
            cliSupport.listen();
        } catch (Exception e) {
            throw new ServiceException(e);
        } finally {
            logger.info("CLI application started");
        }
    }

    public void stop() {
        logger.info("Stopping CLI application...");
        primaryBusinessLogicService.stop();
        secondaryBusinessLogicService.stop();
        logger.info("Stop done");
    }
}