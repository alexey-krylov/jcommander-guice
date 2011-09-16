/****************************************************************************\
 __FILE..........: SecondaryBusinessLogicService.java
 __AUTHOR........: lampsound
 __COPYRIGHT.....: Copyright (c) 2011 universe.net
 _________________All rights reserved.
 __VERSION.......: 1.0
 __DESCRIPTION...:
 __HISTORY.......: DATE       COMMENT
 _____________________________________________________________________________
 ________________:16.09.11 lampsound: created
 ****************************************************************************/


package net.universe.jcg.service;

import java.util.List;

public interface SecondaryBusinessLogicService extends Service {

    /*===========================================[ INTERFACE METHODS ]==============*/

    void executeSecondaryBusinessLogic(List<String> identifiers) throws ServiceException;
}