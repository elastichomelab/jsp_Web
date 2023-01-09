# jsp_Web
사진빼고 저작권 없음 다 가져가도됨

# http

## build

``` bash
docker build -t webapp
```

## volume
``` bash
docker volume create --name tomcat-log
```

## run
``` bash
docker run -d -p 80:8080 \
    -v tomcat-log:/usr/local/tomcat/conf \
    -v tomcat-log:/usr/local/tomcat/logs \
    --name webapp\
    --memory="700m" --memory-reservation=256m \
    --cpus=".5" \
    webapp
```


# 주의사항
sql은 따로 dockerswarm으로 만들진 않을꺼라 다른 로그인이나 회원등록은 안될것임

sql 자체는 정상적으로 돌아가기에 ./sql에 있는것을 보고 db를 작성바람