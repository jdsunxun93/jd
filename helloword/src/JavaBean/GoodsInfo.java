package JavaBean;

public class GoodsInfo {
	String Goods_ID;
    String Goods_name;
    float price;
    int salenumber;
    String describe;
    
    public String getGoods_ID() {
		return Goods_ID;
	}
	public void setGoods_ID(String goods_ID) {
		Goods_ID = goods_ID;
	}
	public String getGoods_name() {
		return Goods_name;
	}
	public void setGoods_name(String goods_name) {
		Goods_name = goods_name;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public int getSalenumber() {
		return salenumber;
	}
	public void setSalenumber(int salenumber) {
		this.salenumber = salenumber;
	}
	public String getDescribe() {
		return describe;
	}
	public void setDescribe(String describe) {
		this.describe = describe;
	}

    
}
