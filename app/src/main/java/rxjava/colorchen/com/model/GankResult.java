package rxjava.colorchen.com.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * ank网站的返回数据集合
 * Created by color on 23/10/2016 12:23.
 */

public class GankResult {
    public boolean error;
    public @SerializedName("results")
    List<GankBeauty> beauties;
}
