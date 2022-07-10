package main.java.io.prozorowicz;

import java.util.List;

interface CompositeBlock extends Block {
    List<Block> getBlocks();
}