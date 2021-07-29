package fr.azgin.main;

import fr.xephi.authme.events.FailedLoginEvent;
import fr.xephi.authme.events.LoginEvent;
import me.leoko.advancedgui.manager.GuiItemManager;
import me.leoko.advancedgui.utils.GuiItemInstance;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.persistence.PersistentDataContainer;

import java.util.ArrayList;
import java.util.List;

public class AuthMeListener implements Listener {

    MainClass main = MainClass.getInstance();
    List<ItemStack> _togiveitems = new ArrayList<ItemStack>();

    int counter = 0;

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event){

        AuthMeHook hook = MainClass.authMeHook;
        Player p = event.getPlayer();
        PersistentDataContainer container = p.getPersistentDataContainer();
        p.setMetadata("authmegui_counter", new FixedMetadataValue(main, counter));


        p.setMetadata("authmegui_registered", new FixedMetadataValue(main, hook.isRegistered(p.getName())));


        if(!hook.isLogged(p) || !hook.isRegistered(p.getName())){
            final GuiItemInstance itemInstance = GuiItemManager.getInstance().getItemInstance("authme_login");

            ItemStack in_hand = p.getInventory().getItemInMainHand();
            _togiveitems.add(counter, in_hand);

            main.getLogger().warning("testing");
            p.getInventory().setItemInMainHand(itemInstance.createItem());
        }

        counter++;
    }

    @EventHandler
    public void PlayerLogged(LoginEvent event){
        Player p = event.getPlayer();

        if(p.hasMetadata("authmegui_counter")) {

            List<MetadataValue> _items = p.getMetadata("authmegui_counter");
            MetadataValue _counter = _items.get(_items.size() - 1);

            ItemStack _itemtogive = _togiveitems.get(_counter.asInt());
            p.getInventory().setItemInMainHand(_itemtogive);

            p.removeMetadata("authmegui_counter", main);
        }
    }

    @EventHandler
    public void PlayerFaild(PlayerQuitEvent event){
        Player p = event.getPlayer();

        if(p.hasMetadata("authmegui_counter")) {

            List<MetadataValue> _items = p.getMetadata("authmegui_counter");
            MetadataValue _counter = _items.get(_items.size() - 1);

            ItemStack _itemtogive = _togiveitems.get(_counter.asInt());
            p.getInventory().setItemInMainHand(_itemtogive);

            p.removeMetadata("authmegui_counter", main);
        }
    }


}
