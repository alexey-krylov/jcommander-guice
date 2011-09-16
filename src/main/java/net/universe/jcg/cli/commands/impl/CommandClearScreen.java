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
import net.universe.jcg.cli.commands.Command;
import net.universe.jcg.cli.commands.ExecutionException;

import javax.inject.Named;

import static org.fusesource.jansi.Ansi.ansi;

@Parameters(commandDescription = "Clear the screen")
@Named("cls")
public class CommandClearScreen extends Command {

    /*===========================================[ CLASS METHODS ]==============*/

    @Override
    public String[] getAliases() {
        return new String[]{"clear"};
    }

    @Override
    public void execute() throws ExecutionException {
        System.out.println(ansi().eraseScreen());
    }
}
