package com.sythatic.modernmetro.mixin;

import com.sythatic.modernmetro.block.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
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

	private double getSpeedForRail(BlockState blockState) {
		if (blockState.isOf(ModBlocks.POWERRAIL1)) return 8.0;
		if (blockState.isOf(ModBlocks.POWERRAIL2)) return 20.0;
		if (blockState.isOf(ModBlocks.POWERRAIL3)) return 40.0;
		if (blockState.isOf(ModBlocks.POWERRAIL4)) return 80.0;
		if (blockState.isOf(ModBlocks.POWERRAIL5)) return 120.0;
		return 4.0;
	}

	@Redirect(method = "moveOnRail", at = @At(value = "INVOKE", target = "Lnet/minecraft/block/BlockState;isOf(Lnet/minecraft/block/Block;)Z"))
	private boolean checkForNewPoweredRailTypes(BlockState state, Block block) {
		return state.isIn(com.sythatic.modernmetro.ModernMetro.TAG_POWERED_RAILS);
	}

	@Redirect(method = "moveOnRail", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/math/Vec3d;add(DDD)Lnet/minecraft/util/math/Vec3d;", ordinal = 5))
	private Vec3d increaseAccelForNewRails(Vec3d vec, double x, double y, double z) {
		Vec3d newvec = vec.add(x, y, z);
		BlockState blockState = this.getWorld().getBlockState(this.getBlockPos());
		return newvec.multiply(getSpeedForRail(blockState) / 8d);
	}

	@Redirect(method = "moveOnRail", at = @At(value = "INVOKE", target = "Ljava/lang/Math;min(DD)D"))
	private double increaseSpeedCap(double a, double b) {
		return Math.min(8.0, b);
	}

	@Redirect(method = "moveOnRail", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/vehicle/AbstractMinecartEntity;getMaxSpeed()D"))
	public double increaseMaxSpeedOnNewRails(AbstractMinecartEntity instance) {
		BlockState blockState = this.getWorld().getBlockState(this.getBlockPos());
		maxSpeed = getSpeedForRail(blockState);
		return maxSpeed / (this.isTouchingWater() ? 40.0 : 20.0);
	}
}