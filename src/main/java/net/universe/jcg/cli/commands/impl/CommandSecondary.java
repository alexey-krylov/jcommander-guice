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
import com.google.inject.Inject;
import net.universe.jcg.cli.commands.Command;
import net.universe.jcg.cli.commands.ExecutionException;
import net.universe.jcg.service.SecondaryBusinessLogicService;
import net.universe.jcg.service.ServiceException;

import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;

@Parameters(commandDescription = "Execute the logic of secondary service")
@Named("do-secondary")
public class CommandSecondary extends Command {

    /*===========================================[ INSTANCE VARIABLES ]=========*/

    @Parameter(description = "Entity identifiers", required = true)
    protected List<String> identifiers = new ArrayList<String>();

    private SecondaryBusinessLogicService secondaryBusinessLogicService;

    /*===========================================[ CONSTRUCTORS ]===============*/

    @Inject
    public CommandSecondary(SecondaryBusinessLogicService secondaryBusinessLogicService) {
        this.secondaryBusinessLogicService = secondaryBusinessLogicService;
    }

    /*===========================================[ CLASS METHODS ]==============*/

    @Override
    public String[] getAliases() {
        return new String[]{"sec", "ds"};
    }

    @Override
    public void execute() throws ExecutionException {
        try {
            secondaryBusinessLogicService.executeSecondaryBusinessLogic(identifiers);
        } catch (ServiceException e) {
            throw new ExecutionException(e);
        }
    }
}