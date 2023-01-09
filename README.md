# jsp_Web
사진빼고 저작권 x 다 가져가도됨

# build

``` bash
docker build -t webapp
```

# volume
``` bash
docker volume create --name tomcat -o size=1GiB -o "cpu=0.5"
```

# run
``` bash
docker run -d -p 80:8080 -v tomcat:/usr/local/tomcat/conf -v tomcat:/usr/local/tomcat/logs --name webapp tomcat:latest
```
