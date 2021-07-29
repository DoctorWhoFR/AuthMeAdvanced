package fr.azgin.main;

import me.leoko.advancedgui.manager.LayoutManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class MainClass extends JavaPlugin {

    public static AuthMeHook authMeHook = null;
    private static MainClass instance;

    public void onEnable() {

        authMeHook = new AuthMeHook();

        // other initializations...
        instance = this;

        if (getServer().getPluginManager().isPluginEnabled("AuthMe")) {
            // it's safe to get AuthMe's AuthMeApi instance, and so forth...
            getServer().getPluginManager().registerEvents(new AuthMeListener(), this);
            authMeHook.initializeHook();
        }

        if(getServer().getPluginManager().isPluginEnabled("AdvancedGUI")){
            LayoutManager.getInstance().registerLayoutExtension(new GuiExtension(), this);
        }

        Bukkit.getPluginManager().registerEvents(new AuthMeListener(), this);
    }

    public static MainClass getInstance() {
        return instance;
    }


}
