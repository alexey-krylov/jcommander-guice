/****************************************************************************\
 __FILE..........: SecondaryBusinessLogicServiceImpl.java
 __AUTHOR........: lampsound
 __COPYRIGHT.....: Copyright (c) 2011 universe.net
 _________________All rights reserved.
 __VERSION.......: 1.0
 __DESCRIPTION...:
 __HISTORY.......: DATE       COMMENT
 _____________________________________________________________________________
 ________________:16.09.11 lampsound: created
 ****************************************************************************/


package net.universe.jcg.service.impl;

import net.universe.jcg.service.SecondaryBusinessLogicService;
import net.universe.jcg.service.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class SecondaryBusinessLogicServiceImpl implements SecondaryBusinessLogicService {

    /*===========================================[ STATIC VARIABLES ]=============*/

    private static final Logger logger = LoggerFactory.getLogger(SecondaryBusinessLogicServiceImpl.class);

    /*===========================================[ CLASS METHODS ]==============*/

    public void executeSecondaryBusinessLogic(List<String> identifiers) throws ServiceException {
        logger.info("Executing secondary business logic");
        for (String identifier : identifiers) {
            logger.info(String.format("Inbound entity ID: %s", identifier));
        }
    }

    public void start() throws ServiceException {
        logger.info("Secondary business logic service started");
    }

    public void stop() {
        logger.info("Secondary business logic service stopped");
    }
}
