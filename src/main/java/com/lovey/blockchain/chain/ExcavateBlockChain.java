package com.lovey.blockchain.chain;

import com.lovey.blockchain.util.StringUtil;

import java.util.ArrayList;

/**
 * 挖矿
 *
 * @author liwc
 * @version V1.0
 * @date 2018/4/19 11:01
 */
public class ExcavateBlockChain {

    public static ArrayList<Block> blockChain = new ArrayList<Block>();

    public static int difficulty = 5;

    public static void main(String[] args) {

        System.out.println("正在尝试挖掘block 1... ");
        addBlock(new Block("这是第一个block", "0"));

        System.out.println("正在尝试挖掘block 2... ");
        addBlock(new Block("这是第二个block", blockChain.get(blockChain.size() - 1).hash));

        System.out.println("正在尝试挖掘block 3... ");
        addBlock(new Block("这是第三个block", blockChain.get(blockChain.size() - 1).hash));

        System.out.println("区块链状态: " + (isChainValid() ? "正常" : "异常"));

        String blockChainJson = StringUtil.getJson(blockChain);
        System.out.println("区块链: ");
        System.out.println(blockChainJson);
    }

    public static Boolean isChainValid() {

        Block currentBlock;
        Block previousBlock;
        String hashTarget = new String(new char[difficulty]).replace('\0', '0');

        for (int i = 1; i < blockChain.size(); i++) {
            currentBlock = blockChain.get(i);
            previousBlock = blockChain.get(i - 1);
            if (!currentBlock.hash.equals(currentBlock.calculateHash())) {
                System.out.println("此区块当前hash异常");
                return false;
            }
            if (!previousBlock.hash.equals(currentBlock.previousHash)) {
                System.out.println("此区块上一个hash异常");
                return false;
            }
            if (!currentBlock.hash.substring(0, difficulty).equals(hashTarget)) {
                System.out.println("此区块尚未被开采");
                return false;
            }
        }
        return true;
    }

    public static void addBlock(Block newBlock) {
        newBlock.mineBlock(difficulty);
        blockChain.add(newBlock);
    }
}
