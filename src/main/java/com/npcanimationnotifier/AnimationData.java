package com.npcanimationnotifier;


public class AnimationData
{
    private final int animationId;
    private final String animationName;

    private final int npcId;

    public AnimationData(int npcId, int animationId, String animationName)
    {
        this.npcId = npcId;
        this.animationId = animationId;
        this.animationName = animationName;
    }

    public int getAnimationId()
    {
        return animationId;
    }

    public String getAnimationName()
    {
        return animationName;
    }

    public int getNpcId()
    {
        return npcId;
    }

    @Override
    public String toString()
    {
        return "AnimationData{" +
                "npcId=" + npcId +
                ", animationId=" + animationId +
                ", animationName='" + animationName + '\'' +
                '}';
    }
}
