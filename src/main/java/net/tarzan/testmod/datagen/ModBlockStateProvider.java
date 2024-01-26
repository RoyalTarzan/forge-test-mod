package net.tarzan.testmod.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.*;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;
import net.tarzan.testmod.TestMod1;
import net.tarzan.testmod.block.ModBlocks;

public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, TestMod1.MODID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        blockWithItem(ModBlocks.ALUMINIUM_BLOCK);
        blockWithItem(ModBlocks.RAW_ALUMINIUM_BLOCK);
        blockWithItem(ModBlocks.RAW_TITANIUM_BLOCK);
        blockWithItem(ModBlocks.TITANIUM_BLOCK);
        blockWithItem(ModBlocks.FERYL_STONE);
        blockWithItem(ModBlocks.SOAP_STONE);

        blockWithItem(ModBlocks.ALUMINIUM_ORE);
        blockWithItem(ModBlocks.DEEPSLATE_ALUMINIUM_ORE);
        blockWithItem(ModBlocks.SOAP_STONE_ALUMINIUM_ORE);
        blockWithItem(ModBlocks.FERYL_STONE_ALUMINIUM_ORE);

        stairsBlock(((StairBlock) ModBlocks.FERYL_STAIRS.get()), blockTexture(ModBlocks.FERYL_STONE.get()));
        slabBlock(((SlabBlock) ModBlocks.FERYL_SLAB.get()), blockTexture(ModBlocks.FERYL_STONE.get()),blockTexture(ModBlocks.FERYL_STONE.get()));
        stairsBlock(((StairBlock) ModBlocks.SOAP_STONE_STAIRS.get()), blockTexture(ModBlocks.SOAP_STONE.get()));
        slabBlock(((SlabBlock) ModBlocks.SOAP_STONE_SLAB.get()), blockTexture(ModBlocks.SOAP_STONE.get()),blockTexture(ModBlocks.SOAP_STONE.get()));

        buttonBlock(((ButtonBlock) ModBlocks.FERYL_BUTTON.get()), blockTexture(ModBlocks.FERYL_STONE.get()));
        pressurePlateBlock(((PressurePlateBlock) ModBlocks.FERYL_PRESSURE_PLATE.get()), blockTexture(ModBlocks.FERYL_STONE.get()));
        buttonBlock(((ButtonBlock) ModBlocks.SOAP_STONE_BUTTON.get()), blockTexture(ModBlocks.SOAP_STONE.get()));
        pressurePlateBlock(((PressurePlateBlock) ModBlocks.SOAP_STONE_PRESSURE_PLATE.get()), blockTexture(ModBlocks.SOAP_STONE.get()));

        wallBlock(((WallBlock) ModBlocks.FERYL_WALL.get()), blockTexture(ModBlocks.FERYL_STONE.get()));
        wallBlock(((WallBlock) ModBlocks.SOAP_STONE_WALL.get()), blockTexture(ModBlocks.SOAP_STONE.get()));

    }

    private void blockWithItem(RegistryObject<Block> blockRegistryObject){
        simpleBlockWithItem(blockRegistryObject.get(),cubeAll(blockRegistryObject.get()));
    }
}