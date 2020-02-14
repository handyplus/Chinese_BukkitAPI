package org.bukkit.event.inventory;

import org.bukkit.block.Block;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.event.block.BlockEvent;
import org.bukkit.inventory.BrewerInventory;
import org.jetbrains.annotations.NotNull;

/**
 * 当酿造完成时触发这个事件. 
 */
public class BrewEvent extends BlockEvent implements Cancellable {
    private static final HandlerList handlers = new HandlerList();
    private BrewerInventory contents;
	private int fuelLevel;
    private boolean cancelled;

    public BrewEvent(@NotNull Block brewer, @NotNull BrewerInventory contents, int fuelLevel) {
        super(brewer);
        this.contents = contents;
        this.fuelLevel = fuelLevel;
    }

    /**
     * 获取此事件中的酿造台的物品栏. 
     * <p>
     * 原文：Gets the contents of the Brewing Stand.
     *
     * @return 酿造台的物品栏
     */
    @NotNull
    public BrewerInventory getContents() {
        return contents;
    }

	/**
     * Gets the remaining fuel level.
     *
     * @return the remaining fuel
     */
    public int getFuelLevel() {
        return fuelLevel;
    }

    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    @Override
    public void setCancelled(boolean cancel) {
        cancelled = cancel;
    }

    @NotNull
    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    @NotNull
    public static HandlerList getHandlerList() {
        return handlers;
    }
}
