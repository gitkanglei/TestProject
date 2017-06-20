package com.pptv.mylistviewadapter.model;

/**
 * @author LeiKang
 * @time 2017/2/24
 */
public class Amodel implements visible
{
    private String name;

    private int age;

    @Override
    public int type(TypeFactory typeFactory)
    {
        return typeFactory.AmodelType();
    }
}
