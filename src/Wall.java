import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Wall implements Structure {
    private List<Block> blocks;

    private List<Block> findAllBlocksInCompositeBlocks() {
        List<Block> blocksInCompositeBlocks = new ArrayList<>();
        List<CompositeBlock> compositeBlocks = findBlocksByMaterial("composite");
        for (CompositeBlock compositeBlock : compositeBlocks) {
            blocksInCompositeBlocks.addAll(compositeBlock.getBlocks());
        }
        return blocksInCompositeBlocks;
    }

    @Override
    public Optional findBlockByColor(final String color) {
        Optional blockByColor = blocks.stream().filter(block -> block.getColor() == color).findFirst();
        if (blockByColor.isEmpty()) {
            blockByColor = findAllBlocksInCompositeBlocks().stream()
                    .filter(block -> block.getColor() == color).findFirst();
        }
        return blockByColor;
    }

    @Override
    public List findBlocksByMaterial(final String material) {
        List<Block> allBlocksByMaterial = blocks.stream()
                .filter(block -> block.getMaterial() == material).collect(Collectors.toList());
        if (!material.equals("composite")) {
            allBlocksByMaterial.addAll(findAllBlocksInCompositeBlocks().stream()
                    .filter(block -> block.getMaterial() == material).collect(Collectors.toList()));
        }
        return allBlocksByMaterial;
    }

    @Override
    public int count() {
        int blocksInCompositeBlocks = findAllBlocksInCompositeBlocks().size();
        int nonCompositeBlockCount = blocks.size() - findBlocksByMaterial("composite").size();
        return nonCompositeBlockCount + blocksInCompositeBlocks;
    }
}
