package com.pptv.mylistviewadapter.model;

/**
 * @author LeiKang
 * @time 2017/2/24
 */
public class TypeFactoryImp implements TypeFactory
{
    public final static int TYPE_AMODEL = 1;

    public final static int TYPE_BMODEL = 2;

    @Override
    public int AmodelType()
    {
        return TYPE_AMODEL;
    }

    @Override
    public int BmodelType()
    {
        return TYPE_BMODEL;
    }
}
