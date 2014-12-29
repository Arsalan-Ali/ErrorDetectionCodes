package com.ali.arsalan.errordetectioncodes;






public class DataCentre {
	static private DataCentre sData;
	private String msgBits;
	private String msgBitsSent;
	private String pattern;
	private String fcs;
	private int noOfMsgBitsSent;
	private String codeWord;
	
	private String dataWord;
	private DataCentre(){
		msgBits="";
		msgBitsSent="";
		pattern="";
		fcs="";
		noOfMsgBitsSent=0;
		codeWord="";
	}
	
	public String getCodeWord() {
		return codeWord;
	}


	public void setCodeWord(String codeWord) {
		this.codeWord = codeWord;
	}


	public String getDataWord() {
		return dataWord;
	}


	public void setDataWord(String dataWord) {
		this.dataWord = dataWord;
	}
	public int getNoOfMsgBitsSent() {
		return noOfMsgBitsSent;
	}


	public void setNoOfMsgBitsSent(int noOfMsgBitsSent) {
		this.noOfMsgBitsSent = noOfMsgBitsSent;
	}


	public String getMsgBits() {
		return msgBits;
	}


	public void setMsgBits(String msgBits) {
		this.msgBits = msgBits;
	}


	public String getMsgBitsSent() {
		return msgBitsSent;
	}


	public void setMsgBitsSent(String msgBitsSent) {
		this.msgBitsSent = msgBitsSent;
	}


	public String getPattern() {
		return pattern;
	}


	public void setPattern(String pattern) {
		this.pattern = pattern;
	}


	public String getFcs() {
		return fcs;
	}


	public void setFcs(String fcs) {
		this.fcs = fcs;
	}


	static public DataCentre getInstance(){
		if(null==sData){
			sData=new DataCentre();
		}
		return sData;
	}
	public String calculateFCS(){
		if(msgBits.length()==0)
			throw new RuntimeException();
		if(pattern.length()>0){
			noOfMsgBitsSent=msgBits.length()+pattern.length()-1;			
			String extra="";
			for(int i=0;i<noOfMsgBitsSent-msgBits.length();i++){
				extra+="0";
			}
			String msgBitsWithExtra=msgBits+extra;
			String rem=msgBitsWithExtra.substring(0, pattern.length());
			for(int i=pattern.length();i<=msgBitsWithExtra.length();i++){
				if(rem.charAt(0)=='1'){
					String temp="";	
					for(int j=0;j<pattern.length();j++){							
						temp=temp+((Character.getNumericValue(pattern.charAt(j)))^(Character.getNumericValue(rem.charAt(j))));
									
					}
					rem=temp.substring(1,temp.length());
					if(i!=msgBitsWithExtra.length()){
						rem=rem+msgBitsWithExtra.charAt(i);
					}
				}
				else{
					rem=rem.substring(1,rem.length());
					if(i!=msgBitsWithExtra.length()){
						rem=rem+msgBitsWithExtra.charAt(i);
					}
				}
			}
			
			fcs=rem;
			msgBitsSent=msgBits+fcs;
		}
		else{
			fcs="None";
			msgBitsSent=msgBits;
		}
		
		
		return fcs;
	}
	private long binaryToInt(String binary){
		long value=0;
		for(int i=binary.length()-1,j=0;i>=0;i--,j++){
			value+=(int)Math.pow(2, j)*Character.getNumericValue(binary.charAt(i));			
		}
		
		return value;
		
	}
	public int calcErrPos(){
		String posBits="";
		int temp;
		char[] tempCodeWord=codeWord.toCharArray();
		char chTemp;
		
		for(int i=0;i<tempCodeWord.length/2;i++){
			chTemp=tempCodeWord[i];
			tempCodeWord[i]=tempCodeWord[tempCodeWord.length-i-1];
			tempCodeWord[tempCodeWord.length-i-1]=chTemp;
		}
		
		for(int i=0;(int)Math.pow(2, i)<=tempCodeWord.length;i++){
			temp=0;
			for(int j=(int)Math.pow(2, i);j<=tempCodeWord.length;j+=(int)Math.pow(2, i+1)){				
				for(int k=j;k<j+(int)Math.pow(2, i) && k<=tempCodeWord.length;k++){
					temp^=Character.getNumericValue(tempCodeWord[k-1]);
					
				}
			}
			
			
			posBits=temp+""+posBits;
		}
		
		int pos=((int)binaryToInt(posBits));
		return pos;
		
		
	}

	public String calcCodeWord() {
		int k=0,i=1,j=0;
		for(;;i++){
			if(i==(int)Math.pow(2, j)){
				j++;
				continue;
			}
			k++;
			if(k>=dataWord.length()){
				break;
			}
		}
		
		char[] codeWord=new char[i];
		for(i=0;i<codeWord.length;i++){
			codeWord[i]=0;
		}
		
		k=dataWord.length()-1;i=0;j=0;
		for(;i<codeWord.length;i++){
			if(i==(int)Math.pow(2, j)-1){
				j++;
				continue;
			}
			codeWord[i]=dataWord.charAt(k);
			k--;
			
		}
		
		
		
		
		
		int temp;
		for(i=0 ;(int)Math.pow(2, i)<=codeWord.length;i++){
			temp=0;
			for(j=(int)Math.pow(2, i);j<=codeWord.length;j+=(int)Math.pow(2, i+1)){				
				for(k=j;k<j+(int)Math.pow(2, i) && k<=codeWord.length;k++){
					if(k==(int)Math.pow(2, i))
						continue;
					temp^=Character.getNumericValue(codeWord[k-1]);
					
				}
			}
			
			
			codeWord[(int)Math.pow(2, i)-1]=(char)(temp+48);
			
		}
		String word="";
		for(i=0;i<codeWord.length;i++){
			word=codeWord[i]+""+word;
		}
		
		return word;
		 
	}
}
