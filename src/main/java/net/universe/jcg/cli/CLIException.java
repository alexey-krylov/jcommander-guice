/****************************************************************************\
 __FILE..........: CLIException.java
 __AUTHOR........: lampsound
 __COPYRIGHT.....: Copyright (c) 2011 universe.net
 _________________All rights reserved.
 __VERSION.......: 1.0
 __DESCRIPTION...:
 __HISTORY.......: DATE       COMMENT
 _____________________________________________________________________________
 ________________:16.09.11 lampsound: created
 ****************************************************************************/


package net.universe.jcg.cli;

public class CLIException extends Exception {

    /*===========================================[ STATIC VARIABLES ]=============*/

    private static final long serialVersionUID = 1888139632436054804L;

    /*===========================================[ CONSTRUCTORS ]===============*/

    public CLIException(String message) {
        super(message);
    }

    public CLIException(String message, Throwable cause) {
        super(message, cause);
    }

    public CLIException(Throwable cause) {
        super(cause);
    }
}
