package net.tarzan.world_depth.item.custom;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.tarzan.world_depth.item.ModArmorMaterials;

import java.util.Map;

public class ModArmorItem extends ArmorItem {
    private static final Multimap<ArmorMaterial, MobEffectInstance> MATERIAL_TO_EFFECT_MAP;
            static {Multimap<ArmorMaterial, MobEffectInstance> map = ArrayListMultimap.create();
                map.put(ModArmorMaterials.ENERGIZED_ALUMINIUM, new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 1,
                        3, false, false, false));
                map.put(ModArmorMaterials.ENERGIZED_ALUMINIUM, new MobEffectInstance(MobEffects.JUMP, 1, 4,
                        false, false, false));
                map.put(ModArmorMaterials.ENERGIZED_TITANIUM, new MobEffectInstance(MobEffects.DAMAGE_BOOST, 1, 3,
                        false, false, false));
                map.put(ModArmorMaterials.ENERGIZED_TITANIUM, new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 1, 2,
                        false, false, false));
                map.put(ModArmorMaterials.TALIUM, new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 1, 1,
                        false, false, false));
                map.put(ModArmorMaterials.TALIUM, new MobEffectInstance(MobEffects.JUMP, 1, 2,
                        false, false, false));
                map.put(ModArmorMaterials.TALIUM, new MobEffectInstance(MobEffects.DAMAGE_BOOST, 1, 1,
                        false, false, false));
                map.put(ModArmorMaterials.TALIUM, new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 1, 11,
                        false, false, false));
                MATERIAL_TO_EFFECT_MAP = map;
            }

    public ModArmorItem(ArmorMaterial pMaterial, Type pType, Properties pProperties) {
        super(pMaterial, pType, pProperties);
    }

    public void onArmorTick(ItemStack stack, Level world, Player player) {
        if(!world.isClientSide()) {
            if(hasFullSuitOfArmorOn(player)) {
                evaluateArmorEffects(player);
            }
        }
    }

    private void evaluateArmorEffects(Player player) {
        for (Map.Entry<ArmorMaterial, MobEffectInstance> entry : MATERIAL_TO_EFFECT_MAP.entries()) {
            ArmorMaterial mapArmorMaterial = entry.getKey();
            MobEffectInstance mapStatusEffect = entry.getValue();

            if(hasCorrectArmorOn(mapArmorMaterial, player)) {
                addStatusEffectForMaterial(player, mapArmorMaterial, mapStatusEffect);
            }
        }
    }

    private void addStatusEffectForMaterial(Player player, ArmorMaterial mapArmorMaterial,
                                            MobEffectInstance mapStatusEffect) {
        boolean hasPlayerEffect = player.hasEffect(mapStatusEffect.getEffect());

        if(hasCorrectArmorOn(mapArmorMaterial, player) && !hasPlayerEffect) {
            player.addEffect(new MobEffectInstance(mapStatusEffect));
        }
    }

    private boolean hasFullSuitOfArmorOn(Player player) {
        ItemStack boots = player.getInventory().getArmor(0);
        ItemStack leggings = player.getInventory().getArmor(1);
        ItemStack breastplate = player.getInventory().getArmor(2);
        ItemStack helmet = player.getInventory().getArmor(3);

        return !helmet.isEmpty() && !breastplate.isEmpty()
                && !leggings.isEmpty() && !boots.isEmpty();
    }

    private boolean hasCorrectArmorOn(ArmorMaterial material, Player player) {
        for (ItemStack armorStack : player.getInventory().armor) {
            if(!(armorStack.getItem() instanceof ArmorItem)) {
                return false;
            }
        }

        ArmorItem boots = ((ArmorItem)player.getInventory().getArmor(0).getItem());
        ArmorItem leggings = ((ArmorItem)player.getInventory().getArmor(1).getItem());
        ArmorItem breastplate = ((ArmorItem)player.getInventory().getArmor(2).getItem());
        ArmorItem helmet = ((ArmorItem)player.getInventory().getArmor(3).getItem());

        return helmet.getMaterial() == material && breastplate.getMaterial() == material &&
                leggings.getMaterial() == material && boots.getMaterial() == material;
    }
}
