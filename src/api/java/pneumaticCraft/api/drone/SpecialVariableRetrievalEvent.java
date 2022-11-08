package pneumaticCraft.api.drone;

import cpw.mods.fml.common.eventhandler.Event;
import net.minecraft.item.ItemStack;
import net.minecraft.world.ChunkPosition;

/**
 * Fired when a Drone is trying to get a special coordinate, by accessing a variable with '$' prefix.
 * These event are posted on the MinecraftForge.EVENT_BUS.
 */
public abstract class SpecialVariableRetrievalEvent extends Event {

    /**
     * The special variable name, with the '$' stripped away.
     */
    public final String specialVarName;

    /**
     * The returning coordinate
     */
    public SpecialVariableRetrievalEvent(String specialVarName) {

        this.specialVarName = specialVarName;
    }

    public abstract static class CoordinateVariable extends SpecialVariableRetrievalEvent {
        public ChunkPosition coordinate;

        public CoordinateVariable(String specialVarName) {
            super(specialVarName);
        }

        public static class Drone extends CoordinateVariable {
            public final IDrone drone;

            public Drone(IDrone drone, String specialVarName) {
                super(specialVarName);
                this.drone = drone;
            }
        }
    }

    public abstract static class ItemVariable extends SpecialVariableRetrievalEvent {
        public ItemStack item;

        public ItemVariable(String specialVarName) {
            super(specialVarName);
        }

        public static class Drone extends ItemVariable {
            public final IDrone drone;

            public Drone(IDrone drone, String specialVarName) {
                super(specialVarName);
                this.drone = drone;
            }
        }
    }
}
