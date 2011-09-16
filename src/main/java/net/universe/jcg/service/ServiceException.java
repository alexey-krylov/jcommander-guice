/****************************************************************************\
 __FILE..........: ServiceException.java
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

import net.universe.jcg.cli.CLIException;

public class ServiceException extends CLIException {

    /*===========================================[ STATIC VARIABLES ]=============*/

    private static final long serialVersionUID = -5757513763778748223L;

    /*===========================================[ CONSTRUCTORS ]===============*/

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public ServiceException(Throwable cause) {
        super(cause);
    }
}