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

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.beust.jcommander.Parameters;
import com.google.inject.Inject;
import com.google.inject.Provider;
import net.universe.jcg.cli.commands.Command;
import net.universe.jcg.cli.commands.ExecutionException;
import org.fusesource.jansi.Ansi;

import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;

import static org.fusesource.jansi.Ansi.ansi;

@Parameters(commandDescription = "Print command usage help")
@Named("usage")
public class CommandUsage extends Command {

    /*===========================================[ STATIC VARIABLES ]=============*/

    private static final String[] EXAMPLES = {"do-primary -id 42 -count 100500",
            "dp -id 43 -c 100",
            "do-secondary 8248329894 4395349542 123443210",
            "ds 8248329894 4395349542 123443210",
            "usage do-primary"};

    /*========================================[ INSTANCE VARIABLES ]=============*/

    @Parameter(description = "Command names")
    protected List<String> commandNames = new ArrayList<String>();
    private Provider<JCommander> jCommanderProvider;

    /*===========================================[ CONSTRUCTORS ]===============*/

    @Inject
    public CommandUsage(Provider<JCommander> jCommanderProvider) {
        this.jCommanderProvider = jCommanderProvider;
    }

    /*===========================================[ CLASS METHODS ]==============*/

    @Override
    public String[] getAliases() {
        return new String[]{"help"};
    }

    @Override
    public void execute() throws ExecutionException {
        JCommander jCommander = jCommanderProvider.get();
        if (commandNames.isEmpty()) {
            jCommander.usage();
            System.out.println(ansi().a(Ansi.Attribute.INTENSITY_BOLD).fg(Ansi.Color.YELLOW).a("Call Examples:").reset());
            for (String example : EXAMPLES) {
                System.out.println(ansi().fg(Ansi.Color.GREEN).a("* " + example).reset());
            }
        } else {
            for (String commandName : commandNames) {
                try {
                    jCommander.getCommandDescription(commandName);
                    jCommander.usage(commandName);
                } catch (ParameterException ignored) {
                    logger.info(String.format("%s: no usage help. Command unknown!", commandName));
                }
            }
        }
    }
}