/****************************************************************************\
 __FILE..........: PrimaryBusinessLogicServiceImpl.java
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

import com.google.inject.Inject;
import net.universe.jcg.service.PrimaryBusinessLogicService;
import net.universe.jcg.service.SecondaryBusinessLogicService;
import net.universe.jcg.service.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class PrimaryBusinessLogicServiceImpl implements PrimaryBusinessLogicService {

    /*===========================================[ STATIC VARIABLES ]=============*/

    private static final Logger logger = LoggerFactory.getLogger(PrimaryBusinessLogicServiceImpl.class);

    /*===========================================[ INSTANCE VARIABLES ]=========*/

    @Inject
    private SecondaryBusinessLogicService secondaryBusinessLogicService;

    /*===========================================[ CLASS METHODS ]==============*/

    public void executePrimaryBusinessLogic(long count, String id) throws ServiceException {
        logger.info("Executing primary business logic");
        logger.info(String.format("Count is: %d", count));

        for (long i = 0L; i < count; i++) {
            List<String> ids = new ArrayList<String>();
            ids.add(id);
            secondaryBusinessLogicService.executeSecondaryBusinessLogic(ids);
        }
    }

    public void start() throws ServiceException {
        logger.info("Primary business logic service started");
    }

    public void stop() {
        logger.info("Primary business logic service stopped");
    }
}