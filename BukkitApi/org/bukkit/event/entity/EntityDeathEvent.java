package org.bukkit.event.entity;

import java.util.List;
import org.bukkit.damage.DamageSource;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

/**
 * 当一个实体死亡时触发本事件
 */
public class EntityDeathEvent extends EntityEvent {
    private static final HandlerList handlers = new HandlerList();
    private final DamageSource damageSource;
    private final List<ItemStack> drops;
    private int dropExp = 0;

    public EntityDeathEvent(@NotNull final LivingEntity entity, @NotNull DamageSource damageSource, @NotNull final List<ItemStack> drops) {
        this(entity, damageSource, drops, 0);
    }

    public EntityDeathEvent(@NotNull final LivingEntity what, @NotNull DamageSource damageSource, @NotNull final List<ItemStack> drops, final int droppedExp) {
        super(what);
        this.damageSource = damageSource;
        this.drops = drops;
        this.dropExp = droppedExp;
    }

    @NotNull
    @Override
    public LivingEntity getEntity() {
        return (LivingEntity) entity;
    }

    /**
     * 获取造成此实体死亡的伤害来源.
     * <p>
     * 原文:Gets the source of damage which caused the death.
     *
     * @return 造成此实体死亡的伤害来源
     */
    @NotNull
    public DamageSource getDamageSource() {
        return damageSource;
    }

    /**
     * 返回这个死亡的实体掉落的经验数量.
     * <p>
     * 这不表明这个实体有多少经验值,而是它死亡时应该被创建多少掉落的经验值.
     * <p>
     * 原文:
     * Gets how much EXP should be dropped from this death.
     * <p>
     * This does not indicate how much EXP should be taken from the entity in
     * question, merely how much should be created after its death.
     *
     * @return 返回掉落的经验值数量
     */
    public int getDroppedExp() {
        return dropExp;
    }

    /**
     * 设置这个实体死亡所掉落的经验值数量.
     * <p>
     * 这不表明这个实体有多少经验值,而是它死亡时应该被创建多少掉落的经验值.
     * <p>
     * 原文:Sets how much EXP should be dropped from this death.
     * <p>
     * This does not indicate how much EXP should be taken from the entity in
     * question, merely how much should be created after its death.
     *
     * @param exp 掉落的经验值的数量
     */
    public void setDroppedExp(int exp) {
        this.dropExp = exp;
    }

    /**
     * 返回这实体死亡掉落物品的集合.
     * <p>
     * 原文:Gets all the items which will drop when the entity dies
     *
     * @return 死亡时所掉落的所有物品
     */
    @NotNull
    public List<ItemStack> getDrops() {
        return drops;
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