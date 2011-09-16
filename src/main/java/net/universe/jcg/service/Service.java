/****************************************************************************\
 __FILE..........: Service.java
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

public interface Service {

    /*===========================================[ INTERFACE METHODS ]==============*/

    void start() throws ServiceException;

    void stop();

}
