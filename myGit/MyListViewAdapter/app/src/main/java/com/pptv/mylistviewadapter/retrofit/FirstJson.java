package com.pptv.mylistviewadapter.retrofit;

import java.util.List;

/**
 * @anthor LeiKang
 */
public class FirstJson
{

    /**
     * data : [{"id":1,"icon":
     * "http://grocery.pptv.com/lpic/93f/993/f1b/dd27bc08d6655280090c5f254ef7dc92.png"
     * ,"structure":1,"name":"推荐"},{"id":2,"icon":
     * "http://grocery.pptv.com/lpic/944/84f/643/9bafe0779c4b6b98f4bb43fa89dcebbe.png"
     * ,"structure":2,"name":"直播单"}] code : 200 msg : 成功
     */

    private int code;

    private String msg;

    private List<DataBean> data;

    public void setCode(int code)
    {
        this.code = code;
    }

    public void setMsg(String msg)
    {
        this.msg = msg;
    }

    public void setData(List<DataBean> data)
    {
        this.data = data;
    }

    public int getCode()
    {
        return code;
    }

    public String getMsg()
    {
        return msg;
    }

    public List<DataBean> getData()
    {
        return data;
    }

    public static class DataBean
    {
        /**
         * id : 1 icon : http://grocery.pptv.com/lpic/93f/993/f1b/
         * dd27bc08d6655280090c5f254ef7dc92.png structure : 1 name : 推荐
         */

        private int id;

        private String icon;

        private int structure;

        private String name;

        public void setId(int id)
        {
            this.id = id;
        }

        public void setIcon(String icon)
        {
            this.icon = icon;
        }

        public void setStructure(int structure)
        {
            this.structure = structure;
        }

        public void setName(String name)
        {
            this.name = name;
        }

        public int getId()
        {
            return id;
        }

        public String getIcon()
        {
            return icon;
        }

        public int getStructure()
        {
            return structure;
        }

        public String getName()
        {
            return name;
        }
    }
}
