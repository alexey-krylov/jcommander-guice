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

import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;
import com.beust.jcommander.validators.PositiveInteger;
import com.google.inject.Inject;
import net.universe.jcg.cli.commands.Command;
import net.universe.jcg.cli.commands.ExecutionException;
import net.universe.jcg.service.PrimaryBusinessLogicService;
import net.universe.jcg.service.ServiceException;

import javax.inject.Named;

@Parameters(commandDescription = "Execute the logic of primary service")
@Named("do-primary")
public class CommandPrimary extends Command {

    /*===========================================[ INSTANCE VARIABLES ]=========*/

    @Parameter(names = {"-verbose", "-v"}, description = "Verbose mode")
    protected boolean verbose;

    @Parameter(names = {"-id"}, description = "Entity ID", required = true)
    protected String id;

    @Parameter(names = {"-count", "-c"}, validateWith = PositiveInteger.class, description = "Entities count", required = true)
    protected long count;

    private PrimaryBusinessLogicService primaryBusinessLogicService;

    /*===========================================[ CONSTRUCTORS ]===============*/

    @Inject
    public CommandPrimary(PrimaryBusinessLogicService primaryBusinessLogicService) {
        this.primaryBusinessLogicService = primaryBusinessLogicService;
    }

    @Override
    public String[] getAliases() {
        return new String[]{"dp", "primary"};
    }

    /*===========================================[ CLASS METHODS ]==============*/

    @Override
    public void execute() throws ExecutionException {
        try {
            if (verbose) {
                logger.info(String.format("Executing primary business logic with parameters: [count=%d, id=%s]", count, id));
            }

            primaryBusinessLogicService.executePrimaryBusinessLogic(count, id);
        } catch (ServiceException e) {
            throw new ExecutionException(e);
        }
    }
}