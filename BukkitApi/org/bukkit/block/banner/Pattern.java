package org.bukkit.block.banner;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableMap;
import java.util.Map;
import java.util.NoSuchElementException;
import org.bukkit.Bukkit;
import org.bukkit.DyeColor;
import org.bukkit.NamespacedKey;
import org.bukkit.Registry;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.configuration.serialization.SerializableAs;
import org.jetbrains.annotations.NotNull;

@SerializableAs("Pattern")
public class Pattern implements ConfigurationSerializable {

    private static final String COLOR = "color";
    private static final String PATTERN = "pattern";

    private final DyeColor color;
    private final PatternType pattern;

    /**
     * 从指定的颜色和图案类型创建一个新的图案.
     * <p>
     * 原文:
     * Creates a new pattern from the specified color and
     * pattern type
     *
     * @param color   图案的颜色
     * @param pattern 图案的类型
     */
    public Pattern(@NotNull DyeColor color, @NotNull PatternType pattern) {
        this.color = color;
        this.pattern = pattern;
    }

    /**
     * 反序列化构造函数.
     * <p>
     * 原文:
     * Constructor for deserialization.
     *
     * @param map 从 Map 中反序列化
     */
    public Pattern(@NotNull Map<String, Object> map) {
        color = DyeColor.legacyValueOf(getString(map, COLOR));

        String value = getString(map, PATTERN);
        PatternType patternType = PatternType.getByIdentifier(value);

        if (patternType == null) {
            patternType = Bukkit.getUnsafe().get(Registry.BANNER_PATTERN, NamespacedKey.fromString(value));
        }

        Preconditions.checkNotNull(patternType, "Pattern type for key %s cannot be null", value);

        pattern = patternType;
    }

    private static String getString(@NotNull Map<?, ?> map, @NotNull Object key) {
        Object str = map.get(key);
        if (str instanceof String) {
            return (String) str;
        }
        throw new NoSuchElementException(map + " does not contain " + key);
    }

    @NotNull
    @Override
    public Map<String, Object> serialize() {
        return ImmutableMap.<String, Object>of(
            COLOR, color.toString(),
            PATTERN, pattern.getKey().toString()
        );
    }

    /**
     * 返回图案的颜色.
     * <p>
     * 原文:
     * Returns the color of the pattern
     *
     * @return 图案的颜色
     */
    @NotNull
    public DyeColor getColor() {
        return color;
    }

    /**
     * 返回图案的类型.
     * <p>
     * 原文:
     * Returns the type of pattern
     *
     * @return 图案的类型
     */
    @NotNull
    public PatternType getPattern() {
        return pattern;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + (this.color != null ? this.color.hashCode() : 0);
        hash = 97 * hash + (this.pattern != null ? this.pattern.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Pattern other = (Pattern) obj;
        return this.color == other.color && this.pattern == other.pattern;
    }
}