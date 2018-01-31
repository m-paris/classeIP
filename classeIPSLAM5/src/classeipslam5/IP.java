package classeipslam5;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author m-paris
 */
public class IP {
    
    public int octet1, octet2, octet3, octet4;

    public static IP getInstance(int o1, int o2, int o3, int o4) throws Exception{
        
        if (o1>0 && o1<=255){
            if (o2>0 && o2<=255){
                if (o3>0 && o3<=255){
                    if (o4>0 && o4<=255){
                        
                        IP ip = new IP(o1,o2,o3,o4);
                        return ip;
                    }
                }
            }
        }
        throw new Exception("Ip incorrecte");
    }
    
    
    public IP(int octet1, int octet2, int octet3, int octet4) {
        this.octet1 = octet1;
        this.octet2 = octet2;
        this.octet3 = octet3;
        this.octet4 = octet4;
    }

    public int getOctet1() {
        return octet1;
    }

    public void setOctet1(int octet1) {
        this.octet1 = octet1;
    }

    public int getOctet2() {
        return octet2;
    }

    public void setOctet2(int octet2) {
        this.octet2 = octet2;
    }

    public int getOctet3() {
        return octet3;
    }

    public void setOctet3(int octet3) {
        this.octet3 = octet3;
    }

    public int getOctet4() {
        return octet4;
    }

    public void setOctet4(int octet4) {
        this.octet4 = octet4;
    }
    
    public char getClasse() {
    char classe =' ';
        if (octet1 >= 1 && octet1 < 128) {
            classe = 'A';
        }
        if (octet1 >= 128 && octet1 < 192) {
            classe ='B';
        }
        if (octet1 >= 192 && octet1 <= 223) {
            classe= 'C';
        }
        return classe;

    }
    
    public String adresseReseau() {
        String adresseReseau=" ";
        if (this.getClasse() == 'A') {
            adresseReseau= "ladresse reseau est : " + this.getOctet1() + ".0.0.0";
        }
        if (this.getClasse() == 'B') {
             adresseReseau=  "Ladresse reseau est : " + this.getOctet1() + "." + this.getOctet2() + ".0.0";
        }
        if (this.getClasse() == 'C') {
             adresseReseau=  "Ladresse reseau est : " + this.getOctet1() + "." + this.getOctet2() + "." + this.getOctet3() + ".0";
        } 
        return adresseReseau;
    }

    public boolean estMemeReseau(IP ip){
        return ip.adresseReseau().equals(this.adresseReseau());
    }
    
    @Override
    public String toString() {
        
        return "IP{" +this.octet1 + "." + this.octet2 + "." + this.octet3 + "." + this.octet4 + "}";
    }
    
    public String toStringBinaire(){
        return Integer.toBinaryString(octet1)+"."+Integer.toBinaryString(octet2)+"."+Integer.toBinaryString(octet3)+"."+Integer.toBinaryString(octet4);
    }
    
    public IP getIpSuivant(){
        int a =this.octet1;
        int b = this.octet2;
        int c =this.octet3;
        int d =this.octet4;
        
        if (d<255){
            return new IP(a,b,c,d+1);
        }
        if (d==255 && c<255){
            return new IP(a,b,c+1,0);
        }
        if(d==255 && c == 255 && b<255){
            return new IP(a,b+1,0,0);
        }
        if (d==255 && c==255 && b==255 && a<255){
            return new IP(a+1,0,0,0);
        }
        else {
            return this;
        }
    }
    
    public static void main(String[]args) throws Exception{
          IP ip1 = new IP(192,255,140,2);
          IP ip2 = new IP(192,255,100,0);
          System.out.println(ip1.toString());
          System.out.println(ip1.getClasse());
          System.out.println(ip1.adresseReseau());
          System.out.println(ip1.estMemeReseau(ip2));
          
          System.out.println(getInstance(192,25,17,100));
          System.out.println(ip1.toStringBinaire());
          System.out.println(ip1.getIpSuivant());
    }
}
