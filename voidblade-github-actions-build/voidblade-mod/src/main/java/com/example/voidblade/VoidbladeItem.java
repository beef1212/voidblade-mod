
package com.example.voidblade;

import java.util.List;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class VoidbladeItem extends Item {

    public VoidbladeItem(ToolMaterial material, Settings settings) {
        super(settings.attributeModifiers(
            SwordItem.createAttributeModifiers(material, 7.0F, -2.6F)
        ));
    }

    @Override
    public TypedActionResult<ItemStack> use(
            World world, PlayerEntity user, Hand hand) {

        ItemStack stack = user.getStackInHand(hand);

        if (user.getItemCooldownManager().isCoolingDown(this)) {
            return TypedActionResult.fail(stack);
        }

        user.getItemCooldownManager().set(this, 80);
        user.swingHand(hand, true);

        world.playSound(
            null,
            user.getX(), user.getY(), user.getZ(),
            SoundEvents.ENTITY_PLAYER_ATTACK_SWEEP,
            SoundCategory.PLAYERS,
            1.3F, 0.65F
        );

        Vec3d look = user.getRotationVec(1.0F);
        Vec3d center = user.getEyePos().add(look.multiply(2.7));

        Box area = new Box(
            center.x - 2.0, center.y - 1.5, center.z - 2.0,
            center.x + 2.0, center.y + 1.5, center.z + 2.0
        );

        if (!world.isClient && world instanceof ServerWorld serverWorld) {
            serverWorld.spawnParticles(
                ParticleTypes.SOUL_FIRE_FLAME,
                center.x, center.y, center.z,
                35, 0.9, 0.6, 0.9, 0.08
            );

            serverWorld.spawnParticles(
                ParticleTypes.SWEEP_ATTACK,
                center.x, center.y, center.z,
                8, 1.0, 0.4, 1.0, 0.0
            );

            List<LivingEntity> targets = world.getEntitiesByClass(
                LivingEntity.class,
                area,
                e -> e != user && e.isAlive() && user.canSee(e)
            );

            for (LivingEntity target : targets) {
                Vec3d push = target.getPos()
                    .subtract(user.getPos())
                    .normalize()
                    .multiply(1.45);

                target.damage(
                    user.getDamageSources().playerAttack(user),
                    9.0F
                );

                target.addVelocity(push.x, 0.45, push.z);
                target.velocityModified = true;
            }
        }

        return TypedActionResult.success(stack, world.isClient);
    }

    @Override
    public void appendTooltip(
            ItemStack stack,
            TooltipContext context,
            List<Text> tooltip,
            TooltipType type) {

        tooltip.add(Text.literal("§b우클릭: 공허 파동 §7(재사용 대기 4초)"));
        tooltip.add(Text.literal("§8강력한 베기와 넉백 효과"));
    }
}
