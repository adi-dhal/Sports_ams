package Sports_ams.data_model;

public class Product {
	
	int id;
	String attribute_val_1;
	String attribute_val_2;
	String attribute_val_3;
	String attribute_val_4;
	String attribute_val_5;
	String attribute_val_6;
	String attribute_val_7;
	String attribute_val_8;
	String attribute_val_9;
	String attribute_name_1;
	String attribute_name_2;
	String attribute_name_3;
	String attribute_name_4;
	String attribute_name_5;
	String attribute_name_6;
	String attribute_name_7;
	String attribute_name_8;
	String attribute_name_9;

	public Product(String attribute_val_1, String attribute_val_2, String attribute_val_3, String attribute_val_4,
			String attribute_val_5, String attribute_val_6, String attribute_val_7, String attribute_val_8,
			String attribute_val_9, String attribute_name_1, String attribute_name_2, String attribute_name_3,
			String attribute_name_4, String attribute_name_5, String attribute_name_6, String attribute_name_7,
			String attribute_name_8, String attribute_name_9) {
		super();
		this.attribute_val_1 = attribute_val_1;
		this.attribute_val_2 = attribute_val_2;
		this.attribute_val_3 = attribute_val_3;
		this.attribute_val_4 = attribute_val_4;
		this.attribute_val_5 = attribute_val_5;
		this.attribute_val_6 = attribute_val_6;
		this.attribute_val_7 = attribute_val_7;
		this.attribute_val_8 = attribute_val_8;
		this.attribute_val_9 = attribute_val_9;
		this.attribute_name_1 = attribute_name_1;
		this.attribute_name_2 = attribute_name_2;
		this.attribute_name_3 = attribute_name_3;
		this.attribute_name_4 = attribute_name_4;
		this.attribute_name_5 = attribute_name_5;
		this.attribute_name_6 = attribute_name_6;
		this.attribute_name_7 = attribute_name_7;
		this.attribute_name_8 = attribute_name_8;
		this.attribute_name_9 = attribute_name_9;
	}
	public Product(int id, String attribute_val_1, String attribute_val_2, String attribute_val_3,
			String attribute_val_4, String attribute_val_5, String attribute_val_6, String attribute_val_7,
			String attribute_val_8, String attribute_val_9, String attribute_name_1, String attribute_name_2,
			String attribute_name_3, String attribute_name_4, String attribute_name_5, String attribute_name_6,
			String attribute_name_7, String attribute_name_8, String attribute_name_9) {
		super();
		this.id = id;
		this.attribute_val_1 = attribute_val_1;
		this.attribute_val_2 = attribute_val_2;
		this.attribute_val_3 = attribute_val_3;
		this.attribute_val_4 = attribute_val_4;
		this.attribute_val_5 = attribute_val_5;
		this.attribute_val_6 = attribute_val_6;
		this.attribute_val_7 = attribute_val_7;
		this.attribute_val_8 = attribute_val_8;
		this.attribute_val_9 = attribute_val_9;
		this.attribute_name_1 = attribute_name_1;
		this.attribute_name_2 = attribute_name_2;
		this.attribute_name_3 = attribute_name_3;
		this.attribute_name_4 = attribute_name_4;
		this.attribute_name_5 = attribute_name_5;
		this.attribute_name_6 = attribute_name_6;
		this.attribute_name_7 = attribute_name_7;
		this.attribute_name_8 = attribute_name_8;
		this.attribute_name_9 = attribute_name_9;
	}
	
	public Product() {
		// TODO Auto-generated constructor stub
	}
	public void setAttribute_val(String val,int index)
	{
		switch(index)
		{
		case 1: setAttribute_val_1(val);return;
		case 2: setAttribute_val_2(val);return;
		case 3: setAttribute_val_3(val);return;
		case 4: setAttribute_val_4(val);return;
		case 5: setAttribute_val_5(val);return;
		case 6: setAttribute_val_6(val);return;
		case 7: setAttribute_val_7(val);return;
		case 8: setAttribute_val_8(val);return;
		case 9: setAttribute_val_9(val);return;		
		}
	}
	public void setAttribute_name(String name,int index)
	{
		switch(index)
		{
		case 1: setAttribute_name_1(name);return;
		case 2: setAttribute_name_2(name);return;
		case 3: setAttribute_name_3(name);return;
		case 4: setAttribute_name_4(name);return;
		case 5: setAttribute_name_5(name);return;
		case 6: setAttribute_name_6(name);return;
		case 7: setAttribute_name_7(name);return;
		case 8: setAttribute_name_8(name);return;
		case 9: setAttribute_name_9(name);return;		
		}
	}
	public String getAttribute_val(int index)
	{
		switch(index)
		{
		case 1: return(getAttribute_val_1());
		case 2: return(getAttribute_val_2());
		case 3: return(getAttribute_val_3());
		case 4: return(getAttribute_val_4());
		case 5: return(getAttribute_val_5());
		case 6: return(getAttribute_val_6());
		case 7: return(getAttribute_val_7());
		case 8: return(getAttribute_val_8());
		case 9: return(getAttribute_val_9());
		}
		return "";
	}
	public String getAttribute_name(int index)
	{
		switch(index)
		{
		case 1: return (getAttribute_name_1());
		case 2: return (getAttribute_name_2());
		case 3: return (getAttribute_name_3());
		case 4: return (getAttribute_name_4());
		case 5: return (getAttribute_name_5());
		case 6: return (getAttribute_name_6());
		case 7: return (getAttribute_name_7());
		case 8: return (getAttribute_name_8());
		case 9: return (getAttribute_name_9());
		}
		return "";
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAttribute_val_1() {
		return attribute_val_1;
	}
	public void setAttribute_val_1(String attribute_val_1) {
		this.attribute_val_1 = attribute_val_1;
	}
	public String getAttribute_val_2() {
		return attribute_val_2;
	}
	public void setAttribute_val_2(String attribute_val_2) {
		this.attribute_val_2 = attribute_val_2;
	}
	public String getAttribute_val_3() {
		return attribute_val_3;
	}
	public void setAttribute_val_3(String attribute_val_3) {
		this.attribute_val_3 = attribute_val_3;
	}
	public String getAttribute_val_4() {
		return attribute_val_4;
	}
	public void setAttribute_val_4(String attribute_val_4) {
		this.attribute_val_4 = attribute_val_4;
	}
	public String getAttribute_val_5() {
		return attribute_val_5;
	}
	public void setAttribute_val_5(String attribute_val_5) {
		this.attribute_val_5 = attribute_val_5;
	}
	public String getAttribute_val_6() {
		return attribute_val_6;
	}
	public void setAttribute_val_6(String attribute_val_6) {
		this.attribute_val_6 = attribute_val_6;
	}
	public String getAttribute_val_7() {
		return attribute_val_7;
	}
	public void setAttribute_val_7(String attribute_val_7) {
		this.attribute_val_7 = attribute_val_7;
	}
	public String getAttribute_val_8() {
		return attribute_val_8;
	}
	public void setAttribute_val_8(String attribute_val_8) {
		this.attribute_val_8 = attribute_val_8;
	}
	public String getAttribute_val_9() {
		return attribute_val_9;
	}
	public void setAttribute_val_9(String attribute_val_9) {
		this.attribute_val_9 = attribute_val_9;
	}
	public String getAttribute_name_1() {
		return attribute_name_1;
	}
	public void setAttribute_name_1(String attribute_name_1) {
		this.attribute_name_1 = attribute_name_1;
	}
	public String getAttribute_name_2() {
		return attribute_name_2;
	}
	public void setAttribute_name_2(String attribute_name_2) {
		this.attribute_name_2 = attribute_name_2;
	}
	public String getAttribute_name_3() {
		return attribute_name_3;
	}
	public void setAttribute_name_3(String attribute_name_3) {
		this.attribute_name_3 = attribute_name_3;
	}
	public String getAttribute_name_4() {
		return attribute_name_4;
	}
	public void setAttribute_name_4(String attribute_name_4) {
		this.attribute_name_4 = attribute_name_4;
	}
	public String getAttribute_name_5() {
		return attribute_name_5;
	}
	public void setAttribute_name_5(String attribute_name_5) {
		this.attribute_name_5 = attribute_name_5;
	}
	public String getAttribute_name_6() {
		return attribute_name_6;
	}
	public void setAttribute_name_6(String attribute_name_6) {
		this.attribute_name_6 = attribute_name_6;
	}
	public String getAttribute_name_7() {
		return attribute_name_7;
	}
	public void setAttribute_name_7(String attribute_name_7) {
		this.attribute_name_7 = attribute_name_7;
	}
	public String getAttribute_name_8() {
		return attribute_name_8;
	}
	public void setAttribute_name_8(String attribute_name_8) {
		this.attribute_name_8 = attribute_name_8;
	}
	public String getAttribute_name_9() {
		return attribute_name_9;
	}
	public void setAttribute_name_9(String attribute_name_9) {
		this.attribute_name_9 = attribute_name_9;
	}
	
	

}
