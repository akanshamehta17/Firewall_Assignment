import java.util.*;
import java.io.*;

public class Firewall{
    
    private String file;
    
    public Firewall(String file_path){
        file = file_path;        
    }
    
    /* Read csv and get Data*/
    public ArrayList<String> getCsvData(){
        File csv = new File(file);
        ArrayList<String> data = new ArrayList<String>();

        try{             
             Scanner inputStream = new Scanner(csv);
             while(inputStream.hasNext()){
                 data.add(inputStream.next());                                  
             }

             inputStream.close();
             
         }catch(FileNotFoundException e){
             e.printStackTrace();
         }

         return data;
    }
    
    /* To check whether to accept the packet or not */
    public boolean accept_packet(String direction, String protocol, int port, String ip_address){
              ArrayList<String> rules_list = getCsvData(); //creating list of rules in csv            
              for(String rule: rules_list){
                  String[] r = rule.split(",");                  
                  if(direction.equals(r[0]) && protocol.equals(r[1]) && 
                    ((r[2].indexOf('-')!=-1) ? isInPortRange(port, r[2]) : port==Integer.parseInt(r[2])) &&
                    ((r[3].indexOf('-')!=-1) ? isInIPRange(ip_address, r[3]) : ip_address.equals(r[3]))){
                        return true;
                    }
              }                                     
              return false;
    }
    

    /* To check whether the input port is in the given range of ports*/
    public boolean isInPortRange(int port, String portRange){
            String[] ports_list = portRange.split("-");
            if(Integer.parseInt(ports_list[0]) <= port && port <= Integer.parseInt(ports_list[1])){
                return true;
            }
                  
            return false;                
    }
    
    /* To check whether the input ip is in the given range of ips*/
    public boolean isInIPRange(String ip_add, String ipRange){        
            String[] ip = ip_add.split("\\."); 
            String[] ip_list = ipRange.split("-");
            String[] ip1 = ip_list[0].split("\\.");
            String[] ip2 = ip_list[1].split("\\.");
            if((Integer.parseInt(ip1[0]) <= Integer.parseInt(ip[0]) && Integer.parseInt(ip[0]) <= Integer.parseInt(ip2[0])) &&
               (Integer.parseInt(ip1[1]) <= Integer.parseInt(ip[1]) && Integer.parseInt(ip[1]) <= Integer.parseInt(ip2[1])) &&
               (Integer.parseInt(ip1[2]) <= Integer.parseInt(ip[2]) && Integer.parseInt(ip[2]) <= Integer.parseInt(ip2[2])) &&
               (Integer.parseInt(ip1[3]) <= Integer.parseInt(ip[3]) && Integer.parseInt(ip[3]) <= Integer.parseInt(ip2[3]))){
                   return true;
               }              
          
            return false;
    }

    public static void main(String[] args) {
        String file = "/path/to/fw.csv"; //Enter your path to csv file        
        Firewall fw = new Firewall(file);        
        System.out.println(fw.accept_packet("inbound",  "tcp",  80,  "192.168.1.2"));
        System.out.println(fw.accept_packet("inbound",  "udp",  53,  "192.168.2.1"));
        System.out.println(fw.accept_packet("outbound",  "tcp",  10234,  "192.168.10.11"));
        System.out.println(fw.accept_packet("inbound",  "tcp",  81,  "192.168.1.2"));        
        System.out.println(fw.accept_packet("inbound",  "udp",  24,  "52.12.48.92"));                
    }
}