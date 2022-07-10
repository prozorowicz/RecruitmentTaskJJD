package main.java.io.prozorowicz;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Wall implements Structure {
    private List<Block> blocks;

    Wall(final List<Block> blocks) {
        this.blocks = blocks;
    }

    /**
     * Implemented methods don't handle case when composite block is a member of itself
     */

    @Override
    public Optional findBlockByColor(String color) {
        if (color == null) {
            throw new IllegalArgumentException("Searched color is null");
        }
        Block result = findBlockByColor(color, blocks);
        if (result == null) {
            return Optional.empty();
        }
        return Optional.of(result);
    }

    public Block findBlockByColor(String color, List<Block> blockList) {
        for (Block block : blockList) {
            if (block.getColor().equals(color)) {
                return block;
            }
            if (block instanceof CompositeBlock) {
                Block innerBlock = findBlockByColor(color, ((CompositeBlock) block).getBlocks());
                if (innerBlock != null) {
                    return innerBlock;
                }
            }
        }
        return null;
    }

    @Override
    public List findBlocksByMaterial(String material) {
        if (material == null) {
            throw new IllegalArgumentException("Searched material is null");
        }
        List<Block> allBlocksByMaterial = new ArrayList<>();

        return findBlocksByMaterial(material, allBlocksByMaterial, blocks);
    }

    public List findBlocksByMaterial(String material, List<Block> allBlocksByMaterial, List<Block> blockList) {
        for (Block block : blockList) {
            if (block.getMaterial().equals(material)) {
                allBlocksByMaterial.add(block);
            }
            if (block instanceof CompositeBlock) {
                findBlocksByMaterial(material, allBlocksByMaterial, ((CompositeBlock) block).getBlocks());
            }
        }
        return allBlocksByMaterial;
    }

    @Override
    public int count() {
        return count(blocks);
    }

    public int count(List<Block> blockList) {
        int count = 0;
        for (Block block : blockList) {
            count++;
            if (block instanceof CompositeBlock) {
                count += count(((CompositeBlock) block).getBlocks());
            }
        }
        return count;
    }
}