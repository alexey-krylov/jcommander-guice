/****************************************************************************\
 __FILE..........: Launcher.java
 __AUTHOR........: lampsound
 __COPYRIGHT.....: Copyright (c) 2011 universe.net
 _________________All rights reserved.
 __VERSION.......: 1.0
 __DESCRIPTION...:
 __HISTORY.......: DATE       COMMENT
 _____________________________________________________________________________
 ________________:25.08.11 lampsound: created
 ****************************************************************************/


package net.universe.jcg.runtime;

import com.google.inject.Guice;
import net.universe.jcg.guice.CLIConfigurationModule;
import net.universe.jcg.service.ServiceException;

import java.util.Arrays;
import java.util.List;

public class Launcher {

    /*===========================================[ STATIC VARIABLES ]=============*/

    private static final String P_SINGLE_COMMAND = "cli-app.single-command";
    private static final String PFX_EXEC = "-e";

    /*===========================================[ CONSTRUCTORS ]===============*/

    private Launcher() {
    }

    /*===========================================[ CLASS METHODS ]==============*/

    public static void main(String[] args) throws ServiceException {
        if (args != null && args.length > 0) {
            List<String> sArgs = Arrays.asList(args);
            int singleCommandStartIndex = sArgs.indexOf(PFX_EXEC);
            if (singleCommandStartIndex != -1) {
                StringBuilder sb = new StringBuilder();
                for (int i = singleCommandStartIndex + 1; i < args.length; i++) {
                    sb.append(args[i]);
                    if (i != args.length - 1) {
                        sb.append(" ");
                    }
                }

                System.setProperty(P_SINGLE_COMMAND, sb.toString());
            }
        }

        Guice.createInjector(new CLIConfigurationModule()).getInstance(CLIApplication.class).start();
    }

    public static boolean isSingleCommandMode() {
        return getCommandLine() != null;
    }

    public static String getCommandLine() {
        return System.getProperty(P_SINGLE_COMMAND);
    }
}