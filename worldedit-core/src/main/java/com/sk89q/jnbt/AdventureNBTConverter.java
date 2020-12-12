/*
 * WorldEdit, a Minecraft world manipulation toolkit
 * Copyright (C) sk89q <http://www.sk89q.com>
 * Copyright (C) WorldEdit team and contributors
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package com.sk89q.jnbt;

import net.kyori.adventure.nbt.BinaryTag;
import net.kyori.adventure.nbt.ByteArrayBinaryTag;
import net.kyori.adventure.nbt.ByteBinaryTag;
import net.kyori.adventure.nbt.CompoundBinaryTag;
import net.kyori.adventure.nbt.DoubleBinaryTag;
import net.kyori.adventure.nbt.EndBinaryTag;
import net.kyori.adventure.nbt.FloatBinaryTag;
import net.kyori.adventure.nbt.IntArrayBinaryTag;
import net.kyori.adventure.nbt.IntBinaryTag;
import net.kyori.adventure.nbt.ListBinaryTag;
import net.kyori.adventure.nbt.LongArrayBinaryTag;
import net.kyori.adventure.nbt.LongBinaryTag;
import net.kyori.adventure.nbt.ShortBinaryTag;
import net.kyori.adventure.nbt.StringBinaryTag;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class AdventureNBTConverter {

    private AdventureNBTConverter() {

    }

    public static BinaryTag toAdventure(Tag tag) {
        if (tag instanceof IntArrayTag) {
            return toAdventure((IntArrayTag) tag);
        } else if (tag instanceof ListTag) {
            return toAdventure((ListTag) tag);
        } else if (tag instanceof LongTag) {
            return toAdventure((LongTag) tag);
        } else if (tag instanceof LongArrayTag) {
            return toAdventure((LongArrayTag) tag);
        } else if (tag instanceof StringTag) {
            return toAdventure((StringTag) tag);
        } else if (tag instanceof IntTag) {
            return toAdventure((IntTag) tag);
        } else if (tag instanceof ByteTag) {
            return toAdventure((ByteTag) tag);
        } else if (tag instanceof ByteArrayTag) {
            return toAdventure((ByteArrayTag) tag);
        } else if (tag instanceof CompoundTag) {
            return toAdventure((CompoundTag) tag);
        } else if (tag instanceof FloatTag) {
            return toAdventure((FloatTag) tag);
        } else if (tag instanceof ShortTag) {
            return toAdventure((ShortTag) tag);
        } else if (tag instanceof DoubleTag) {
            return toAdventure((DoubleTag) tag);
        } else {
            throw new IllegalArgumentException("Can't convert tag of type " + tag.getClass().getCanonicalName());
        }
    }

    public static IntArrayBinaryTag toAdventure(IntArrayTag tag) {
        int[] value = tag.getValue();
        return IntArrayBinaryTag.of(Arrays.copyOf(value, value.length));
    }

    public static ListBinaryTag toAdventure(ListTag tag) {
        ListBinaryTag.Builder<BinaryTag> builder = ListBinaryTag.builder();
        for (Tag child : tag.getValue()) {
            if (child instanceof EndTag) {
                continue;
            }
            builder.add(toAdventure(child));
        }
        return builder.build();
    }

    public static LongBinaryTag toAdventure(LongTag tag) {
        return LongBinaryTag.of(tag.getValue());
    }

    public static LongArrayBinaryTag toAdventure(LongArrayTag tag) {
        return LongArrayBinaryTag.of(tag.getValue().clone());
    }

    public static StringBinaryTag toAdventure(StringTag tag) {
        return StringBinaryTag.of(tag.getValue());
    }

    public static IntBinaryTag toAdventure(IntTag tag) {
        return IntBinaryTag.of(tag.getValue());
    }

    public static ByteBinaryTag toAdventure(ByteTag tag) {
        return ByteBinaryTag.of(tag.getValue());
    }

    public static ByteArrayBinaryTag toAdventure(ByteArrayTag tag) {
        return ByteArrayBinaryTag.of(tag.getValue().clone());
    }

    public static CompoundBinaryTag toAdventure(CompoundTag tag) {
        CompoundBinaryTag.Builder builder = CompoundBinaryTag.builder();
        for (Map.Entry<String, Tag> child : tag.getValue().entrySet()) {
            builder.put(child.getKey(), toAdventure(child.getValue()));
        }
        return builder.build();
    }

    public static FloatBinaryTag toAdventure(FloatTag tag) {
        return FloatBinaryTag.of(tag.getValue());
    }

    public static ShortBinaryTag toAdventure(ShortTag tag) {
        return ShortBinaryTag.of(tag.getValue());
    }

    public static DoubleBinaryTag toAdventure(DoubleTag tag) {
        return DoubleBinaryTag.of(tag.getValue());
    }

    public static Tag fromAdventure(BinaryTag other) {
        if (other instanceof IntArrayBinaryTag) {
            return AdventureNBTConverter.fromAdventure((IntArrayBinaryTag) other);
        } else if (other instanceof ListBinaryTag) {
            return AdventureNBTConverter.fromAdventure((ListBinaryTag) other);
        } else if (other instanceof EndBinaryTag) {
            return AdventureNBTConverter.fromAdventure((EndBinaryTag) other);
        } else if (other instanceof LongBinaryTag) {
            return AdventureNBTConverter.fromAdventure((LongBinaryTag) other);
        } else if (other instanceof LongArrayBinaryTag) {
            return AdventureNBTConverter.fromAdventure((LongArrayBinaryTag) other);
        } else if (other instanceof StringBinaryTag) {
            return AdventureNBTConverter.fromAdventure((StringBinaryTag) other);
        } else if (other instanceof IntBinaryTag) {
            return AdventureNBTConverter.fromAdventure((IntBinaryTag) other);
        } else if (other instanceof ByteBinaryTag) {
            return AdventureNBTConverter.fromAdventure((ByteBinaryTag) other);
        } else if (other instanceof ByteArrayBinaryTag) {
            return AdventureNBTConverter.fromAdventure((ByteArrayBinaryTag) other);
        } else if (other instanceof CompoundBinaryTag) {
            return AdventureNBTConverter.fromAdventure((CompoundBinaryTag) other);
        } else if (other instanceof FloatBinaryTag) {
            return AdventureNBTConverter.fromAdventure((FloatBinaryTag) other);
        } else if (other instanceof ShortBinaryTag) {
            return AdventureNBTConverter.fromAdventure((ShortBinaryTag) other);
        } else if (other instanceof DoubleBinaryTag) {
            return fromAdventure((DoubleBinaryTag) other);
        } else {
            throw new IllegalArgumentException("Can't convert other of type " + other.getClass().getCanonicalName());
        }
    }

    public static IntArrayTag fromAdventure(IntArrayBinaryTag other) {
        int[] value = other.value();
        return new IntArrayTag(Arrays.copyOf(value, value.length));
    }

    public static ListTag fromAdventure(ListBinaryTag other) {
        List<Tag> list = new ArrayList<>();
        Class<? extends Tag> listClass = StringTag.class;
        int tags = other.size();
        for (int i = 0; i < tags; i++) {
            Tag child = fromAdventure(other.get(0));
            list.add(child);
            listClass = child.getClass();
        }
        return new ListTag(listClass, list);
    }

    public static EndTag fromAdventure(EndBinaryTag other) {
        return new EndTag();
    }

    public static LongTag fromAdventure(LongBinaryTag other) {
        return new LongTag(other.value());
    }

    public static LongArrayTag fromAdventure(LongArrayBinaryTag other) {
        return new LongArrayTag(other.value().clone());
    }

    public static StringTag fromAdventure(StringBinaryTag other) {
        return new StringTag(other.value());
    }

    public static IntTag fromAdventure(IntBinaryTag other) {
        return new IntTag(other.value());
    }

    public static ByteTag fromAdventure(ByteBinaryTag other) {
        return new ByteTag(other.value());
    }

    public static ByteArrayTag fromAdventure(ByteArrayBinaryTag other) {
        return new ByteArrayTag(other.value().clone());
    }

    public static CompoundTag fromAdventure(CompoundBinaryTag other) {
        Set<String> tags = other.keySet();
        Map<String, Tag> map = new HashMap<>();
        for (String tagName : tags) {
            map.put(tagName, fromAdventure(other.get(tagName)));
        }
        return new CompoundTag(map);
    }

    public static FloatTag fromAdventure(FloatBinaryTag other) {
        return new FloatTag(other.value());
    }

    public static ShortTag fromAdventure(ShortBinaryTag other) {
        return new ShortTag(other.value());
    }

    public static DoubleTag fromAdventure(DoubleBinaryTag other) {
        return new DoubleTag(other.value());
    }

}
