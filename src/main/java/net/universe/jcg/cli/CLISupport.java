/****************************************************************************\
 __FILE..........: CLISupport.java
 __AUTHOR........: lampsound
 __COPYRIGHT.....: Copyright (c) 2011 universe.net
 _________________All rights reserved.
 __VERSION.......: 1.0
 __DESCRIPTION...:
 __HISTORY.......: DATE       COMMENT
 _____________________________________________________________________________
 ________________:01.09.11 lampsound: created
 ****************************************************************************/


package net.universe.jcg.cli;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.MissingCommandException;
import com.beust.jcommander.ParameterException;
import com.google.inject.Inject;
import com.google.inject.Injector;
import jline.ArgumentCompletor;
import jline.Completor;
import jline.ConsoleReader;
import jline.SimpleCompletor;
import net.universe.jcg.cli.commands.Command;
import net.universe.jcg.runtime.Launcher;
import org.apache.commons.lang3.SystemUtils;
import org.fusesource.jansi.Ansi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

import static org.fusesource.jansi.Ansi.Color.GREEN;
import static org.fusesource.jansi.Ansi.ansi;

/**
 * Listens the user input and performs a Command execution.
 *
 * @see Command
 */
public class CLISupport {

    /*===========================================[ STATIC VARIABLES ]=============*/

    private static final Logger logger = LoggerFactory.getLogger(CLISupport.class);
    private static final String COMMAND_PROMPT = "> ";

    /*===========================================[ INSTANCE VARIABLES ]=========*/

    private Executor executor;
    private Injector injector;
    private AtomicBoolean isWorking;

    /*===========================================[ CONSTRUCTORS ]===============*/

    @Inject
    public CLISupport(Injector injector) {
        this.injector = injector;
        executor = Executors.newSingleThreadExecutor();
        isWorking = new AtomicBoolean();
    }

    /*===========================================[ CLASS METHODS ]==============*/

    public void listen() {
        System.out.println(ansi().eraseScreen().a(Ansi.Attribute.INTENSITY_BOLD).fg(GREEN).a("Welcome to ").
                fg(Ansi.Color.RED).a("JCommander-Guice").fg(GREEN).a(" application!").newline().reset());
        injector.getInstance(JCommander.class).usage();
        initCLIListener();
    }

    /**
     * Start in parallel thread - we should release the method listen()
     */
    private void initCLIListener() {
        executor.execute(new CLIListener());
    }

    private class CLIListener implements Runnable {

        private Executor commandExecutor;

        private CLIListener() {
            commandExecutor = Executors.newCachedThreadPool();
        }

        @SuppressWarnings({"MagicNumber"})
        public void run() {
            // Detection for single command launch mode
            if (Launcher.isSingleCommandMode()) {
                execute(Launcher.getCommandLine());

                while (isWorking.get()) {
                    try {
                        TimeUnit.MILLISECONDS.sleep(100L);
                    } catch (InterruptedException e) {
                        logger.error("Error", e);
                    }
                }
                logger.info("Command executed. Exiting...");
                System.exit(0);
            }

            try {
                ConsoleReader reader = createConsoleReader();
                printWelcome();

                while (true) {
                    try {
                        String commandLine = reader.readLine(COMMAND_PROMPT);
                        if (commandLine == null) {
                            return;
                        }

                        if (commandLine.isEmpty()) {
                            printWelcome();
                            continue;
                        }

                        execute(commandLine);
                    } catch (IOException e) {
                        logger.error("Error", e);
                        System.exit(-1);
                    }
                }
            } catch (IOException e) {
                logger.error("Error creating ConsoleReader", e);
            }
        }

        private ConsoleReader createConsoleReader() throws IOException {
            ConsoleReader reader = new ConsoleReader();
            final JCommander jCommander = injector.getInstance(JCommander.class);
            Map<String, JCommander> commands = jCommander.getCommands();
            Set<String> strings = commands.keySet();

            /**
             * Create command completion mechanics
             */
            List<Completor> completors = new ArrayList<Completor>();
            completors.add(new SimpleCompletor(strings.toArray(new String[strings.size()])));
            reader.addCompletor(new ArgumentCompletor(completors));
            return reader;
        }

        private void execute(String commandLine) {
            String sCommand = "";
            try {
                String[] split = commandLine.split(" ");
                if (split.length > 0) {
                    sCommand = split[0];
                    final JCommander jCommander = injector.getInstance(JCommander.class);
                    jCommander.parse(split);

                    String parsedCommand = jCommander.getParsedCommand();
                    List<Object> commands = jCommander.getCommands().get(parsedCommand).getObjects();
                    for (final Object command : commands) {
                        if (command instanceof Command) {
                            isWorking.set(true);

                            // Concurrent command execution model
                            commandExecutor.execute(new Runnable() {
                                public void run() {
                                    Command aCommand = (Command) command;
                                    try {
                                        aCommand.execute();
                                    } catch (Exception e) {
                                        logger.error("Error", e);
                                    } finally {
                                        if (!Launcher.isSingleCommandMode()) {
                                            printWelcome();
                                            System.out.print(COMMAND_PROMPT);
                                            System.out.flush();
                                        }
                                        isWorking.set(false);
                                    }
                                }
                            });
                        }
                    }
                } else {
                    logger.info("command not found");
                }
            } catch (MissingCommandException ignored) {
                logger.info(String.format("%s: command not found", sCommand));
                printWelcome();
            } catch (ParameterException mce) {
                logger.info(String.format("%s: invalid parameters passed [%s]. See usage example 'usage %s'", sCommand, mce.getMessage(), sCommand));
                printWelcome();
            } catch (Exception ignored) {
                logger.info(String.format("%s: invalid parameters passed. See usage example 'usage %s'", sCommand, sCommand));
                printWelcome();
            }
        }

        private void printWelcome() {
            logger.info(SystemUtils.LINE_SEPARATOR);
            System.out.println(ansi().a(Ansi.Attribute.INTENSITY_BOLD).fg(Ansi.Color.GREEN).a("Enter the command:").reset());
        }
    }
}