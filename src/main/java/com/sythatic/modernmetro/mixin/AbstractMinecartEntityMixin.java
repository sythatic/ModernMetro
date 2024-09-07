package com.sythatic.modernmetro.mixin;

import com.sythatic.modernmetro.ModernMetro;
import com.sythatic.modernmetro.block.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.vehicle.AbstractMinecartEntity;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(AbstractMinecartEntity.class)
public abstract class AbstractMinecartEntityMixin extends Entity {
	@Unique
	private double maxSpeed = 8.0;

	public AbstractMinecartEntityMixin(EntityType<?> entityType, World world) {
		super(entityType, world);
	}

	@Redirect(method = "moveOnRail", at = @At(value = "INVOKE", target = "Lnet/minecraft/block/BlockState;isOf(Lnet/minecraft/block/Block;)Z"))
	private boolean checkForNewPoweredRailTypes(BlockState state, Block block) {
		return state.isIn(ModernMetro.TAG_POWERED_RAILS);
	}

	@Redirect(method = "moveOnRail", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/math/Vec3d;add(DDD)Lnet/minecraft/util/math/Vec3d;", ordinal = 5))
	private Vec3d increaseAccelForNewRails(Vec3d vec, double x, double y, double z) {
		Vec3d newvec = vec.add(x, y, z);
		BlockState blockState = this.getWorld().getBlockState(this.getBlockPos());
		if (blockState.isOf(ModBlocks.IRONRAIL)) {
			return newvec.multiply(10 / 8d);
		} else if (blockState.isOf(ModBlocks.DIAMONDRAIL)) {
			return newvec.multiply(40 / 8d);
		} else if (blockState.isOf(ModBlocks.NETHERITERAIL)) {
			return newvec.multiply(80 / 8d);
		} else if (blockState.isOf(ModBlocks.EMERALDRAIL)) {
			return newvec.multiply((159d + (2d/3d)) / 8d);
		}
		return newvec;
	}

	@Redirect(method = "moveOnRail", at = @At(value = "INVOKE", target = "Ljava/lang/Math;min(DD)D"))
	private double increaseSpeedCap(double a, double b) {
		return Math.min(8.0, b);
	}

	@Redirect(method = "moveOnRail", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/vehicle/AbstractMinecartEntity;getMaxSpeed()D"))
	public double increaseMaxSpeedOnNewRails(AbstractMinecartEntity instance) {
		double speed = maxSpeed;
		BlockState blockState = this.getWorld().getBlockState(this.getBlockPos());
		if (blockState.isOf(ModBlocks.IRONRAIL)) {
			speed = 10.0;
		} else if (blockState.isOf(Blocks.POWERED_RAIL)) {
			speed = 20.0;
		} else if (blockState.isOf(ModBlocks.DIAMONDRAIL)) {
			speed = 40.0;
		} else if (blockState.isOf(ModBlocks.NETHERITERAIL)) {
			speed = 80.0;
		} else if (blockState.isOf(ModBlocks.EMERALDRAIL)) {
			speed = 159.0 + (2d/3d);
		}
		maxSpeed = speed;
		return speed / (this.isTouchingWater() ? 40.0 : 20.0);
	}
}