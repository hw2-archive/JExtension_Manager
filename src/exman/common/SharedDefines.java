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
    
    public enum emJava_ver {
        J_15("joomla 1.5"),
        LATEST("joomla 1.6");
        
        private emJava_ver (String ver) {
            this.version = ver;
        }
        
        public String version = null;
    }
    /* string alternative
     * private static String[] java_ver={
        "joomla 1.5", // 0
        "joomla 1.6+" // 1
    };*/
    
    public enum emJava_ext {
        THEMES("Themes"),
        PLUGINS("Plugins"),
        MODULES("Modules"),
        COMPONENTS("Components");
        
        private emJava_ext (String ver) {
            this.type = ver;
        }
        
        public String type = null;
    }
    
    
}
