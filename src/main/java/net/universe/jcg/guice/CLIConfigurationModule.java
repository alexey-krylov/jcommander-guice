/****************************************************************************\
 __FILE..........: ServiceModule.java
 __AUTHOR........: lampsound
 __COPYRIGHT.....: Copyright (c) 2011 universe.net
 _________________All rights reserved.
 __VERSION.......: 1.0
 __DESCRIPTION...:
 __HISTORY.......: DATE       COMMENT
 _____________________________________________________________________________
 ________________:24.08.11 lampsound: created
 ****************************************************************************/


package net.universe.jcg.guice;

import com.beust.jcommander.JCommander;
import com.google.inject.AbstractModule;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Provides;
import net.universe.jcg.cli.CLISupport;
import net.universe.jcg.cli.commands.Command;
import net.universe.jcg.cli.commands.impl.*;
import net.universe.jcg.runtime.CLIApplication;
import net.universe.jcg.service.PrimaryBusinessLogicService;
import net.universe.jcg.service.SecondaryBusinessLogicService;
import net.universe.jcg.service.impl.PrimaryBusinessLogicServiceImpl;
import net.universe.jcg.service.impl.SecondaryBusinessLogicServiceImpl;
import org.fusesource.jansi.AnsiConsole;

import java.util.ArrayList;
import java.util.Collection;

@SuppressWarnings({"OverlyCoupledMethod"})
public class CLIConfigurationModule extends AbstractModule {

    /*===========================================[ CLASS METHODS ]==============*/

    @Override
    protected void configure() {
        AnsiConsole.systemInstall();

        bind(PrimaryBusinessLogicService.class).to(PrimaryBusinessLogicServiceImpl.class).asEagerSingleton();
        bind(SecondaryBusinessLogicService.class).to(SecondaryBusinessLogicServiceImpl.class).asEagerSingleton();
        bind(CLISupport.class).asEagerSingleton();

        bind(CLIApplication.class).asEagerSingleton();

        bind(JCommander.class).toProvider(JCommanderProvider.class);

        bind(CommandClearScreen.class);
        bind(CommandExit.class);
        bind(CommandUsage.class);
        bind(CommandPrimary.class);
        bind(CommandSecondary.class);
    }

    @Provides
    @Inject
    public Collection<Command> provideAvailableCommands(Injector injector) {
        Collection<Command> commands = new ArrayList<Command>();
        commands.add(injector.getInstance(CommandClearScreen.class));
        commands.add(injector.getInstance(CommandExit.class));
        commands.add(injector.getInstance(CommandUsage.class));
        commands.add(injector.getInstance(CommandPrimary.class));
        commands.add(injector.getInstance(CommandSecondary.class));
        return commands;
    }
}