package com.bigdious.endgarden.entities;

import com.bigdious.endgarden.init.EGBlocks;
import com.bigdious.endgarden.init.EGEntities;
import com.bigdious.endgarden.init.EGItems;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;

public class EGBoat extends Boat {

	private static final EntityDataAccessor<Integer> BOAT_TYPE = SynchedEntityData.defineId(EGBoat.class, EntityDataSerializers.INT);

	public EGBoat(EntityType<? extends Boat> type, Level level) {
		super(type, level);
		this.blocksBuilding = true;
	}

	public EGBoat(Level level, double x, double y, double z) {
		this(EGEntities.WHISPERIA_BOAT.get(), level);
		this.setPos(x, y, z);
		this.xo = x;
		this.yo = y;
		this.zo = z;
	}

	public EGBoat.Type getEGBoatType() {
		return EGBoat.Type.byId(this.getEntityData().get(BOAT_TYPE));
	}

	@Override
	public Item getDropItem() {
		return switch (this.getEGBoatType()) {
			case WHISPERIA -> EGItems.WHISPERIA_BOAT.get();
		};
	}

	public void setEGBoatType(EGBoat.Type boatType) {
		this.getEntityData().set(BOAT_TYPE, boatType.ordinal());
	}
	@Override
	protected void defineSynchedData(SynchedEntityData.Builder builder) {
		super.defineSynchedData(builder);
		builder.define(BOAT_TYPE, Type.WHISPERIA.ordinal());
	}

	@Override
	protected void addAdditionalSaveData(CompoundTag compound) {
		compound.putString("Type", this.getEGBoatType().getName());
	}

	@Override
	protected void readAdditionalSaveData(CompoundTag compound) {
		if (compound.contains("Type", 8)) {
			this.setEGBoatType(EGBoat.Type.getTypeFromString(compound.getString("Type")));
		}
	}


	public enum Type {
		WHISPERIA(EGBlocks.WHISPERIA_PLANKS.get(), "whisperia");

		private final String name;
		private final Block block;

		Type(Block block, String name) {
			this.name = name;
			this.block = block;
		}

		public String getName() {
			return this.name;
		}

		public Block asPlank() {
			return this.block;
		}

		public String toString() {
			return this.name;
		}

		public static EGBoat.Type byId(int id) {
			EGBoat.Type[] aEGBoatEntity$type = values();
			if (id < 0 || id >= aEGBoatEntity$type.length) {
				id = 0;
			}

			return aEGBoatEntity$type[id];
		}

		public static EGBoat.Type getTypeFromString(String nameIn) {
			EGBoat.Type[] boatTypeArray = values();

			for (Type type : boatTypeArray) {
				if (type.getName().equals(nameIn)) {
					return type;
				}
			}

			return boatTypeArray[0];
		}
	}
}
