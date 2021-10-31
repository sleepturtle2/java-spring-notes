## Download 
sudo apt install tomcat9 tomcat9-admin 

## Enable/disable/status/restart
sudo systemctl enable/disable/status/restart tomcat9

## Tomcat server running 
Runs on port 8080 by default. Hence can be accessed on the localhost port 8080
http://127.0.0.1:8080

## Configure tomcat 
By default, at /etc/tomcat9
Add/edit users at : /etc/tomcat9/tomcat-users/xml

## Default Installation Directory 
/usr/share/tomcat9 (add this path while eclipse server installation)

## Notes 
In Ubuntu, the default installation directory is /usr/share/tomcat9. Here sudo rename ./etc to ./conf. Copy 'catalina.properties' and 'catalina.poliy' from /etc/tomcat9/conf. Then 
```
 
# fix directories
cd /usr/share/tomcat9
sudo ln -s /var/lib/tomcat9/conf conf
sudo ln -s /var/log/tomcat9 log
sudo ln -s /etc/tomcat9/policy.d/03catalina.policy conf/catalina.policy
sudo chmod -R a+rwx /usr/share/tomcat9/conf

cp -r /usr/share/tomcat9/conf/ ~/eclipse-workspace/Servers/Tomcat\ v9.0\ Server\ at\ localhost-config/
```

Run the project with Run As -> Server -> Tomcat Server. 
Here make sure the Tomcat Admin Port is not blank. (By default it is '-'). 