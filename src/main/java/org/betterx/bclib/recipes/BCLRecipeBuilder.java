package org.betterx.bclib.recipes;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.ItemLike;

public class BCLRecipeBuilder {
    public static AlloyingRecipe.Builder alloying(ResourceLocation id, ItemLike output) {
        return AlloyingRecipe.Builder.create(id, output);
    }

    public static AnvilRecipe.Builder anvil(ResourceLocation id) {
        return AnvilRecipe.create(id);
    }

    public static BlastFurnaceRecipe blasting(ResourceLocation id, ItemLike output) {
        return BlastFurnaceRecipe.make(id, output);
    }

    public static GridRecipe crafting(ResourceLocation id, ItemLike output) {
        return BCLRecipeBuilder.crafting(

                id, output);
    }

    public static FurnaceRecipe smelting(ResourceLocation id, ItemLike output) {
        return FurnaceRecipe.make(id, output);
    }

    public static SmithingTableRecipe smithing(ResourceLocation id, ItemLike output) {
        return SmithingTableRecipe.make(id, output);
    }

    public static StoneCutterRecipe stonecutting(ResourceLocation id, ItemLike output) {
        return StoneCutterRecipe.make(id, output);
    }
}
