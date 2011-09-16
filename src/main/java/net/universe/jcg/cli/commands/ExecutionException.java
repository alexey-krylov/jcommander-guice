/****************************************************************************\
 __FILE..........: ExecutionException.java
 __AUTHOR........: lampsound
 __COPYRIGHT.....: Copyright (c) 2011 universe.net
 _________________All rights reserved.
 __VERSION.......: 1.0
 __DESCRIPTION...:
 __HISTORY.......: DATE       COMMENT
 _____________________________________________________________________________
 ________________:16.09.11 lampsound: created
 ****************************************************************************/


package net.universe.jcg.cli.commands;

import net.universe.jcg.cli.CLIException;

public class ExecutionException extends CLIException {

    /*===========================================[ STATIC VARIABLES ]=============*/

    private static final long serialVersionUID = -1584537902430735662L;

    /*===========================================[ CONSTRUCTORS ]===============*/

    public ExecutionException(String message) {
        super(message);
    }

    public ExecutionException(String message, Throwable cause) {
        super(message, cause);
    }

    public ExecutionException(Throwable cause) {
        super(cause);
    }
}