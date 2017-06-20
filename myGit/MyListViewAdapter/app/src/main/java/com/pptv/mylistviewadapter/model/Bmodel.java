package com.pptv.mylistviewadapter.model;

/**
 * @author LeiKang
 * @time 2017/2/24
 */
public class Bmodel implements visible
{
    private String book;

    private String no;

    @Override
    public int type(TypeFactory typeFactory)
    {
        return typeFactory.BmodelType();
    }
}
