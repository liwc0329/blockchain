package com.lovey.blockchain.chain;

import com.lovey.blockchain.util.StringUtil;

/**
 * 区块类
 *
 * @author liwc
 * @version V1.0
 * @date 2018/4/19 10:40
 */
public class Block {

    /**
     * 当前区块的hash
     */
    public String hash;
    /**
     * 上一个区块的hash
     */
    public String previousHash;

    /**
     * 区块的数据
     */
    private String data;

    /**
     * 时间戳
     */
    private long timeStamp;

    /**
     * Number once的缩写,在密码学中Nonce是一个只被使用一次的任意或非重复的随机数值。
     * 这里指比较次数
     */
    private int nonce;

    /**
     * 构造器
     */
    public Block(String data, String previousHash) {
        this.nonce = 0;
        this.data = data;
        this.previousHash = previousHash;
        this.timeStamp = System.currentTimeMillis();
        this.hash = calculateHash();

    }

    /**
     * 计算区块的内容的Hash值
     */
    public String calculateHash() {
        return StringUtil.applySha256(previousHash + Long.toString(timeStamp) + Integer.toString(nonce) + data);
    }

    /**
     * 增加直到达到目标Nonce值哈希。
     */
    public void mineBlock(int difficulty) {
        String target = StringUtil.getDificultyString(difficulty);
        while (!hash.substring(0, difficulty).equals(target)) {
            nonce++;
            hash = calculateHash();
        }
        System.out.println("Block已挖到!!! : " + hash);
    }
}
