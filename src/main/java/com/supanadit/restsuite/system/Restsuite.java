package com.supanadit.restsuite.system;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Restsuite {
    public static String workspaceDirectory = ".restsuite";

    public static Path getWorkspaceDirectory() {
        return Paths.get(java.lang.System.getProperty("user.home"), workspaceDirectory);
    }

    public static void createWorkspaceDirectory() {
        Path workspace = getWorkspaceDirectory();
        if (!Files.exists(workspace)) {
            try {
                Files.createDirectories(workspace);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
