# Firewall_Assignment

1.) Tested my solution against following test cases:

fw.accept_packet("inbound",  "tcp",  80,  "192.168.1.2");
fw.accept_packet("inbound",  "udp",  53,  "192.168.2.1");
fw.accept_packet("outbound",  "tcp",  10234,  "192.168.10.11");        
fw.accept_packet("inbound",  "tcp",  81,  "192.168.1.2");        
fw.accept_packet("inbound",  "udp",  24,  "52.12.48.92");                
fw.accept_packet("outbound",  "tcp",  65536,  "192.168.10.11");
fw.accept_packet("inbound","tcp",80,"192.168.1.266");

2.) Due to time limit, I could only validate the port and ip address provided as input. I did not get time to validate each rule in CSV file, so I assumed all entries in CSV file to be valid. Also, if I had more time, I would have used design pattern like Proxy to make my code more efficient and reusable. Also, I would have checked my code for large datasets.

3.) I am interested in following teams:
    Platform, Data
