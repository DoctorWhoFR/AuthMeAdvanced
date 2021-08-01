package fr.azgin.main;

import me.leoko.advancedgui.utils.Interaction;
import me.leoko.advancedgui.utils.Layout;
import me.leoko.advancedgui.utils.LayoutExtension;
import me.leoko.advancedgui.utils.components.TextComponent;
import me.leoko.advancedgui.utils.events.LayoutLoadEvent;
import org.bukkit.metadata.MetadataValue;

import java.util.List;

public class GuiExtension implements LayoutExtension {

    Layout layout = null;

    StringBuilder final_password = new StringBuilder();
    StringBuilder dollard_password = new StringBuilder();

    @Override
    public void onLayoutLoad(LayoutLoadEvent event) {
        Layout layout = event.getLayout();

        if(layout.getName().equals("authme_login")){
            this.layout = layout;
            setupLoginDigit();
            setupLoginGui();
        }
    }

    public void addToInput(String number, Interaction context){
        Layout _layout = context.getLayout();
        dollard_password.append("*");

        TextComponent comp = (TextComponent) _layout.getComponentTree().locate(GuiData.input_text);
        comp.setText(dollard_password.toString());

        final_password.append(number);
    }

    public void resetInput(Interaction context){

        Layout _layout = context.getLayout();;

        TextComponent comp = (TextComponent) _layout.getComponentTree().locate(GuiData.input_text);
        this.final_password = new StringBuilder();
        this.dollard_password = new StringBuilder();
        comp.setText("");

    }

    public void setupLoginDigit(){

        layout.getComponentTree().locate(GuiData.BTN_0).setClickAction((context, player, primaryTrigger) -> addToInput("0", context));
        layout.getComponentTree().locate(GuiData.BTN_1).setClickAction((context, player, primaryTrigger) -> addToInput("1", context));
        layout.getComponentTree().locate(GuiData.BTN_2).setClickAction((context, player, primaryTrigger) -> addToInput("2", context));
        layout.getComponentTree().locate(GuiData.BTN_3).setClickAction((context, player, primaryTrigger) -> addToInput("3", context));
        layout.getComponentTree().locate(GuiData.BTN_4).setClickAction((context, player, primaryTrigger) -> addToInput("4", context));
        layout.getComponentTree().locate(GuiData.BTN_5).setClickAction((context, player, primaryTrigger) -> addToInput("5", context));
        layout.getComponentTree().locate(GuiData.BTN_6).setClickAction((context, player, primaryTrigger) -> addToInput("6", context));
        layout.getComponentTree().locate(GuiData.BTN_7).setClickAction((context, player, primaryTrigger) -> addToInput("7", context));
        layout.getComponentTree().locate(GuiData.BTN_8).setClickAction((context, player, primaryTrigger) -> addToInput("8", context));
        layout.getComponentTree().locate(GuiData.BTN_9).setClickAction((context, player, primaryTrigger) -> addToInput("9", context));

    }

    public void setupLoginGui(){

        /*
        Setup digit first
         */


        /*
            Valid Bouton
         */
        layout.getComponentTree().locate(GuiData.valid_btn).setClickAction((context, player, primaryTrigger) -> {
            /*
            If player isnt register do register command

             */

            if(player.hasMetadata("authmegui_registered")){
                List<MetadataValue> lists = player.getMetadata("authmegui_registered");
                MetadataValue list_value = lists.get(lists.size() - 1);

                if(list_value.asBoolean()){
                    player.performCommand("login " + final_password.toString());
                } else {
                    player.performCommand("register " + final_password.toString() + " " + final_password);
                }

                this.final_password = new StringBuilder();
                this.dollard_password = new StringBuilder();
            }
        });

        layout.getComponentTree().locate(GuiData.reset_btn).setClickAction((context, player, primaryTrigger) -> resetInput(context));

    }
}
