/****************************************************************************\
 __FILE..........: JCommanderProvider.java
 __AUTHOR........: lampsound
 __COPYRIGHT.....: Copyright (c) 2011 universe.net
 _________________All rights reserved.
 __VERSION.......: 1.0
 __DESCRIPTION...:
 __HISTORY.......: DATE       COMMENT
 _____________________________________________________________________________
 ________________:02.09.11 lampsound: created
 ****************************************************************************/


package net.universe.jcg.guice;

import com.beust.jcommander.JCommander;
import com.google.inject.Inject;
import com.google.inject.Provider;
import net.universe.jcg.cli.commands.Command;

import java.util.Collection;

@SuppressWarnings({"MismatchedQueryAndUpdateOfCollection"})
public class JCommanderProvider implements Provider<JCommander> {

    /*===========================================[ INSTANCE VARIABLES ]=========*/

    @Inject
    private Collection<Command> commands;

    /*===========================================[ CLASS METHODS ]==============*/

    /**
     * Constructs the new JCommander instance with all commands.
     *
     * @return
     */
    public JCommander get() {
        JCommander commander = new JCommander();
        for (Command command : commands) {
            addCommand(commander, command);
        }
        return commander;
    }

    /*===========================================[ CLASS METHODS ]==============*/

    private void addCommand(JCommander commander, Command command) {
        commander.addCommand(command.getCommandName(), command, command.getAliases());
    }
}