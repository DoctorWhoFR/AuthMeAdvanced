package fr.azgin.main;

import fr.xephi.authme.api.v3.AuthMeApi;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

// This should be the only class that uses AuthMe's AuthMeApi
public class AuthMeHook {
    private AuthMeApi authMeApi;

    // We will see when it's safe to invoke this in the next chapter
    public void initializeHook() {
        authMeApi = AuthMeApi.getInstance();
    }

    public boolean registerPlayer(String name, String password) {
        if (authMeApi != null) { // check that the API is loaded
            return authMeApi.registerPlayer(name, password);
        }
        return false;
    }

    public boolean checkPassword(String name, String password){
        if(authMeApi != null){
            return authMeApi.checkPassword(name, password);
        }
        return false;
    }

    public void forceLogin(String name){
        if(authMeApi != null){
            authMeApi.forceLogin(Bukkit.getPlayer(name));
        }
    }

    public boolean isLogged(Player p){
        if(authMeApi != null){
            return authMeApi.isAuthenticated(p);
        }

        return false;
    }

    public boolean isRegistered(String name){
        if(authMeApi != null){
            return authMeApi.isRegistered(name);
        }
        return false;
    }
}