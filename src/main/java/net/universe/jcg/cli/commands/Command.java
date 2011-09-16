/****************************************************************************\
 __FILE..........: Command.java
 __AUTHOR........: lampsound
 __COPYRIGHT.....: Copyright (c) 2011 universe.net
 _________________All rights reserved.
 __VERSION.......: 1.0
 __DESCRIPTION...:
 __HISTORY.......: DATE       COMMENT
 _____________________________________________________________________________
 ________________:31.08.11 lampsound: created
 ****************************************************************************/


package net.universe.jcg.cli.commands;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Named;

@SuppressWarnings({"AbstractClassExtendsConcreteClass"})
public abstract class Command {

    /*===========================================[ STATIC VARIABLES ]=============*/

    private static final String[] NO_ALIASES = new String[]{};

    /*===========================================[ INSTANCE VARIABLES ]=========*/

    protected Logger logger;

    private String commandName;

    /*===========================================[ CONSTRUCTORS ]===============*/

    protected Command() {
        logger = LoggerFactory.getLogger(getClass());
        commandName = getClass().getAnnotation(Named.class).value();
    }

    /*===========================================[ CLASS METHODS ]==============*/

    public String[] getAliases() {
        return NO_ALIASES;
    }

    public final String getCommandName() {
        return commandName;
    }

    public abstract void execute() throws ExecutionException;
}
