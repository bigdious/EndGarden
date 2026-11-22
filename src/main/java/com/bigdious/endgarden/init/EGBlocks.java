package com.bigdious.endgarden.init;

import com.bigdious.endgarden.EG;
import com.bigdious.endgarden.blocks.*;
import com.bigdious.endgarden.worldgen.tree.EGTreeGrowers;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Function;
import java.util.function.Supplier;

public class EGBlocks {
	public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(EG.MODID);

	public static final DeferredBlock<Block> WELKIN_GRASS = register("welkin_grass", WelkinGrassBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(Blocks.MOSS_BLOCK).forceSolidOn());
	public static final DeferredBlock<Block> WELKIN_GRASS_BUNDLE = registerWithItem("welkin_grass_bundle", Block::new, () -> BlockBehaviour.Properties.ofFullCopy(Blocks.MOSS_BLOCK).forceSolidOn());
	public static final DeferredBlock<Block> WELKIN_SPROUTS = registerWithItem("welkin_sprouts", NoGrowGrassBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(Blocks.SHORT_GRASS).offsetType(BlockBehaviour.OffsetType.XZ));

	public static final BlockSetType WHISPERIA_SET = new BlockSetType(EG.prefix("whisperia").toString());
	public static final WoodType WHISPERIA_TYPE = WoodType.register(new WoodType(EG.prefix("whisperia").toString(), WHISPERIA_SET));
	public static final DeferredBlock<Block> STRIPPED_WHISPERIA_LOG = registerWithItem("whisperia_log_stripped", ActuallyUseableDirectionalBlock::new, () -> BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_GRAY).ignitedByLava().instrument(NoteBlockInstrument.BASS).strength(2.0F).sound(SoundType.WOOD));
	public static final DeferredBlock<Block> STRIPPED_WHISPERIA_WOOD = registerWithItem("whisperia_wood_stripped", ActuallyUseableDirectionalBlock::new, () -> BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_GRAY).ignitedByLava().instrument(NoteBlockInstrument.BASS).strength(2.0F).sound(SoundType.WOOD));
	public static final DeferredBlock<Block> WHISPERIA_LOG = registerWithItem("whisperia_log", ActuallyUseableDirectionalBlock::new, () -> BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_GRAY).ignitedByLava().instrument(NoteBlockInstrument.BASS).strength(2.0F).sound(SoundType.WOOD));
	public static final DeferredBlock<Block> WHISPERIA_WOOD = registerWithItem("whisperia_wood", ActuallyUseableDirectionalBlock::new, () -> BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_GRAY).ignitedByLava().instrument(NoteBlockInstrument.BASS).strength(2.0F).sound(SoundType.WOOD));
	public static final DeferredBlock<Block> WHISPERIA_PLANKS = registerWithItem("whisperia_planks", Block::new, () -> BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_PURPLE).ignitedByLava().instrument(NoteBlockInstrument.BASS).strength(2.0F, 3.0F).sound(SoundType.WOOD));
	public static final DeferredBlock<StairBlock> WHISPERIA_STAIRS = registerWithItem("whisperia_stairs", properties -> new StairBlock(WHISPERIA_PLANKS.get().defaultBlockState(), properties), () -> BlockBehaviour.Properties.ofFullCopy(WHISPERIA_PLANKS.get()));
	public static final DeferredBlock<SlabBlock> WHISPERIA_SLAB = registerWithItem("whisperia_slab", SlabBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(WHISPERIA_PLANKS.get()));
	public static final DeferredBlock<FenceBlock> WHISPERIA_FENCE = registerWithItem("whisperia_fence", FenceBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(WHISPERIA_PLANKS.get()));
	public static final DeferredBlock<FenceGateBlock> WHISPERIA_FENCE_GATE = registerWithItem("whisperia_fence_gate", properties -> new FenceGateBlock(WHISPERIA_TYPE, properties), () -> BlockBehaviour.Properties.ofFullCopy(WHISPERIA_PLANKS.get()).forceSolidOn());
	public static final DeferredBlock<PressurePlateBlock> WHISPERIA_PRESSURE_PLATE = registerWithItem("whisperia_pressure_plate", properties -> new PressurePlateBlock(WHISPERIA_SET, properties), () -> BlockBehaviour.Properties.ofFullCopy(WHISPERIA_PLANKS.get()).forceSolidOn());
	public static final DeferredBlock<ButtonBlock> WHISPERIA_BUTTON = registerWithItem("whisperia_button", properties -> new ButtonBlock(WHISPERIA_SET, 30, properties), () -> BlockBehaviour.Properties.ofFullCopy(WHISPERIA_PLANKS.get()));
	public static final DeferredBlock<TrapDoorBlock> WHISPERIA_TRAPDOOR = registerWithItem("whisperia_trapdoor", properties -> new TrapDoorBlock(WHISPERIA_SET, properties), () -> BlockBehaviour.Properties.ofFullCopy(WHISPERIA_PLANKS.get()).noOcclusion());
	public static final DeferredBlock<DoorBlock> WHISPERIA_DOOR = register("whisperia_door", properties -> new DoorBlock(WHISPERIA_SET, properties), () -> WHISPERIA_PLANKS.get().properties().noOcclusion());
	public static final DeferredBlock<StandingSignBlock> WHISPERIA_SIGN = register("whisperia_sign", properties -> new StandingSignBlock(WHISPERIA_TYPE,properties), () -> BlockBehaviour.Properties.ofFullCopy(WHISPERIA_PLANKS.get()).noOcclusion().noCollission().forceSolidOn());
	public static final DeferredBlock<WallSignBlock> WHISPERIA_WALL_SIGN = register("whisperia_wall_sign", properties -> new WallSignBlock(WHISPERIA_TYPE, properties), () -> BlockBehaviour.Properties.ofFullCopy(WHISPERIA_PLANKS.get()).noOcclusion().noCollission().forceSolidOn());
	public static final DeferredBlock<CeilingHangingSignBlock> WHISPERIA_HANGING_SIGN = register("whisperia_hanging_sign", properties -> new CeilingHangingSignBlock(WHISPERIA_TYPE, properties), () -> BlockBehaviour.Properties.ofFullCopy(WHISPERIA_PLANKS.get()).noOcclusion().noCollission().forceSolidOn());
	public static final DeferredBlock<WallHangingSignBlock> WHISPERIA_WALL_HANGING_SIGN = register("whisperia_wall_hanging_sign", properties -> new WallHangingSignBlock(WHISPERIA_TYPE, properties), () -> BlockBehaviour.Properties.ofFullCopy(WHISPERIA_PLANKS.get()).noOcclusion().forceSolidOn());

	public static final DeferredBlock<Block> WHISPERIA_SAPLING = registerWithItem("whisperia_sapling", properties -> new SaplingBlock(EGTreeGrowers.WHISPERIA, properties), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SAPLING));
	public static final DeferredBlock<Block> WHISPERIA_LEAVES = registerWithItem("whisperia_leaves", WhisperiaLeavesBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES));
	public static final DeferredBlock<Block> WHISPERIA_HANGINGS = registerWithItem("whisperia_hangings", WhisperiaHangingsBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(Blocks.HANGING_ROOTS).noOcclusion());
	public static final DeferredBlock<Block> GHOST_WHISPERIA_SAPLING = registerWithItem("ghost_whisperia_sapling", properties -> new SaplingBlock(EGTreeGrowers.GHOST_WHISPERIA, properties), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SAPLING));
	public static final DeferredBlock<Block> GHOST_WHISPERIA_LEAVES = registerWithItem("ghost_whisperia_leaves", WhisperiaLeavesBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES));
	public static final DeferredBlock<Block> GHOST_WHISPERIA_HANGINGS = registerWithItem("ghost_whisperia_hangings", WhisperiaHangingsBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(Blocks.HANGING_ROOTS).noOcclusion());

	public static final DeferredBlock<Block> STAR_FERN_TEAL = registerWithItem("star_fern_teal", NoGrowGrassBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(Blocks.LILY_OF_THE_VALLEY).noOcclusion());
	public static final DeferredBlock<Block> STAR_FERN_MAGENTA = registerWithItem("star_fern_magenta", NoGrowGrassBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(Blocks.LILY_OF_THE_VALLEY).noOcclusion());
	public static final DeferredBlock<Block> ORCHESTRAL_BLOOM = registerWithItem("orchestral_bloom", properties -> new OrchestralBloomBlock(MobEffects.LEVITATION, 10, properties), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.LILY_OF_THE_VALLEY).noOcclusion());


	public static <T extends Block> DeferredBlock<T> register(String name, Function<BlockBehaviour.Properties, T> block, Supplier<BlockBehaviour.Properties> properties) {
		return BLOCKS.register(name, () -> block.apply(properties.get()));
	}
	public static <T extends Block> DeferredBlock<T> registerWithItem(String name, Function<BlockBehaviour.Properties, T> block, Supplier<BlockBehaviour.Properties> properties) {
		DeferredBlock<T> ret = BLOCKS.register(name, () -> block.apply(properties.get()));
		EGItems.register(name, itemProps -> new BlockItem(ret.get(), itemProps), Item.Properties::new);
		return ret;
	}
}
