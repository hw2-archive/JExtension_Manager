/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package exman.common;

/**
 *
 * @author giuseppe
 */
public class SharedDefines {

    private static final String sqlInstFileName = "install_mod.sql";
    private static final String sqlUninstFileName = "uninstall_mod.sql";

    public static String getSqlInstFileName() {
        return sqlInstFileName;
    }

    public static String getSqlUninstFileName() {
        return sqlUninstFileName;
    }

    public enum emJava_ver {

        J_15("1.5.0", true),
        LATEST("1.6.0", false);

        private emJava_ver(String ver, boolean extPrefix) {
            this.version = ver;
            this.extPrefix = extPrefix;
        }
        private String version = null;
        private boolean extPrefix;

        public boolean hasExtPrefix() {
            return extPrefix;
        }

        public String getVersion() {
            return version;
        }
    }
    /* string alternative
     * private static String[] java_ver={
    "joomla 1.5", // 0
    "joomla 1.6+" // 1
    };*/

    public enum emJava_ext {

        template("templates", ""),
        plugin("plugins", ""),
        module("modules", "mod_"),
        component("components", "com_");

        private emJava_ext(String folder, String prefix) {
            this.folder = folder;
            this.prefix = prefix;
        }
        private String folder = null;
        private String prefix = null;

        public String getPrefix() {
            return prefix;
        }

        public String getFolder() {
            return folder;
        }
    }
}
