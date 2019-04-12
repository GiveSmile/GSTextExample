package com.hs.administrator.test.utils.BuilderPattern;

import com.hs.administrator.test.utils.BuilderPattern.BUilder;
import com.hs.administrator.test.utils.BuilderPattern.Character;

/**
 * @auther : yanbin
 * @time : 2018/11/1 0001 16:57
 * @describe :创造者模式 - 创造Builder接口实现类
 */

public class ConcreteBuilder implements BUilder {
    private Character mCharacter = new Character();

    @Override
    public void setSex(String sex) {
        mCharacter.setSex(sex);
    }

    @Override
    public void setFace(String face) {
        mCharacter.setFace(face);
    }

    @Override
    public void setClothes(String clothes) {
        mCharacter.setClothes(clothes);
    }

    @Override
    public Character build() {
        return mCharacter;
    }
}
