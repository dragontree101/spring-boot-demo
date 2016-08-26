docker build -t dragon/nginx .

if [ $? -eq 0 ];then
    echo "docker build success"
else
    echo "docker build fail"
    exit -1
fi

docker run -d -p 80:80 --name dragon-nginx dragon/nginx
