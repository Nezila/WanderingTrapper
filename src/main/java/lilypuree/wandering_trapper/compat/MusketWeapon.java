package lilypuree.wandering_trapper.compat;

import ewewukek.musketmod.BulletEntity;
import ewewukek.musketmod.Config;
import ewewukek.musketmod.MusketItem;
import ewewukek.musketmod.MusketMod;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;


public class MusketWeapon implements IWeaponSelector {
    @Override
    public Item getWeapon() {
        return MusketMod.MUSKET;
    }

    @Override
    public double getMoveSpeedAmp() {
        return 0.4D;
    }

    @Override
    public int getAttackCooldown() {
        return MusketItem.RELOAD_DURATION;
    }

    @Override
    public int getWeaponLoadTime() {
        return MusketItem.LOADING_STAGE_1 + MusketItem.LOADING_STAGE_2 + MusketItem.LOADING_STAGE_3;
    }

    @Override
    public float getProjectileSpeed(int loadTime) {
        return 1.0F;
    }

    @Override
    public float getMaxAttackDistance() {
        return 120.0F;
    }

    @Override
    public Entity getProjectile(LivingEntity shooter, float distanceFactor) {
        BulletEntity bullet = new BulletEntity(shooter.world);
        bullet.shooterUuid = shooter.getUniqueID();
        bullet.setPosition(shooter.getPosX(), shooter.getPosY() + shooter.getEyeHeight() - 0.1D, shooter.getPosZ());
        return bullet;
    }

    @Override
    public Entity shoot(Entity projectile, double dirX, double dirY, double dirZ, int difficulty) {
        if (projectile instanceof BulletEntity) {
            Vec3d front = new Vec3d(dirX, dirY, dirZ).normalize();
            float angle = 6.2831855F * projectile.world.getRandom().nextFloat();
            float gaussian = Math.abs((float) projectile.world.getRandom().nextGaussian());
            if (gaussian > 4.0F) {
                gaussian = 4.0F;
            }
            double stdDev = Config.INSTANCE.bulletStdDev.get();
            front = front.rotatePitch((float) stdDev * gaussian * MathHelper.sin(angle)).rotateYaw((float) stdDev * gaussian * MathHelper.cos(angle));
            Vec3d motion = front.scale(Config.INSTANCE.bulletSpeed.get());
            ((BulletEntity) projectile).setMotion(motion);
            ((BulletEntity) projectile).doFireParticles = true;
        }
        return projectile;
    }

    @Override
    public SoundEvent getShootSound() {
        return MusketItem.SOUND_MUSKET_FIRE;
    }

    @Override
    public boolean isGun() {
        return true;
    }
}