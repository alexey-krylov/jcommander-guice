/****************************************************************************\
 __FILE..........: SendCommand.java
 __AUTHOR........: lampsound
 __COPYRIGHT.....: Copyright (c) 2011 universe.net
 _________________All rights reserved.
 __VERSION.......: 1.0
 __DESCRIPTION...:
 __HISTORY.......: DATE       COMMENT
 _____________________________________________________________________________
 ________________:31.08.11 lampsound: created
 ****************************************************************************/


package net.universe.jcg.cli.commands.impl;

import com.beust.jcommander.Parameters;
import com.google.inject.Inject;
import net.universe.jcg.cli.commands.Command;
import net.universe.jcg.cli.commands.ExecutionException;
import net.universe.jcg.runtime.CLIApplication;
import org.fusesource.jansi.Ansi;

import javax.inject.Named;

import static org.fusesource.jansi.Ansi.ansi;

@Parameters(commandDescription = "Exit from CLI application")
@Named("exit")
public class CommandExit extends Command {

    /*===========================================[ INSTANCE VARIABLES ]=========*/

    @Inject
    private CLIApplication application;

    /*===========================================[ CLASS METHODS ]==============*/

    @Override
    public String[] getAliases() {
        return new String[]{"quit", "stop", "x", "q"};
    }

    @Override
    public void execute() throws ExecutionException {
        logger.info("User requested exit...");
        // Stopping the application
        application.stop();

        System.out.println(ansi().a(Ansi.Attribute.INTENSITY_BOLD).fg(Ansi.Color.WHITE).a("Good bye!").reset());
        System.exit(0);
    }
}
