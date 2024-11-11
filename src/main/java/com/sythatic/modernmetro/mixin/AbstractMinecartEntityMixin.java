package com.sythatic.modernmetro.mixin;

import com.sythatic.modernmetro.block.AcceleratorRailBlock;
import com.sythatic.modernmetro.block.PowerRailBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.vehicle.AbstractMinecartEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(AbstractMinecartEntity.class)
public abstract class AbstractMinecartEntityMixin extends Entity {

	@Unique
	private double maxSpeed = 2.0;

	public AbstractMinecartEntityMixin(EntityType<?> entityType, World world) {
		super(entityType, world);
	}

	@Redirect(method = "moveOnRail", at = @At(value = "INVOKE", target = "Lnet/minecraft/block/BlockState;isOf(Lnet/minecraft/block/Block;)Z"))
	private boolean checkRailTypes(BlockState state, Block block) {
		return state.isIn(com.sythatic.modernmetro.ModernMetro.ALL_RAILS);
	}

	@Redirect(method = "moveOnRail", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/math/Vec3d;add(DDD)Lnet/minecraft/util/math/Vec3d;", ordinal = 5))
	private Vec3d modifyRailAcceleration(Vec3d vec, double x, double y, double z) {
		Vec3d newvec = vec.add(x, y, z);
		BlockState blockState = this.getWorld().getBlockState(this.getBlockPos());
		if (blockState.isOf(AcceleratorRailBlock.ACCELERATORRAIL)) {
			return newvec.multiply(2 / 8d);
		} else if (blockState.isOf(PowerRailBlock.POWERRAIL1)) {
			return newvec.multiply(8 / 8d);
		} else if (blockState.isOf(PowerRailBlock.POWERRAIL2)) {
			return newvec.multiply(16 / 8d);
		} else if (blockState.isOf(PowerRailBlock.POWERRAIL3)) {
			return newvec.multiply(32 / 8d);
		} else if (blockState.isOf(PowerRailBlock.POWERRAIL4)) {
			return newvec.multiply(64 / 8d);
		} else if (blockState.isOf(PowerRailBlock.POWERRAIL5)) {
			return newvec.multiply(128d / 8d);
		}
		return newvec;
	}

	@Redirect(method = "moveOnRail", at = @At(value = "INVOKE", target = "Ljava/lang/Math;min(DD)D"))
	private double modifyRailSpeedCap(double a, double b) {
		return Math.min(2.0, b);
	}

	@Redirect(method = "moveOnRail", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/vehicle/AbstractMinecartEntity;getMaxSpeed()D"))
	public double modifyRailMaxSpeed(AbstractMinecartEntity instance) {
		double speed = maxSpeed;
		BlockState blockState = this.getWorld().getBlockState(this.getBlockPos());
		if (blockState.isOf(AcceleratorRailBlock.ACCELERATORRAIL)) {
			speed = 2.0;
		} else if (blockState.isOf(Blocks.POWERED_RAIL)) {
			speed = 4.0;
		} else if (blockState.isOf(PowerRailBlock.POWERRAIL1)) {
			speed = 8.0;
		} else if (blockState.isOf(PowerRailBlock.POWERRAIL2)) {
			speed = 16.0;
		} else if (blockState.isOf(PowerRailBlock.POWERRAIL3)) {
			speed = 32.0;
		} else if (blockState.isOf(PowerRailBlock.POWERRAIL4)) {
			speed = 64.0;
		} else if (blockState.isOf(PowerRailBlock.POWERRAIL5)) {
			speed = 128.0;
		}
		maxSpeed = speed;
		return speed / (this.isTouchingWater() ? 16.0 : 8.0);
	}

	@Inject(method = "moveOnRail", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/vehicle/AbstractMinecartEntity;getVelocity()Lnet/minecraft/util/math/Vec3d;", shift = At.Shift.AFTER, ordinal = 9), cancellable = true, require = 1)
	private void injectRailRecall(BlockPos pos, BlockState state, CallbackInfo ci) {
		if(state.isOf(AcceleratorRailBlock.ACCELERATORRAIL)){
			((AcceleratorRailBlock)state.getBlock()).affectMinecart((AbstractMinecartEntity)(Object)this, state);
			ci.cancel();
		}
	}

}