package sm.lavenderbiome;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;
import net.fabricmc.fabric.api.registry.CompostingChanceRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sm.lavenderbiome.block.ModBlocks;
import sm.lavenderbiome.item.ModItems;
import sm.lavenderbiome.util.CrosspickUsageEvent;
import sm.lavenderbiome.world.gen.ModWorldGeneration;

public class LavenderBiome implements ModInitializer {

    //Mod ID, must be always this one.
	public static final String MOD_ID = "lavenderbiome";

	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.

        //initialize mod items and blocks here
        ModGroups.registerItemGroups();
        ModItems.registerModItems();
        ModBlocks.registerModBlocks();

        //initialize world generation
        ModWorldGeneration.generateModWorldGen();

        //register crosspick event
        PlayerBlockBreakEvents.BEFORE.register(new CrosspickUsageEvent());

        //register composting chances
        CompostingChanceRegistry.INSTANCE.add(ModItems.LAVENDER_SEEDS, 0.3F);
        CompostingChanceRegistry.INSTANCE.add(ModBlocks.LAVENDER, 0.5F);
	}
}