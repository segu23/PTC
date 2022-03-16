package org.kayteam.ptc.task;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.kayteam.api.scheduler.Task;
import org.kayteam.ptc.PTC;

public class BlockRegenerationTask extends Task {

    private final Block block;
    private int regenerationCooldown;
    private final Material initialBlockMaterial;

    public BlockRegenerationTask(Block block) {
        super(PTC.getPTC(), 20);
        this.block = block;
        this.regenerationCooldown = PTC.getGeneralConfigurations().cooldownMaterials.get(block.getType());
        this.initialBlockMaterial = block.getType();
        this.block.setType(Material.BEDROCK);
        System.out.println(PTC.getGeneralConfigurations().cooldownMaterials);
    }

    @Override
    public void actions() {
        if(regenerationCooldown == 0){
            block.setType(initialBlockMaterial);
        }
        regenerationCooldown--;
    }
}
