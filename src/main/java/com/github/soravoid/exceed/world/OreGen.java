package com.github.soravoid.exceed.world;

import com.github.soravoid.exceed.Exceed;
import com.github.soravoid.exceed.init.ExceedBlocks;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.placement.CountRangeConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.registries.ForgeRegistries;

@Mod.EventBusSubscriber(modid = Exceed.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class OreGen
{
    @SubscribeEvent
    public static void setupOreGen(FMLCommonSetupEvent e)
    {
        for(Biome biome : ForgeRegistries.BIOMES)
        {
            if(biome.getCategory() == Biome.Category.NETHER)
            {

            }
            else if (biome.getCategory() == Biome.Category.THEEND)
            {

            }
            else
            {
                biome.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.ORE.withConfiguration(
                        new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, ExceedBlocks.TETHERED_ORE.get().getDefaultState(), 8)
                ).withPlacement(Placement.COUNT_RANGE.configure(new CountRangeConfig(3, 0, 0, 10))));
            }
        }
    }
}
